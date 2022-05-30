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
@RequestMapping("/bot")
public class Controller {
    private static Logger logger = LoggerFactory.getLogger(Controller.class);
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
    public BaseResponse<AddBotResponse> addBot(AddBotRequest request){
        try {
            Bot bot = BotHelper.newBot(request.getBotNum(),request.getPassword());
            bot.login();
            try {
                if (request.getDirectLogin()) {
                    logger.info(bot.getId() + "started!");
                    botManager.addBot(bot);
                } else {
                    bot.close();
                }
                BotEntity botEntity = new BotEntity();
                botEntity.setBotNum(request.getBotNum());
                botEntity.setPassword(request.getPassword());
                int insert = botMapper.insert(botEntity);

                if (insert > 0) {
                    return ResponseBuilder.createSuccessResponse(new AddBotResponse());
                } else {
                    return ResponseBuilder.createErrorResponse("add bot failed");
                }
            }catch (Exception e){
                bot.close();
                throw new RuntimeException(e);
            }
        }catch (Exception e){
            logger.error("error",e);
            return ResponseBuilder.createErrorResponse(e.toString());
        }
    }

    @GetMapping("/login")
    public BaseResponse<AddBotResponse> loginBot(LoginBotRequest request){
        try {
            QueryWrapper<BotEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("bot_num",request.getBotId());
            BotEntity botEntity = botMapper.selectOne(queryWrapper);
            if(botEntity == null){
                return ResponseBuilder.createErrorResponse("bot not exists");
            }
            Bot bot = botManager.getBot(botEntity.getBotNum());
            if(bot != null){
                return ResponseBuilder.createSuccessResponse(new AddBotResponse());
            }else{
                bot = BotHelper.newBot(botEntity.getBotNum(), botEntity.getPassword());
                bot.login();
                botManager.addBot(bot);
            }
            return ResponseBuilder.createSuccessResponse(new AddBotResponse());
        }catch (Exception e){
            logger.error("error",e);
            return ResponseBuilder.createErrorResponse(e.toString());
        }
    }

    @GetMapping("/delete")
    public BaseResponse<AddBotResponse> deleteBot(LoginBotRequest request){
        try {
            QueryWrapper<BotEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("bot_num",request.getBotId());
            BotEntity botEntity = botMapper.selectOne(queryWrapper);

            Bot bot = botManager.getBot(botEntity.getBotNum());
            if(bot != null) {
                bot.close();
                //todo removeListeners
            }
            QueryWrapper<Listener> listenerQueryWrapper = new QueryWrapper<Listener>().eq("bot_num", botEntity.getBotNum());
            List<Listener> listeners = listenerMapper.selectList(listenerQueryWrapper);

            for (Listener listener : listeners) {
                botManager.removeListener(listener.getId());
            }
            if(listeners.size() > 0){
                listenerMapper.deleteBatchIds(listeners.stream().map(Listener::getId).collect(Collectors.toList()));
            }
            botMapper.deleteById(botEntity.getId());

            return ResponseBuilder.createSuccessResponse(new AddBotResponse());
        }catch (Exception e){
            logger.error("error",e);
            return ResponseBuilder.createErrorResponse(e.toString());
        }
    }

    public List<Listener> getAllListener(){
        List<Listener> listeners = listenerMapper.selectList(null);
        return listeners;
    }

    public List<Listener> getRunningListener(){

        List<Listener> listeners = listenerMapper.selectList(null);
        return listeners;
    }

    @GetMapping("/logout")
    public BaseResponse<AddBotResponse> logoutBot(LoginBotRequest request){
        try {
            QueryWrapper<BotEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("bot_num",request.getBotId());
            BotEntity botEntity = botMapper.selectOne(queryWrapper);

            Bot bot = botManager.getBot(botEntity.getBotNum());
            if (bot != null) {
                bot.close();
                botManager.removeBot(bot.getId());
                //todo removeListeners
            }
            QueryWrapper<Listener> listenerQueryWrapper = new QueryWrapper<Listener>().eq("bot_num", botEntity.getBotNum());
            List<Listener> listeners = listenerMapper.selectList(listenerQueryWrapper);
            for (Listener listener : listeners) {
                botManager.removeListener(listener.getId());
            }
            return ResponseBuilder.createSuccessResponse(new AddBotResponse());
        }catch (Exception e){
            logger.error("error",e);
            return ResponseBuilder.createErrorResponse(e.toString());
        }
    }

}
