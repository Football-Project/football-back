package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Agent;
import com.example.entity.Player;
import com.example.entity.Team;
import com.example.jwt.JwtUtil;
import com.example.service.AgentService;
import com.example.service.PlayerService;
import com.example.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AdminController {
    
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    TeamService tService;

    @Autowired
    AgentService aService;

    @Autowired
    PlayerService pService;
    
    //팀 등록
    //127.0.0.1:8080/REST/admin/teaminsert
    @RequestMapping(value = "/admin/teaminsert", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> teaminsertPOST(@RequestBody Team[] team,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            for(int i =0; i < team.length; i++){
                tService.insertTeam(team[i]);
                map.put("status", 200);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //에이전트 등록
    //127.0.0.1:8080/REST/admin/agentinsert
    @RequestMapping(value = "/admin/agentinsert", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> agentinsertPOST(@RequestBody Agent[] agent,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            for(int i =0; i < agent.length; i++){
                aService.insertAgent(agent[i]);
                map.put("status", 200);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

    //선수 등록
    //127.0.0.1:8080/REST/admin/playerinsert
    @RequestMapping(value = "/admin/playerinsert", method = {RequestMethod.POST},
    consumes = MediaType.ALL_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> agentinsertPOST(
        @ModelAttribute Player player,
        @RequestParam(name = "file") MultipartFile file,
        @RequestHeader("token") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            player.setImage(file.getBytes());
            player.setImagename(file.getOriginalFilename());
            player.setImagetype(file.getContentType());
            pService.insertPlayer(player);
            map.put("status", 200);
        }
        catch(Exception e){
            e.printStackTrace();
            map.put("status", e.hashCode());
        }
        return map;
    }

}