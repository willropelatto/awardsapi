package br.com.goldenraspberryawards.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.goldenraspberryawards.models.Studio;

public interface StudioRepository extends JpaRepository<Studio, Integer> {

	Optional<Studio> findByName(String name);
}
