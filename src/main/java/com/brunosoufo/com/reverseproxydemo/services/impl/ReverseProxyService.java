package com.brunosoufo.com.reverseproxydemo.services.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.brunosoufo.com.reverseproxydemo.enums.PageType;
import com.brunosoufo.com.reverseproxydemo.services.IReverseProxyService;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * ReverseProxyService
 */
@Service
public class ReverseProxyService implements IReverseProxyService {

    private String baseUrl="https://www.bbc.com/";

    @Override
    public String getBaseUrl() {
        return this.baseUrl;
    }

    @Override
    public String getPage(PageType pageType, String param, String currentUrl)
            throws ClientProtocolException, IOException {
                CloseableHttpClient httpClient = this.initHttpClient();
                String url;
                switch (pageType) {
                    case HOME:
                        url = this.baseUrl+"/news/";
                        break;
  
                    default:
                        url = this.baseUrl+"/news/";
                    break;
                }
                HttpGet httpgGet = new HttpGet(url);
                CloseableHttpResponse response = httpClient.execute(httpgGet);
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                httpClient.close();
                Document doc = Jsoup.parse(responseBody);
                Elements elements = doc.select("#news-top-stories-container");
                Elements headElements = doc.select("head");

                Element element = elements.first();
                element.prepend(headElements.first().html());
                return element.html();

                //return this.formatBody(responseBody,currentUrl);
    }

    private CloseableHttpClient initHttpClient() {
        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
        CloseableHttpClient client = HttpClients.custom().setRedirectStrategy(redirectStrategy).build();

                return client;
    }
    
}