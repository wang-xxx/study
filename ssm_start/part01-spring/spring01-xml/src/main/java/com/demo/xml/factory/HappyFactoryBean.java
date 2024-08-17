package com.demo.xml.factory;

import com.demo.xml.pojo.HappyMachine;
import org.springframework.beans.factory.FactoryBean;

public class HappyFactoryBean implements FactoryBean<HappyMachine> {

    private String machineName;

    @Override
    public HappyMachine getObject() throws Exception {
        HappyMachine happyMachine = new HappyMachine();
        happyMachine.setMachineName(this.machineName);
        return happyMachine;
    }

    @Override
    public Class<?> getObjectType() {
        return HappyMachine.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
}
