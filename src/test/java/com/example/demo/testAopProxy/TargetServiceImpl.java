package com.example.demo.testAopProxy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DongDexuan
 * @date 2018/8/21 14:44
 * @desc
 */
@Service
public class TargetServiceImpl implements TargetService,BeanSelfAware
{
    private TargetService self;
    public void setSelf(Object proxyBean)
    { //通过InjectBeanSelfProcessor注入自己（目标对象）的AOP代理对象
        this.self = (TargetService) proxyBean;
    }
    public void a()
    {
        self.b();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void b()
    {
        //执行数据库操作
    }
}
