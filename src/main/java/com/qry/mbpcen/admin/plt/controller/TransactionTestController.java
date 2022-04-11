package com.qry.mbpcen.admin.plt.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qry.mbpcen.admin.config.resourcename.ResourceName;
import com.qry.mbpcen.admin.plt.service.TransactionTestService;



//===================================================================
//@RestController 어노테이션의 역할 (스프링 4부터 추가)
//@ResponseBody 어노테이션과 @Controller 어노테이션을 합쳐서 만든 어노테이션
//  --> @ResponseBody 어노테이션이 있으면 실행결과는 View를 거치지 않고 Http ResponseBody에 직접 입력된다.
//  --> MappingJacksonHttMessageConverter를 통해 Json으로 결과가 표현된다.
//===================================================================
@RestController
@RequestMapping(value = "/test")
public class TransactionTestController {
    private final AtomicLong counter = new AtomicLong();

	/** 메뉴 Service */
	@Resource(name = ResourceName.SERVICE_TRANSACTIONTEST)
	protected TransactionTestService service;


	/**
	 * connection test
	 * @return
	 */
	@RequestMapping(value = "/conn" )
	public String test_conn()  {
		ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttr.getRequest();	
		HttpSession httpSession = request.getSession();
		String sess_id = httpSession.getId();
		
		
		System.out.println( "\r\n\r\n" +
				"***************************************************************************************************\r\n" + 
				"                              START [ TransactionTestController.test_conn() ] \r\n" + 
				"***************************************************************************************************\r\n" + 
				"- SESSION_ID             : [" +sess_id+"] \r\n" +
				"- REUEST_URL             : [/test/conn] \r\n" +
				"***************************************************************************************************\r\n");
		
		Map<String, String> param_map = new HashMap<String, String>();
		param_map.put("COLUMN1","111");
		service.selectTestQuery(param_map);
		
		
		return "";
	}
	
	
	/**
	 * transaction test
	 * @return
	 */
	@RequestMapping(value = "/test_transaction" )
	public String test_transaction()  {
		ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttr.getRequest();	
		HttpSession httpSession = request.getSession();
		String sess_id = httpSession.getId();
		
		
		System.out.println("\r\n\r\n" +
				"***************************************************************************************************\r\n" + 
				"                              START [ TransactionTestController.test_transaction() ] \r\n" + 
				"***************************************************************************************************\r\n" + 
				"- SESSION_ID             : [" +sess_id+"] \r\n" +
				"- REUEST_URL             : [/plt/test_transaction] \r\n" +
				"***************************************************************************************************\r\n");
		
		Map<String, String> param_map = new HashMap<String, String>();
		param_map.put("COLUMN1","111");
		service.test_Tx(param_map);
		
		
		return "";
	}
	
}
