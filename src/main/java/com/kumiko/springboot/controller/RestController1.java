package com.kumiko.springboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
public class RestController1 {

	@RequestMapping("/{num}")
	public String index(@PathVariable int num,@PathVariable int a) {
		System.out.println(a);
		int res = 0;
		for (int i = 1; i <= num; i++)
			res += i;
		return "total: " + res;

	}
}
