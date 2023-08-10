package com.spring.project3rd.controller;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
