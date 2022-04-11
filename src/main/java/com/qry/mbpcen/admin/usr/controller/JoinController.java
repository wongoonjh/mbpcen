package com.qry.mbpcen.admin.usr.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qry.mbpcen.admin.config.resourcename.ResourceName;
import com.qry.mbpcen.admin.usr.service.UserService;


@Controller
@RequestMapping("/views")
public class JoinController {
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
	
	
    
	   @RequestMapping(value = "/joinPage")
	    public String joinPage(){
	      
	        return "views/joinPage";
//	        return "login_default";
	    }
	  
	   
	   
	   
	    @RequestMapping(value = "/joinProcess")
	    public String joinProcess(@RequestParam(value = "userID", required = true) String user_id //고유세션값(state_token값)  authorization code 를 발급받을때 검증용 사용  
	    						 ,@RequestParam(value = "userName", required = true) String user_name
	    						 ,@RequestParam(value = "userPW", required = true) String user_pw
	    						 ,@ModelAttribute("userInfo") HashMap<String, Object> userInfoMap
	    						 ,Map<String, Object> pMap
	    						 ,ModelMap model){
	    	
			ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = requestAttr.getRequest();	
			HttpSession httpSession = request.getSession();
			String sess_id = httpSession.getId();
			logger.info( "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n" +
					"***************************************************************************************************\r\n" + 
					"                              START [ /views/joinProcess ] \r\n" + 
					"***************************************************************************************************\r\n" + 
					"- SESSION_ID         : [" +sess_id+"] \r\n" +
					"- userID             : [" +user_id+"] \r\n" +
					"- userName           : [" +user_name+"] \r\n" +
					"- userPW             : [" +user_pw+"] \r\n" +
//					"- model              : [" +model.toString()+"] \r\n" +
//					"- pMap               : [" +pMap+"] \r\n" +
//					"- modelmodel.get()   : [" +model.get("userInfo")+"] \r\n" +
//					"- userInfoMap             : [" +userInfoMap+"] \r\n" +
					"***************************************************************************************************\r\n");
			
			Map<String, Object> param_map = new HashMap<String, Object>();
			param_map.put("USER_ID", user_id);
			param_map.put("USER_NAME", user_name);
			param_map.put("USER_PW", passwordEncoder.encode(user_pw));
			param_map.put("USER_EMAIL", user_pw);
			param_map.put("REG_ID", "system");
			Object obj = service.insertUserInfo(param_map);
			logger.debug("1111111 : " +obj.toString());
	        return "redirect:/manage/dashboard";
//	        return "login_default";
	    }
	
	
		
}
