package com.cht.springcloud.service;
import com.cht.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class)
//@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientService {

   @PostMapping("/dept/add")
   boolean addDept(Dept dept);

   @GetMapping("/dept/get/{id}")
   Dept queryById(@PathVariable("id") Long id);

   @GetMapping("/dept/list")
   List<Dept> queryAll();
}
