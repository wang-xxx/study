package com.demo.allcnno.pojo;

public class HappyComponent {

    private HappyMachine happyMachine;

    public void doWork() {
        System.out.println("HappyComponent.doWork()");
    }

    public HappyMachine getHappyMachine() {
        return happyMachine;
    }

    public void setHappyMachine(HappyMachine happyMachine) {
        this.happyMachine = happyMachine;
    }
}
