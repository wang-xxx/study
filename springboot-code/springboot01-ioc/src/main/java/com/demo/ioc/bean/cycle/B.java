package com.demo.ioc.bean.cycle;

import org.springframework.stereotype.Component;

@Component
public class B {

    //@Autowired
    private A a;

}
