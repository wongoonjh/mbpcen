package com.qry.mbpcen.admin.plt.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class UIController {
    private final Log logger = LogFactory.getLog(getClass());
    @Autowired


    
    @RequestMapping(value = "/test/submit")
    public String testsubmit(){
    	
    	System.out.println("testsubmit");
    	System.out.println("testsubmit");
    	System.out.println("testsubmit");
        return "test";
//        return "login_default";
    }
    
    
    @RequestMapping(value = "/submit")
    public String submit(){
    	
    	System.out.println("submit");
    	System.out.println("submit");
    	System.out.println("submit");
        return "test";
//        return "login_default";
    }
    
    
    
    
    
    
    
  
	
}
