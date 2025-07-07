package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor //Builder패턴을 사용하면 @Data를 사용하고 있어도 기본생성자는 없어진다.
public class EmpVO {
	private Integer employeeId; //not null일때 object로 전달하는 편이 좋다.
  private String lastName;
  private String email;
  @DateTimeFormat(pattern = "yyyy-MM-dd") //날짜포멧을 변경할 수 있다.
  //@See java.text.SimpleDateFormat 를 사용한다.
  private Date hireDate;
  private String jobId;
  private Double salary;
}
