package com.mongo.morphia.complex.kingdee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongo.morphia.complex.kingdee.core.dao.BaseDAOImpl;
import com.mongo.morphia.complex.kingdee.domain.AttendanceSet;
import com.mongo.morphia.complex.kingdee.domain.WifiEntity;
import com.mongo.morphia.complex.kingdee.morphia.integer.CriteriaQuery;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

/**
 *
 * @since 2013-8-22
 * @author yifeng_xie
 */
@Repository
public class AttendanceSetDAOImpl extends BaseDAOImpl<AttendanceSet> implements AttendanceSetDAO{
	@Override
	  public  AttendanceSet find(String id){
		  return super.find(id);
	  }
    
    private CriteriaQuery<AttendanceSet> getAllQuery(){
    	CriteriaQuery<AttendanceSet> query=super.getQuery();
        return query;
    }
    
    @Override
    public List<AttendanceSet> findByNetworkId(String networkId)
    {
        CriteriaQuery<AttendanceSet> query = getAllQuery();
        
        query.field("networkId").equal(networkId);
        return super.getDAO().query(query).asList();
    }
    
    @Override
    public List<AttendanceSet> findByNetworkIdAndDept(String networkId,String deptId)
    {
        CriteriaQuery<AttendanceSet> query = getAllQuery();
        
        query.field("networkId").equal(networkId);
        query.field("deptId").equal(deptId);
        return super.getDAO().query(query).asList();
    }
    
//    @Override
//    public void delByNetworkId(String networkId)
//    {
//        CriteriaQuery<AttendanceSet> query = getAllQuery();
//        
//        query.field("networkId").equal(networkId);
//        
//        super.getDAO().delete(query);
//    }
//    
//    @Override
//    public void delByNetworkIdAndDept(String networkId,String deptId)
//    {
//        CriteriaQuery<AttendanceSet> query = getAllQuery();
//        
//        query.field("networkId").equal(networkId);
//        query.field("deptId").equal(deptId);
//        super.getDAO().delete(query);
//    }
//    
//    @Override
//    public void delByIds(List<String> ids)
//    {
//        CriteriaQuery<AttendanceSet> query = getAllQuery();
//        query.field("_id").hasAnyOf(ids);
//        super.getDAO().delete(query);
//    }
    
    @Override
    public List<AttendanceSet> findByNetworkId(int start,int limit)
    {
        CriteriaQuery<AttendanceSet> query = getAllQuery();
        
        query.order("_id");
        query.offset(start).limit(limit);
        return super.getDAO().query(query).asList();
    }
    
    @Override
    public List<AttendanceSet> findByNetworkIdWithPage(String networkId, int start,int limit)
    {
    	CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.order("_id");
    	query.offset(start).limit(limit);
    	return super.getDAO().query(query).asList();
    }
    @Override
    public List<AttendanceSet> findByNetworkIdWithPageAndDept(String networkId,String deptId, int start,int limit)
    {
    	CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.field("deptId").equal(deptId);
    	query.order("_id");
    	query.offset(start).limit(limit);
    	return super.getDAO().query(query).asList();
    }

//	@Override
//	public Integer countAttendanceSetsByNetworkIdAndDept(String networkId,String deptId) {
//		CriteriaQuery<AttendanceSet> query = getAllQuery();
//    	query.field("networkId").equal(networkId);
//    	query.field("deptId").equal(deptId);
//    	Long count = super.getDAO().count(query);
//    	return count.intValue();
//	}
//
//	@Override
//	public Integer countAttendanceSetsByNetworkId(String networkId) {
//		CriteriaQuery<AttendanceSet> query = getAllQuery();
//    	query.field("networkId").equal(networkId);
//    	Long count = super.getDAO().count(query);
//    	return count.intValue();
//	}
	
	@Override
	public List<AttendanceSet> findByNetworkIdAndWifi(String networkId,
			String bssid, int type) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	WifiEntity wifi = new WifiEntity();
    	wifi.setBssid(bssid);
    	wifi.setType(type);
    	query.field("wifis").hasThisElement(wifi);
    	query.readPreference(ReadPreference.secondaryPreferred());
		return super.getDAO().query(query).asList();
	}
	
	@Override
	public List<AttendanceSet> findByNetworkIdAndWifiAndDept(String networkId,String deptId,
			String bssid, int type) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.field("deptId").equal(deptId);
    	WifiEntity wifi = new WifiEntity();
    	wifi.setBssid(bssid);
    	wifi.setType(type);
    	query.field("wifis").hasThisElement(wifi);
    	query.readPreference(ReadPreference.secondaryPreferred());
		return super.getDAO().query(query).asList();
	}
	
	@Override
	public List<AttendanceSet> findByNetworkIdAndGeo(String networkId,
			String positionName, String address) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.field("position.positionName").equal(positionName);
    	query.field("position.address").equal(address);
    	query.readPreference(ReadPreference.secondaryPreferred());
		return super.getDAO().query(query).asList();
	}

	@Override
	public List<AttendanceSet> findByNetworkIdAndGeoAndDept(String networkId,String deptId,
			String positionName, String address) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.field("deptId").equal(deptId);
    	query.field("position.positionName").equal(positionName);
    	query.field("position.address").equal(address);
    	query.readPreference(ReadPreference.secondaryPreferred());
		return super.getDAO().query(query).asList();
	}
	@Override
	public List<AttendanceSet> findByNetworkIdAndGeoAndDept(String networkId,String deptId,
			String positionName) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.field("deptId").equal(deptId);
    	query.field("position.positionName").equal(positionName);
    	query.readPreference(ReadPreference.secondaryPreferred());
		return super.getDAO().query(query).asList();
	}
	@Override
	public List<AttendanceSet> findByNetworkIdWithNoPostion(String networkId) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.field("position").doesNotExist();
    	query.readPreference(ReadPreference.secondaryPreferred());
		return super.getDAO().query(query).asList();
	}

	@Override
	public List<AttendanceSet> findByNetworkIdWithNoPostionAndDept(String networkId,String deptId) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.field("deptId").equal(deptId);
    	query.field("position").doesNotExist();
    	query.readPreference(ReadPreference.secondaryPreferred());
		return super.getDAO().query(query).asList();
	}
	
	@Override
	public List<AttendanceSet> findWiFiAtttendByNetworkId(String networkId, int type) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.field("wifis.type").equal(type);
		return super.getDAO().query(query).asList();
	}
	
	@Override
	public List<AttendanceSet> findWiFiAtttendByNetworkIdAndDept(String networkId,String deptId, int type) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
    	query.field("networkId").equal(networkId);
    	query.field("deptId").equal(deptId);
    	query.field("wifis.type").equal(type);
		return super.getDAO().query(query).asList();
	}
	//findByDeptId
	@Override
	public List<AttendanceSet> findByDeptId(String deptId) {
		CriteriaQuery<AttendanceSet> query = getAllQuery();
		if (deptId.equals("null")){
	    	query.field("deptId").equal(null);
		}else{
			query.field("deptId").equal(deptId);
		}
		return super.getDAO().query(query).asList();
	}
}
