package br.com.goldenraspberryawards.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AwardIntervalResponse {

	private List<ProducerIntervalResponse> min;

	private List<ProducerIntervalResponse> max;
}
