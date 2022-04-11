package com.qry.mbpcen.admin.plt.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qry.mbpcen.admin.plt.model.ApiErrorDetail;
import com.qry.mbpcen.admin.usr.exception.UserNotFoundException;




//===================================================================
//@ControllerAdvice 어노테이션의 역할 (스프링 3.2부터 추가)
// - Controller의 모든 예외에 대해 공통으로 처리할 수 있다.
// @EnableWebMvc 어노테이션을 mvc설정에 추가해야 한다.
//  --> 왜냐하면 예외처리를 위해서는 ExceptionHandlerExceptionResolver가 필요한데
//  --> @EnableWebMvc어노테이션이 RequestMappingHandlerMapping, RequestMappingHandlerAdapter,ExceptionHandlerExceptionResolver 를 설정에 등록해 주는 역할을 하는데
//  --> @ExceptionHandler어노테이션을 사용하는 메서드 작성시에 HttpMessageConverter가 @RequestBody메서드 파라미터와 @ResponseBody 메서드 반환값을 지원할 수  있도록 해주기 떄문이다.
//===================================================================
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorDetail> handleUserNotFoundException(UserNotFoundException unfe){
    	System.out.println("\r\n\r\n==============================================");
    	System.out.println("@ControllerAdvice:UserNotFoundException.class");
    	System.out.println("==============================================\r\n\r\n");
    	
        ApiErrorDetail errorDetail = new ApiErrorDetail();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setCode(1002);
        errorDetail.setMessage(unfe.getMessage());

        return new ResponseEntity(errorDetail, HttpStatus.NOT_FOUND);
    }
}
