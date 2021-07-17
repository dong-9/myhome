package com.example.rest.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Element {
	private Long startTimeInUsecs;
	private Long endTimeInUsecs;
	private Integer intervalInSecs;
	private List<Integer> idxList;
	private Date date;
}
