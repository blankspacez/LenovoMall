package com.mall.lenovoMall;

import com.mall.lenovoMall.util.IdWorker;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mall.lenovoMall.mapper")
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public IdWorker getIdWork() {
        return new IdWorker();
    }

    @Bean
    public Queue queue() {
        return new Queue("seckill_order",true);
    }

}
