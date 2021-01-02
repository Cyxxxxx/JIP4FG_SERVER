package cn.yuc.jip4fg_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.yuc.common", "cn.yuc.jip4fg_server"})
@MapperScan({"cn.yuc.jip4fg_server.*.dao","cn.yuc.jip4fg_server.*.*.dao","com.baomidou.mybatisplus.core.mapper"})
@EnableTransactionManagement
@EnableScheduling
public class Jip4fgServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jip4fgServerApplication.class, args);
	}

}
