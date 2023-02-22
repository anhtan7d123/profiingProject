package com.example.profiling.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ResponseService {
    public Map<String,Object> responseBody(Integer status, String message, Object data){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status);
        body.put("message", message);
        if(data != null){
            body.put("data",data);
        }
        return body;
    }
}
