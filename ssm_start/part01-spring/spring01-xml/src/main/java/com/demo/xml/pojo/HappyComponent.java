package com.demo.xml.pojo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HappyComponent {

    private String componentName;

    private HappyMachine happyMachine;

    private List<String> gridFriends;
    private Set<String> heros;
    private Map<String, String> skills;

    public HappyComponent() {
        System.out.println("默认构造函数执行");
    }

    public void doWork() {
        System.out.println("HappyComponent doWork");
    }

    public HappyMachine getHappyMachine() {
        return happyMachine;
    }

    public void setHappyMachine(HappyMachine happyMachine) {
        this.happyMachine = happyMachine;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        System.out.println("setComponentName");
        this.componentName = componentName;
    }

    public List<String> getGridFriends() {
        return gridFriends;
    }

    public void setGridFriends(List<String> gridFriends) {
        this.gridFriends = gridFriends;
    }

    public Set<String> getHeros() {
        return heros;
    }

    public void setHeros(Set<String> heros) {
        this.heros = heros;
    }

    public Map<String, String> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "HappyComponent{" +
                "componentName='" + componentName + '\'' +
                ", happyMachine=" + happyMachine +
                ", gridFriends=" + gridFriends +
                ", heros=" + heros +
                ", skills=" + skills +
                '}';
    }
}
