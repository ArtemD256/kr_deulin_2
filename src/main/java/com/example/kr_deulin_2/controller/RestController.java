package com.example.kr_deulin_2.controller;


import com.example.kr_deulin_2.model.Services;
import com.example.kr_deulin_2.model.Fsm;
import com.example.kr_deulin_2.services.FsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api")
public class RestController {

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
    private final FsmService fsmService;

    public RestController(@Autowired FsmService fsmService) {
        this.fsmService = fsmService;
    }

    @GetMapping("/fsm")
    public ResponseEntity<Fsm> getFsm(){
        return ResponseEntity.ok(fsmService.getFsm());
    }

    @PostMapping("/turnOn")
    public ResponseEntity<String> turnOn(){
        fsmService.turnOn();
        return ResponseEntity.ok("Автомойка запущена!");
    }

    @PostMapping("/turnOff")
    public ResponseEntity<String> turnOff(){
        fsmService.turnOff();
        return ResponseEntity.ok("Автомойка отключена!");
    }

    @PostMapping("/onlyWater")
    public ResponseEntity<String> onlyWater(){
        fsmService.OnlyWater();
        return ResponseEntity.ok("Режим onlyWater выполнен");
    }

    @PostMapping("/waterAndFoam")
    public ResponseEntity<String> waterAndFoam(){
        fsmService.waterAndFoam();
        return ResponseEntity.ok("Режим waterAndFoam выполнен");
    }

    @PostMapping("/waterAndWax")
    public ResponseEntity<String> waterAndWax(){
        fsmService.waterAndWax();
        return ResponseEntity.ok("Режим waterAndWax выполнен");
    }

    @PostMapping("/waterFoamDrying")
    public ResponseEntity<String> waterFoamDrying(){
        fsmService.waterFoamDrying();
        return ResponseEntity.ok("Режим waterFoamDrying выполнен");
    }

    @PostMapping("/waterFoamWax")
    public ResponseEntity<String> waterFoamWax(){
        fsmService.waterFoamWax();
        return ResponseEntity.ok("Режим waterFoamWax выполнен");
    }

    @GetMapping("/fsmService")
    public ResponseEntity<List<Services>> getFsmService(){
        return ResponseEntity.ok(fsmService.getFsmService());
    }
    @PostMapping("/added")
    public ResponseEntity<?> added(){
        fsmService.add();
        return ResponseEntity.ok("5 литров всего залито!");
    }
}
