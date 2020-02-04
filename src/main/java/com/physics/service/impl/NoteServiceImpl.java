package com.physics.service.impl;

import com.physics.mapper.NoteMapper;
import com.physics.pojo.Lesson;
import com.physics.pojo.Note;
import com.physics.service.NoteService;
import com.physics.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    NoteMapper noteMapper;
    @Transactional(isolation = READ_UNCOMMITTED)
    @Override
    public List<Note> queryAll() {
        List<Note> notes;
        notes= redisUtil.getList("notes".getBytes(),0,-1);
        if(notes.size() == 0){
            notes = noteMapper.queryAll();
            if(notes.size() != 0){
                redisUtil.setList("notes".getBytes(),notes);
            }
        }
        return notes.size() == 0 ? new ArrayList<>() : notes;
    }
}
