package me.zonglun.seckilling.service;

import java.util.List;

import me.zonglun.seckilling.dao.GoodsDao;
import me.zonglun.seckilling.domain.MiaoshaGoods;
import me.zonglun.seckilling.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GoodsService {
	
	@Autowired
	GoodsDao goodsDao;

    /**
     * 得到列表商品的所有的信息
     * @return
     */
	public List<GoodsVo> listGoodsVo(){
		return goodsDao.listGoodsVo();
	}

	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

    /**
     * 减少库存数量
     * @param goods
     */
	public void reduceStock(GoodsVo goods) {
		MiaoshaGoods g = new MiaoshaGoods();
		g.setGoodsId(goods.getId());
		goodsDao.reduceStock(g);
	}
}
