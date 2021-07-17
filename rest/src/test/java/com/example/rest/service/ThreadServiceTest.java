package com.example.rest.service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Slf4j
class ThreadServiceTest {
	@Test
	public void testCompletableFuture() throws ExecutionException, InterruptedException {
		for(int i = 0; i < 5; i++){
			CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> sum());
			Integer res = sumFuture.get();
			log.info("#Res {}", res);
		}
	}

	public Integer sum(){
		Integer sum = 0;
		Integer cnt = 0;
		while(true){
			if(cnt == 5){
				return sum;
			}
			log.info("sum {}", sum);
			sum++;
			cnt++;
		}
	}



	@Test
	public void testHttpClient() throws IOException, URISyntaxException {
		Gson gson = new Gson();
		String url = "https://www.google.com";
		List nameValueParis = Arrays.asList(
				new BasicNameValuePair("param1", "value1"),
				new BasicNameValuePair("param2", "value2")
		);


		/*
			nameValuePairs.add(new BasicNameValuePair("param1", "value1"));
    nameValuePairs.add(new BasicNameValuePair("param2", "value2"));
    HttpGet httpGet = new HttpGet("https://example.com");
    URI uri = new URIBuilder(httpGet.getURI())
      .addParameters(nameValuePairs)
      .build();
   ((HttpRequestBase) httpGet).setURI(uri);
		 */


		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		URI uri = new URIBuilder(httpGet.getURI()).addParameters(nameValueParis).build();
		httpGet.setURI(uri);

		HttpPost httpPost = new HttpPost();
		httpPost.setEntity(new StringEntity(gson.toJson("aa")));

		HttpResponse httpResponse = httpClient.execute(httpGet);

//		log.info("# Response {}", httpResponse);
		HttpEntity entity = httpResponse.getEntity();
//		log.info("# Entity {}", entity);
		String content = EntityUtils.toString(entity);
//		log.info("# Content {}", content);
	}
}