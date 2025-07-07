package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//여기서는 '*'이 두개가 된다.
@MapperScan(basePackages = "com.yedam.app.**.mapper")
//mapper 인식시키기 위한 어노테이션(mybatis가 제공하는)
// mapper가 있는 패키지를 적어준다.
public class Sp02Application {

	public static void main(String[] args) {
		SpringApplication.run(Sp02Application.class, args);
	}

}
