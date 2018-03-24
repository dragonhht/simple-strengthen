package simple.strengthen.aop;

import simple.strengthen.model.AdviceModel;
import simple.strengthen.model.AopModel;
import simple.strengthen.proxy.ProxyHandler;
import simple.strengthen.proxy.advice.ProxyAdvice;
import simple.strengthen.utils.GetBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-24
 */
public class Aop {

    /** 前置. */
    private ProxyAdvice before;
    /** 后置. */
    private ProxyAdvice after;

    public Aop(AopModel model) throws Throwable {
        initAop(model);
    }

    public Aop(ProxyAdvice before, ProxyAdvice after) {
        this.before = before;
        this.after = after;
    }

    /**
     * 执行方法.
     * @param className 需执行的方法的类名
     * @param methodName 需执行的方法
     * @param paramTypes 执行方法的参数类型
     * @param args 执行方法的参数
     * @return 执行方法的返回值
     * @throws Throwable
     */
    public Object execut(String className, String methodName, Class<?>[] paramTypes, Object... args) throws Throwable {
        GetBean getBean = GetBean.getInstance();
        Object obj = getProxyObject(className);
        return getBean.invokeMethod(obj, methodName, paramTypes, args);
    }

    public Object execut(String className, String returnType, String methodNameWithParams, Object... args) throws Throwable {
        GetBean getBean = GetBean.getInstance();
        Object obj = getProxyObject(className);
        return getBean.invokeMethod(obj, returnType,  methodNameWithParams, args);
    }

    /**
     * 获取代理对象.
     * @param className 对象的类名
     * @return
     * @throws Throwable
     */
    private Object getProxyObject(String className) throws Throwable {
        GetBean getBean = GetBean.getInstance();
        Object object = getBean.getBean(className);
        InvocationHandler handler = new ProxyHandler(object, before, after);
        Class classInterface = getBean.getInterface(className);
        Object obj = Proxy.newProxyInstance(getBean.getClass().getClassLoader(), object.getClass().getInterfaces(), handler);
        return obj;
    }

    /**
     * 传入xml读取的信息，初始化Aop类.
     * @param model AopModel类信息(xml信息)
     * @throws Throwable
     */
    public void initAop(AopModel model) throws Throwable {
        GetBean getBean = GetBean.getInstance();
        if (model.getAdvices().size() > 0) {
            for (AdviceModel advice : model.getAdvices()) {
                if ("before".equals(advice.getLocaltion())) {
                    Object obj = getBean.getBean(advice.getClassName());
                    before = new ProxyAdvice(obj, advice.getMethodName(), null);
                }
                if ("after".equals(advice.getLocaltion())) {
                    Object obj = getBean.getBean(advice.getClassName());
                    after = new ProxyAdvice(obj, advice.getMethodName(), null);
                }
            }
        }
    }

}
