package com.qry.mbpcen.admin.usr.controller;


import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qry.mbpcen.admin.config.resourcename.ResourceName;
import com.qry.mbpcen.admin.usr.service.UserService;


@Controller
@RequestMapping("/views")
public class LoginController {
	private final Log logger = LogFactory.getLog(getClass());
	
	// ===================================================================
	// @Bean 을 통해 PasswordEncoder를 정하는 저 부분도 중요합니다.
	// 유저 pw의 암호화 방식을 정하는 부분 입니다. 일반적으로는 그냥 저 BCryptPasswordEncoder를 쓰시면 됩니다.
	// 프로젝트에 따라 암호화 방식이 정해진 경우도 많으므로 4번에서는 이 부분도 커스텀 해보겠습니다.
	// 참고로 이 부분은 프로젝트 내 어디에 있던 상관 없습니다.
	// SpringSecurityConfig쪽에 넣어도 되고, 아예 다른 클래스에 넣어놔도 됩니다.
	// 전 여기에 넣는게 제일 깔끔한 구성이라 생각해서 여기에 넣었을 뿐입니다.
	// ===================================================================
	@Resource(name = "PasswordEncoder")
	public PasswordEncoder passwordEncoder;
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new SHA512PasswordEncoder();
//	}
	
	/** User Service */
	@Resource(name = ResourceName.SERVICE_USER)
	protected UserService service;
	
	
    
	@RequestMapping(value = "/loginPage")
	public String loginPage() {

		return "views/loginPage";
	}
    
  
}
