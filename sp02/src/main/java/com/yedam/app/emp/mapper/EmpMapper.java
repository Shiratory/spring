package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	// Mapper 골격
	
	// 전체 조회
	public List<EmpVO> selectAll();
	//VO : 값을 지니고 있는 객체(원래라면 읽기전용, 지금은 관습적으로 읽기 전용이 아니라도 사용)
	//DTO : 데이터 전송을 할때 사용하는 객체
	
	//단건조회
	public EmpVO selectInfo(EmpVO empVO);
	
	//등록
	public int insertInfo(EmpVO empVO);
	
	//수정
	public int updateInfo(@Param("id") int eid,
												@Param("info") EmpVO empVO);
	//@Param의 이름은 xml에서 사용되는 변수 이름이 된다.
	
	//삭제
	public int deleteInfo(int employeeId);
	
}
