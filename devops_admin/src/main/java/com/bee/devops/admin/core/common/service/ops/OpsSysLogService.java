package com.bee.devops.admin.core.common.service.ops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsSysLogDao;
import com.bee.devops.admin.core.common.entity.ops.OpsSysLog;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class OpsSysLogService {
	@Autowired
	OpsSysLogDao opsLogDao;

	public int insert(OpsSysLog log) {
		// 通过类型名获取日志类型ID
		Long typeId = opsLogDao.selectLogTypeByName(log.getTypeName());
		if (typeId == null) {
			// 若日志类型中不存在，则记录该类型，并回调主键ID
			opsLogDao.insertLogType(log);
		} else if(opsLogDao.selectLogTypeById(typeId).getIsEnable() == 0){
			// 如果类型存在，并且开启日志，则将日志类型ID存入日志记录中
			log.setTypeId(typeId);
		} else {
			// 未开启日志，返回0
			return 0;
		}
		// 存入日志记录，并返回成功数
		return opsLogDao.insertLogRecord(log);
	}
	
	public List<OpsSysLog> selectLogType() {
		return opsLogDao.selectLogType();
	}
	
	public int updateSysLogTypeIsEnable(Integer status, Long typeId) {
		return opsLogDao.updateSysLogTypeIsEnable(status, typeId);
	}
	
	public PageBean<OpsSysLog> selectSysLogRecord(int pageNum, int pageSize, String searchName, Long typeId, String startTime, String endTime){
		PageHelper.startPage(pageNum, pageSize);
		List<OpsSysLog> lists = opsLogDao.selectSysLogRecord(searchName, typeId, startTime, endTime);
		return new PageBean<>(lists);
	}
}
