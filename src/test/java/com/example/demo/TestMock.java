package com.example.demo;

import org.easymock.EasyMock;
import org.junit.Test;

/**
 * @author DongDexuan
 * @date 2018-6-28 13:51
 * @desc
 */
public class TestMock {

    @Test
    public void test(){
        ApplianceService mock = EasyMock.createMock(ApplianceService.class);
        mock.test1();
        EasyMock.expectLastCall().andStubReturn("mock.....");
        mock.test2();
        EasyMock.expectLastCall().andStubReturn("mock2.....");
        EasyMock.replay(mock);

        System.out.println(mock.test1());
        System.out.println(mock.test2());

        ApplianceService applianceService1 = new ApplianceService();
        System.out.println(applianceService1.test1());
        System.out.println(applianceService1.test2());

        ApplianceService applianceService = new ApplianceService() {
            @Override
            public String test1() {
                return "mock1...";
            }
        };
        System.out.println(applianceService.test1());
        System.out.println(applianceService.test2());
    }
}
