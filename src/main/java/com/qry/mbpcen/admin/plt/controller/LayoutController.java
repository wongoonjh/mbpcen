package com.qry.mbpcen.admin.plt.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qry.mbpcen.admin.config.resourcename.ResourceName;
import com.qry.mbpcen.admin.plt.service.TransactionTestService;
import com.qry.mbpcen.admin.usr.service.UserService;


@Controller
@RequestMapping("/tpl")
public class LayoutController {
	private final Log logger = LogFactory.getLog(getClass());
	
	

  
    @RequestMapping(value = "/getHeader")
    public String getHeaderLayout(){
    	
		ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttr.getRequest();	
		HttpSession httpSession = request.getSession();
		String sess_id = httpSession.getId();
		logger.info( "\r\n\r\n" +
				"***************************************************************************************************\r\n" + 
				"                              START [ /tpl/getHeader.layout ] \r\n" + 
				"***************************************************************************************************\r\n" + 
				"- SESSION_ID         : [" +sess_id+"] \r\n" +
	
//				"- model              : [" +model.toString()+"] \r\n" +
//				"- pMap               : [" +pMap+"] \r\n" +
//				"- modelmodel.get()   : [" +model.get("userInfo")+"] \r\n" +
//				"- userInfoMap             : [" +userInfoMap+"] \r\n" +
				"***************************************************************************************************\r\n");
		

        return "tpl/layout_header";
//        return "login_default";
    }
}
