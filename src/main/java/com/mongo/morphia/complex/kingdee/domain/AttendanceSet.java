package com.mongo.morphia.complex.kingdee.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongo.morphia.complex.core_dal.annotation.PersistenceUnit;
import com.mongo.morphia.complex.kingdee.core.server.AbstractDynaBObject;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Index;
import com.google.code.morphia.annotations.Indexes;
import com.google.code.morphia.annotations.Transient;
/**
 * 考勤信息配置
 * @since 2013-8-22
 * @author yifeng_xie
 */
@Indexes(value = { @Index(value = "networkId", background = true)})
@PersistenceUnit(value="att")
public class AttendanceSet extends AbstractDynaBObject{
    private static final long serialVersionUID = 1792178397322580766L;
    
    private String networkId;
    private String groupId;
    private String deptId;

    private Position position; //考勤区域及二维地理位置
    
    private Date createTime;
    private Date updateTime;
    private String operateUserId;// 变更人userId
    
    private String  recomSwitch;// 推荐开关 off/on 
    private String createUserId;// 记录创建人
    
    
    @Embedded
    private Attendance attendance; //考勤时段设置

    private AttendanceMBShare attendanceMBShare; //考勤微博分享内容
    
    
    @Transient
    private List<Position> positions;//考勤地点名称集合
    
    private int num; //前多少位提示上班考勤排名 (默认10位)
    
    private List<WifiEntity> wifis = new ArrayList<WifiEntity>(50);	// 签到点关联的WiFi热点
    
    /**
     * @return the attendanceMBShare
     */
    public AttendanceMBShare getAttendanceMBShare() {
        return attendanceMBShare;
    }

    /**
     * @param attendanceMBShare the attendanceMBShare to set
     */
    public void setAttendanceMBShare(AttendanceMBShare attendanceMBShare) {
        this.attendanceMBShare = attendanceMBShare;
    }

    public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return the positions
     */
    public List<Position> getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    /**
     * @return the networkId
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * @param networkId the networkId to set
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

   
    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        this.updateTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the operateUserId
     */
    public String getOperateUserId() {
        return operateUserId;
    }

    /**
     * @param operateUserId the operateUserId to set
     */
    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    /**
     * @return the attendance
     */
    public Attendance getAttendance() {
        return attendance;
    }

    /**
     * @param attendance the attendance to set
     */
    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
    
	public String getRecomSwitch() {
		return recomSwitch;
	}

	public void setRecomSwitch(String recomSwitch) {
		this.recomSwitch = recomSwitch;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public List<WifiEntity> getWifis() {
		return wifis;
	}

	public void setWifis(List<WifiEntity> wifis) {
		this.wifis = wifis;
	}

}
