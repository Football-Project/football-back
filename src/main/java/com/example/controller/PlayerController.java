package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Player;
import com.example.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    PlayerService pService;

    // 선수 1명 조회
    //127.0.0.1:8080/REST/playerone?no=
    @RequestMapping(value = "/playerone", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> playeroneGET(Player player,
    @RequestParam("no") long no) {
        Map<String, Object> map = new HashMap<>();
        try{
            Player player2 = pService.getPlayerOne(no);
            map.put("status", "200");
            map.put("player",player2 );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
    
}
