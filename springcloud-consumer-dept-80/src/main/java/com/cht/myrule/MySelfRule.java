package com.cht.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//编写自定义算法
@Configuration
public class MySelfRule {

    @Bean
    public IRule iRule(){
        return new RandomRule_ZY(); //达到的目的，用我们重新选择的随机算法替换默认的轮询算法
    }
}
