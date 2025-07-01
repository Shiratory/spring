package com.yedam.work01.mapper;

import java.util.List;

import com.yedam.work01.service.DeptVO;

public interface DeptMapper {

	// 전체조회
	public List<DeptVO> selectAll();
	
	// 단건조회
	public DeptVO selectInfo(DeptVO deptVO);
	
	// 등록
	public int insertInfo(DeptVO deptVO);
}
