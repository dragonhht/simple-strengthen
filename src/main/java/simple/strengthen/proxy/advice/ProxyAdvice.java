package simple.strengthen.proxy.advice;

import lombok.Data;
import simple.strengthen.proxy.advice.BaseAdvice;

import java.lang.reflect.InvocationTargetException;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-24
 */
@Data
public class ProxyAdvice extends BaseAdvice {

    /** 执行方法的对象. */
    private Object target;
    /** 执行的方法名. */
    private String methodName;
    /** 执行的方法的参数类型. */
    Class<?>[] methodParams;
    /** 执行的方法的参数. */
    Object[] args;

    public ProxyAdvice(Object target) {
        this.target = target;
    }

    public ProxyAdvice(Object target, String methodName, Class<?>[] methodParams, Object... args) {
        this.target = target;
        this.methodName = methodName;
        this.methodParams = methodParams;
        this.args = args;
    }

    @Override
    protected Object invoke(Object object, String methodName, Class<?>[] methodParams, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return super.invoke(object, methodName, methodParams, args);
    }

    public Object invoke() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invoke(target, methodName, methodParams, args);
    }
}
