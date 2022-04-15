# Windows 媛쒕컻�옄 LOCAL媛쒕컻 異붽� �솚寃� �꽕�젙 �솗�씤.

1. Vm args 異붽�
 -Djava.security.egd=file:/dev/./urandom
 -DRUNNING.MODE=LOCAL
 -Dlogging.config=classpath:logback_local.xml
 -DSERVER.INSTANCE.ID=01
 -Dspring.profiles.active=dev

 [Project] 硫붾돱 > Run As > Run Configurations... > [tab] Arguments  > VM arguments: �뿉 異붽�
 

2.logback_local.xml �꽕�젙
   <property name="LOG_PATH" value="c:/log/admin" />
   
   windows �궗�슜�옄�뒗 c:/log/admin  �뵒�젆�넗由� �깮�꽦�썑 寃쎈줈 蹂�寃쏀빐以��떎.
   
3. DB�꽕�젙
  application-local.properties �뙆�씪�쓽  spring.datasource.hikari.jdbc-url �젙蹂댁뿉�꽌
  oracle Wallet 寃쎈줈瑜� �솗�씤�븳�떎.
  
4. build Path�젙蹂� 諛� openJDK 寃쎈줈 �솗�씤
  .classpath

  
  
  
5. logback-local.xml

6. jenkins

7. git


---------------!11!2
