package com.cht.springcloud.controller;
import com.cht.springcloud.pojo.Dept;
import com.cht.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//提供restful服务
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * DiscoveryClient 可以用来获取一些配置的信息，得到具体的微服务！
     */
    @Autowired
    private DiscoveryClient client; //选的是接口型的

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept queryById(@PathVariable("id") Long id){
        Dept dept = this.deptService.queryById(id);
        //也就是如果查询的id号不存在，那么dept就为null，为null就报异常，
        //但此时该方法已经被@HystrixCommand修饰，那么就会触发该注解里指定的方法，
        //也就是processHystrix_Get。注：不报异常或不出错就不会触发，该怎么样还怎么样
        if(null == dept){
            //触发processHystrix_Get方法
            throw new RuntimeException("该ID："+id+"没有对应的信息");
        }
        //return deptService.queryById(id);
        return dept;
    }
    //捕获异常
    public Dept processHystrix_Get(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("该ID："+id+"没有对应的信息，null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    /**
     * 获取一些注册进来的微服务的信息
     */
    @GetMapping("/dept/discovery")
    public Object discovery() {
        // 获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>services:" + services);
        // 得到一个具体的微服务信息,通过具体的微服务id，applicaioinName；
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t" + // 主机名称
                            instance.getPort() + "\t" + // 端口号
                            instance.getUri() + "\t" + // uri
                            instance.getServiceId() // 服务id
            );
        }
        return this.client;
    }
}
