package com.example.demo.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DongDexuan
 * @date 2018/8/3 17:52
 * @desc
 */
@RestController
public class RefreshControler {

    @RequestMapping("refresh/situation")
    public void refreshSituation(Long applianceCode){


    }
}
