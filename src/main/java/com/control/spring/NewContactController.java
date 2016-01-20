package com.control.spring;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.imp.BaseController;
import com.common.util.HttpHelper;
import com.common.util.PropertiesUtils;


@Controller
@RequestMapping(value="/newcontact")
public class NewContactController extends BaseController {
	
	final static String CONTACT_HOST = "contact.host" ;
	final static String ACCOUNT_HOST = "account.host" ;
	/**
	 * 修改人员信息
	 * @param eid
	 * @param data
	 * @param nonce
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/input/person/updateInfo",method=RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	public Object updatePersonInfo(String eid, String data, String nonce) {
		Map<String, Object> map = initMessage();
		try {
			data = deEncrypt(eid,data,map);
			final Map<String, String> params = params(eid, data, nonce);
			final String url = URLHelper.getBaseUrl(CONTACT_HOST)
					+ "/contact/person/update";
			Object object = process(url, params);
			logInfo ("请求 input/person/updateInfo 结束，返回结果：" + object) ;
			return object;
		} catch (Exception e) {
			processError(map, e);
			return ERROR(map);
		}
	}
	
	private Map<String, String> params(String eid, String data, String nonce) {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("eid", eid);
		params.put("data", data);
		params.put("nonce", nonce);
		return params;
	}

	private Object process(final String url, Map<String, String> params) {
		Map<String, Object> map = initMessage();
		try {
			String rtn = HttpHelper.post(params, url);
			logInfo(rtn);
			return rtn;
		} catch (Exception e) {
			processError(map, e);
			return ERROR(map);
		}
	}
	
	public static class URLHelper {

		public static String getBaseUrl(String hostKey){
			
			return PropertiesUtils.getInstance().getProperty(hostKey);
		}
		
		public static String getUrl(String hostKey,String partUrlKey){
			
			return getBaseUrl(hostKey) + PropertiesUtils.getInstance().getProperty(partUrlKey);
		}
	}
}
