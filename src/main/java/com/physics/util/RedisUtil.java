package com.physics.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RedisUtil {
    @Autowired
    RedisTemplate redisTemplate = null;
    public long setList(byte[] key, List<?> value) {
        Object result = redisTemplate.execute((conn) -> {
            long size = 0;
            for (Object val : value) {
                // 迭代list的每一个元素， push到key对应的list
                size = conn.rPush(key, SerializeUtil.serialize(val));
            }
            return size;
        } , false);
        return (long) result;
    }
//    Redis Lrange 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。
//    其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。
//    你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
    public <T> List<T> getList(byte[] key, int page, int size) {
        Object result = redisTemplate.execute((conn) -> {
            // 先查询list里面的总长度
            Long len = conn.lLen(key);
            // 得到偏移量
            int offeset = getOffeset(page, size);
            // 如果偏移量已经大于总长度， 则直接返回null
            if (offeset > len) {
                return null;
            }
            // 得到集合里面对应位置的数据
            List<byte[]> list =  conn.lRange(key, offeset, size);
            List<T> listOs =new ArrayList<>();
            // 将byte数组返序列化成对象
            for (byte[] bs : list) {
                listOs.add((T) SerializeUtil.unserialize(bs));
            }
            return listOs;
        } , false);
        return result == null ? new ArrayList<>() : (List<T>) result;
    }

    /**
     * 获取偏移量
     **/
    private int getOffeset(int page, int size) {
        return page == 0 ? 0 : page * size;
    }
}
