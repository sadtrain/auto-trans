package com.sadtrain.autotrans.life;

import com.sadtrain.autotrans.mirai.bots.BotManager;
import com.sadtrain.autotrans.mirai.bots.MyBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class StartGlobalKeyboard implements CommandLineRunner {

	@Autowired
	private LifeCycleManager lifeCycleManager;

	@Autowired
	private BotManager botManager;


	@Override
	public void run(String... args) {
		lifeCycleManager.addService(botManager);
		lifeCycleManager.start();
	}
 
}
