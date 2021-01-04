package com.muniao.auth2demo;

import com.muniao.auth2demo.vo.OauthTokenResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Auth2DemoApplicationTests {

    @Test
    public void contextLoads() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("123"));
        System.out.println(passwordEncoder.encode("123"));
        System.out.println(passwordEncoder.encode("123"));

        System.out.println(passwordEncoder.matches("123", "$2a$10$LDwuVkti0rBTqT2.8LAdceOWYHjpO9vixkMFKxsJHx1j4Oi7THjZa"));
    }

    @Test
    public void getOauthTokenByPost() {
        String url = "http://localhost:8080/oauth/token";

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "javaboy");
        params.add("password", "123");
        params.add("grant_type", "password");
        params.add("client_id", "password");
        params.add("scope", "all");
        params.add("client_secret", "123");

        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<OauthTokenResultVO> response = client.exchange(url, method, requestEntity, OauthTokenResultVO.class);

        OauthTokenResultVO result = response.getBody();
        System.out.println(result);
    }

    @Test
    public void refreshOauthTokenByPost() {
        String url = "http://localhost:8080/oauth/token";

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("refresh_token", "15623635-cfb4-4135-8e2d-db99a608d54c");
        params.add("client_id", "password");
        params.add("client_secret", "123");

        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<OauthTokenResultVO> response = client.exchange(url, method, requestEntity, OauthTokenResultVO.class);

        OauthTokenResultVO result = response.getBody();
        System.out.println(result);
    }
}
