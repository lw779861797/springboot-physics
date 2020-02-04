package com.physics.service.impl;

import com.physics.mapper.GoodsMapper;
import com.physics.pojo.Goods;
import com.physics.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Transactional(isolation = READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Goods> queryGoods() {
        return goodsMapper.queryGoods();
    }

    @Transactional(isolation = READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(Goods goods) {
        goodsMapper.insert(goods);
    }
    @Transactional(isolation = READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public Goods queryGoodsByGoodId(int goods_id) {
        return goodsMapper.queryGoodsByGoodId(goods_id);
    }
}
