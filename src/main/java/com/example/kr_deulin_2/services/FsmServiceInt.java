package com.example.kr_deulin_2.services;


import com.example.kr_deulin_2.model.Services;
import com.example.kr_deulin_2.model.Fsm;

import java.util.List;

public interface FsmServiceInt {

    /*
       Включение автомойки
    */
    void turnOn() throws RuntimeException;

    /*
      Выключение автомойки
   */
    void turnOff() throws RuntimeException;

    /*
       Запуск режима только вода (1)
    */
    void OnlyWater() throws RuntimeException;

    /*
        Запуск режима вода-пена-вода (2)
    */
    void waterAndFoam() throws RuntimeException;

    /*
        Запуск режима вода-воск-вода(3)
    */
    void waterAndWax() throws RuntimeException;

    /*
       Запуск режима вода-пена-вода-сушка(4)
    */
    void waterFoamDrying() throws RuntimeException;

    /*
       Запуск режим Вода-пена-вода-воск-вода(5)
    */
    void waterFoamWax() throws RuntimeException;
    /*
        Получение автомойки
     */
    Fsm getFsm();

    List<Services> getFsmService();

}
