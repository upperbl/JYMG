package com.ph.remote.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpUtils {

    private static final CloseableHttpClient httpclient = HttpClients.createDefault() ;

    public HttpUtils(){}


    private final String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImU2NWJkN2E1LTM5ZWQtNDIxNS05OWVjLTJkYWE0ODQxZGZiNiIsImlhdCI6MTYwNDMwMTQzNSwic3ViIjoiZGV2ZWxvcGVyLzY3YzMyNDk4LThjYzItOTIyMS05OWU5LTE3NjkzYWRkNzRlYyIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjEwNC4yMzIuMjM0LjgiXSwidHlwZSI6ImNsaWVudCJ9XX0.PpWf1r-_UUo2nXowULasmI8Yt2fFR6Fmpd2s7oYalGQG4kkye0s91CH_tZkQNAbYZb9d7QI4XvVFltHfkyzBow";
	
    public String get(String url) {
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("Authorization", token);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            //3.执行get请求并返回结果
            response = httpclient.execute(httpget);

            HttpEntity entity =  response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {

            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
