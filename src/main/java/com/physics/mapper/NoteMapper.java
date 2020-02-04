package com.physics.mapper;

import com.physics.pojo.Note;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteMapper {

    List<Note> queryAll();
}
