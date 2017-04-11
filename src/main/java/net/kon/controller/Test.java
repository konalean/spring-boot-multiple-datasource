package net.kon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.kon.model.primary.ZTest;
import net.kon.model.primary.ZTestRepository;
import net.kon.model.secondary.ZUser;
import net.kon.model.secondary.ZUserRepository;

@RestController
public class Test {
	
	@Autowired
	private ZTestRepository dao;
	
	@Autowired
	private ZUserRepository dao2;
	
	@RequestMapping("/test")
	public String test() {
		ZTest ztest = dao.findByUuid("1");
		System.out.println(ztest);
		
		ZUser zuser = dao2.findOne(1);
		System.out.println(zuser);
		
		ZUser zuser2 = dao2.findByAdmId(2);
		System.out.println(zuser2);
		return "1234";
	}
}
