package com.bee.devops.admin.component.sp.model.kec;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 实例状态类型
 * 
 * @description InstanceState
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class InstanceState {
	/**
	 * 实例状态
	 * 类型: String
	 * 有效值: active | building | paused | suspended | stopped | rescued | resized | soft-delete | deleted | deleting | error | scheduling | block_device_mapping
	 * | networking | spawning | image_snapshot | image_backup | updating_password | resize_prep | resize_migrating | resize_migrated | resize_finish |
	 * resize_finish | resize_reverting | resize_confirming | migrating | rebooting | rebooting_hard | pausing | unpausing | suspending | resuming | stopping |
	 * starting | powering-off | powering-on | rescuing | unrescuing | rebuilding | rebuild_block_device_mapping | rebuild_spawning | deleting 主机状态注释
	 * 是否可缺省: 否
	 */
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
