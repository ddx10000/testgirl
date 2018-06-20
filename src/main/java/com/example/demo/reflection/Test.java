package com.example.demo.reflection;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
@MqttMapper("/test")
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    private static final String MQTT_INIT_PACKAGE = "com.example.demo.reflection";

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        MqttConnPool.init(MQTT_INIT_PACKAGE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dong","dexuan");
        invoke(123, "/test/test", jsonObject);
    }

    @MqttMapper("/test")
    public void test(int chanelId, JSONObject data){
        System.out.println(chanelId + " " + data.toJSONString());
    }

    public static Object invoke(int chanelId, String reqTopic, JSONObject data)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object object = null;
        MethodAccess methodAccess = MqttConnPool.getByUrl(
                reqTopic.equalsIgnoreCase("/transport") ?
                        reqTopic + data.getString("topic") : reqTopic
        );
        if (null != methodAccess) {
            logger.debug("PublishController starts topic {} params [{}]", reqTopic, data);
            object = methodAccess.getMethod().invoke(methodAccess.getObj(), chanelId, data);
            logger.debug("PublishController ends topic {} result [{}]", reqTopic, object);
        } else {
            logger.error("upstream command does not exist: {} ", reqTopic);
        }
        return object;
    }
}
