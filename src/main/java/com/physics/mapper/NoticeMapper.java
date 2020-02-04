package com.physics.mapper;

import com.physics.pojo.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {
    List<Notice> queryByAll();
}
