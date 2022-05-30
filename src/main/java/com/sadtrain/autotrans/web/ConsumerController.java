package com.sadtrain.autotrans.web;

import com.sadtrain.autotrans.db.entity.Consumer;
import com.sadtrain.autotrans.db.entity.Listener;
import com.sadtrain.autotrans.db.mapper.BotMapper;
import com.sadtrain.autotrans.db.mapper.ConsumerMapper;
import com.sadtrain.autotrans.db.mapper.ListenerMapper;
import com.sadtrain.autotrans.mirai.bots.BotManager;
import com.sadtrain.autotrans.web.response.AddBotResponse;
import com.sadtrain.autotrans.web.response.BaseResponse;
import com.sadtrain.autotrans.web.response.ResponseBuilder;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);
    @Autowired
    private BotManager botManager;
    @Autowired
    private BotMapper botMapper;

    @Autowired
    private ListenerMapper listenerMapper;

    @Autowired
    private ConsumerMapper consumerMapper;

    public String addConsumer(){
        return "ok";
    }

    @GetMapping("/add")
    public BaseResponse<AddBotResponse> addBot(Consumer consumer){
        try {
            consumerMapper.insert(consumer);
            botManager.addConsumer(consumer, consumer.getListenerId());
            return ResponseBuilder.createSuccessResponse(new AddBotResponse());
        }catch (Exception e){
            logger.error("error",e);
            return ResponseBuilder.createErrorResponse(e.toString());
        }
    }

    @GetMapping("/remove")
    public BaseResponse<AddBotResponse> removeBot(Integer listener){
        try {
            Integer integer = botManager.removeConsumer(listener);

            consumerMapper.deleteById(integer);
            return ResponseBuilder.createSuccessResponse(new AddBotResponse());
        }catch (Exception e){
            logger.error("error",e);
            return ResponseBuilder.createErrorResponse(e.toString());
        }
    }

}
