package com.example.rest.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class T2 {
	private String a;
	private String b;
	private List<String> list;
}
