package me.zonglun.seckilling.service;

import me.zonglun.seckilling.domain.OrderInfo;
import me.zonglun.seckilling.domain.SeckillUser;
import me.zonglun.seckilling.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	/**
	 * 返回订单的详情页面
	 * @param user
	 * @param goods
	 * @return
	 */
	@Transactional
	public OrderInfo miaosha(SeckillUser user, GoodsVo goods) {
		//减库存 下订单 写入秒杀订单
		goodsService.reduceStock(goods);
		return orderService.createOrder(user, goods);
	}
	
}
