package com.demo.register.config;

import com.demo.register.pojo.Country;
import com.demo.register.pojo.Province;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean的声明：
 *
 * <p>1.使用@Configuration+@Bean注解
 *
 * <p>2.使用@Import: ①导入配置类  ②导入ImportSelector接口实现类
 */
@Configuration
public class CommonConfig {

    /**
     * 注入Bean-Country
     * 如果配置文件中没有配置，则不注入
     */
    @ConditionalOnProperty(prefix = "country", name = {"name", "system"})
    @Bean
    public Country country(@Value("${country.name}") String name, @Value("${country.system}") String system) {
        Country country = new Country();
        country.setName(name);
        country.setSystem(system);
        return country;
    }

    /**
     * Bean的名称默认为方法名
     * 如果需要引用Bean，则方法入参声明即可
     */
    /*//@Bean("aa")
    @Bean
    public Province province(Country country) {
        return new Province();
    }*/

    /**
     * 如果IOC容器中不存在Bean-Country，则注入Bean-Province，否则不注入
     */
    /*@ConditionalOnMissingBean(Country.class)
    @Bean
    public Province province() {
        return new Province();
    }*/

    /**
     * 如果IOC容器中存在DispatcherServlet，则注入Bean-Province，否则不注入
     */
    @ConditionalOnClass(name = "org.springframework.web.servlet.DispatcherServlet")
    @Bean
    public Province province() {
        return new Province();
    }

}
