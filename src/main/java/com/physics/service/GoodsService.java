package com.physics.service;


import com.physics.pojo.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> queryGoods();

    void insert(Goods goods);

    Goods queryGoodsByGoodId(int goods_id);
}
