package com.example.kr_deulin_2.model;

import com.example.kr_deulin_2.model.Enums.Step;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.kr_deulin_2.model.Enums.Event;
import com.example.kr_deulin_2.model.Enums.Statement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*
 Rest - методы для работы с автомойкой
    1) void turnOn() - Включить
    2) void turnOff() - Выключить
    3) void OnlyWater() - вода
    4) void waterAndFoam() - вода-пена-вода
    5) void waterAndWax() - вода-воск
    6) void waterFoamDrying() - вода-пена-вода-сушка
    7) void waterFoamWax() - вода-пена-вода-воск
    8) FSM getFsm() - получить автомойку
 */

@Repository
public class Fsm {

    private int isFoam = 0;
    private int isWax = 0;

    @JsonIgnore
    private static Fsm fsm;
    private Boolean isTheWaterOn;
    private Boolean isFoamIncluded;
    private Boolean isDryingMachine;
    private Boolean isWaxCoating;
    private Statement statement;
    private List<Services> servicesList;
    @JsonIgnore
    private Integer money;

    public Fsm() {
        /*
            Инициализация нашего списка услуг по умолчанию
         */
        List<Services> services = new ArrayList<>();
        services.add(new Services("Вода",150,1));
        services.add(new Services("Вода+Пена",250,2));
        services.add(new Services("Вода+Воск",300,3));
        services.add(new Services("Вода+Пена+Сушка",350,4));
        services.add(new Services("Вода+Пена+Воск",450,5));

        /*
            Инициализация нашего конечного автомата.
         */
        this.statement = Statement.OFF;
        this.money = 0;
        this.isDryingMachine = false;
        this.isFoamIncluded = false;
        this.isTheWaterOn = false;
        this.isWaxCoating = false;
        this.servicesList = services;
    }

    public static Fsm getFsm() {
        return fsm;
    }

    public Boolean getTheWaterOn() {
        return isTheWaterOn;
    }

    public Boolean getFoamIncluded() {
        return isFoamIncluded;
    }

    public Boolean getDryingMachine() {
        return isDryingMachine;
    }

    public Boolean getWaxCoating() {
        return isWaxCoating;
    }

    public Statement getStatement() {
        return statement;
    }

    public List<Services> getServicesList() {
        return servicesList;
    }

    public Integer getMoney() {
        return money;
    }

    public void setTheWaterOn(Boolean theWaterOn) {
        isTheWaterOn = theWaterOn;
    }

    public void setFoamIncluded(Boolean foamIncluded) {
        isFoamIncluded = foamIncluded;
    }

    public void setDryingMachine(Boolean dryingMachine) {
        isDryingMachine = dryingMachine;
    }

