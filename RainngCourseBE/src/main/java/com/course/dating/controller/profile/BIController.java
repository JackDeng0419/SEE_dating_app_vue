package com.course.dating.controller.profile;

import com.course.dating.dao.profile.BIDAO;
import com.course.dating.entity.Session.SessionUserEntity;
import com.course.dating.entity.profile.BIEntity;
import com.course.dating.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/profile/BI")
@CrossOrigin
@RestController
public class BIController {
    @Autowired
    private BIDAO BIdao;

    @GetMapping("/get")
    public ResponseEntity<CommonResult> getLoginStatus(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        SessionUserEntity entity = (SessionUserEntity)session.getAttribute("user_info");
        if(entity==null){
            return  ResponseEntity.status(HttpStatus.LOCKED).body(CommonResult.success("log out"));
        }
        Integer userid = entity.getUserId();

        BIEntity bi = BIdao.get(userid);
        if(bi==null){
            bi = new BIEntity();
        }
        Map<String, Object> map = new HashMap<>(12);
        map.put("first_name", bi.getFirstName());
        map.put("last_name",bi.getLastName());
        map.put("age",bi.getAge());
        map.put("gender", bi.getGender());
        map.put("birthday", bi.getBirthday());
        map.put("nationality",bi.getNationality());
        map.put("preferred_language",bi.getPreferredLanguage());
        map.put("profession", bi.getProfession());
        map.put("education",bi.getEducation());
        map.put("relationship_status", bi.getRelationshipStatus());
        return ResponseEntity.status(HttpStatus.OK).body(CommonResult.success("successful",map));
    }

    @PostMapping("/update")
    public ResponseEntity<CommonResult> update(HttpServletRequest request, @RequestParam Map<String, Object> params) {
        HttpSession session = request.getSession(true);
        SessionUserEntity entity = (SessionUserEntity)session.getAttribute("user_info");
        if(entity==null){
            return  ResponseEntity.status(HttpStatus.LOCKED).body(CommonResult.success("log out"));
        }
        Integer userid = entity.getUserId();
        params.put("id", userid);
        BIEntity new_info = new BIEntity();
        new_info.setAge(Integer.valueOf((String) params.get("age")));
        new_info.setBirthday(Date.valueOf((String)params.get("birthday")));
        new_info.setEducation((String) params.get("education"));
        new_info.setId(userid);
        new_info.setGender((String) params.get("gender"));
        new_info.setNationality((String) params.get("nationality"));
        new_info.setFirstName((String) params.get("first_name"));
        new_info.setLastName((String) params.get("last_name"));
        new_info.setPreferredLanguage((String) params.get("preferred_language"));
        new_info.setProfession((String) params.get("profession"));
        new_info.setRelationshipStatus((String) params.get("relationship_status"));
        Integer a = BIdao.update(new_info);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResult.success("successful",null));
    }
}
