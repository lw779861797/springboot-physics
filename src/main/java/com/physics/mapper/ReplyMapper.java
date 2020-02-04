package com.physics.mapper;

import com.physics.pojo.Reply;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyMapper {
    Reply queryReplyById(int anser_id);
}
