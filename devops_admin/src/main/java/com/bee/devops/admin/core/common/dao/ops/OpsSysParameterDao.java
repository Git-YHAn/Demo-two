package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsSysParameter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Roc created on 2018/8/2
 */
@Mapper
public interface OpsSysParameterDao {

    /**
     * 更新系统参数
     *
     * @param name  系统参数名称
     * @param value  系统参数内容
     * @return 更新成功数量
     */
    Integer saveOrUpdate(@Param("name") String name, @Param("value") String value);

    /**
     * 返回所有 系统参数
     *
     * @return 返回所有 系统参数
     */
    List<OpsSysParameter> queryAllOpsSysParameters();

    /**
     * 根据参数名称获取系统参数对象
     *
     * @param name 参数名称
     * @return 具体系统参数对象
     */
    OpsSysParameter queryOpsSysParameterByName(String name);
}
