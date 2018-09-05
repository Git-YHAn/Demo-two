package com.bee.devops.admin.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * date转string
	 * @param date
	 * @param strFormate
	 * @return
	 */
	public static String dateToStr(Date date,String strFormate){
		SimpleDateFormat sdf = new SimpleDateFormat(strFormate);
		String res=sdf.format(date);
		return res;
	}
}
