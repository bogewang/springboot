package com.bogewang.mapper;

import com.bogewang.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bogewang on 2017/6/1.
 */
@Mapper
public interface ProductMapper {
    Product select(@Param("id") long id);
    void update(Product product);
}
