package com.qry.mbpcen.admin;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AdminV3Application {

	public static void main(String[] args) {
		// ===================================================================
		// run 메서드는 ConfigurableApplicationContext를 반환함.
		// ===================================================================
		ApplicationContext ctx = SpringApplication.run(AdminV3Application.class, args);

		/**
		 * 스프링부트에서 로드하는 빈 확인
		 */
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		for (String beanName : beanNames) {
			System.out.println(beanName);
		}

	}

}
