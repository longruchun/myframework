package com.hx.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.javafx.collections.MappingChange.Map;



public class system_servlet {
    public static HashMap<String, my_servlet> map= new HashMap<String,my_servlet>(); 
    
    public void reading(String config) {
    	//用dom4j技术，将配置文件中的servlet全部读取到list中
    	//读取的代码
   	 try {
   		 
			if (config == null || config.isEmpty()) {
				//throw new Exception("自定义框架的配置文件为空");
			}
			java.io.InputStream is = system_servlet.class.getResourceAsStream("WEB-INF/myservlet.xml");
			System.out.println("asdkjfhasd:"+is);
			Document doc = new SAXReader().read(is);
			Element root = doc.getRootElement();
			Iterator<Element> it = root.elements("bean").iterator();
			
			
			while(it.hasNext()) {
				Element bean = it.next();
				
				my_servlet m_s=new my_servlet();
				m_s.setId(bean.attributeValue("id"));
				m_s.setClazz(bean.attributeValue("class"));
				
				map.put(m_s.getId(),m_s);
			}
			
			System.out.println(map.keySet());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
