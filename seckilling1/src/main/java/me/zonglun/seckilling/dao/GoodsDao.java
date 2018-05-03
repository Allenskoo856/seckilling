package me.zonglun.seckilling.dao;

import me.zonglun.seckilling.domain.MiaoshaGoods;
import me.zonglun.seckilling.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("SELECT g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price FROM miaosha_goods mg LEFT JOIN goods g ON mg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("SELECT g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price FROM miaosha_goods mg LEFT JOIN goods g ON mg.goods_id = g.id WHERE g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("UPDATE miaosha_goods SET stock_count = stock_count - 1 WHERE goods_id = #{goodsId} AND stock_count > 0")
    public int reduceStock(MiaoshaGoods g);

}
