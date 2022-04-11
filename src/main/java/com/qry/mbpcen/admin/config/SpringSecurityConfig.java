package com.qry.mbpcen.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.qry.mbpcen.admin.usr.service.LoginService;


//===================================================================
//각종 설정은 WebSecurityConfigurerAdapter에 오버로딩 되어 있는 다양한 configure 함수를 오버라이드 해서 설정할 수 있습니다.
//우선 위와 같이 HttpSecurity만 인자로 가지는(메소드 시그니쳐에 따라 다양한 configure 가능) configure를 오버라이딩 해줍니다.
//===================================================================
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginService loginIdPwValidator;
	//===================================================================	
	//1. '.anyRequest().authenticated()'에서 어떠한 URI로 접근하던지 인증이 필요함을 설정합니다.
	//2. 'formLogin()'에서 폼방식 로그인을 사용할 것임을 알리고, logout도 필요하니 logout도 추가해줍니다.
	//3. 'defaultSuccessUrl'로 로그인 성공 시 이동할 uri를 적어줍니다.
	//===================================================================
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//===================================================================
				//서버를 클라우드에 올리는 경우, 일반적으로 로드밸런서를 사용중이라면 로드밸런서에 상태 체크 url을 작성해야 합니다. 
				//이런 경우 스프링 시큐리티에서 인증을 진행하지 않아야만 정상적으로 처리가 가능합니다. 
				//이처럼 로그인 없이 접근 가능해야하는 URI는 SpringSecurityConfig에 
				//'.antMatchers("/chk").permitAll()'와 같이 예외를 설정할 수 있습니다.
				//===================================================================
		 		.antMatchers("/test/**").permitAll()    // test page
		 		.antMatchers("/th3").permitAll()    
		 		.antMatchers("/views/joinPage").permitAll()   
		 		.antMatchers("/views/joinProcess").permitAll()   
		 		.antMatchers("/views/userList").permitAll()   //임시 작업을 위해 인증 해제 
		 		//===================================================================
		 		//antMatchers("/manage").hasAuthority("ROLE_ADMIN")' 부분처럼 처리하면 됩니다. 
		 		//그럼 해당 사용자가 ADMIN의 role을 가지고 있어야만 '/manage' 이하의 uri에 접근 가능하게 됩니다. 
		 		//ROLE은 DB에 넣어두면 되겠죠.
		 		//===================================================================
		 		.antMatchers("/manage").hasAuthority("ROLE_ADMIN")
		 		.anyRequest().authenticated()  //어떠한 URI로 접근하던지 인증이 필요함을 설정합니다.
		    .and()
		    	.formLogin().disable()	//기본 로그인 페이지 없애기
		    	.formLogin()                   //.formLogin()'에서 폼방식 로그인을 사용할 것임을 알리고,
		    	.loginPage("/views/loginPage")    //.loginPage("/view/login")' 에서 커스텀 페이지로 로그인 페이지를 변경합니다.
		    	//===================================================================
		    	//loginProcessingUrl("/loginProc")' 은 별도로 Controller에 만들어야 하는게 아니고, 
		    	//formLogin 방식이므로 해당 주소를 어디로 처리할지 정해주는 겁니다. 
		    	//그럼 저 '/view/login'에서 '<form method="post" action="/loginProc">'와 같이 form의 action을 정해주면 
		    	//알아서 스프링 시큐리티쪽으로 id와 pw를 보내게 됩니다.
		    	//===================================================================
		    	.loginProcessingUrl("/loginProcess")
                .usernameParameter("userID")
                .passwordParameter("userPW")
		    	.defaultSuccessUrl("/manage/dashboard", true).permitAll()
			.and()
				.logout()              // logout도 필요하니 logout도 추가해줍니다.
				//===================================================================
				//.logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc"))' 부분 처럼 처리하면 
				//'/logoutProc'을 호출할 시 로그아웃이 되고, 그럼 인증된게 사라지니 다시 로그인 페이지로 자동으로 이동되게 되는 것입니다. 
				//이 부분은 생략 가능해서 이렇게 서브로 넣었습니다. 생략 시 default로 '/logout' 호출 시 로그아웃이 가능합니다.
				//===================================================================
				.logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc"))
			.and()
				.csrf().disable();
//				.csrf();
	}
	
	//===================================================================	
	//css나 이미지 파일 등의 경우 인증이 되지 않은 상태에서도 보여져야 하는 경우가 대부분이다.
	//이 경우 별도로 WebSecurity 하나를 인자로 갖는 configure를 오버라이딩해서 예외 처리를 할 수 있습니다.
	//===================================================================
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**","/assets/js/**","/assets/css/**","/assets/img/**","/assets/vendor/**","/assets/scss/**");
    }
    
    
	// ===================================================================
	// AuthenticationManagerBuilder를 인자로 갖는 configure를 추가해줬습니다.
	// 저렇게 설정하면 이제 유저가 id와 pw를 입력한 후 form이 발송되면 LoginIdPwValidator 쪽으로 id가 넘어가 비교할 수
	// 있게 됩니다.
	// ===================================================================
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginIdPwValidator);
    }

}
