package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.Player;
import com.example.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    // 선수 전체 조회
    //127.0.0.1:8080/REST/playerall
    @RequestMapping(value = "/playerall", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> playerallGET(Player player ) {
        Map<String, Object> map = new HashMap<>();
        try{
            List<Player> playerAll = pService.getPlayerAll();
            map.put("status", "200");
            map.put("player",playerAll );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
    
    //팀 번호 별 선수 조회
    // 127.0.0.1:8080/REST/bnoplayer?page=1&bno=
    @RequestMapping(value = "/bnoplayer", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> BnoplayerGET(Player player,
    @RequestParam(value = "page", defaultValue = "1")int page,
    @RequestParam(name = "bno")long bno) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 16);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Player> Bnoplayer = pService.getPlayerByTeamno(bno, pageable);
            map.put("status", "200");
            map.put("player",Bnoplayer );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //에이전트 번호 별 선수 조회
    // 127.0.0.1:8080/REST/bnoplayer?page=1&bno=
    @RequestMapping(value = "/anoplayer", method = {RequestMethod.GET},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> AnoplayerGET(Player player,
    @RequestParam(value = "page", defaultValue = "1")int page,
    @RequestParam(name = "ano")long ano) {
        //페이지 네이션 처리
        PageRequest pageable = PageRequest.of(page-1, 16);
        Map<String, Object> map = new HashMap<>();
        try{
            List<Player> Bnoplayer = pService.getPlayerByAgentno(ano, pageable);
            map.put("status", "200");
            map.put("player",Bnoplayer );
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }
    
}