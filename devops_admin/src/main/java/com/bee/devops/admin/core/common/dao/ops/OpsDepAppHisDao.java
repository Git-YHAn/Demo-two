package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsDepApp;
import com.bee.devops.admin.core.vo.response.HistoryVersionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OpsDepAppHisDao {

    void save(OpsDepApp opsDepApp);

    List<HistoryVersionVo> queryPublishAppHistory(@Param("envId") Long envId, @Param("proId") Long proId, @Param("appName") String appName, @Param("publishStatus") Integer publishStatus);
}
