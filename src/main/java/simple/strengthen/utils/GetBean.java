package simple.strengthen.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-23
 */
public class GetBean {

    private static final GetBean GET_BEAN = new GetBean();

    private GetBean() {

    }

    public static GetBean getInstance() {
        return GET_BEAN;
    }

    public Class getClass(String className) throws ClassNotFoundException {
        Class<?> clas = Class.forName(className);
        return  clas;
    }

    public Object getBean(Class<?> clas) throws IllegalAccessException, InstantiationException {
        Object obj = clas.newInstance();
        return obj;
    }

    public Object getBean(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = getClass(className);
        Object obj = cls.newInstance();
        return  obj;
    }

    public Method getMethod(String className, String methodName, Class<?>... parameterTypes) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clas = getClass(className);
        Method method = clas.getMethod(methodName, parameterTypes);
        return method;
    }

    public Method getMethod(Class<?> clas, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = clas.getMethod(methodName, parameterTypes);
        return method;
    }

    public Object invokeMethod(Object object, Method method, Object... args) throws InvocationTargetException, IllegalAccessException {
        Object result = method.invoke(object, args);
        return result;
    }

    public Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = object.getClass().getMethod(methodName, parameterTypes);
        Object obj = method.invoke(object, args);
        return obj;
    }

    public Object invokeMethod(String className, String methodName, Class<?>[] parameterTypes, Object... args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clas = getClass(className);
        Method method = getMethod(clas, methodName, parameterTypes);
        Object obj = clas.newInstance();
        Object result = method.invoke(obj, args);
        return result;
    }

    public Class<?> getInterface(Class<?> clas) {
        return clas.getInterfaces()[0];
    }

    public Class<?> getInterface(String className) throws ClassNotFoundException {
        Class<?> clas = getClass(className);
        return clas.getInterfaces()[0];
    }


}
