package com.bee.devops.admin.core.common.service.ops;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.enums.DeployStatusEnums;
import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsDepAppOrderDao;
import com.bee.devops.admin.core.common.dao.ops.OpsDepAppOrderInfoDao;
import com.bee.devops.admin.core.common.entity.ops.OpsDepAppOrder;
import com.bee.devops.admin.core.vo.response.DeployAppVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderDetailVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoResponse;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderVo;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class OpsDepAppOrderService {
	
	@Autowired
	OpsDepAppOrderDao opsDepAppOrderDao;
	@Autowired
	OpsDepAppOrderInfoDao opsDepAppOrderInfoDao;
	
    public long insertDepAppOrder(OpsDepAppOrder opsdepapporder) {
        return opsDepAppOrderDao.insertDepAppOrder(opsdepapporder);
    }
    
	public OpsDepAppOrderDetailVo getOrderInfos(Long orderId) {
		return opsDepAppOrderDao.queryByOrderId(orderId);
	}
	
	public PageBean<OpsDepAppOrderDetailVo> queryOrdersByPage(int pageOn, int pageSize, Long envId, Long proId, String title) {
		PageHelper.startPage(pageOn, pageSize);
		List<OpsDepAppOrderDetailVo> lists = opsDepAppOrderDao.queryOrders(proId,envId,title);
		return new PageBean<>(lists);
	}

	public List<OpsDepAppOrderVo> queryOrders(List<OpsDepAppOrderDetailVo> lists) {
		List<OpsDepAppOrderVo> opsdepapporderlist= new ArrayList<OpsDepAppOrderVo>();
		for(OpsDepAppOrderDetailVo odaod:lists){
			OpsDepAppOrderVo opsdepappordervo = new OpsDepAppOrderVo();
			opsdepappordervo.setOrderId(odaod.getOrderId());
			opsdepappordervo.setOperateUserName(odaod.getOperateUserName());
			opsdepappordervo.setCreateDate(odaod.getCreateDate());
			opsdepappordervo.setOrderStatus(odaod.getOrderStatus());
			opsdepappordervo.setOrderTitle(odaod.getOrderTitle());
			opsdepappordervo.setTotalNum(odaod.getDeployOrderInfos().size());
			int successNum=0;
			int errorNum=0;
			for(OpsDepAppOrderInfoResponse opsdepapporderinfo :odaod.getDeployOrderInfos()){
				if(opsdepapporderinfo.getDeployStatus() == DeployStatusEnums.DEPLOY_SUCCESS.getValue()){
					successNum = successNum + 1;
				}else if(opsdepapporderinfo.getDeployStatus() < 0){
					errorNum = errorNum + 1;
				}
			}
			opsdepappordervo.setSuccessNum(successNum);
			opsdepappordervo.setErrorNum(errorNum);
			opsdepapporderlist.add(opsdepappordervo);
		}
		return opsdepapporderlist;
	}
	
	public Integer deleteOrderById(Long orderId) {
		OpsDepAppOrder opsDepAppOrder = opsDepAppOrderDao.queryByOrderKey(orderId);
		if(opsDepAppOrder.getOrderStatus() != 0) {
			return 0;
		}else {
			opsDepAppOrderInfoDao.deleteOrderInfosByOrderId(orderId);
			opsDepAppOrderDao.deleteOrderById(orderId);
			return 1;
		}
	}

	public List<DeployAppVo> getAppDeployData(String time, Long proId, Long envId) {
		return opsDepAppOrderInfoDao.getAppDeployData(time, proId, envId);
	}

	public DeployAppVo getAppPublishStatus() {
		return opsDepAppOrderInfoDao.getAppPublishStatus();
	}
}
