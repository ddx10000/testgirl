package com.example.demo.testAopProxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author DongDexuan
 * @date 2018/8/21 14:42
 * @desc
 */
public class InjectBeanSelfProcessor
        implements BeanPostProcessor, ApplicationContextAware {
    ApplicationContext context;

    private static Log log = LogFactory.getLog(InjectBeanSelfProcessor.class);

    public InjectBeanSelfProcessor() {

    }


    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.context = context;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof BeanSelfAware) {//如果Bean实现了BeanSelfAware标识接口，就将代理对象注入
            BeanSelfAware myBean = (BeanSelfAware) bean;
            if (!AopUtils.isAopProxy(bean)) {
                Class c = bean.getClass();
                Service serviceAnnotation = (Service) c.getAnnotation(Service.class);
                if (serviceAnnotation != null)
                    try {
                        bean = context.getBean(beanName);
                        if (AopUtils.isAopProxy(bean)) ;
                    } catch (BeanCurrentlyInCreationException beancurrentlyincreationexception) {
                    } catch (Exception ex) {
                        log.fatal((new StringBuilder()).append("No Proxy Bean for service ").append(bean.getClass()).append(" ").append(ex.getMessage()).toString(), ex);
                    }
            }
            myBean.setSelf(bean);
            return myBean;
        } else {
            return bean;
        }
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }
}
