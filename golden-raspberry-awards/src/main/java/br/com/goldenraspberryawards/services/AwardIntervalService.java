package br.com.goldenraspberryawards.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.goldenraspberryawards.exceptions.EnumCategory;
import br.com.goldenraspberryawards.exceptions.ErrorAwardInterval;
import br.com.goldenraspberryawards.models.ProducerDTO;
import br.com.goldenraspberryawards.models.ProducerMaxMinDTO;
import br.com.goldenraspberryawards.repositories.ProducerRepository;
import br.com.goldenraspberryawards.responses.AwardIntervalResponse;
import br.com.goldenraspberryawards.responses.ProducerIntervalResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AwardIntervalService {

	@Autowired
	private ProducerRepository producerRepository;

	public AwardIntervalResponse getAwardInterval() throws ErrorAwardInterval {
		try {
			ProducerMaxMinDTO intervals = producerRepository.findProducersMaxMinInterval();
			List<ProducerDTO> max = producerRepository.findProducerInterval(intervals.getMaxIntvl());
			List<ProducerDTO> min = producerRepository.findProducerInterval(intervals.getMinIntvl());

			return new AwardIntervalResponse(convert(min), convert(max));
		} catch (ErrorAwardInterval e) {
			throw e;
		} catch (Exception e) {
			log.error("Failure to process intervals", e);
			throw new ErrorAwardInterval(EnumCategory.PROCESS, "Failure to convert intervals", e);
		}
	}

	private List<ProducerIntervalResponse> convert(List<ProducerDTO> list) throws ErrorAwardInterval {
		try {
			return list.stream().map(item -> {
				ProducerIntervalResponse response = new ProducerIntervalResponse();
				response.setProducer(item.getProducerName());
				response.setInterval(Integer.parseInt(item.getIntvl()));
				response.setPreviousWin(Integer.parseInt(item.getYear()));
				response.setFollowingWin(Integer.parseInt(item.getFollowYear()));
				return response;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Failure to convert intervals", e);
			throw new ErrorAwardInterval(EnumCategory.CONVERSION, "Failure to convert intervals", e);
		}
	}
}
