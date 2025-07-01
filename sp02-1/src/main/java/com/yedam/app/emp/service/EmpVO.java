package com.yedam.app.emp.service;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // @Builder시 생략되므로 명시적으로 추가함
public class EmpVO {
	// employee_id => employeeId => setEmployeeId
	private Integer employeeId;
	private String lastName;
	private String email;
	private Date hireDate;
	private String jobId;
	private double salary;
}