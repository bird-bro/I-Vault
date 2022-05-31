package com.microworld.vault;

import com.bird.common.annotation.EnableGlobalException;
import com.bird.common.exception.Disable;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@MapperScan(basePackages = "com.microworld.vault.modules.*.mapper")
@ComponentScans(value = {
		@ComponentScan("com.microworld.config"),
		@ComponentScan("com.microworld.common.interceptor"),
		@ComponentScan("com.bird.common.redis"),
})
@EnableAsync
@EnableGlobalException
@EnableAspectJAutoProxy
public class VaultApplication {


	public static void main(String[] args) {
		Disable.disableAccessWarnings();
		SpringApplication.run(VaultApplication.class, args);
	}

}
