package com.demo.allcnno.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.demo.allcnno.pojo.HappyComponent;
import com.demo.allcnno.pojo.HappyMachine;
import com.demo.allcnno.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.demo.allcnno")
@PropertySource({"classpath:config.properties", "classpath:db.properties"})
@Import({StudentConfig.class})
public class SpringConfig {

    /*@Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;*/

    @Bean(name = {"db1", "db2"})
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DruidDataSource dataSource(@Value("${jdbc.driverClassName}") String driverClassName,
                                      @Value("${jdbc.url}") String url,
                                      @Value("${jdbc.username}") String username,
                                      @Value("${jdbc.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public User user() {
        return new User("张三", "admin123456");
    }

    @Bean
    public HappyMachine happyMachine() {
        HappyMachine happyMachine = new HappyMachine();
        happyMachine.setMachineName("开心机");
        return happyMachine;
    }

    @Bean
    @Conditional({MyCondition.class})
    public HappyComponent happyComponent(HappyMachine happyMachine) {
        HappyComponent happyComponent = new HappyComponent();
        happyComponent.setHappyMachine(happyMachine);
        return happyComponent;
    }

}