    public void setWaxCoating(Boolean waxCoating) {
        isWaxCoating = waxCoating;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

    /*
        Операции не связанные с ID товара
     */
    public void step(Event event) throws Exception {
        switch (event){
            case TurnOnTheWater -> {
                if(statement != Statement.READY){
                    throw new Exception("FSM is not ready");
                }
                System.err.println("On Water.\nPlease wait 60 second.");
                statement = Statement.WATER;
                this.isTheWaterOn = true;
                Thread.sleep(15000);
                System.out.println("Выключаем воду");
                this.isTheWaterOn = false;
                statement = Statement.READY;
            }
            case TurnOnTheFoam -> {
                if (statement != Statement.READY) {
                    throw new Exception("FSM is not ready!");
                }
                if (isFoam < 100) {
                    throw new Exception("FSM is not ready!");
                }
                System.out.println("On Foam.\nPlease wait 60 second.");
                statement = Statement.FOAM;
                this.isFoamIncluded = true;
                Thread.sleep(15000);
                System.out.println("Выключаем пену");
                this.isFoamIncluded = false;
                statement = Statement.READY;
            }
            case TurnOnWax -> {
                if(statement != Statement.READY){
                    throw new Exception("FSM is not ready!");
                }
                if (isWax < 100) {
                    throw new Exception("FSM is not ready!");
                }
                System.out.println("On Wax.\nPlease wait 40 second.");
                statement = Statement.WAX;
                this.isWaxCoating = true;
                Thread.sleep(40000);
                System.out.println("Выключаем воск");
                statement = Statement.READY;
                this.isWaxCoating = false;
            }
            case TurnOnDrying -> {
                if(statement != Statement.READY){
                    throw new Exception("FSM is not ready!");
                }
                System.out.println("On drying.\nPlease wait 60 second.");
                statement = Statement.DRYING;
                this.isDryingMachine = true;
                Thread.sleep(60000);
                System.out.println("Выключаем сушку");
                statement = Statement.READY;
                this.isDryingMachine = false;
            }

            case add -> {
                if(statement != Statement.READY){
                    throw new Exception("FSM is not ready!");
                }
                System.out.println("FSN ADDED!");
                statement = Statement.ADDED;
                isWax+=5000;
                isFoam+=5000;
                Thread.sleep(7000);
                statement = Statement.READY;
            }
            case CheckFoam -> {
                if (isFoam < 100) {
                    throw new Exception("Foam not found!");
                }
            }
            case CheckWax -> {
                if (isWax < 100) {
                    throw new Exception("Wax not found!!");
                }
            }
        }
    }
    public void stepTwo(Step event) throws Exception {
        switch (event){
            case waterAndFoam -> {
                this.step(Event.CheckFoam);
                this.stepTwo(Event.go_pay,2);
                this.step(Event.TurnOnTheWater);
                this.step(Event.TurnOnTheFoam);
                this.step(Event.TurnOnTheWater);
            }
            case waterAndWax -> {
                this.step(Event.CheckWax);
                this.stepTwo(Event.go_pay,3);
                this.step(Event.TurnOnTheWater);
                this.step(Event.TurnOnWax);
                this.step(Event.TurnOnTheWater);
            }
            case waterFoamDrying -> {
                this.step(Event.CheckFoam);
                this.stepTwo(Event.go_pay,4);
                this.step(Event.TurnOnTheWater);
                this.step(Event.TurnOnTheFoam);
                this.step(Event.TurnOnTheWater);
                this.step(Event.TurnOnDrying);
            }
            case waterFoamWax -> {
                this.step(Event.CheckFoam);
                this.step(Event.CheckWax);
                this.stepTwo(Event.go_pay,4);
                this.step(Event.TurnOnTheWater);
                this.step(Event.TurnOnTheFoam);
                this.step(Event.TurnOnTheWater);
                this.step(Event.TurnOnWax);
                this.step(Event.TurnOnTheWater);
            }
            case OnlyWater -> {
                this.stepTwo(Event.go_pay,1);
                this.step(Event.TurnOnTheWater);
            }

            case add -> {
                this.step(Event.add);
            }

            case turnOn -> {
                if(statement != Statement.OFF){
                    throw new Exception("FSM is not ready!");
                }
                System.out.println("FSN on!");
                statement = Statement.ON;
                Thread.sleep(3000);
                System.out.println("GO!!!");
                statement = Statement.READY;
            }

            case turnOff -> {
                if(statement != Statement.READY){
                    throw new Exception("FSM is not ready!");
                }
                System.out.println("FSN off!");
                statement = Statement.ON;
                Thread.sleep(3000);
                statement = Statement.OFF;
            }
        }
    }
    /*
        Операции связанные с Id товара
     */
    private void stepTwo(Event event, Integer id) throws Exception {
        switch (event) {
            case go_pay -> {
                money += servicesList.stream()
                        .filter(x -> x.getId().equals(id))
                        .findFirst()
                        .orElseThrow(Exception::new)
                        .getPrice();
                System.err.println("PAYMENT");
                Thread.sleep(2000);
            }
        }
    }

    public int getIsFoam() {
        return isFoam;
    }

    public int getIsWax() {
        return isWax;
    }
}
