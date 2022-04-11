package com.qry.mbpcen.admin.plt.repository;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qry.mbpcen.admin.config.resourcename.ResourceName;


/**
 * Created by yun_dev1 on 2017-01-06.
 */
@Repository(ResourceName.REPOSITORY_TRANSACTIONTEST)
public class TransactionTestRepository {
    private static final String MAPPER_NAME_SPACE = "test.tx.";

    
	/************************************************************************************/
	//SqlMapClientTemplate는 SqlMapClient를 감싸서 애플리케이션 대신 물 밑에서 세션을 열고 닫아주며
	//발생하는 모든 SQLException을 잡아서 스프링의 비검사형 예외 중 하나로 변환해 다시 던지는 역할을한다.
	/************************************************************************************/
    @Autowired
    private SqlSessionTemplate sqlSession;

 

	/**
	 * 접속테스
	 * @return 
	 */
	public Object test_connect() {
		return sqlSession.selectOne(MAPPER_NAME_SPACE + "testcc");
		
	}



	/**
	 * 접속 및 테스트 쿼리 
	 * @param param_map 
	 * @return
	 */
	public Object selectTestQuery(Map<String, String> param_map) {
		return sqlSession.selectOne(MAPPER_NAME_SPACE + "selectTestQuery" ,param_map);
		
	}



	/**
	 *트랜잭션 테스트[인서트쿼리]
	 */
	public void insertTestData() {
		sqlSession.insert(MAPPER_NAME_SPACE + "insertTestData");
		
	}

    
    
}
