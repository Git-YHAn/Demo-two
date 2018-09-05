package com.bee.devops.admin.core.controller.ops.deploy;

import java.util.List;

import com.bee.devops.admin.core.asynctask.AsyncPublishTask;
import com.bee.devops.admin.core.vo.response.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsDepAppOrder;
import com.bee.devops.admin.core.common.entity.ops.OpsDepAppOrderInfo;
import com.bee.devops.admin.core.common.service.ops.OpsBaseServerService;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppOrderInfosService;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppOrderService;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppService;
import com.bee.devops.admin.core.controller.ops.base.OpsBaseAppController;
import com.bee.devops.admin.core.vo.request.OpsDepAppOrderInfoRequest;
import com.bee.devops.admin.core.vo.request.OpsDepAppOrderInfosRequest;
import com.bee.devops.admin.core.vo.request.OpsDepAppOrderRequest;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/order/publish")
public class OpsDepAppOrderController extends BaseController {
	final static Logger log = Logger.getLogger(OpsBaseAppController.class);

    @Autowired
    OpsDepAppOrderService opsDepAppOrderService;
    @Autowired
    OpsDepAppOrderInfosService opsDepAppOrderInfosService;
    @Autowired
    OpsDepAppService opsDepAppService;
    @Autowired
    OpsBaseServerService opsBaseServerService;
    @Autowired
    AsyncPublishTask asyncPublishTask;

    @ApiOperation(value = "根据工单ID查询工单详细信息", notes = "根据工单ID查询工单详细信息")
    @RequestMapping(value = "/search/{orderId}", method = RequestMethod.GET)
    public ResultHandler<OpsDepAppOrderDetailVo> getOrderInfos(@PathVariable Long orderId) {
        OpsDepAppOrderDetailVo opsDepAppOrderDetail = opsDepAppOrderService.getOrderInfos(orderId);
        return ResultHandler.success(opsDepAppOrderDetail);
    }

	@ApiOperation(value = "根据工单详细信息ID查询工单详细信息", notes = "根据工单详细信息ID查询工单详细信息")
	@RequestMapping(value = "/search/orderinfo/{orderInfoId}", method = RequestMethod.GET)
	public ResultHandler<OpsDepAppOrderInfoResponse> getOrderInfo(@PathVariable Long orderInfoId) {
		OpsDepAppOrderInfoResponse opsDepAppOrderInfo = opsDepAppOrderInfosService.getOrderInfo(orderInfoId);
		return ResultHandler.success(opsDepAppOrderInfo);
	}
	
	@ApiOperation(value = "根据工单详细信息IDS查询工单详细信息", notes = "根据工单详细信息IDS查询工单详细信息")
	@RequestMapping(value = "/search/orderinfos", method = RequestMethod.POST)
	public ResultHandler<List<OpsDepAppOrderInfoResponse>> queryOrderInfos(@RequestBody OpsDepAppOrderInfosRequest orderinfosrequest) {
		List<OpsDepAppOrderInfoResponse> list = opsDepAppOrderInfosService.queryOrderInfos(orderinfosrequest.getOrderInfosId());
		return ResultHandler.success(list);
	}
	
	@ApiOperation(value = "根据项目ID和环境ID查询工单列表", notes = "根据项目ID和环境ID查询工单列表")
	@RequestMapping(value = { "/search/orders/{pageNum}/{pageSize}/{proId}/{envId}/{title}",
    "/search/orders/{pageNum}/{pageSize}/{proId}/{envId}" }, method = RequestMethod.GET)
	public ResultHandler<PageBean<OpsDepAppOrderVo>> queryOrders(@PathVariable Integer pageNum, @PathVariable Integer pageSize,
	        @PathVariable Long proId, @PathVariable Long envId, @PathVariable(required = false) String title) {
		PageBean<OpsDepAppOrderDetailVo> pageBean = opsDepAppOrderService.queryOrdersByPage(pageNum, pageSize, envId, proId, title);
		List<OpsDepAppOrderVo> list = opsDepAppOrderService.queryOrders(pageBean.getResults());
		PageBean<OpsDepAppOrderVo> data = new PageBean<>(pageBean.getMeta(), list);
		return ResultHandler.success(data);
	}

