package com.mongo.morphia.complex.kingdee;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongo.morphia.complex.kingdee.domain.AttendanceSet;
import com.mongo.morphia.complex.kingdee.server.AttendanceSetServiceImpl;
@Controller
public class test {
	@RequestMapping(value="/morphia/test")
	public void test1() {
		AttendanceSetServiceImpl attendanceSetService = new AttendanceSetServiceImpl();
		String id ="103";
//		attendanceSetService.findAttendanceSetById(id);
		 List<AttendanceSet> AttendanceSetlist =attendanceSetService.getListByNetworkId(id);
		 for (AttendanceSet attendanceSet:AttendanceSetlist){
			 System.out.println(attendanceSet.toString());
		 }
	}

}
