package br.com.goldenraspberryawards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.goldenraspberryawards.services.DataInformationService;

@Component
public class MainEventListenerImpl implements MainEventListener {

	@Autowired
	private DataInformationService service;

	@Value("${csv.filePath}")
	private String filePath;

	@Override
	public void onApplicationReadyEvent() {
		service.initialize(filePath);
	}
}
