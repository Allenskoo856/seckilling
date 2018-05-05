package me.zonglun.seckilling.service;

import me.zonglun.seckilling.domain.MiaoshaOrder;
import me.zonglun.seckilling.redis.MiaoshaKey;
import me.zonglun.seckilling.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.zonglun.seckilling.domain.MiaoshaUser;
import me.zonglun.seckilling.domain.OrderInfo;
import me.zonglun.seckilling.vo.GoodsVo;

/**
 * @author: xcallen
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        // 减库存 下订单 写入秒杀订单
        boolean success = goodsService.reduceStock(goods);
        if (success) {
            return orderService.createOrder(user, goods);
        } else {
            setGoodsOver(goods.getId());
            return null;
        }
    }


    /**
     * 得到秒杀结果---
     * -1 秒杀失败
     *  0 没有买完--继续轮询查询
     * @param userId
     * @param goodsId
     * @return
     */
    public long getMiaoshaResult(Long userId, long goodsId) {
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        // 秒杀成功
        if (order != null) {
            return order.getOrderId();
        } else {
            // 是否是商品卖完了
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 商品是否已经买完
     * @param goodsId
     */
    private void setGoodsOver(Long goodsId) {
        redisService.set(MiaoshaKey.isGoodsOver, "" + goodsId, true);
    }

    /**
     *  判断商品是否存在
     * @param goodsId
     * @return 商品是否存在
     */
    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(MiaoshaKey.isGoodsOver, "" + goodsId);
    }

}
