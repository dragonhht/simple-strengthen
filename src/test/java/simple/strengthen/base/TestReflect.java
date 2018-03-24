package simple.strengthen.base;

import org.junit.Test;
import simple.strengthen.aop.Aop;
import simple.strengthen.bean.Advice;
import simple.strengthen.bean.Student;
import simple.strengthen.model.AdviceModel;
import simple.strengthen.model.AopModel;
import simple.strengthen.proxy.advice.ProxyAdvice;
import simple.strengthen.proxy.ProxyHandler;
import simple.strengthen.utils.GetBean;
import simple.strengthen.utils.AopXmlUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-23
 */
public class TestReflect {

    @Test
    public void testGetBeanInvokeMethod2() throws Exception {
        GetBean getBean = GetBean.getInstance();
        String className = "simple.strengthen.bean.Student";
        String methodName = "print";
        Class<?>[] parameterTypes = {int.class, String.class};
        getBean.invokeMethod(className, methodName, parameterTypes, 18, "南昌");
    }

    @Test
    public void testGetBeanInvokeMethod3() throws Exception {
        GetBean getBean = GetBean.getInstance();
        String className = "simple.strengthen.bean.Student";
        String methodName = "getAddress";
        Class<?>[] parameterTypes = {};
        Object object = getBean.invokeMethod(className, methodName, parameterTypes);
        System.out.println(object);
    }

    @Test
    public void testProxy() throws Exception {
        GetBean getBean = GetBean.getInstance();
        String className = "simple.strengthen.bean.Student";
        Object student = getBean.getBean(className);
        InvocationHandler handler = new ProxyHandler(student);
        Class classInterface = getBean.getInterface(className);
        Class<?>[] params = {int.class, String.class};
        Object stu = classInterface.cast(Proxy.newProxyInstance(getBean.getClass().getClassLoader(), student.getClass().getInterfaces(), handler));
        getBean.invokeMethod(stu, "print", params, 18, "南昌");
    }

    @Test
    public void testGetInterface() throws Exception {
        GetBean getBean = GetBean.getInstance();
        String className = "simple.strengthen.bean.Student";
        String methodName = "getAddress";
        Class clas = getBean.getClass(className);
        System.out.println(clas.getGenericSuperclass().getTypeName());

        Class classes = getBean.getInterface(clas);
        Student student = new Student();
        Object o = classes.cast(student);
        System.out.println(o);
    }

    @Test
    public void testProxy1() throws Throwable {

        Advice advice = new Advice();
        ProxyAdvice before = new ProxyAdvice(advice, "before", null);
        ProxyAdvice after = new ProxyAdvice(advice, "after", null);

        String className = "simple.strengthen.bean.Student";
        String methodName = "print";
        Class<?>[] params = {int.class, String.class};
        Aop aop = new Aop(before, after);
        aop.execut(className, methodName, params, 19, "南昌");
    }

    @Test
    public void testXml() throws Exception {
        String xmlPath = "resources/config/aop.xml";
        AopModel model = AopXmlUtil.parserXml(xmlPath);
        System.out.println(model);
    }

    @Test
    public void testAop1() throws Throwable {
        String xmlPath = "resources/config/aop.xml";
        AopModel model = AopXmlUtil.parserXml(xmlPath);
        Aop aop = new Aop(model);
        aop.execut(model.getClassName(), model.getReturnType(), model.getExecut(), 18, "江西");
    }

    @Test
    public void testClassType() throws Exception {
        String methodName = "print(int,java.lang.String)";
        GetBean getBean = GetBean.getInstance();
        Class<?> clas = getBean.getClass("simple.strengthen.bean.Student");
        Method[] methods = clas.getMethods();
        for (Method method : methods) {
            //System.out.println(method);
            if (method.toString().contains(methodName)) {
                System.out.println(method);
            }
        }
    }

}
