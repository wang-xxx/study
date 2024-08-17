package com.demo.register;

import com.demo.register.anno.EnableCommonConfig;
import com.demo.register.pojo.Province;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@Import({CommonConfig.class})
//@Import(CommonImportSelector.class)
@EnableCommonConfig
public class RegisterApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(RegisterApplication.class, args);
        //Country country = applicationContext.getBean(Country.class);
        //System.out.println(country);
        Province province = applicationContext.getBean("province", Province.class);
        System.out.println(province);
    }

}
