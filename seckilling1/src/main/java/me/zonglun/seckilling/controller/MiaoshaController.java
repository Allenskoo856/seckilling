package me.zonglun.seckilling.controller;

import me.zonglun.seckilling.domain.MiaoshaUser;
import me.zonglun.seckilling.rabbitmq.MQSender;
import me.zonglun.seckilling.rabbitmq.MiaoshaMessage;
import me.zonglun.seckilling.redis.GoodsKey;
import me.zonglun.seckilling.redis.RedisService;
import me.zonglun.seckilling.result.CodeMsg;
import me.zonglun.seckilling.result.Result;
import me.zonglun.seckilling.service.GoodsService;
import me.zonglun.seckilling.service.MiaoshaService;
import me.zonglun.seckilling.service.MiaoshaUserService;
import me.zonglun.seckilling.service.OrderService;
import me.zonglun.seckilling.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author :xcallen
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean{

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MiaoshaService miaoshaService;

	@Autowired
	MQSender mqSender;

	/**
	 *
	 * @param model
	 * @param user
	 * @param goodsId
	 * @return
	 */
    @RequestMapping(value="/do_miaosha", method=RequestMethod.POST)
    @ResponseBody
    public Result<Integer> miaosha(Model model, MiaoshaUser user,
								   @RequestParam("goodsId")long goodsId) {
    	model.addAttribute("user", user);
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
		// 预减库存---从缓存中取到库存
		long stock = redisService.decr(GoodsKey.getMiaoShaGoodsStock, "" + goodsId);
		if (stock < 0) {
			return Result.error(CodeMsg.REPEATE_MIAOSHA);
		}
		// 入队
		MiaoshaMessage mm = new MiaoshaMessage();
		mm.setUser(user);
		mm.setGoodsId(goodsId);
		mqSender.sendMiaoshaMessage(mm);
		// 排队中
		return Result.success(0);
    }

	/**
	 * orderId：成功
	 * -1：秒杀失败
	 * 0： 排队中
	 * */
	@RequestMapping(value="/result", method=RequestMethod.GET)
	@ResponseBody
	public Result<Long> miaoshaResult(Model model,MiaoshaUser user,
									  @RequestParam("goodsId")long goodsId) {
		model.addAttribute("user", user);
		if(user == null) {
			return Result.error(CodeMsg.SESSION_ERROR);
		}
		long result  =miaoshaService.getMiaoshaResult(user.getId(), goodsId);
		return Result.success(result);
	}

	/**
	 * 系统初始化--启动的时候将库存数量放入redis之中
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
		if (goodsVoList == null) {
			return;
		}
		for (GoodsVo goods : goodsVoList) {
			redisService.set(GoodsKey.getMiaoShaGoodsStock, "" + goods.getId(), goods.getStockCount());
		}
	}
}
