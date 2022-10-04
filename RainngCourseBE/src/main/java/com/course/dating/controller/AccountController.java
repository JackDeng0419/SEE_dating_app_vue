package com.course.dating.controller;

import com.course.dating.dao.AccountDAO;
import com.course.dating.entity.AccountEntity;
import com.course.dating.entity.Session.SessionCodeEntity;
import com.course.dating.entity.Session.SessionUserEntity;
import com.course.dating.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import static com.course.dating.utils.encryption.AES_decrypt;
import static com.course.dating.utils.encryption.AES_encrypt;

@RequestMapping("/login")
@CrossOrigin
@RestController
public class AccountController {
    @Autowired
    private AccountDAO accountdao;

    @PostMapping(value="/check")
    public ResponseEntity<CommonResult> match(HttpServletRequest request,@RequestParam Map<String, Object> params){
        String username = (String)params.get("username");
        String password = (String)params.get("password");
        AccountEntity account = accountdao.getByUsername(username);
        if(account==null){// USER_NOT_EXIST
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(CommonResult.error("user not exist"));
        }
        if(account.getPassword().equals(password)){ //SUCCESS
            HttpSession session = request.getSession();

            SessionCodeEntity entity = (SessionCodeEntity)session.getAttribute("code");
            if(entity!=null){
                if(System.currentTimeMillis()-entity.getCreated_time()<=60000){
                    return ResponseEntity.status(HttpStatus.LOCKED).body(CommonResult.error("Code should be generated after 1 min."));
                }
            }

            SessionUserEntity newentity1 = new SessionUserEntity();
            newentity1.setUserId(account.getId());
            newentity1.setUsername(account.getUsername());
            newentity1.setMobileNumber(account.getMobileNumber());
            newentity1.setEmail(account.getEmail());
            session.setAttribute("user_info",newentity1);

            String code = "";
            StringBuilder sb = new StringBuilder(code);
            Random random = new Random();
            for(int i = 0;i<6;i++){
                sb.append(random.nextInt(10));
            }
            SessionCodeEntity newentity2 = new SessionCodeEntity();
            newentity2.setCode(sb.toString());
            newentity2.setCreated_time(System.currentTimeMillis());
            session.setAttribute("code", newentity2);
            System.out.println(sb);
            //SMSInterface a = new SMSInterface();
            //a.create();
            //a.send(account.getMobileNumber(),code);

            Map<String, Object> map = new HashMap<>(3);
            map.put("mobile_number", account.getMobileNumber());
            map.put("email", account.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(CommonResult.success("login successful", map));
        }
        else{ //PASSWORD_WRONG
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(CommonResult.error("password wrong"));
        }
        /*System.out.println(data);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(CommonResult.error("password wrong"));*/
    }

    @PostMapping(value="/login")
    public ResponseEntity<CommonResult> login(HttpServletRequest request, @RequestParam Map<String, Object> params){
        String code = (String)params.get("code");
        HttpSession session = request.getSession(true);
        SessionCodeEntity entitycode = (SessionCodeEntity)session.getAttribute("code");
        if(Objects.equals(entitycode.getCode(), code)){
            SessionUserEntity entityuser = (SessionUserEntity)session.getAttribute("user_info");
            entityuser.setLoggedIn(true);
            session.setAttribute("user_info", entityuser);
            Map<String, Object> map = new HashMap<>(3);
            map.put("userid", entityuser.getUserId());
            map.put("username", entityuser.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(CommonResult.success("login successful", map));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(CommonResult.error("code wrong"));
        }
    }

    @GetMapping("/status")
    public ResponseEntity<CommonResult> getLoginStatus(HttpServletRequest request) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        HttpSession session = request.getSession(true);
        SessionUserEntity entity = (SessionUserEntity)session.getAttribute("user_info");
        if (entity == null) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(CommonResult.success("log out"));
        }
        else{
            if(entity.getLoggedIn()){
                return ResponseEntity.status(HttpStatus.OK).body(CommonResult.success("log in"));
            }
            else{
                return ResponseEntity.status(HttpStatus.LOCKED).body(CommonResult.success("log out"));
            }
        }
        /*String plaintext = AES_decrypt((String)params.get("ciphertext"), (String)params.get("key"));
        Map<String, Object> map = new HashMap<>(3);
        map.put("userid", AES_encrypt(plaintext, (String)params.get("key"), (String)params.get("iv")));
        return ResponseEntity.status(HttpStatus.OK).body(CommonResult.success("login successful", map));*/
    }

    @PostMapping(value="/signup")
    public ResponseEntity<CommonResult> signup(@RequestParam Map<String, Object> params) {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String mobile_number = (String) params.get("mobile_number");
        String email = (String) params.get("email");
        accountdao.insert(username, password, mobile_number, email);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResult.success("login successful"));
    }

    /**
     * 公钥字符串还原为公钥
     *
     * @param publicKeyString 公钥字符串
     * @return 公钥
     * @throws Exception
     */
    public Key publicKeyStringToKey(String publicKeyString) throws Exception {
        byte[] publicBytes = Base64.getDecoder().decode(publicKeyString);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicBytes));
    }

    /**
     * 私钥字符串还原为私钥
     *
     * @param privateKeyString 私钥字符串
     * @return 私钥
     * @throws Exception
     */
    public PrivateKey privateKeyStringToKey(String privateKeyString) throws Exception {
        byte[] privateBytes = Base64.getDecoder().decode(privateKeyString);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}