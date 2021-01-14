package br.com.goldenraspberryawards;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public interface MainEventListener {

	@EventListener(ApplicationReadyEvent.class)
	void onApplicationReadyEvent();

}