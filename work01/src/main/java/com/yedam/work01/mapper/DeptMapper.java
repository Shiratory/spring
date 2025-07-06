package com.yedam.work01.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yedam.work01.service.DeptVO;

@Mapper
public interface DeptMapper {

	// 전체조회
	public List<DeptVO> selectAll();
	
	// 단건조회
	public DeptVO selectInfo(DeptVO deptVO);
	
	// 등록
	public int insertInfo(DeptVO deptVO);
	
	// 수정
	public int updateInfo(@Param("id") int deptid,
						  @Param("info")DeptVO deptVO);
	
	// 삭제
	public int deleteInfo(int departmentId);
}

