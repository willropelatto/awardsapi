package br.com.goldenraspberryawards.models;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieInput {

	@CsvBindByName(column = "year")
	private Integer year;

	@CsvBindByName(column = "title")
	private String title;

	@CsvBindByName(column = "studios")
	private String studios;

	@CsvBindByName(column = "producers")
	private String producers;

	@CsvBindByName(column = "winner")
	private String winner;

}
