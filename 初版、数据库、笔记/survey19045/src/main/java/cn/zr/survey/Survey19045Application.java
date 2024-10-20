package cn.zr.survey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.zr.survey.mapper"})
public class Survey19045Application {  //启动类

    public static void main(String[] args) {
        SpringApplication.run(Survey19045Application.class, args);
    }

}
