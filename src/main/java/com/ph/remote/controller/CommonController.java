package com.ph.remote.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ph.remote.dto.CommonReturn;
import com.ph.remote.entity.Clan;
import com.ph.remote.entity.Players;
import com.ph.remote.service.ClanService;
import com.ph.remote.service.PlayerService;
import com.ph.remote.util.DataApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class CommonController {

    @Autowired
    DataApi dataApi;

    @Autowired
    PlayerService playerService;

    @Autowired
    ClanService clanService;

    //test
    @RequestMapping("/")
    public String hello()  {


        return "hello";
    }



    //获取某个玩家的详细信息
    @RequestMapping("/player/{tag}")
    public CommonReturn getPlayers(@PathVariable String tag) throws JsonProcessingException {
        CommonReturn commonReturn = new CommonReturn();
        Players players = playerService.findByTag(tag);
        if(players.getTag() != null)
            commonReturn.success(players);
        else{
            commonReturn.fail("没有该玩家:"+tag);
        }
        return commonReturn;
    }

    @RequestMapping("/clans/{tag}")
    public CommonReturn getClans(@PathVariable String tag) {
        CommonReturn commonReturn = new CommonReturn();
        Clan clan = clanService.findByTag(tag);
        if(clan.getTag() == null)
            commonReturn.fail("没有该部落");
        else{
            commonReturn.success(clan);
        }

        return commonReturn;
    }

    //某个部落所有玩家的信息#Y0LQPQU99
    @RequestMapping("/clans/players/{clantag}")
    public CommonReturn getPlayersByActive(@PathVariable String clantag){
        CommonReturn commonReturn = new CommonReturn();
        clantag.replace("#","\\%23");
        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  "+clantag);
        //List<Players> playersList = playerService.findByClanTagIgnoreSome(clantag, Collections.singleton("clanTag"));
        //List<Players> playersList = playerService.findAll();
        List<Players> playersList = playerService.findByClanTagIgnoreSome("%23GVR820LU", Collections.singleton("clanTag"));
        //if(playersList == null) {
            if(playersList.size()==0) {
                {
                    commonReturn.fail("没有该部落 "+clantag);
                }
        }
        else{
            commonReturn.success(playersList);
        }
        return commonReturn;
    }
    //某个部落所有玩家的信息#Y0LQPQU99
    @RequestMapping("/clans/players/donatedesc/{clantag}")
    public CommonReturn getPlayersByDonated(@PathVariable String clantag){
        CommonReturn commonReturn = new CommonReturn();
        List<Players> playersList = playerService.findByClanTagOrderByDonationsDesc("%23GVR820LU");

        if(playersList.size()==0) {
            {
                commonReturn.fail("没有该部落 "+clantag);
            }
        }
        else{

            commonReturn.success(playersList);
        }
        return commonReturn;
    }
}
