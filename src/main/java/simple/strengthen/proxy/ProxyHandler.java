package simple.strengthen.proxy;

import simple.strengthen.proxy.advice.ProxyAdvice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-23
 */
public class ProxyHandler implements InvocationHandler {

    /** 代理的类. */
    private Object target;
    /** 前置. */
    private ProxyAdvice before;
    /** 后置. */
    private ProxyAdvice after;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    public ProxyHandler(Object target, ProxyAdvice before, ProxyAdvice after) {
        this.target = target;
        this.before = before;
        this.after = after;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if (before != null) {
            // 执行前置方法
            before.invoke();
        }
        result = method.invoke(target, args);
        if (after != null) {
            // 执行后置方法
            after.invoke();
        }
        return result;
    }

}
