package com.qry.mbpcen.admin.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
//@PropertySource("application.properties")
public class OracleDBConnectionConfig implements TransactionManagementConfigurer {
	@Value("${spring.datasource.hikari.jdbc-url}")
	private String dbUrl;
	
	@Value("${spring.datasource.username}")
	private String dbUsername;
	
	@Value("${spring.datasource.password}")
	private String dbPassword;
	
	@Value("${spring.datasource.hikari.data-source-class-name}")
	private String dataSourceClassName;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;	
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource(){
		
		
       final HikariConfig hikariConfig = new HikariConfig();
//       hikariConfig.setDataSourceClassName(dataSourceClassName);
       hikariConfig.setDriverClassName(driverClassName);
//       hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
//       hikariConfig.setDriverClassName("core.log.jdbc.driver.OracleDriver");
       hikariConfig.setJdbcUrl(dbUrl);
//       hikariConfig.setDataSourceJNDI(dbClassName);
       hikariConfig.setUsername(dbUsername);
       hikariConfig.setPassword(dbPassword);
//       hikariConfig.addDataSourceProperty("url", dbUrl);

       
       hikariConfig.setLeakDetectionThreshold(2000);
       hikariConfig.setPoolName("mpbcen_oracle_dbpool");
       
       HikariDataSource dataSource = new HikariDataSource(hikariConfig);
       return dataSource;
    }


//	@Bean(destroyMethod = "shutdown")
//	public DataSource dataSource(){
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        EmbeddedDatabase h2 = builder
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("schema-h2.sql")
//                .addScript("data-h2.sql")
//                .build();
//
//        return h2;
//    }

	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager();
	}

}
