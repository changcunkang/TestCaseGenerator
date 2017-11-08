package com.cams.blaze.service;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cams.blaze.request.Application;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.thoughtworks.xstream.converters.SingleValueConverter;

class XStreamDateConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class arg0) {
		// TODO Auto-generated method stub
		return Date.class == arg0;
	}

	@Override
	public Object fromString(String arg0) {
		// TODO Auto-generated method stub
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(arg0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString(Object arg0) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format((Date)arg0);
	}

}

public class XSTreamHelper {
	
	private static XStream xStream = null;
	
	private static Map<String, Class> SIMPLE_NAME_JAVACLASS_MAP = new HashMap<String, Class>();
	
	public static XStream getXStream(Object obj){
		if(xStream == null){
			xStream = new XStream( new DomDriver(null,new XmlFriendlyNameCoder("_-", "_"))  );
			
			xStream.setMode(XStream.NO_REFERENCES);
			
			//xStream.processAnnotations(new Class[]{Product.class, Account.class, Customer.class});
			
			xStream.registerConverter(new XStreamDateConverter());
			try {
				buildDataStructor( xStream,obj );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return xStream;
	}
	
	private static void buildDataStructor(XStream tmpXstream,Object obj) throws Exception{	
		Class appClass = obj.getClass();	
		Queue<Class> queue = new LinkedList<Class>();
		queue.add(appClass);
		SIMPLE_NAME_JAVACLASS_MAP.put(appClass.getSimpleName(), appClass);
		
		
		
		while( queue.size()>0 ){
			Class tmpCls = queue.poll();
			
			tmpXstream.alias(tmpCls.getSimpleName(), tmpCls);
			
			for(Field field : tmpCls.getDeclaredFields()){
				if( isBasicType( field.getType() ) ){
					tmpXstream.useAttributeFor(tmpCls, field.getName());
				}
				else{
					if(  Collection.class.isAssignableFrom( field.getType() )){
						if(field.getGenericType() instanceof ParameterizedType){   
							 ParameterizedType pt = (ParameterizedType) field.getGenericType();  
					         //得到泛型里的class类型对象    
					         Class genericClazz = (Class)pt.getActualTypeArguments()[0];  
					         
					         String fieldName = field.getName();
					         
					         xStream.addImplicitCollection(tmpCls, fieldName, genericClazz);
					         
					         if(SIMPLE_NAME_JAVACLASS_MAP.get(genericClazz.getSimpleName()) == null){
					        	 queue.add(genericClazz);
					        	 
						         SIMPLE_NAME_JAVACLASS_MAP.put(genericClazz.getSimpleName(), genericClazz);
					        }
					    }
					}
					else{
						
						tmpXstream.aliasField(field.getType().getSimpleName(), tmpCls, field.getName());
						
						if(SIMPLE_NAME_JAVACLASS_MAP.get(field.getType().getSimpleName()) == null){
							queue.add( field.getType() );
							SIMPLE_NAME_JAVACLASS_MAP.put(field.getType().getSimpleName(), field.getType());
							
						}
					}
				}
			}
		}
	}
	
	public static Object readApplication(String filePath,Object obj){
		return getXStream(obj).fromXML(new File(filePath));
	}
	
	public static synchronized Object fromXMLToObject(String xmlStr,Object obj){
		return getXStream(obj).fromXML(xmlStr);
	}
	
	public static synchronized String fromObjectToXML(Object object,Object obj){
		return getXStream(obj).toXML(object);
	}
	
	public static Class getJavaBomBySimpleName(String classSimpleName){
		return SIMPLE_NAME_JAVACLASS_MAP.get(classSimpleName);
	}
	

	public static boolean isBasicType(Class fieldType){
		  if(fieldType.isPrimitive() ||
				  fieldType == Integer.class || fieldType == Double.class ||
				  fieldType == Float.class || fieldType == String.class ||
				  fieldType == java.util.Date.class || fieldType == Short.class ||
				  fieldType == Long.class || fieldType == Byte.class ||
				  fieldType == Boolean.class || fieldType == Byte.class ||
				  fieldType == Character.class || fieldType == Character.class 
				  ){
			  return true;
		  }
		  return false;
	}

	private static String convertJavaBeanToXMLStr(Object obj ) throws Exception{
		String result = null;
		
		JAXBContext context = JAXBContext.newInstance(obj.getClass());  
        Marshaller marshaller = context.createMarshaller();  
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
        
        StringWriter writer = new StringWriter();  
        marshaller.marshal(obj, writer);  
        result = writer.toString();  
        
        return result;
	}
	
	public static Application converyToJavaBean(String xml) throws Exception {  
		Application rtnApp = null;  
        
		JAXBContext context = JAXBContext.newInstance(Application.class);  
        Unmarshaller unmarshaller = context.createUnmarshaller();  
        rtnApp = (Application) unmarshaller.unmarshal(new StringReader(xml));  
  
        return rtnApp;  
    }
	
	public static void main(String[] args){
		
		//Application app = generateApp();
		
		XStream xStream = XSTreamHelper.getXStream(new Application());
		
		//System.out.println( xStream.toXML(app) );
		
		Object object = xStream.fromXML(new File( "C:/temp/2.xml") );
		
		//Object object = generateApp();
		
		System.out.println( xStream.toXML(object) );
		
		System.out.println( "1" );
	}
	
}
