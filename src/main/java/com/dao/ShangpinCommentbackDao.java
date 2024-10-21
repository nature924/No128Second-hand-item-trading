package com.dao;

import com.entity.ShangpinCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShangpinCommentbackView;

/**
 * 商品评价 Dao 接口
 *
 * @author 
 */
public interface ShangpinCommentbackDao extends BaseMapper<ShangpinCommentbackEntity> {

   List<ShangpinCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
