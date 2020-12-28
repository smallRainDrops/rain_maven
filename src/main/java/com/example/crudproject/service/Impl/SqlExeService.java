package com.example.crudproject.service.Impl;

import com.example.crudproject.dto.QueryResult;
import com.example.crudproject.utils.CSVUtils;
import com.example.crudproject.utils.DateUtils;
import com.example.crudproject.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 14041665
 */
@Service
public class SqlExeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataSource readDataSource;

    @Autowired
    private DataSource writeDataSource;

    /**
     * @param sqlStr
     * @param limit
     * @return @
     */
    public Object sqlExe(String sqlStr, int limit, String loginName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement st = null;
        ResultSet rs = null;
        Object obj = null;
        try {

            if (!StringUtils.isEmpty(sqlStr)) {
                if (sqlStr.trim().toUpperCase().startsWith("SELECT")) {
                    conn = readDataSource.getConnection();
                    QueryResult<Object[]> dataList = new QueryResult<Object[]>();
                    List<Object[]> objectList = new ArrayList<Object[]>();
                    String sqlLimitStr = sqlStr + " LIMIT 0, ?";//NOSONAR
                    pstmt = conn.prepareStatement(sqlLimitStr);//NOSONAR
                    pstmt.setInt(1, limit);
                    rs = pstmt.executeQuery();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    Object objArray[] = new Object[columnCount];
                    // 列名
                    for (int i = 1; i <= columnCount; i++) {
                        objArray[i - 1] = rsmd.getColumnName(i);
                    }
                    objectList.add(objArray);
                    // 数据
                    while (rs.next()) {
                        objArray = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            objArray[i - 1] = rs.getString(i);
                        }
                        if (objArray.length > 0) {
                            objectList.add(objArray);
                        }
                    }
                    dataList.setDatas(objectList);
                    obj = (Object) dataList;
                } else {
                    if (sqlStr.lastIndexOf("&&&&&") > -1) {
                        conn = writeDataSource.getConnection();
                        conn.setAutoCommit(false);
                        sqlStr = sqlStr.replace("&&&&&", "");
                        st = conn.createStatement();
                        int count = st.executeUpdate(sqlStr);
                        conn.commit();
                        obj = (Object) count;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("SqlExeServiceImpl#sqlExe:" + e.toString(), e);
            throw new RuntimeException(e.toString(), e);
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error(e.toString(), e);
                    rs = null;// 强制释放
                }
            }
            if (null != st) {
                try {
                    st.close();
                } catch (Exception e) {
                    logger.error(e.toString(), e);
                    st = null;// 强制释放
                }
            }

            if (null != pstmt) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    logger.error(e.toString(), e);
                    pstmt = null;// 强制释放
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error(e.toString(), e);
                    conn = null;// 强制释放
                }
            }
        }
        return obj;
    }



    /**
     * @param sqlStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public String sqlExeExpData(String sqlStr, int limit) {
        StringBuilder content = new StringBuilder("");
        // 结果集
        QueryResult<Object[]> dataList;
        Object obj = sqlExe(sqlStr, limit, null);
        if (null != obj) {
            dataList = (QueryResult<Object[]>) obj;
            if (null != dataList.getDatas() && !dataList.getDatas().isEmpty()) {
                List<Object[]> objectList = dataList.getDatas();
                for (int i = 0; i < objectList.size(); i++) {
                    Object[] objNum = objectList.get(i);
                    for (int j = 0; j < objNum.length; j++) {
                        content.append(StringUtil.addCsvToObj(objNum[j]));
                    }
                    content.append("\n");
                }
            }
        }
        return content.toString();
    }


    /**
     * @param sqlStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public String sqlSpecExeExpData(String sqlStr, int limit) {
        // 固定抬头
        LinkedHashMap map = new LinkedHashMap();
        List exportData = new ArrayList<Map>();
        String path = "D:\\JD\\";
        // step的 本地路径
        String fileName = "DOWNLOAD_DATA_" + System.currentTimeMillis();
        // 结果集
        QueryResult<Object[]> dataList;
        Object obj = sqlExe(sqlStr, limit, null);
        if (null != obj) {
            dataList = (QueryResult<Object[]>) obj;
            if (null != dataList.getDatas() && !dataList.getDatas().isEmpty()) {
                List<Object[]> objectList = dataList.getDatas();
                for (int i = 0; i < objectList.size(); i++) {
                    Object[] objNum = objectList.get(i);
                    if(ObjectUtils.isEmpty(objNum)){
                        continue;
                    }
                    Map row2 = new LinkedHashMap<String, String>();
                    for (int j = 0; j < objNum.length; j++) {
                        if(i == 0){
                            map.put(StringUtil.valueOf(j+1), StringUtil.addCsvToObj(objNum[j]));
                        continue;
                        }else{
                            row2.put(StringUtil.valueOf(j+1), StringUtil.addCsvToObj(objNum[j]));
                        }
                    }
                    if(i> 0){
                        exportData.add(row2);
                    }
                }
            }
        }

        File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
        return file.getName();
    }


}