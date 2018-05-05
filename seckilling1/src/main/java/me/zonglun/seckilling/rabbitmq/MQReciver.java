package me.zonglun.seckilling.rabbitmq;

import me.zonglun.seckilling.domain.MiaoshaOrder;
import me.zonglun.seckilling.domain.MiaoshaUser;
import me.zonglun.seckilling.redis.RedisService;
import me.zonglun.seckilling.service.GoodsService;
import me.zonglun.seckilling.service.MiaoshaService;
import me.zonglun.seckilling.service.OrderService;
import me.zonglun.seckilling.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Administrator
 * @create 2018-05-04 19:35
 */
@Service
public class MQReciver {

    private static Logger log = LoggerFactory.getLogger(MQReciver.class);

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RabbitListener(queues = MQconfig.QUEUE)
    public void receive(String message) {
        log.info("receive message:" + message);
        MiaoshaMessage mm = RedisService.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user = mm.getUser();
        long goodsId = mm.getGoodsId();

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, goods);
    }
}
