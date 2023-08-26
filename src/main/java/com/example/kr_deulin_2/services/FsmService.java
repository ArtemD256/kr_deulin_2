package com.example.kr_deulin_2.services;


import com.example.kr_deulin_2.model.Enums.Step;
import com.example.kr_deulin_2.model.Services;
import com.example.kr_deulin_2.model.Fsm;
import com.example.kr_deulin_2.model.Enums.Event;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    Сервис для какой-то бизнес логики
 */

@Service
public class FsmService implements FsmServiceInt {

    private static Fsm fsm;

    public FsmService() {
        fsm = getInstance();
    }

    @Override
    public void turnOn() throws RuntimeException {
        try {
            fsm.stepTwo(Step.turnOn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void turnOff() throws RuntimeException {
        try {
            fsm.stepTwo(Step.turnOff);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void OnlyWater() throws RuntimeException {
        try {
            fsm.stepTwo(Step.OnlyWater);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void waterAndFoam() throws RuntimeException {
        try {
            fsm.stepTwo(Step.waterAndFoam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void waterAndWax() throws RuntimeException {
        try {
            fsm.stepTwo(Step.waterAndFoam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void waterFoamDrying() throws RuntimeException {
        try {
            fsm.stepTwo(Step.waterFoamDrying);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void waterFoamWax() throws RuntimeException {
        try {
            fsm.stepTwo(Step.waterFoamWax);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add(){
        try {
            fsm.stepTwo(Step.add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fsm getFsm() {
        return fsm;
    }

    private Fsm getInstance(){
        if(fsm == null){
            fsm = new Fsm();
            return fsm;
        }
        return fsm;
    }

    @Override
    public List<Services> getFsmService() {
        return fsm.getServicesList();
    }
}
