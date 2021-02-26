package com.wooyoo.learning.dao.mapper;

import com.mysql.fabric.xmlrpc.base.Param;
import com.wooyoo.learning.dao.domain.Product;
import org.apache.catalina.mapper.Mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {
    Product select(
            @Param("id")
                    long id);

    void update(Product product);
}
