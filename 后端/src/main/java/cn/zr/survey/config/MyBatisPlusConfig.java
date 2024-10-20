package cn.zr.survey.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {//mybatisPlus配置
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        //创建插件的主体
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //向主体中添加插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //向主体添加**插件
        return interceptor;

    }
}
