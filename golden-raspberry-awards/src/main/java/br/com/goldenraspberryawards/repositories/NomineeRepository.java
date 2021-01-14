package br.com.goldenraspberryawards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.goldenraspberryawards.models.Nominee;

public interface NomineeRepository extends JpaRepository<Nominee, Integer> {

}
