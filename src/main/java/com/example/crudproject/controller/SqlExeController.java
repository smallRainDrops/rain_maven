package com.example.crudproject.controller;

import com.example.crudproject.dto.QueryResult;
import com.example.crudproject.service.Impl.SqlExeService;
import com.example.crudproject.utils.CSVUtils;
import com.example.crudproject.utils.DateUtils;
import com.example.crudproject.utils.FileDownloadUtil;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.Gson;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 14041665
 */
@RestController
@RequestMapping("/sssabc")
public class SqlExeController extends BaseController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected SqlExeService sqlExeService;

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/sqlExeOneTableList")
    public String sqlExeOneTableList(String sqlStr) {
        int limit = 10000;
        // 结果行数
        QueryResult dataList = new QueryResult<Object[]>();
        Object obj = sqlExeService.sqlExe(sqlStr, limit, null);
        if (null != obj) {
            dataList = (QueryResult<Object[]>) obj;
            return new Gson().toJson(dataList.getDatas());
        }
        return "flase";
    }

    /**
     * 导出数据
     *
     * @param response
     */
    @PostMapping("/sqlExeOneTableExpData")
    public String sqlExeOneTableExpData(String sqlStr, HttpServletResponse response) {
        int limit = 10000;
        String content = sqlExeService.sqlExeExpData(sqlStr, limit);
        String filename = System.currentTimeMillis() + ".csv";
        FileDownloadUtil.downloadFileCsv(filename, content, response, "application/x-msdownload");
        return "ok";
    }

    /**
     * 导出数据
     *
     * @param response
     */
    @PostMapping("/sqlExeExpData")
    public String sqlExeExpData(String sqlStr, HttpServletResponse response) {
        int limit = 10000;
        sqlExeService.sqlSpecExeExpData(sqlStr, limit);
        return "OK";
    }
/**
 * 获取 localIP
 */
//    public static void main(String[] args) {
//        String localIP = getLocalIP();
//        System.out.println(localIP);
//    }
//
//    public static String getLocalIP() {
//        InetAddress addr = null;
//        try {
//            addr = InetAddress.getLocalHost();
//        } catch (UnknownHostException e) {
//            return "";
//        }
//        byte[] ipAddr = addr.getAddress();
//        String ipAddrStr = "";
//        for (int i = 0; i < ipAddr.length; i++) {
//            if (i > 0) {
//                ipAddrStr += ".";
//            }
//            ipAddrStr += ipAddr[i] & 0xFF;
//        }
//        return ipAddrStr;
//    }
}