/**
 * @author heping
 * @date2018年6月4日
 */
package com.bee.devops.admin.common.utils;

import java.util.Date;


public class VersionCodeUtils {
	
	//根据上次版本号生成最新版本号
	public static String getVersionCode(String versionCode){
		if(versionCode!=null){
			String[] array = versionCode.split("_");
			int v = Integer.valueOf(array[array.length-1]);
			v++;
			if(v<=9){
				return versionCode.substring(0, 11)+"0"+v;
			}
			return versionCode.substring(0,11)+v;
		}
		String date = DateUtils.dateToStr(new Date(), "yyyyMMdd");
		return "V_"+date+"_00";
	}
}
