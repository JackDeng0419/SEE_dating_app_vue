package com.course.dating.utils;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class encryption {
    //RSA
    private PrivateKey RSA_privateKey;
    private PublicKey RSA_publicKey;

    public PrivateKey getRSA_privateKey() {
        return RSA_privateKey;
    }

    public void setRSA_privateKey(PrivateKey RSA_privateKey) {
        this.RSA_privateKey = RSA_privateKey;
    }

    public PublicKey getRSA_publicKey() {
        return RSA_publicKey;
    }

    public void setRSA_publicKey(PublicKey RSA_publicKey) {
        this.RSA_publicKey = RSA_publicKey;
    }

    public encryption () throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.RSA_publicKey = keyPair.getPublic();
        this.RSA_privateKey = keyPair.getPrivate();
    }

    public String publicKeyToString() {
        return Base64.getEncoder().encodeToString(this.RSA_publicKey.getEncoded());
    }

    public String RSA_decrypt(String ciphertext, PrivateKey privateKey) throws Exception {
        byte[] cipherbytes = Base64.getDecoder().decode(ciphertext);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] input = cipher.doFinal(cipherbytes);
        return new String(input);
    }

    public static String AES_encrypt(String plaintext, String cipher_key, String cipher_iv) {
        try {
            byte[] key = Base64.getDecoder().decode(cipher_key);
            byte[] iv = Base64.getDecoder().decode(cipher_iv);
            Cipher cipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
            byte[] encryptData = cipher.doFinal(plaintext.getBytes());
            assert encryptData.length == plaintext.getBytes().length + 16;
            byte[] message = new byte[12 + plaintext.getBytes().length + 16];
            System.arraycopy(iv, 0, message, 0, 12);
            System.arraycopy(encryptData, 0, message, 12, encryptData.length);
            return Base64.getEncoder().encodeToString(message);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException ignored) {
        }
        return null;
    }

    public static String AES_decrypt(String cipher_text, String cipher_key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] key = Base64.getDecoder().decode(cipher_key);
        byte[] ciphertext = Base64.getDecoder().decode(cipher_text);
        GCMParameterSpec iv = new GCMParameterSpec(128, ciphertext, 0, 12);
        Cipher cipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
        SecretKey key2 = new SecretKeySpec(key, "AES");

        cipher.init(Cipher.DECRYPT_MODE, key2, iv);

        //这边和nodejs不同的一点是 不需要移除后面的16位
        byte[] decryptData = cipher.doFinal(ciphertext, 12, ciphertext.length - 12);
        return new String(decryptData);
    }

    private static SecretKeySpec getSecretKey(String encryptPass) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        // 初始化密钥生成器，AES要求密钥长度为128位、192位、256位
        kg.init(256, new SecureRandom(encryptPass.getBytes()));
        SecretKey secretKey = kg.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), "AES");// 转换为AES专用密钥
    }

    public Map<String, String> map_decrpyption(Map<String, Object> params, String key) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Map<String, String> new_map = new HashMap<>();
        for(Map.Entry<String, Object> entry : params.entrySet()){
            String cipheretext = (String) entry.getValue();
            String plaintext = AES_decrypt(cipheretext, key);
            new_map.put(entry.getKey(), plaintext);
        }
        return new_map;
    }

}
