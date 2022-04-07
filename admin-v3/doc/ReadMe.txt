# Windows 개발자 LOCAL개발 추가 환경 설정 확인.

1. Vm args 추가
 -Djava.security.egd=file:/dev/./urandom
 -DRUNNING.MODE=LOCAL
 -Dlogging.config=classpath:logback_local.xml
 -DSERVER.INSTANCE.ID=01
 -Dspring.profiles.active=dev

 [Project] 메뉴 > Run As > Run Configurations... > [tab] Arguments  > VM arguments: 에 추가
 

2.logback_local.xml 설정
   <property name="LOG_PATH" value="c:/log/admin" />
   
   windows 사용자는 c:/log/admin  디렉토리 생성후 경로 변경해준다.
   
3. DB설정
  application-local.properties 파일의  spring.datasource.hikari.jdbc-url 정보에서
  oracle Wallet 경로를 확인한다.
  
4. build Path정보 및 openJDK 경로 확인
  .classpath

  
  
  
5. logback-local.xml

6. jenkins

7. git


---------------!11!
