package com.mongo.morphia.complex.kingdee.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongo.morphia.complex.kingdee.dao.AttendanceSetDAO;
import com.mongo.morphia.complex.kingdee.dao.AttendanceSetDAOImpl;
import com.mongo.morphia.complex.kingdee.domain.AttendanceSet;
import com.mongo.morphia.complex.kingdee.server.inter.AttendanceSetService;

public class AttendanceSetServiceImpl  implements AttendanceSetService{
    @Autowired
//    private AttendanceSetDAO attendDAO;
	@Override
	public void delAttendance(List<String> ids) {
//        attendDAO.delByIds(ids);
//        customAttendSetDAO.deleteByAttendSets(ids);
		
	}

	@Override
	public AttendanceSet findAttendanceSetById(String id) {
		AttendanceSetDAO attendDAO = new AttendanceSetDAOImpl();
		return attendDAO.find(id);
	}

	@Override
	public  List<AttendanceSet> getListByNetworkId(String networkId) {
		AttendanceSetDAO attendDAO = new AttendanceSetDAOImpl();
		return attendDAO.findByNetworkId(networkId);
	}
	
	
}
