/**
 * SUNING APPLIANCE CHAINS.
 * Copyright (c) 2012-2012 All Rights Reserved.
 */
package com.example.crudproject.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述： 基类controller
 *
 * @author 作者 陈琦
 * @version 1.0.0
 * @created 2012-6-25 上午11:52:11
 * @checkin 19051942 by 2019/11/01
 * @date 2012-6-25 上午11:52:11
 */

public class BaseController {

    static Logger log = LoggerFactory.getLogger(BaseController.class);
    public static final String VIEW = "view";
    public static final String LIST = "list";
    public static final String STATUS = "status";
    public static final String WARN = "warn";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    public static final String MESSAGES = "messages";
    public static final String CONTENT = "content";
    public static final Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

    /**
     * AJAX输出，返回null
     *
     * @param content
     * @param type
     * @return
     */
    public String ajax(HttpServletResponse response, String content, String type) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            log.error("IOException:", e);
        }
        return null;
    }


    /**
     * AJAX输出文本，返回null
     *
     * @param text
     * @return
     */
    public String ajaxText(HttpServletResponse response, String text) {
        return ajax(response, text, "text/plain");
    }

    /**
     * AJAX输出HTML，返回null
     *
     * @param html
     * @return
     */
    public String ajaxHtml(HttpServletResponse response, String html) {
        return ajax(response, html, "text/html");
    }

    /**
     * AJAX输出XML，返回null
     *
     * @param xml
     * @return
     */
    public String ajaxXml(HttpServletResponse response, String xml) {
        return ajax(response, xml, "text/xml");
    }

    /**
     * 根据字符串输出JSON，返回null
     *
     * @param jsonString
     * @return
     */
    public String ajaxJson(HttpServletResponse response, String jsonString) {
        return ajax(response, jsonString, "text/html");
    }

    /**
     * 根据Map输出JSON，返回null
     *
     * @param jsonMap
     * @return
     */
    public String ajaxJson(HttpServletResponse response, Map<String, String> jsonMap) {
        return ajax(response, gson.toJson(jsonMap), "text/html");
    }

    /**
     * 输出JSON警告消息，返回null
     *
     * @param message
     * @return
     */
    public String ajaxJsonWarnMessage(HttpServletResponse response, String message) {
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put(STATUS, WARN);
        jsonMap.put(MESSAGE, message);
        return ajax(response, gson.toJson(jsonMap), "text/html");
    }

    /**
     * 输出JSON警告消息，返回null
     *
     * @param messages
     * @return
     */
    public String ajaxJsonWarnMessages(HttpServletResponse response, List<String> messages) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put(STATUS, WARN);
        jsonMap.put(MESSAGES, messages);
        return ajax(response, gson.toJson(jsonMap), "text/html");
    }

    /**
     * 输出JSON成功消息，返回null
     *
     * @param message
     * @return
     */
    public String ajaxJsonSuccessMessage(HttpServletResponse response, String message) {

        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put(STATUS, SUCCESS);
        jsonMap.put(MESSAGE, message);
        return ajax(response, gson.toJson(jsonMap), "text/html");
    }

    /**
     * 输出JSON成功消息，返回null
     *
     * @param messages
     * @return
     */
    public String ajaxJsonSuccessMessages(HttpServletResponse response, List<String> messages) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put(STATUS, SUCCESS);
        jsonMap.put(MESSAGES, messages);
        return ajax(response, gson.toJson(jsonMap), "text/html");
    }

    /**
     * 输出JSON错误消息，返回null
     *
     * @param message
     * @return
     */
    public String ajaxJsonErrorMessage(HttpServletResponse response, String message) {
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put(STATUS, ERROR);
        jsonMap.put(MESSAGE, message);
        return ajax(response, gson.toJson(jsonMap), "text/html");
    }

    /**
     * 输出JSON错误消息，返回null
     *
     * @param messages
     * @return
     */
    public String ajaxJsonErrorMessages(HttpServletResponse response, List<String> messages) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put(STATUS, ERROR);
        jsonMap.put(MESSAGES, messages);
        return ajax(response, gson.toJson(jsonMap), "text/html");
    }

    /**
     * 设置页面不缓存
     */
    public void setResponseNoCache(HttpServletResponse response) {
        response.setHeader("progma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
    }
}
