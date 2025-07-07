package com.yedam.app.aop.mapper;

import org.apache.ibatis.annotations.Insert;

public interface AopMapper {
	//mapper.xml을 만들지 않고 @Insert()사용하면 분리작업을 하지 않아도 된다.
	@Insert("INSERT INTO t_aop_test VALUES(#{value})")
	public int insert(String value);
}