	@ApiOperation(value = "制定发布工单", notes = "制定发布工单")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResultHandler<Long> insertOrder(@RequestBody OpsDepAppOrderRequest depapporderrequest) {
		OpsDepAppOrder opsdepapporder = depapporderrequest.transEntity();		
		opsdepapporder.setOperateUserId(getAdminUser().getAdminUserId());
	    opsDepAppOrderService.insertDepAppOrder(opsdepapporder);
		List<OpsDepAppOrderInfoRequest> orderinfolist = depapporderrequest.getDeployOrderInfos();
		for(OpsDepAppOrderInfoRequest orderinfo : orderinfolist){
			OpsDepAppOrderInfo opsdepapporderinfo = orderinfo.transEntity();
			opsdepapporderinfo.setOrderId(opsdepapporder.getOrderId());
			opsDepAppOrderInfosService.insertDepAppOrderInfo(opsdepapporderinfo);
		}
		return ResultHandler.success(opsdepapporder.getOrderId());
	}
	
	@ApiOperation(value = "根据项目ID和环境ID查询所有应用", notes = "根据项目ID和环境ID查询所有应用")
	@RequestMapping(value = "/search/apps/{proId}/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsDepAppResponse>> queryPublishApps(@PathVariable Long proId, @PathVariable Long envId) {
		List<OpsDepAppResponse> apps = opsDepAppService.queryPublishApps(proId, envId);
		return ResultHandler.success(apps);
	}

	@ApiOperation(value = "发布工单中自动发布功能", notes = "发布工单中自动发布功能")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultHandler<String> saveOrderApp(@RequestBody OpsDepAppOrderRequest request) {
		String header = getRequest().getHeader("referer");
		asyncPublishTask.multiOrderDeployApp(request, header);
		return ResultHandler.success("success");
	}

	@ApiOperation(value = "发布工单中部署代码功能", notes = "发布工单中部署代码功能")
	@RequestMapping(value = "/deploy/code", method = RequestMethod.POST)
	public ResultHandler<String> deployCode(@RequestBody OpsDepAppOrderRequest request) {
		String header = getRequest().getHeader("referer");
		asyncPublishTask.orderDeployCode(request, header);
		return ResultHandler.success("success");
	}

    @ApiOperation(value = "发布工单中重启服务功能", notes = "发布工单中重启服务功能")
    @RequestMapping(value = "/restart/service", method = RequestMethod.POST)
    public ResultHandler<String> restartService(@RequestBody OpsDepAppOrderRequest request) {
        String header = getRequest().getHeader("referer");
        asyncPublishTask.orderRestartService(request, header);
        return ResultHandler.success("success");
    }

    @ApiOperation(value = "根据工单ID删除工单", notes = "根据工单ID删除工单")
    @RequestMapping(value = "/delete/{orderId}", method = RequestMethod.POST)
    public ResultHandler<String> deleteOrderById(@PathVariable("orderId") Long orderId) {
        int result = opsDepAppOrderService.deleteOrderById(orderId);
        if (result == 0) {
            return ResultHandler.error("该工单状态不是未发布，不能进行删除！");
        }
        return ResultHandler.success("删除成功！");
    }

    /**
     * 获取发布应用状态
     * @author Yang Chunhai
     * @return
     */
    @RequestMapping(value = "/app/status/{time}/{proId}/{envId}", method = RequestMethod.GET)
    public ResultHandler<List<DeployAppVo>> getAppDeployData(@PathVariable String time, @PathVariable Long proId, @PathVariable Long envId) {
    	List<DeployAppVo> appDeployData = opsDepAppOrderService.getAppDeployData(time, proId, envId);
        return ResultHandler.success(appDeployData);
    }
    
    /**
     * 获取发布应用数量情况
     * @author Yang Chunhai
     * @return
     */
    @RequestMapping(value = "/search/status", method = RequestMethod.GET)
    public ResultHandler<DeployAppVo> getAppPublishStatus() {
    	DeployAppVo deployAppVo = opsDepAppOrderService.getAppPublishStatus();
        return ResultHandler.success(deployAppVo == null ? new DeployAppVo() : deployAppVo);
    }
}
