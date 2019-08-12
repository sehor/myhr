package myhr;

import myhr.AcitveMQ.JmsComponent;
import myhr.config.componet.CustomFilterInvocationSecurityMetadataSource;
import myhr.data.domain.Message;
import myhr.data.domain.user.Menu;
import myhr.data.domain.user.Role;
import myhr.data.repository.MenuRepository;
import myhr.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import myhr.controller.TestAop;
import myhr.data.domain.Author;
import org.springframework.util.AntPathMatcher;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyhrApplicationTests {

	private final Logger logger=LogManager.getLogger();
/*	private AntPathMatcher antPathMatcher=new AntPathMatcher();*/
	
/*	@Autowired
	Author me;
	@Autowired
	TestAop ta;*/

/*	@Autowired
	RoleService roleService;
	@Autowired
	MenuRepository menuRepository;
	@Autowired
	CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;*/
@Autowired
	JmsComponent jmsComponent;

	@Test
	public void contextLoads() throws Exception {
		
		/*
		 * for (String favorite : me.getFavorites()) { log.info(favorite); }
		 */
	/*	logger.info("i am l4j2");
		ta.testAspect();*/

	/*	for (String	 roleName:roleService.findRolesNameByPath("/admin/save"))
		  System.out.println(roleName);

    List<Menu> menus=menuRepository.findAll();
    menus.forEach(menu -> System.out.println(menu.getPattern()+" "+antPathMatcher.match(menu.getPattern(),"/admin/**")));*/

	/*	customFilterInvocationSecurityMetadataSource.getAttributes("/admin")
				.forEach(s->System.out.println("test securityconfig"+s+s.getAttribute()));*/

		Message message=new Message();
		message.setName("i am a message!");
		message.setDate(new Date());
		jmsComponent.sendMessage(message);
	}

}
