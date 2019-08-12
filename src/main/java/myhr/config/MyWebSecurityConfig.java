package myhr.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhr.config.componet.CustomAccessDecisionManger;
import myhr.config.componet.CustomFilterInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import myhr.service.UserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	@Autowired
	CustomFilterInvocationSecurityMetadataSource  customFilterInvocationSecurityMetadataSource;
	@Autowired
	CustomAccessDecisionManger customAccessDecisionManger;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	RoleHierarchy roleHierarchy(){
		RoleHierarchyImpl roleHierarchy=new RoleHierarchyImpl();
		String hierarchy="ROLE_DBA>ROLE_ADMIN,ROLE_ADMIN>ROLE_USER,ROLE_USER>ROLE_GUEST";
		roleHierarchy.setHierarchy(hierarchy);
		return roleHierarchy;
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

/*		  BCryptPasswordEncoder coder=new BCryptPasswordEncoder();
		  System.out.print("admin123="+coder.encode("admin123"));
		  auth.inMemoryAuthentication().withUser("admin").password(coder.encode(
		 "admin123")).roles("ADMIN", "USER") .and()
		 .withUser("guest").password(coder.encode("123")).roles("USER") .and()
		 .withUser("dba").password(coder.encode("dba123")).roles("DBA");*/

    	auth.userDetailsService(userService);
       
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                /*.antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").access("hasAnyRole('ADMIN','USER')")
                .antMatchers("/dba/**").access("hasRole('ADMIN') or hasRole('DBA')")
                .anyRequest().authenticated()*/
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O o) {
						o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
						o.setAccessDecisionManager(customAccessDecisionManger);
						return o;
					}
				})
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						// TODO Auto-generated method stub
						Object principal=authentication.getPrincipal();
						response.setContentType("application/json;charset=utf-8");
						PrintWriter out=response.getWriter();
						response.setStatus(200);
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("status", 200);
						map.put("msg", principal);
						
						ObjectMapper om=new ObjectMapper();
						out.write(om.writeValueAsString(map));
						out.flush();
						out.close();
						
					}
				})
                
                .failureHandler(new AuthenticationFailureHandler() {
					
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException exception) throws IOException, ServletException {
						// TODO Auto-generated method stub
						
						response.setContentType("application/json;charset=utf-8");
						PrintWriter out=response.getWriter();
						response.setStatus(401);
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("status", 401);
				  
						if(exception instanceof LockedException) {
							map.put("msg", "账户锁定，登录失败！");
						}
						else if (exception instanceof BadCredentialsException) {
							map.put("msg", "错误的用户名或密码，登录失败！");
						}
						else if (exception instanceof DisabledException) {
							map.put("msg", "账户被禁用，登录失败！");
						}else if (exception instanceof AccountExpiredException) {
							map.put("msg", "账户已失效，登录失败！");
						}else if (exception instanceof CredentialsExpiredException) {
							map.put("msg", "密码已过期，登录失败！");
						}
						else {
							map.put("msg", "登录发生错误！");
						}
						ObjectMapper om=new ObjectMapper();
						out.write(om.writeValueAsString(map));
						out.flush();
						out.close();
						
					}
				})
                .and()  
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
					
					@Override
					public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
						// TODO Auto-generated method stub
						
					}
				})
                .logoutSuccessHandler(new LogoutSuccessHandler() {
					
					@Override
					public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
							throws IOException, ServletException {
						// TODO Auto-generated method stub
						response.sendRedirect("/login");
					}
				})
                .permitAll()
                .and()
                .csrf().disable();
    }

/*    @Bean
	CustomFilterInvocationSecurityMetadataSource csm(){
    	return customFilterInvocationSecurityMetadataSource;
	}

	@Bean
	CustomAccessDecisionManger cad(){
    	return customAccessDecisionManger;
	}*/
    
}
