package cn.xdl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "cn.xdl.ydma.dao")
@EnableDiscoveryClient
@ServletComponentScan
@EnableCaching
public class RunIssueBoot {

	public static void main(String[] args) {
		SpringApplication.run(RunIssueBoot.class, args);
	}

}
