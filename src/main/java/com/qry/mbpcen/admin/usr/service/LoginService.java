package com.qry.mbpcen.admin.usr.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qry.mbpcen.admin.plt.repository.UserRepository;
import com.qry.mbpcen.admin.usr.model.UserVO;

@Service
public class LoginService implements UserDetailsService {

	private final Log logger = LogFactory.getLog(getClass());
	
	
	//===================================================================	
	// @Bean 을 통해 PasswordEncoder를 정하는 저 부분도 중요합니다.
	// 유저 pw의 암호화 방식을 정하는 부분 입니다. 일반적으로는 그냥 저 BCryptPasswordEncoder를 쓰시면 됩니다.
	// 프로젝트에 따라 암호화 방식이 정해진 경우도 많으므로 4번에서는 이 부분도 커스텀 해보겠습니다.
	// 참고로 이 부분은 프로젝트 내 어디에 있던 상관 없습니다.
	// SpringSecurityConfig쪽에 넣어도 되고, 아예 다른 클래스에 넣어놔도 됩니다. 
	//전 여기에 넣는게 제일 깔끔한 구성이라 생각해서 여기에 넣었을 뿐입니다.
	//===================================================================
	@Bean("PasswordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new SHA512PasswordEncoder();
//	}

	@Autowired
	private UserRepository userDao;
	
	@Override
	public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
		ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttr.getRequest();	
		HttpSession httpSession = request.getSession();
		String sess_id = httpSession.getId();
		logger.info( "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n" +
				"***************************************************************************************************\r\n" + 
				"                              START [ LoginService.loadUserByUsername(String userID) ] \r\n" + 
				"***************************************************************************************************\r\n" + 
				"- SESSION_ID         : [" +sess_id+"] \r\n" +
				"***************************************************************************************************\r\n");
		
		
		Map<String, Object> param_map = new HashMap<String, Object>();
		param_map.put("USER_ID", userID);
		UserVO user = userDao.getUserInfo(param_map);
		
		
		if (user == null) {
			return null;
		}

		String pw = user.getUserPW(); // "d404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db"
		String roles = user.getUserRole(); // "USER"

		
		return User.builder().username(userID).password(pw).roles(roles).build();
	}
    
}