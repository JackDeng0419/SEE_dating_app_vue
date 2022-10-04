package com.course.dating.controller;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;
import java.util.Set;

public class SMSInterface {
    CCPRestSmsSDK sdk = new CCPRestSmsSDK();
    public void create(){
        sdk.init("app.cloopen.com", "8883");
        sdk.setAccount("\n" +
                "8aaf0708837e29e6018398eeba9203bf", "8b1156e69d6345a7ad38b10f91fcc9a4");
        sdk.setAppId("\n" +
                "8aaf0708837e29e6018398eebb7103c6");
        sdk.setBodyType(BodyType.Type_JSON);
    }

    public void send(String mobile_number, String content){
        String templateId= "1";
        String[] datas = {content,"10"};
        HashMap<String, Object> result = sdk.sendTemplateSMS(mobile_number,templateId,datas);
        if("000000".equals(result.get("statusCode"))){
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            System.out.println("error_code=" + result.get("statusCode") +" error_msg= "+result.get("statusMsg"));
        }
    }
}
