package com.souvik.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.souvik.vo.UserProfile;

@Controller
public class MyController {
	
	@PostMapping("/bankname")
	public @ResponseBody String sayName(@RequestBody UserProfile user){
		return "Bank Name: ICICI";
	}
	
	@PostMapping("/bankaddress")
	public @ResponseBody String sayAddress(@RequestBody String req){
		return "Bank Address: ICICI,Bengaluru";
	}
	
	@GetMapping("/hello")
	public @ResponseBody String sayHello(){
		return "Hello";
	}
	
	public  String sayName(){
		return "Bank Name: ICICI";
	}
	
    @RequestMapping(value = "/secured", method = RequestMethod.GET)
    public String secured(){
        System.out.println("Inside secured()");
        return "Hello user !!! : " + new Date();
    }

}
