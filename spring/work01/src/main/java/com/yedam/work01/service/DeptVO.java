package com.yedam.work01.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DeptVO {

	private Integer departmentId;
	private String departmentName;
	private Integer managerId;
	private Integer locationId;
}
