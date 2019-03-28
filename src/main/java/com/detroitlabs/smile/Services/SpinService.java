package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.SpinDataModel.AllSpinData;

import com.detroitlabs.smile.Model.Token.Token;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.io.IOException;


@Component
public class SpinService {



    public Token needThisTokenAlready(){

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept",org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
//        headers.add("User-Agent", "Spring");
//        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        String json = "{\"device\":{\"mobileType\":\"ios\",\"uid\":\"e0314af1-6470-43a8-a554-42fd0ac8b6d5\"},\"grantType\":\"device\"}\r\n";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "Spring");

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        Token answer = restTemplate.postForObject("https://web.spin.pm/api/v1/auth_tokens",entity, Token.class);


//        ResponseEntity.ok().header("Accept","application/json").header("User-Agent","Spring").body(json);
//        ResponseEntity<Token> tokenResponseEntity = restTemplate.postForEntity("https://web.spin.pm/api/v1/auth_tokens", HttpMethod.POST,Token.class);

        return answer;

    }



    public AllSpinData fetchSpinData(double lat, double lng) throws IOException {

        String jwtToken = needThisTokenAlready().getJwt();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "BEARER " + jwtToken);
        headers.add("Accept",org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        headers.add("User-Agent", "Spring" );
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<AllSpinData> response = restTemplate.exchange("https://web.spin.pm/api/v3/vehicles?lng="+ lng+ "&lat=" + lat,
                HttpMethod.GET, httpEntity, AllSpinData.class);

        return response.getBody();
    }


//    public String fetchJWTSpin(){
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        try {
//            HttpPost request = new HttpPost("https://web.spin.pm/api/v1/auth_tokens");
//            StringEntity params = new StringEntity("{\"device\":{\"mobileType\":\"ios\",\"uid\":\"e0314af1-6470-43a8-a554-42fd0ac8b6d5\"},\"grantType\":\"device\"}");
//            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
//            request.setEntity(params);
//            HttpResponse response = httpClient.execute(request);
//            System.out.println(response.toString());
//            return response.toString();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }


//    public String fetchToken(){
//        Map<String, Object>jsonValues = new HashMap<String,Object>();
//        Object device = new Object();
//
//        jsonValues.put;
//        jsonValues.put("device","uid");
//        jsonValues.put("mobile-type","ios");
//        jsonValues.put("uid","e0314af1-6470-43a8-a554-42fd0ac8b6d5e0314af1-6470-43a8-a554-42fd0ac8b6d5");
//        jsonValues.put("grantType","device");
//
//
//        JSONObject json = new JSONObject();
//
//    }

}
