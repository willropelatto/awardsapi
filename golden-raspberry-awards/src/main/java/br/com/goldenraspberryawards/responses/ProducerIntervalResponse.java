package br.com.goldenraspberryawards.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProducerIntervalResponse {

	private String producer;

	private int interval;

	private int previousWin;

	private int followingWin;
}
