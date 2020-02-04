package com.physics.service.impl;

import com.physics.mapper.CodeMapper;
import com.physics.pojo.Code;
import com.physics.service.CodeService;
import com.physics.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    CodeMapper codeMapper;
    @Transactional(isolation = READ_UNCOMMITTED)
    @Override
    public List<Code> queryAll() {
        List<Code> codes;
        codes= redisUtil.getList("codes".getBytes(),0,-1);
        if(codes.size() == 0){
            codes = codeMapper.queryAll();
            if(codes.size() != 0){
                redisUtil.setList("codes".getBytes(),codes);
            }
        }
        return codes.size() == 0 ? new ArrayList<>() : codes;
    }
}
