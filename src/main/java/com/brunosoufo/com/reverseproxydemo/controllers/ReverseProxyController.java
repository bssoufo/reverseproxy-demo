package com.brunosoufo.com.reverseproxydemo.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.brunosoufo.com.reverseproxydemo.enums.PageType;
import com.brunosoufo.com.reverseproxydemo.services.IReverseProxyService;
import com.brunosoufo.com.reverseproxydemo.services.impl.ReverseProxyService;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReverseProxyController
 */
@RestController

public class ReverseProxyController {

    @Autowired
    IReverseProxyService reverseProxyService;

    @Autowired
    HttpServletRequest request;

    @GetMapping(value = "/news")
    public String bbcHome() throws ClientProtocolException, IOException {
       return  reverseProxyService.getPage(PageType.HOME, null, this.request.getRequestURL().toString());
   }


   @GetMapping(value = "/news/{newsId}")
   public String bbcNews(@PathVariable String newsId) throws ClientProtocolException, IOException {
      return  reverseProxyService.getPage(PageType.HOME, newsId, this.request.getRequestURL().toString());
  }
   
}