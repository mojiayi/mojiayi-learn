package com.mojiayi.learn.log4j2;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/4/16.
 */
@Controller
@RequestMapping("/log4j2Test")
public class Log4j2TestController {
    private static final Logger debugLog = LogManager.getLogger("debugLog");
    private static final Logger infoLog = LogManager.getLogger("infoLog");
    private static final Logger warnLog = LogManager.getLogger("warnLog");

    @RequestMapping(value = "/debugLog", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject debugLog() {
        debugLog.debug("output debug log to file");
        JSONObject json = new JSONObject();
        json.put("status", 0);
        json.put("msg", "debugLog");
        return json;
    }

    @RequestMapping(value = "/infoLog", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject infoLog() {
        infoLog.info("output info log to file,info");
        JSONObject json = new JSONObject();
        json.put("status", 0);
        json.put("msg", "infoLog");
        json.put("sysTime", System.currentTimeMillis());
        return json;
    }

    @RequestMapping(value = "/warnLog", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject warnLog() {
        warnLog.info("output warn log to file,info");
        warnLog.warn("output warn log to file,warn");
        warnLog.error("output warn log to file,error");
        JSONObject json = new JSONObject();
        json.put("status", 0);
        json.put("msg", "warnLog");
        json.put("sysTime", System.currentTimeMillis());
        return json;
    }
}
