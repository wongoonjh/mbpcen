package com.qry.mbpcen.admin.plt.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qry.mbpcen.admin.config.resourcename.ResourceName;
import com.qry.mbpcen.admin.usr.model.UserVO;


/**
 * Created by yun_dev1 on 2017-01-06.
 */
@Repository(ResourceName.REPOSITORY_USER)
public class UserRepository {
    private static final String MAPPER_NAME_SPACE = "user.";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List getUserInfoAll(){
        return sqlSessionTemplate.selectList(MAPPER_NAME_SPACE + "selectUserInfoAll");
    }

    public void adduserInfo(UserVO userVO){
        sqlSessionTemplate.insert(MAPPER_NAME_SPACE+ "addUserInfo", userVO);
    }

    public List findByUserNameLike(String userName){
        Map<String,Object> params = new HashMap();
        params.put("userName", userName);

        return sqlSessionTemplate.selectList(MAPPER_NAME_SPACE + "findByUserNameLike", params);
    }

    public UserVO findByUserName(String userName){
        Map<String,Object> params = new HashMap();
        params.put("userName", userName);

        return sqlSessionTemplate.selectOne(MAPPER_NAME_SPACE + "findByUserName", params);
    }

    
    
    
    
	public UserVO getUserInfo(Map<String, Object> param_map) {
		
//		UserVO userVO = new UserVO("test@naver.com","TESTER","ADMIN");
		 
		return sqlSessionTemplate.selectOne(MAPPER_NAME_SPACE+ "getUserInfo",param_map);
	}

	public Object insertUserInfo(Map<String, Object> param_map) {
		Object obj = sqlSessionTemplate.insert(MAPPER_NAME_SPACE+ "insertUserInfo", param_map);
		return obj;
		
	}


}
