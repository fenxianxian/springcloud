package com.cht.springcloud.pojo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
@Data
@NoArgsConstructor
@Accessors(chain = true) //chain为true，说明它支持链式写法
//所有实体类务必序列化，方便在网络传输
public class Dept implements Serializable {

    private Long deptno;
    private String dname;
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }
    /*
    链式写法：
        Dept dept = Dept();
        dept.setDeptNo(11).setDname("ss").setDb_source("db1");
    */
}
