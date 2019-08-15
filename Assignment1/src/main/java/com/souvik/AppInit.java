package com.souvik;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AppInit {
	private static ApplicationContext applicationContext;
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(AppInit.class, args);
		/*String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }*/
		
		}

}
