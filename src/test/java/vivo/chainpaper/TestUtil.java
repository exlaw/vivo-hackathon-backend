package vivo.chainpaper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class TestUtil {
    /* 该方法用于传入某实例对象以及对象方法名，通过反射调用该对象的某个get方法 */
    public static void getProperty(Object beanObj){
        try {
            Field[] fields = beanObj.getClass().getDeclaredFields();//获得属性
            Class clazz = beanObj.getClass();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                // 此处应该判断beanObj,property不为null
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                if (getMethod != null) {
                    System.out.println(beanObj+"的字段是:"+field.getName()+"，类型是："+field.getType()+"，取到的值是： "+getMethod.invoke(beanObj));
                }
            }
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
        } catch (InvocationTargetException e) {
            //e.printStackTrace();
        } catch (IntrospectionException e) {
            //e.printStackTrace();
        }
    }

    /* 该方法用于传入某实例对象以及对象方法名、修改值，通过放射调用该对象的某个set方法设置修改值 */
    public static void setProperty(Object beanObj,
                                   Object value){
        try {
            Field[] fields = beanObj.getClass().getDeclaredFields();//获得属性
            Class clazz = beanObj.getClass();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                // 此处应该判断beanObj,property不为null
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), beanObj.getClass());
                Method setMethod = pd.getWriteMethod();
                if (setMethod != null) {
                    System.out.println(beanObj+"的字段是:"+field.getName()+"，参数类型是："+field.getType()+"，set的值是： "+value);
                    //这里注意实体类中set方法中的参数类型，如果不是String类型则进行相对应的转换
                    if(field.getType().toString().equals("int")){
                        int a=0;
                        setMethod.invoke(beanObj,a);
                    }else if(field.getType().toString().equals("class java.lang.String")) {
                        setMethod.invoke(beanObj, value);//invoke是执行set方法
                    }else if(field.getType().toString().equals("interface java.util.List")) {
                        setMethod.invoke(beanObj,new ArrayList<String>());
                    }else if(field.getType().toString().equals("long")) {
                        setMethod.invoke(beanObj,1l);
                    }else if(field.getType().toString().equals("boolean")) {
                        setMethod.invoke(beanObj,true);
                    }
                }
            }
        } catch (SecurityException e) {
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
        } catch (InvocationTargetException e) {
            //e.printStackTrace();
        } catch (IntrospectionException e) {
           // e.printStackTrace();
        }
    }
}
