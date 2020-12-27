package com.jufo.mappers;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
	
	public int selectBookCnt();

}
