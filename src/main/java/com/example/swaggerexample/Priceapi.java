package com.example.swaggerexample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class Priceapi {
    public Object callApi() throws IOException, ParseException {
        StringBuilder result = new StringBuilder();

        String urlstr = "https://api.bithumb.com/public/candlestick/KLAY_KRW/24h";  // 기준시간 - 시가 - 종가 - 고가 - 저가 - 거래량
        URL url = new URL(urlstr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        Flux<String> flux = Flux.just(br.readLine());

        flux.subscribe(result::append);

        urlConnection.disconnect();

        String jText = result.toString();

        JSONParser parser = new JSONParser();

        JSONObject obj = (JSONObject) parser.parse(jText);

        Object status = obj.get("status");
        System.out.println(status.toString());

        JSONArray parseResponse = (JSONArray) obj.get("data");


        return parseResponse;
    }
}
