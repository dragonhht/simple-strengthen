package simple.strengthen.proxy.advice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-23
 */
public class BaseAdvice {

    /**
     * 执行方法
     * @param object 执行方法的对象
     * @param methodName 执行的方法名
     * @param methodParams 执行的方法的参数类型
     * @param args 执行的方法的参数
     * @return 执行的方法的返回值
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    protected Object invoke(Object object, String methodName, Class<?>[] methodParams, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = object.getClass().getMethod(methodName, methodParams);
        Object result = method.invoke(object, args);
        return result;
    }

}
