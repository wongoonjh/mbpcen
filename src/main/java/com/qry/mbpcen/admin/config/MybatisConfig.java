package com.qry.mbpcen.admin.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@ComponentScan(basePackages = {"com.qry.mbpcen"})
public class MybatisConfig {
	
//    @Bean
//    public DataSource dataSource(){
//        return new EmbeddedDatabaseBuilder()
//                    .setName("jpubtestdb")
//                .setType(EmbeddedDatabaseType.HSQL)
//                .addScript("schema-hsqldb.sql")
//                .addScript("data-hsqldb.sql")
//                .build();
//    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation((new PathMatchingResourcePatternResolver().getResource("classpath:sql/mybatis-config.xml")));
        sqlSessionFactoryBean.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath:sql/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
