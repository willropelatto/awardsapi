package br.com.goldenraspberryawards.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldenraspberryawards.exceptions.ErrorAwardInterval;
import br.com.goldenraspberryawards.responses.AwardIntervalResponse;
import br.com.goldenraspberryawards.services.AwardIntervalService;

@RestController
public class AwardIntervalHandler {

	@Autowired
	private AwardIntervalService service;

	@GetMapping("/interval")
	public ResponseEntity<AwardIntervalResponse> getInterval() {
		ResponseEntity<AwardIntervalResponse> response = null;

		try {
			AwardIntervalResponse awards = service.getAwardInterval();

			response = new ResponseEntity<>(awards, HttpStatus.OK);
		} catch (ErrorAwardInterval e) {
			response = new ResponseEntity<>(new AwardIntervalResponse(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
