package com.fico.testCaseGenerator.XSTream;

import com.fico.testCaseGenerator.util.PropertiesConfig;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/29/2017
 */
public class XSTreamHelper {

    private XStream xStream = null;

    private Map<String, Class> SIMPLE_NAME_JAVACLASS_MAP = new HashMap<String, Class>();

    public XStream getXStream(){
        if(xStream == null){
            xStream = new XStream( new DomDriver(null,new XmlFriendlyNameCoder("_-", "_"))  );

            xStream.setMode(XStream.NO_REFERENCES);

            //xStream.processAnnotations(new Class[]{Product.class, Account.class, Customer.class});

            xStream.registerConverter(new XStreamDateConverter());
            try {
                buildDataStructor( xStream );
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return xStream;
    }

    private  void buildDataStructor(XStream tmpXstream) throws Exception{

        String applicationClassFullPackageName = ResourceBundle.getBundle("javaBOM").getString("rootApplicationFullName");

        Class appClass = Class.forName( applicationClassFullPackageName );

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

    public  Object readApplication(String filePath){
        return getXStream().fromXML(new File(filePath));
    }

    public  synchronized Object fromXMLToObject(String xmlStr){
        return getXStream().fromXML(xmlStr);
    }

    public  synchronized String fromObjectToXML(Object object){
        return getXStream().toXML(object);
    }

    public  Class getJavaBomBySimpleName(String classSimpleName){
        return SIMPLE_NAME_JAVACLASS_MAP.get(classSimpleName);
    }


    public  boolean isBasicType(Class fieldType){
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

//    private  String convertJavaBeanToXMLStr(Object obj ) throws Exception{
//        String result = null;
//
//        JAXBContext context = JAXBContext.newInstance(obj.getClass());
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//
//        StringWriter writer = new StringWriter();
//        marshaller.marshal(obj, writer);
//        result = writer.toString();
//
//        return result;
//    }
//
//    public  Application converyToJavaBean(String xml) throws Exception {
//        Application rtnApp = null;
//
//        JAXBContext context = JAXBContext.newInstance(Application.class);
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        rtnApp = (Application) unmarshaller.unmarshal(new StringReader(xml));
//
//        return rtnApp;
//    }

}
