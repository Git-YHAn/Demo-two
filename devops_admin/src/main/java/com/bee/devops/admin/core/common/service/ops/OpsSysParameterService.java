package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.core.common.dao.ops.OpsSysParameterDao;
import com.bee.devops.admin.core.common.entity.ops.OpsSysParameter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Roc created on 2018/8/2
 */
@Service
public class OpsSysParameterService {
	private final static Logger logger = Logger.getLogger(OpsSysParameterService.class);
	@Autowired
	private OpsSysParameterDao opsSysParameterDao;

	/**
	 * 如果数据库不存在保存到数据库，若已存在则进行更新
	 *
	 * @param parameterMap
	 *            系统参数 Map（参数名称，参数值）
	 * @return 更新成功返回true 失败返回false
	 */
	public boolean saveOrUpdateOpsParameter(Map<String, String> parameterMap) {
		for (Map.Entry<String, String> param : parameterMap.entrySet()) {
			String name = param.getKey();
			String value = param.getValue();
			if (StringUtils.isBlank(name) || StringUtils.isBlank(value)) {
				logger.error("Invalid parameter with empty of name(" + name + ") or value (" + value + ").");
				return false;
			}

			opsSysParameterDao.saveOrUpdate(param.getKey(), param.getValue());
			OpsSysParameterUtil.cacheSysParameter(param.getKey(), param.getValue());
		}
		return true;
	}

	public List<OpsSysParameter> getAllSysParameters() {
		List<OpsSysParameter> list = opsSysParameterDao.queryAllOpsSysParameters();
		for (OpsSysParameter parameter : list) {
			String name = parameter.getSysParameterName();
			String value = parameter.getSysParameterValue();
			OpsSysParameterUtil.cacheSysParameter(name, value);
		}
		return list;
	}

	public OpsSysParameter getSystParameterByKey(String key) {
		return opsSysParameterDao.queryOpsSysParameterByName(key);
	}
}
