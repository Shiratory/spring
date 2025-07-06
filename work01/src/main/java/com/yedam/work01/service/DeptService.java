package com.yedam.work01.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
	// 전체조회 
	public List<DeptVO> findAllList();
	
	// 단건조회
	public DeptVO findInfoById(DeptVO deptVO);
	
	// 단건등록
	public int createInfo(DeptVO deptvo);
	
	// 단건수정
	public Map<String, Object> modifyInfo(DeptVO deptVO);

	// 단건삭제
	public Map<String, Object> removeInfo(int deptId);
}
