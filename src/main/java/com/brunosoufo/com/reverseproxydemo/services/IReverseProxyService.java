package com.brunosoufo.com.reverseproxydemo.services;

import java.io.IOException;

import com.brunosoufo.com.reverseproxydemo.enums.PageType;

import org.apache.http.client.ClientProtocolException;

/**
 * IReverseProxy
 */
public interface IReverseProxyService {

    public String getBaseUrl();
    public String getPage(PageType pageType, String param, String currentUrl) throws ClientProtocolException, IOException; 
}