package com.example.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {
	private Long startTimeInUsecs;
	private Long endTimeInUsecs;
	private Integer intervalInSecs;
	private List<Integer> idxList;
}
