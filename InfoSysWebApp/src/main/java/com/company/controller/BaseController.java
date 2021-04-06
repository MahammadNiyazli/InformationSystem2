package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/base")
public class BaseController {

    @RequestMapping(method = {RequestMethod.GET})
    public String index(){
        return "base";
    }

}
