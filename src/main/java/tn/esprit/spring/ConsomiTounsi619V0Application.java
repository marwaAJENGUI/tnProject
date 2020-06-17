package tn.esprit.spring;

import java.io.IOException;
import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tn.esprit.spring.config.LoginFilter;


@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages =
{"tn.esprit.spring.*"})
@EnableAspectJAutoProxy
public class ConsomiTounsi619V0Application  {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ConsomiTounsi619V0Application.class, args);		
	}
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean(servlet, "*.jsf");
	}
	@Bean
	public FilterRegistrationBean rewriteFilter() {
		FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
		rwFilter.setDispatcherTypes(
				EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
	}	
	
//	@Bean
//	public FilterRegistrationBean loginFilter() {
//	FilterRegistrationBean lgFilter = new FilterRegistrationBean(new LoginFilter());
//	lgFilter.addUrlPatterns("/*");
//	return lgFilter;
//	}
	
	@Bean
	public FilterRegistrationBean loginFilter() {
	FilterRegistrationBean registration= new FilterRegistrationBean();
	registration.setFilter(new LoginFilter());
	registration.addUrlPatterns("/admin/adminDash.jsf","/admin/Delivery/allDelivery.jsf");
	return registration;
	}
}
