package com.common.XML;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.mongo.morphia.complex.kingdee.common.PersistenceUnitInfo;
import com.mongo.morphia.complex.kingdee.common.consts.PersistenceUnitConstants;


/**
 * 
 * @author Administrator
 *
 */
public class readXml {
	
	/**
	 * XML文件如下
	 * <?xml version="1.0" encoding="utf-8" ?>
	<!-- DataSource配置样例，此文件应放在WEB-INF/datasource/ 目录下。 readPreference、writeConcern参数为DB级别的，其它为实例级别的。 
	readPreference可选值：primary、secondary、secondaryPreferred、primaryPreferred、nearest。 
	writeConcern可选值：NONE、NORMAL、SAFE、MAJORITY、FSYNC_SAFE、JOURNAL_SAFE、REPLICAS_SAFE。 -->
	<persistence>
		<persistence-unit name="configuration">
			<mongodb url="192.168.22.209:27017" dbname="ossDev" user="kingdeeUser" password="kingdeePsw" connectionsPerHost="50" threadsAllowedToBlockForConnectionMultiplier="10" maxWaitTime="10000" connectTimeout="20000" socketTimeout="20000" autoConnectRetry="true" readPreference="secondaryPreferred"/>
		</persistence-unit>

		<tenantIDs>
			<tenantID>demo</tenantID>
			<tenantID>test</tenantID>
		</tenantIDs>
	</persistence>
	**/
	/**
	 * @param fileurl
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	
	public void readXmlFile(URL fileurl) throws XMLStreamException, IOException {

		XMLInputFactory xif = XMLInputFactory.newInstance();//创建一个对象
		XMLEventReader reader; //创建一个对象XMLEventReader
		//fileurl为XML文件的URL地址
		reader = xif.createXMLEventReader(fileurl.openStream());
		try {
			while (reader.hasNext()) {//判断是否读取到内容
				XMLEvent e = reader.nextEvent(); //第一次读取的内容如下：<?xml version="1.0" encoding='utf-8' standalone='no'?>
				if (e.isStartElement()) {//如果读取到内容，判断是否读取的XML内容结构的节点
					StartElement se = e.asStartElement();//获取该节点的内容
					String name = se.getName().getLocalPart(); //获取节点的名称
					//获取se节点的中属性名为name的属性
                    Attribute attr = se.getAttributeByName(new javax.xml.namespace.QName("name"));
					//获取“name”属性的属性值
                    String value =attr.getValue();
                    
                    //应该是获取该节点se的所有属性
					Iterator it =se.getAttributes();
					if (it.hasNext()){
						 Attribute attrtemp=(Attribute) it.next();
						String value1 = attrtemp.getValue();
						String name1=attrtemp.getName().getLocalPart();
					}
                    
                    
				}
			}
		} catch (Exception e) {
		}

	}
}
