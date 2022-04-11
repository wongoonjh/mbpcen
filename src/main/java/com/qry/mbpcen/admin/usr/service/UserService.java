package com.qry.mbpcen.admin.usr.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.qry.mbpcen.admin.config.resourcename.ResourceName;
import com.qry.mbpcen.admin.plt.repository.UserRepository;
import com.qry.mbpcen.admin.usr.model.UserVO;


@Service(ResourceName.SERVICE_USER)
public class UserService {
	@Resource(name = ResourceName.REPOSITORY_USER)
    UserRepository userRepository;

    public Iterable<UserVO> findAllUserInfo(){
        Iterable<UserVO> allUsers = userRepository.getUserInfoAll();
        return allUsers;
    }

    public void dummyInfo(){
        ServletUriComponentsBuilder.fromCurrentRequest()
                .toUriString();
    }

    public void createUser(UserVO userVO){
        System.out.println("userVO::" + userVO.toString());
        userRepository.adduserInfo(userVO);
    }

    public Iterable<? extends UserVO> findByLikeUserName(String userName){
        Iterable<UserVO> resultList = userRepository.findByUserNameLike(userName);
        return resultList;
    }

    public UserVO findByOneUserName(String userName){
        UserVO userVO = userRepository.findByUserName(userName);
        return userVO;
    }
    
    
    
    public Object insertUserInfo(Map<String, Object> param_map){
       Object obj =  userRepository.insertUserInfo(param_map);
       return obj;
    }

    
    
    
    
}
