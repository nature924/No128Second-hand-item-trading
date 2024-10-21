package com.dao;

import com.entity.ShangpinChatEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShangpinChatView;

/**
 * 用户咨询 Dao 接口
 *
 * @author 
 */
public interface ShangpinChatDao extends BaseMapper<ShangpinChatEntity> {

   List<ShangpinChatView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
