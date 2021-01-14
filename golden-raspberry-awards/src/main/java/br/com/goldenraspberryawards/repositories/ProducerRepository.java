package br.com.goldenraspberryawards.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.goldenraspberryawards.models.Producer;
import br.com.goldenraspberryawards.models.ProducerDTO;
import br.com.goldenraspberryawards.models.ProducerMaxMinDTO;

public interface ProducerRepository extends JpaRepository<Producer, Integer> {

	static String SQL_INTERVAL = "SELECT follow_year followyear, (follow_year - year) intvl, producerid, producername, year " //
			+ "FROM (SELECT p.id producerid, p.name producername, n.year, " //
			+ "LEAD(year, 1, year) OVER (PARTITION BY p.id ORDER BY n.year) follow_year " //
			+ "FROM producers p " //
			+ "JOIN producer_nominee pn ON p.id = pn.producer_id " //
			+ "JOIN nominees n ON pn.nominee_id = n.id " //
			+ "WHERE n.winner) tab WHERE (follow_year - year) > 0";

	Optional<Producer> findByName(String name);

	@Query(value = "SELECT followyear, intvl, producerid, producername, year FROM (" + SQL_INTERVAL
			+ ") tab2 where intvl = :intvl", nativeQuery = true)
	List<ProducerDTO> findProducerInterval(@Param("intvl") Integer interval);

	@Query(value = "SELECT max(intvl) maxintvl, min(intvl) minintvl FROM (" + SQL_INTERVAL
			+ ") tab2", nativeQuery = true)
	ProducerMaxMinDTO findProducersMaxMinInterval();
}
