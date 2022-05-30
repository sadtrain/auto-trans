package com.sadtrain.autotrans.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sadtrain.autotrans.db.entity.BotEntity;
import com.sadtrain.autotrans.db.entity.Listener;
import com.sadtrain.autotrans.db.mapper.BotMapper;
import com.sadtrain.autotrans.db.mapper.ListenerMapper;
import com.sadtrain.autotrans.mirai.bots.BotHelper;
import com.sadtrain.autotrans.mirai.bots.BotManager;
import com.sadtrain.autotrans.web.request.AddBotRequest;
import com.sadtrain.autotrans.web.request.LoginBotRequest;
import com.sadtrain.autotrans.web.response.AddBotResponse;
import com.sadtrain.autotrans.web.response.BaseResponse;
import com.sadtrain.autotrans.web.response.ResponseBuilder;
import io.swagger.annotations.Api;
import net.mamoe.mirai.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/listener")
public class ListenerController {
    private static Logger logger = LoggerFactory.getLogger(ListenerController.class);
    @Autowired
    private BotManager botManager;
    @Autowired
    private BotMapper botMapper;

    @Autowired
    private ListenerMapper listenerMapper;

    public String addConsumer(){
        return "ok";
    }

    @GetMapping("/add")
    public BaseResponse<AddBotResponse> addBot(Listener listener){
        try {
            listenerMapper.insert(listener);
            botManager.addListener(listener);
            return ResponseBuilder.createSuccessResponse(new AddBotResponse());
        }catch (Exception e){
            logger.error("error",e);
            return ResponseBuilder.createErrorResponse(e.toString());
        }
    }

    @GetMapping("/remove")
    public BaseResponse<AddBotResponse> removeBot(Integer listener){
        try {
            botManager.removeListener(listener);
            listenerMapper.deleteById(listener);
            return ResponseBuilder.createSuccessResponse(new AddBotResponse());
        }catch (Exception e){
            logger.error("error",e);
            return ResponseBuilder.createErrorResponse(e.toString());
        }
    }

}
