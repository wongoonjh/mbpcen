package com.qry.mbpcen.admin.plt.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qry.mbpcen.admin.config.resourcename.ResourceName;
import com.qry.mbpcen.admin.plt.repository.TransactionTestRepository;


@Service(ResourceName.SERVICE_TRANSACTIONTEST)
public class TransactionTestService {


	@Resource(name = ResourceName.REPOSITORY_TRANSACTIONTEST)
    private TransactionTestRepository dao ;

    
    
	public void test_connect() {
		
		dao.test_connect();
		
	}




	public void selectTestQuery(Map<String, String> param_map) {
		System.out.println("\r\n\r\n" +
				"***************************************************************************************************\r\n" + 
				"                              START [ TransactionTestService.selectTestQuery() ] \r\n" + 
				"***************************************************************************************************\r\n");
		dao.insertTestData();
		System.out.println("11111111111111111111111111111111111");
		dao.selectTestQuery(param_map);
		System.out.println("222222222222222222222222222222222222");
	}




	@Transactional
	public void test_Tx(Map<String, String> param_map) {
		System.out.println("\r\n\r\n" +
				"***************************************************************************************************\r\n" + 
				"                              START [ TransactionTestService.test_Tx() ] \r\n" + 
				"***************************************************************************************************\r\n");
		System.out.println("11111111111111111111111111111111111");
		dao.insertTestData();
		System.out.println("222222222222222222222222222222222222");
		dao.selectTestQuery(param_map);
		System.out.println("333333333333333333333333333333333333");
	}

}
