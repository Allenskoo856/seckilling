package me.zonglun.seckilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.zonglun.seckilling.domain.MiaoshaUser;
import me.zonglun.seckilling.domain.OrderInfo;
import me.zonglun.seckilling.vo.GoodsVo;

@Service
public class MiaoshaService {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	@Transactional
	public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
		//减库存 下订单 写入秒杀订单
		goodsService.reduceStock(goods);
		//order_info maiosha_order
		return orderService.createOrder(user, goods);
	}

	// todo
    public long getMiaoshaResult(Long id, long goodsId) {
		long l1 = 0;
		return l1;
    }

}
