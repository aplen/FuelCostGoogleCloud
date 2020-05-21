package io.github.plindzek;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FuelCostApplication {

	public static void main(String[] args) {

		SpringApplication.run(FuelCostApplication.class, args);

	}
	/*needed for working h2 console*/
	@Bean
	ServletRegistrationBean h2servletRegistration(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

}
