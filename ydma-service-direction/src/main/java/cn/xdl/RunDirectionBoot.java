package cn.xdl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan(basePackages = "cn.xdl.ydma.dao")
@ServletComponentScan
@EnableCaching
@EnableDiscoveryClient//加强型@EnableEurekaClient
@EnableFeignClients//启用Feign
public class RunDirectionBoot {

	public static void main(String[] args) {
		SpringApplication.run(RunDirectionBoot.class,args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
