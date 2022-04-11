package com.qry.mbpcen.admin.plt.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/manage")
public class ManageController {
	private final Log logger = LogFactory.getLog(getClass());
    
	
	@RequestMapping(value = "/dashboard")
    public String dashboard(){
    	ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttr.getRequest();	
		HttpSession httpSession = request.getSession();
		String sess_id = httpSession.getId();
		logger.info( "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n" +
				"***************************************************************************************************\r\n" + 
				"                              START [ /manage/dashboard ] \r\n" + 
				"***************************************************************************************************\r\n" + 
				"- SESSION_ID         : [" +sess_id+"] \r\n" +
//				"- model              : [" +model.toString()+"] \r\n" +
//				"- pMap               : [" +pMap+"] \r\n" +
//				"- modelmodel.get()   : [" +model.get("userInfo")+"] \r\n" +
//				"- userInfoMap             : [" +userInfoMap+"] \r\n" +
				"***************************************************************************************************\r\n");
		
        return "views/index";
    }
  

  
}
