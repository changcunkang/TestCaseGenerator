package com.fico.testCaseGenerator.util;

import com.cams.blaze.request.Loancard;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClassUtil {
	
	 public List<Object> search(Object o, String query)
	  {
	   // System.out.println(o);
	    List objects = new ArrayList();
	    String[] querys = parseQuery(query);
	    if ((querys[0].equals("/")) && 
	      (!o.getClass().getSimpleName().equals(querys[1]))) {
	      return objects;
	    }
  
	    boolean find = (o.getClass().getSimpleName().equals(querys[1])) && (filter(o, querys[2]));
	    if ((querys[3] == null) && (find)) {
	      objects.add(o);
	      return objects;
	    }
	    if ((querys[3] != null) && (find)) {
	      Field[] fields = o.getClass().getDeclaredFields();
	      for (Field field : fields) {
	        field.setAccessible(true);
	        searchField(objects, o, field, querys[3]);
	      }
	    }
	    if (!find) {
	      Field[] fields = o.getClass().getDeclaredFields();
	      for (Field field : fields) {
	        field.setAccessible(true);
	        searchField(objects, o, field, query);
	      }
	    }
	    return objects;
	  }

	  private void searchField(List objects, Object o, Field field, String query) {
	    String[] querys = parseQuery(query);
	    if (Collection.class.isAssignableFrom(field.getType()))
	    {
	      try
	      {
	        Collection collection = (Collection)field.get(o);
	        
	        if(collection != null){
	        	 for (Iterator localIterator = collection.iterator(); localIterator.hasNext(); )
	 	        {
	 	        	Object o1 = localIterator.next();
	 	        	objects.addAll(search(o1, query)); 
	 	          }
	        }
	       
	      }
	      catch (IllegalAccessException e) {
	        e.printStackTrace();
	      }
	    }
	    else if (field.getType().getSimpleName().equals(querys[1])){
	    	try {
		        Object o1 = field.get(o);
		        //if (querys[3] == null) {
		          if (filter(o1, querys[2])){
		            //objects.add(o1);
		            objects.addAll(search(o1, query)); 
		        //}
		          }
		        else{
		        	//objects.addAll(search(o1, querys[3]));
		        }
		          
		      }
		      catch (IllegalAccessException e) {
		        e.printStackTrace();
		      }
	    }
	    else{
	    	if( !isBasicType(field.getType() )){
	    	
	    		try {
	    		
	    		Object o1 = field.get(o);
	    		if(o1 != null){
	    			objects.addAll(search(o1, query));
	    		}
//			        Object o1 = field.get(o);
//			        if (querys[3] == null) {
//			          if (filter(o1, querys[2]))
//			            objects.add(o1);
//			        }
//			        else
//			          objects.addAll(search(o1, querys[3]));
			      }
			      catch (IllegalAccessException e) {
			        e.printStackTrace();
			      }
	    	}
	    }
	  }

	  private boolean isBasicType(Class fieldType){
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
	  
	  private boolean filter(Object o, String exp)
	  {
	    if (o == null) {
	      return false;
	    }
	    if (exp == null) {
	      return true;
	    }
	    boolean f = true;
	    String s = exp.substring(1, exp.length() - 1);
	    String[] strings = s.split(" ");
	    List objects = new ArrayList();
	    for (String s0 : strings) {
	      if (s0!=null && !"".equals(s0) ) {
	        objects.add(s0);
	      }
	    }
	    List list1 = parseExp(o, objects, "and");
	    List list = parseExp(o, list1, "or");
	    for (Iterator i = list.iterator(); ((Iterator)i).hasNext(); ) {
	      Object o1 = ((Iterator)i).next();
	      if ((o1 instanceof Boolean))
	        f = (f) && (((Boolean)o1).booleanValue());
	      else {
	        f = (f) && (filterExp(o, o1.toString()));
	      }
	    }
	    return f;
	  }

	  private List parseExp(Object o, List exp, String con)
	  {
	    for (int i = 0; i < exp.size(); i++) {
	      if (exp.get(i).toString().equalsIgnoreCase(con)) {
	        List list = new ArrayList();

	        Object left = exp.get(i - 1);
	        Object right = exp.get(i + 1);
	        Boolean b1;
	        if ((left instanceof Boolean))
	          b1 = (Boolean)left;
	        else
	          b1 = Boolean.valueOf(filterExp(o, left.toString()));
	        Boolean b2;
	        if ((right instanceof Boolean))
	          b2 = (Boolean)right;
	        else {
	          b2 = Boolean.valueOf(filterExp(o, right.toString()));
	        }
	        if (i > 2) {
	          for (int j = 0; j < i - 1; j++) {
	            list.add(exp.get(j));
	          }
	        }
	        if (con.equalsIgnoreCase("and"))
	          list.add(Boolean.valueOf((b1.booleanValue()) && (b2.booleanValue())));
	        else {
	          list.add(Boolean.valueOf((b1.booleanValue()) || (b2.booleanValue())));
	        }

	        if (i < exp.size() - 2) {
	          for (int m = i + 2; m < exp.size(); m++) {
	            list.add(exp.get(m));
	          }
	        }
	        return parseExp(o, list, con);
	      }
	    }
	    return exp;
	  }

	  private boolean filterExp(Object o, String exp) {
	    if (exp == null) {
	      return true;
	    }
	    if (exp.contains("!=")) {
	      Object[] objects = parseExp(o, exp, "!=");
	      return (objects[1] != null) && (!objects[0].toString().equals(objects[1].toString()));
	    }
	    if (exp.contains(">=")) {
	      Object[] objects = parseExp(o, exp, ">=");
	      if ((objects[1] != null) && 
	        ((objects[1] instanceof Number))) {
	        return Double.valueOf(objects[1].toString()).doubleValue() >= Double.valueOf(objects[0].toString()).doubleValue();
	      }
	    }

	    if (exp.contains("<=")) {
	      Object[] objects = parseExp(o, exp, "<=");
	      if ((objects[1] != null) && 
	        ((objects[1] instanceof Number))) {
	        return Double.valueOf(objects[1].toString()).doubleValue() <= Double.valueOf(objects[0].toString()).doubleValue();
	      }
	    }

	    if (exp.contains("=")) {
	      Object[] objects = parseExp(o, exp, "=");
	      return (objects[1] != null) && (objects[0].toString().equals(objects[1].toString()));
	    }
	    if (exp.contains(">")) {
	      Object[] objects = parseExp(o, exp, ">");
	      if ((objects[1] != null) && 
	        ((objects[1] instanceof Number))) {
	        return Double.valueOf(objects[1].toString()).doubleValue() > Double.valueOf(objects[0].toString()).doubleValue();
	      }
	    }

	    if (exp.contains("<")) {
	      Object[] objects = parseExp(o, exp, "<");
	      if ((objects[1] != null) && 
	        ((objects[1] instanceof Number))) {
	        return Double.valueOf(objects[1].toString()).doubleValue() < Double.valueOf(objects[0].toString()).doubleValue();
	      }
	    }

	    return false;
	  }

	  private Object[] parseExp(Object o, String exp, String connector) {
	    Object[] objects = new Object[2];
	    String[] strings = exp.split(connector);
	    String attr = strings[0].replace("@", "");
	    String value = strings[1].replace("'", "");
	    objects[0] = value;
	    try {
	      Field field = o.getClass().getDeclaredField(attr);
	      field.setAccessible(true);
	      objects[1] = field.get(o);

	      if(field.getType() == Date.class){
	      	objects[0] = new SimpleDateFormat( TestCaseUtils.DATE_FORMAT ).parse(value);
		  }

	    } catch (Exception localException) {
	    }
	    return objects;
	  }

	  private  String[] parseQuery(String query) {
	    String[] strings = new String[4];
	    char[] chars = query.toCharArray();
	    StringBuilder builder = new StringBuilder();
	    int start = 1;

	    if (chars[1] == '/') {
	      start = 2;
	      strings[0] = "//";
	    } else {
	      strings[0] = "/";
	    }
	    for (int i = start; i < chars.length; i++) {
	      if (chars[i] == '/') {
	        strings[3] = query.substring(i);
	        break;
	      }
	      builder.append(chars[i]);
	    }
	    String s = builder.toString();
	    int l = s.indexOf("[");
	    if (l != -1) {
	      strings[1] = s.substring(0, l);
	      strings[2] = s.substring(l);
	    } else {
	      strings[1] = s;
	      strings[2] = null;
	    }

	    return strings;
	  }

	  public  boolean isNull(Object o) {
	    return o == null;
	  }
}
