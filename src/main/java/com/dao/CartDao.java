package com.dao;

import com.entity.CartEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CartView;

/**
 * 购物车 Dao 接口
 *
 * @author 
 */
public interface CartDao extends BaseMapper<CartEntity> {

   List<CartView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
