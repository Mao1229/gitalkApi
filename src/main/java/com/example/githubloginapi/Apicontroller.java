package com.example.githubloginapi;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.mortbay.util.MultiMap;
import org.mortbay.util.UrlEncoded;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Apicontroller {

    @PostMapping("/getAccessToken")
    public String getAccessToken(@RequestBody Map<String, Object> mapList) throws IOException {
        String url = "https://github.com/login/oauth/access_token";

        Map params = new HashMap();
        params.put("code", mapList.get("code"));
        params.put("client_id", mapList.get("client_id"));
        params.put("client_secret", mapList.get("client_secret"));

        String result = HttpUtil.post(url, JSONObject.toJSONString(params));
        MultiMap multiMap = new MultiMap();
        UrlEncoded.decodeTo(result, multiMap, "UTF-8");

        Map resultMap = new HashMap();
        resultMap.put("access_token",multiMap.get("access_token"));
        resultMap.put("scope",multiMap.get("scope"));
        resultMap.put("token_type",multiMap.get("token_type"));
        return JSONObject.toJSONString(resultMap);
    }
}