package com.physics.mapper;

import com.physics.pojo.Code;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeMapper {
     List<Code> queryAll() ;
}
