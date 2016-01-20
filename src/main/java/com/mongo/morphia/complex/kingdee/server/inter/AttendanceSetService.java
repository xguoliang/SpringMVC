package com.mongo.morphia.complex.kingdee.server.inter;

/**
 * Copyright 1993-2013 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.util.List;

import com.mongo.morphia.complex.kingdee.domain.AttendanceSet;



/**
 *
 * @since 2013-8-22
 * @author yifeng_xie
 */
public interface AttendanceSetService {

//    /**
//     * 查询当前网络的考勤设置
//     * @param networkId
//     * @return
//     */
//    AttendanceSet getByNetworkId(String networkId);
    
    /**
     * 删除考勤配置信息
     * @param networkId
     */
//    void delByNetworkId(String networkId);
    
    /**
     * 删除考勤配置信息
     * @param ids
     */
    void delAttendance(List<String> ids);
    
    /**
     * 更新当前网络的考勤设置
     * @param networkId
     * @param userId 操作人Id
     * @param attends 更新的考勤设置集合
     */
//    void updateAttendance(String networkId,String userId,List<AttendanceSet> attends);
    
    /**
     * 考勤时间设置
     * @param attendance
     * @return
     */
//    Attendance extraAttendance(Attendance attendance);
    
    /**
     * 查询当前网络的考勤设置
     * @param networkId
     * @return
     */
    List<AttendanceSet> getListByNetworkId(String networkId);

    /**
     * 根据networkId分页查询所有的AttendanceSet
     * @param networkId
     * @param start
     * @param limit
     * @return
     */
//	public List<AttendanceSet> listAttendanceByNetworkId(String networkId, int start,
//			int limit);
	
	/**
	 * 根据networkId统计AttendanceSet总数
	 * @param networkId
	 * @return	公司号已经存在的AttendanceSet
	 */
//	public Integer countAttendanceSetsByNetworkId(String networkId);

	/**
	 * 更新已经存在的AttendanceSet
	 * @param userId
	 * @param newAttend
	 * @return 
	 */
//	public boolean updateAttendanceset(String userId, AttendanceSet newAttend);

	/**
	 * 保存或者更新已经存在的AttendanceSet
	 * @param userId
	 * @param newAttend
	 * @return	0失败,1成功,2已存在
	 */
//	public Integer saveOrUpdateAttendanceset(String attendSetId, String userId,
//			AttendanceSet newAttend);

	/**
	 * 根据networkId判断wifi自动签到是否可用
	 * @param networkId
	 * @return 0无签到点,1有签到点无关联wifi,2有关联wifi的签到点
	 */
//	public Integer isWiFiClockInAvaliable(String networkId);

	/**
	 * 根据ID获取指定签到点
	 * @param id
	 * @return
	 */
	public AttendanceSet findAttendanceSetById(String id);

//	public List<AttendanceSet> listAttendanceByNetworkIdAndDeptId(String networkId,
//			String deptId, int start, int limit);
//
//	public Integer countAttendanceSetsByNetworkIdAndDept(String networkId,
//			String deptId);
//
//	public Integer isWiFiClockInAvaliable(String networkId, String deptId);
//
//	public List<AttendanceSet> getListByNetworkId(String networkId, String deptId);
//
//	public AttendanceSet getByNetworkId(String networkId, String deptId);
//
//	public Integer saveOrUpdateAttendanceset(String attendSetId, String userId,
//			String deptId, AttendanceSet newAttend);
//
//	public List<AttendanceSet>  findAttendanceSetByDeptId(String deptId);
}
