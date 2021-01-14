package br.com.goldenraspberryawards.services;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.bean.CsvToBeanBuilder;

import br.com.goldenraspberryawards.models.MovieInput;
import br.com.goldenraspberryawards.models.Nominee;
import br.com.goldenraspberryawards.models.Producer;
import br.com.goldenraspberryawards.models.Studio;
import br.com.goldenraspberryawards.repositories.NomineeRepository;
import br.com.goldenraspberryawards.repositories.ProducerRepository;
import br.com.goldenraspberryawards.repositories.StudioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataInformationService {

	@Autowired
	private NomineeRepository movieRepository;

	@Autowired
	private ProducerRepository producerRepository;

	@Autowired
	private StudioRepository studioRepository;

	@Transactional
	public void initialize(String fileName) {
		log.info("Begin initialization");
		InputStream input = getClass().getResourceAsStream(fileName);
		List<MovieInput> movies = new CsvToBeanBuilder<MovieInput>(new InputStreamReader(input))
				.withType(MovieInput.class).withSeparator(';').build().parse();

		log.info("Nominees count: " + movies.size());
		movies.forEach(movieLine -> {
			Nominee movie = new Nominee();
			movie.setTitle(movieLine.getTitle());
			movie.setWinner("yes".equalsIgnoreCase(movieLine.getWinner()));
			movie.setYear(movieLine.getYear());
			movie.setProducers(parseProducer(movieLine.getProducers(), movie));
			movie.setStudios(parseStudios(movieLine.getStudios(), movie));

			movieRepository.saveAndFlush(movie);
		});
		log.info("Finish initialization");
	}

	private List<Studio> parseStudios(String studios, Nominee movie) {
		List<String> list = Arrays.asList(studios.split(","));

		return list.stream().map(studioLine -> {
			return studioRepository.findByName(studioLine.trim()).orElseGet(() -> {
				Studio std = new Studio();
				std.setName(studioLine.trim());
				return studioRepository.saveAndFlush(std);
			});
		}).collect(Collectors.toList());
	}

	private List<Producer> parseProducer(String producers, Nominee movie) {
		List<String> list = Arrays.asList(producers.replace(" and ", ",").split(","));

		return list.stream().map(producerLine -> {
			return producerRepository.findByName(producerLine.trim()).orElseGet(() -> {
				Producer prod = new Producer();
				prod.setName(producerLine.trim());
				return producerRepository.saveAndFlush(prod);
			});
		}).collect(Collectors.toList());
	}

}
