package com.physics.mapper;

import com.physics.pojo.GoodsReply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsReplyMapper {
    List<GoodsReply> queryGoodsReplyById(int goods_id);

    void insert(GoodsReply goodsReply);
}
