package com.example.crudproject.utils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.store.CharUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class FileDownloadUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileDownloadUtil.class);

    private FileDownloadUtil() {
        //按照sonar要求，加上私有共造函数
    }

    /**
     * 文件下载
     *
     * @param filename    下载到客户端时的文名(含后缀名)
     * @param in          文件内容流
     * @param response    客户端响应对象
     * @param contentType 文件类型
     */
    public void downLoadFile(String filename, InputStream in, HttpServletResponse response, String contentType, HttpServletRequest request) {
        try {
            if (null != contentType && !"".equals(contentType)) {
                response.setContentType(contentType);
            } else {
                response.setContentType("application/x-msdownload"); // x-msexcel
            }
            response.setCharacterEncoding("utf-8");
            String agent = request.getHeader("User-Agent");
            if (agent.toLowerCase().indexOf("msie") > -1) {
                filename = this.encodingDownloadFileName(filename, request.getCharacterEncoding());
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            } else {
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + new String(filename.getBytes(request.getCharacterEncoding()), "iso-8859-1") + "\"");
            }
        } catch (Exception ex) {
            logger.error(ex.toString(), ex);
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(in);
            bos = new BufferedOutputStream(response.getOutputStream());
            logger.info("下载文件!");
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            logger.error(e.toString(), e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e1) {
                logger.error(e1.toString(), e1);
            }
        }
    }
    public String encodingDownloadFileName(String fileName, String charset) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString(), e);
        }
        return returnFileName;
    }
    /**
     * 文件下载
     *
     * @param filename    下载到客户端时的文名(含后缀名)
     * @param content     文件内容
     * @param response    客户端响应对象
     * @param contentType 文件类型
     */
    public static void downLoadFileFromStr(String filename, String content, HttpServletResponse response, String contentType) {
        try {
            if (null == content || "".equals(content)) {
                logger.error("传入的文件内容错误,或长度过大!");
                return;
            }
            if (null != contentType && !"".equals(contentType)) {
                response.setContentType(contentType);
            } else {
                response.setContentType("application/x-msdownload"); // x-msexcel
            }
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(filename.getBytes(), "ISO-8859-1")
                    + "\"");
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex.toString(), ex);
        }
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = content.getBytes("UTF-8");
            bos.write(buff, 0, buff.length);
            bos.flush();
        } catch (final IOException e) {
            logger.error(e.toString(), e);
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e) {
                logger.error(e.toString(), e);
            }
        }
    }

    public static void downloadFileCsv(String filename, String content, HttpServletResponse response, String contentType) {
        try {
            if (null == content || "".equals(content)) {
                logger.error("传入的文件内容错误!");
                return;
            }
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(filename.getBytes(), "UTF-8")
                    + "\"");
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex.toString(), ex);
        }
        OutputStreamWriter ow = null;
        try {
            ow = new OutputStreamWriter(response.getOutputStream(), "UTF-16LE");
            ow.write(0xFEFF);
            ow.write(content);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            logger.error("Download file error :" + e.toString());
        } finally {
            if (null != ow) {
                try {
                    ow.flush();
                    ow.close();
                } catch (IOException e) {
                    logger.error(e.toString(), e);
                }
            }
        }
    }

    /**
     * 文件下载
     *
     * @param filename    下载到客户端时的文名(含后缀名)
     * @param content     文件内容
     * @param response    客户端响应对象
     * @param contentType 文件类型
     */
    public static void downLoadFileXml(String filename, String content, HttpServletResponse response, String contentType) {
        try {
            if (null == content || "".equals(content)) {
                logger.error("传入的文件内容错误!");
                return;
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType(contentType + ";charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(filename.getBytes(), "UTF-8")
                    + "\"");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString(), e);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes());
        BufferedOutputStream bos = null;
        try {
            byte[] buf = new byte[1024];
            int len = 0;
            bos = new BufferedOutputStream(response.getOutputStream());
            while ((len = in.read(buf, 0, buf.length)) > 0){
                bos.write(buf, 0, len);
            }
        } catch (final IOException e) {
            logger.error(e.toString(), e);
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e) {
                logger.error(e.toString(), e);
            }
        }
    }

    /**
     * 根据指定地址,读取远程服务器指定文件的内容
     *
     * @param remoteFilePath 远程服务器文件路径(如:http://www.aas.suning.com/licence.txt)
     * @param timeOut        连接/读取超时(毫秒),默认5000ms
     * @return 返回文件内容值
     */
    public static String getFileContentFromRemoteServerDiscard(String remoteFilePath, int timeOut) {
        if (null != remoteFilePath && !"".equals(remoteFilePath.trim())) {
            StringBuilder sb = new StringBuilder();
            BufferedReader in = null;
            HttpURLConnection urlCon = null;
            try {
                URL url = new URL(remoteFilePath);
                urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setConnectTimeout(timeOut > 0 ? timeOut : 5000);
                urlCon.setReadTimeout(timeOut > 0 ? timeOut : 5000);
                in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
                String inputLine = "";
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                return sb.toString();
            } catch (MalformedURLException e) {
                logger.error(e.toString(), e);
                logger.error(e.toString(), e);
            } catch (IOException e) {
                logger.error(e.toString(), e);
                logger.error(e.toString(), e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        logger.error(e.toString(), e);
                    }
                }
                if (urlCon != null) {
                    urlCon.disconnect();
                }
            }
            return "";
        } else {
            logger.error("传入的远程文件路径为空!");
        }
        return "";
    }

    //看这地方的是否需要关闭
    //logger.error(e.toString(), e);e.toString()改下
    public static void downloadXSSFWorkbook(XSSFWorkbook workbook, String fileName, HttpServletResponse response) {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setCharacterEncoding("utf-8");
            workbook.write(out);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}