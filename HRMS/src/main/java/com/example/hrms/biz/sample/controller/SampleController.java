package com.example.hrms.biz.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/sample")
public class SampleController {

    @RequestMapping("")
    public String openSampleView(Model model) {
        return "sample";
    }
}
