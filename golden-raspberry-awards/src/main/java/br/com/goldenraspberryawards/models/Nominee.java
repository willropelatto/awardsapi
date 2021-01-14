package br.com.goldenraspberryawards.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "nominees")
public class Nominee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int year;

	private String title;

	private boolean winner;

	@ManyToMany()
	@JoinTable(name = "producer_nominee", //
			joinColumns = @JoinColumn(name = "nominee_id", referencedColumnName = "id"), //
			inverseJoinColumns = @JoinColumn(name = "producer_id", referencedColumnName = "id"))
	private List<Producer> producers;

	@ManyToMany()
	@JoinTable(name = "studio_nominee", //
			joinColumns = @JoinColumn(name = "nominee_id", referencedColumnName = "id"), //
			inverseJoinColumns = @JoinColumn(name = "studio_id", referencedColumnName = "id"))
	private List<Studio> studios;

}
