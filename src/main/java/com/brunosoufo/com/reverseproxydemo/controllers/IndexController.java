package com.brunosoufo.com.reverseproxydemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * IndexController
 */
@Controller
public class IndexController {

    @GetMapping(value="/")
    public String index() {
        return "index";
    }
    
    
}