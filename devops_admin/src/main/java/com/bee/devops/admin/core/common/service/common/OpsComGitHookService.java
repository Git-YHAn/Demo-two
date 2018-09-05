package com.bee.devops.admin.core.common.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsComGitHookDao;
import com.bee.devops.admin.core.common.entity.common.OpsComGitHook;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class OpsComGitHookService {
   @Autowired
   OpsComGitHookDao opsComGitHookDao;
   
   public int saveGitlabMessage(OpsComGitHook opsComGitHook) {
		return opsComGitHookDao.insertSelective(opsComGitHook);
   }
   
   public OpsComGitHook selectByMsgId(Long msgId) {
		return opsComGitHookDao.selectByPrimaryKey(msgId);
   }
   public int updateGitlabMessage(OpsComGitHook opsComGitHook) {
		return opsComGitHookDao.updateByPrimaryKeySelective(opsComGitHook);
   }
   
   public PageBean<OpsComGitHook> queryByPageData(int pageOn, int pageSize, Long envId, Long proId, Long appId){
		PageHelper.startPage(pageOn, pageSize);
		List<OpsComGitHook> lists = opsComGitHookDao.queryByPageData(envId,appId,proId);
		return new PageBean<>(lists);
   }
}
