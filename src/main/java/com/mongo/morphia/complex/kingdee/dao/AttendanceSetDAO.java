package com.mongo.morphia.complex.kingdee.dao;

/**
 * Copyright 1993-2013 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */




import java.util.List;

import com.mongo.morphia.complex.kingdee.core.dao.BaseDAO;
import com.mongo.morphia.complex.kingdee.domain.AttendanceSet;



/**
 *
 * @since 2013-8-22
 * @author yifeng_xie
 */
public interface AttendanceSetDAO {
//public interface AttendanceSetDAO extends BaseDAO<AttendanceSet>{ //为啥要继承BaseDAO呢，没意义
    
    /**
     * 查询当前网络的考勤设置
     * @param networkId
     * @return
     */
    List<AttendanceSet> findByNetworkId(String networkId);
    
//    /**
//     * 删除考勤配置信息
//     * @param networkId
//     */
//    void delByNetworkId(String networkId);
    
//    /**
//     * 删除考勤配置信息
//     * @param ids
//     */
//    void delByIds(List<String> ids);

    /**
     * 查询所有网络配置信息
     * @param start
     * @param limit
     * @return
     */
    List<AttendanceSet> findByNetworkId(int start,int limit);

    /**
     * 根据networkId分页查询AttendanceSet
     * @param networkId
     * @param start
     * @param limit
     * @return
     */
	public List<AttendanceSet> findByNetworkIdWithPage(String networkId, int start,
			int limit);

//	/**
//	 * 根据networkId统计AttendanceSet总数
//	 * @param networkId
//	 * @return	公司号已经存在的AttendanceSet
//	 */
//	public Integer countAttendanceSetsByNetworkId(String networkId);

	/**
	 * 根据WiFi macid和公司号查找匹配的签到点设置
	 * @param networkId
	 * @param bssid
	 * @param type
	 * @return
	 */
	public List<AttendanceSet> findByNetworkIdAndWifi(String networkId, String bssid, int type);

	/**
	 * 根据公司号及地理坐标查询签到点
	 * @param networkId
	 * @param positionName
	 * @param address
	 * @return
	 */
	public List<AttendanceSet> findByNetworkIdAndGeo(String networkId, String positionName,
			String address);

	/**
	 * 查询未设置地理信息的签到点
	 * @param networkId
	 * @return
	 */
	public List<AttendanceSet> findByNetworkIdWithNoPostion(String networkId);

	/**
	 * 查询指定号下的签到点是否存在已经关联wifi
	 * @param networkId
	 * @param type
	 * @return
	 */
	public List<AttendanceSet> findWiFiAtttendByNetworkId(String networkId, int type);

	/**
	 * 按部门查询考勤点
	 * @param networkId
	 * @param deptId
	 * @return
	 */
	List<AttendanceSet> findByNetworkIdAndDept(String networkId, String deptId);

//	/**
//	 * 根据networkId和deptId统计AttendanceSet总数
//	 * @param networkId
//	 * @return	公司号已经存在的AttendanceSet
//	 */
//	public Integer countAttendanceSetsByNetworkIdAndDept(String networkId, String deptId);

	  /**
     * 根据networkId和deptId分页查询AttendanceSet
     * @param networkId
     * @param start
     * @param limit
     * @return
     */
	public List<AttendanceSet> findByNetworkIdWithPageAndDept(String networkId,
			String deptId, int start, int limit);

//    /**
//     * 删除考勤配置信息
//     * @param networkId
//     * @param deptId
//     */
//	public void delByNetworkIdAndDept(String networkId, String deptId);


	public List<AttendanceSet> findByNetworkIdAndWifiAndDept(String networkId,
			String deptId, String bssid, int type);

	public List<AttendanceSet> findByNetworkIdAndGeoAndDept(String networkId,
			String deptId, String positionName, String address);

	public List<AttendanceSet> findByNetworkIdWithNoPostionAndDept(String networkId,
			String deptId);

	public List<AttendanceSet> findWiFiAtttendByNetworkIdAndDept(String networkId,
			String deptId, int type);

	public List<AttendanceSet> findByDeptId(String deptId);

	public List<AttendanceSet> findByNetworkIdAndGeoAndDept(String networkId,
			String deptId, String positionName);

	AttendanceSet find(String id);





}
