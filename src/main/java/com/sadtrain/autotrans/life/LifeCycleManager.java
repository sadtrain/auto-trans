package com.sadtrain.autotrans.life;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LifeCycleManager implements LifeCycle{

    List<LifeCycle> lifeCycles = new ArrayList<>();

    @Override
    public void start() {
        for (LifeCycle lifeCycle : lifeCycles) {
            lifeCycle.start();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void restart() {

    }

    public void addService(LifeCycle lifeCycle){
        lifeCycles.add(lifeCycle);
    }

}
