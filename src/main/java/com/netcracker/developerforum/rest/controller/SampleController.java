package com.netcracker.developerforum.rest.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yogs0616 on 11/28/2017.
 */
@RestController
@EnableAutoConfiguration
public class SampleController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String helloWorld() {
        return "Hello World!";
    }
}
