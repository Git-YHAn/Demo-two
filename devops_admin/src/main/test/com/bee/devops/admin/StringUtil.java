package com.bee.devops.admin;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	private static void transTbNameToClsName(String str) {
		String[] arr = str.toLowerCase().split("_");
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i].charAt(0);
			String upperCase = StringUtils.upperCase(c + "");
			arr[i] = arr[i].replaceFirst("" + c, upperCase);
		}
		System.out.println(StringUtils.join(arr, ""));
	}
	
	public static void main(String[] args) {
		transTbNameToClsName("ADMIN_ROLE");
		transTbNameToClsName("ADMIN_USER");
		transTbNameToClsName("ADMIN_RESOURCE");
		transTbNameToClsName("ADMIN_ROLE_RESOURCE");
		transTbNameToClsName("ADMIN_USER_ROLE");
		transTbNameToClsName("OPS_VERSION_APP");
		transTbNameToClsName("OPS_VERSION_APP_SYNC");
		transTbNameToClsName("OPS_VERSION_CONFIG");
		transTbNameToClsName("OPS_VERSION_APP_IMG");
		transTbNameToClsName("OPS_VERSION_APP_DEP");
		transTbNameToClsName("OPS_BASE_SERVER");
		transTbNameToClsName("OPS_BASE_SERVER_TEMP");
		transTbNameToClsName("OPS_BASE_SERVER_TYPE");
		transTbNameToClsName("OPS_BASE_ENV");
		transTbNameToClsName("OPS_BASE_ENV_SERVER");
		transTbNameToClsName("OPS_BASE_PRO");
		transTbNameToClsName("OPS_BASE_APP");
		transTbNameToClsName("OPS_BASE_APP_HIS");
		transTbNameToClsName("OPS_BASE_COMPONENT");
		transTbNameToClsName("OPS_CONFIG");
		transTbNameToClsName("OPS_ASSEMBLE_APP");
		transTbNameToClsName("OPS_DEP_APP");
		transTbNameToClsName("OPS_DEP_APP_HIS");
		transTbNameToClsName("OPS_COM_GIT_HOOK");
	}
}
