package com.physics.mapper;

import com.physics.pojo.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    List<Goods> queryGoods();

    void insert(Goods goods);

    Goods queryGoodsByGoodId(int goods_id);
}
