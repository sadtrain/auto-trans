package com.sadtrain.autotrans.life;

import com.sadtrain.autotrans.mirai.bots.MyBot;
import com.sadtrain.autotrans.mirai.listener.LJLGroupListener;
import com.sadtrain.autotrans.mirai.listener.MyGroupListener;
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
	private MyBot myBots;

	@Autowired
	private LJLGroupListener ljlGroupListener;

	@Autowired
	private MyGroupListener myGroupListener;


	@Override
	public void run(String... args) {
		lifeCycleManager.addService(myBots);
		lifeCycleManager.addService(ljlGroupListener);
		lifeCycleManager.addService(myGroupListener);
		lifeCycleManager.start();
	}
 
}
