package com.example.crudproject.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 字符串常用工具类
 * 
 * @author 容小军 2011-4-8
 */
public class StringUtil {
    private static final transient Log log = LogFactory.getLog(StringUtil.class);
    /**
     * 默认分割符
     */
    public static final String DEFAULT_SPLIT = "&";

    private StringUtil() {
        // hide the implicit public one.
    }

    /**
     * 判断字符串是否为空
     * 
     * @param s 字符串
     * @return true：不为空 false ：为空
     */
    public static boolean isNotNull(String s) {
        if (null != s && s.trim().length() != 0) {
            return true;
        }
        return false;
    }

    public static boolean isNull(String s) {
        return !isNotNull(s);
    }

    /**
     * 字符串为空 返 true
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s || s.trim().length() == 0)
            return true;
        return false;
    }
    /**
     * 字符串不为空 返 true
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        if (null != s && s.trim().length() != 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取定长字符串，左补零或者截取定长
     * 
     * @param s 字符串
     * @param length 长度
     * @return 左补零或者截取定长后的字符
     */
    public static String leftFillZero(String s, int length) {
        int fillLength = 0;
        if (s != null) {
            if (s.trim().length() > length) {
                return s.trim().substring(0, length);
            } else {
                fillLength = length - s.length();
            }
        } else {
            fillLength = length;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fillLength; i++) {
            sb.append("0");
        }
        if (s != null) {
            return sb.append(s).toString();
        } else {
            return sb.toString();
        }
    }



    /**
     * 去除字符串中的空格,换行,制表符
     * 
     * @param s 字符串
     * @return 去除空格,换行,制表符的字符串
     */
    public static String trim(String s) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(s);
        String after = m.replaceAll("");
        return after;
    }

    /**
     * 将字符串以某种方式分割成字符串数组
     * 
     * @param s 字符串
     * @param regex 分割方式
     * @return 字符串数组
     */
    public static String[] split(String s, String regex) {
        String[] sa = s.split(regex);
        return sa;
    }

    /**
     * url查询参数a~z 排序(name="aaa"&address="ccc"&city="bbb"->address="ccc"&city="bbb" &name="aaa")
     * 
     * @param parameter 参数列表
     * @param split 分割方式
     * @return 排序后的url
     */
    public static String sortParameter(String parameter, String split) {
        String[] parameters = parameter.split(split);
        Arrays.sort(parameters);
        return StringUtils.join(parameters, split);
    }

    /**
     * url的queryString 自然排序
     * 
     * @param queryString url参数
     * @return 排序后的url
     */
    public static String sortQueryString(String queryString) {
        return sortParameter(queryString, DEFAULT_SPLIT);
    }


    /**
     * 验证数字
     * 
     * @param num
     * @return 0:为空，1：大小不对，2：非数字
     */
    public static int isNum(String num) {
        int result = -1;
        if (num == null || "".equals(num)) {
            result = 0;
        } else {
            try {
                Double db = Double.parseDouble(num);
                if (db < 0) {
                    result = 1;
                }
            } catch (Exception e) {
                log.error(e);
                result = 2;
            }
        }
        return result;
    }

    public static String formatNumber(int num) {
        String str = Integer.toString(num);
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 转化带小数点的数
     * 
     * @param num
     * @return
     */
    public static Integer stringToInteger(String num) {
        String intNum = "";
        if (num.indexOf(".") != -1) {
            intNum = num.substring(0, num.indexOf("."));
        } else {
            intNum = num;
        }
        return Integer.parseInt(intNum);
    }

    public static String addCsvWithDot(String str) {
        String ret = "\t" + str + "\t,";
        return ret;
    }

    public static String addCsvToObjWithDot(Object o) {
        String str = "";
        // 如果为空返回空字符串
        if (o != null) {
            str = String.valueOf(o) + "";
        }
        String ret = "\t" + str + "\t,";
        return ret;
    }

    // 增加过滤换行符和tab符
    public static String addCsvToObj(Object o) {
        String str = "";
        // 如果为空返回空字符串
        if (o != null) {
            str = String.valueOf(o) + "";
        }
        return str + "\t";
    }

    public static String addCsv(String str) {
        return (str == null || "null".equals(str) ? "" : str) + "\t";
    }

    public static String addCsv(Long str) {
        return (str == null ? "" : str) + "\t";
    }

    public static String addCsv(BigDecimal str) {
        return (str == null ? "" : str) + "\t";
    }

    public static String addCsv(Float str) {
        return (str == null ? "" : str) + "\t";
    }

    public static String addCsv(Double str) {
        return (str == null ? "" : str) + "\t";
    }

    public static String addCsv(Integer str) {
        return (str == null ? "" : str) + "\t";
    }

    public static String addXml(String name, String str) {
        String ret = "<" + name + ">" + str + "</" + name + ">";
        return ret;
    }

    public static String addXml(String name, Long str) {
        String ret = "<" + name + ">" + str + "</" + name + ">";
        return ret;
    }

    public static String addXml(String name, BigDecimal str) {
        String ret = "<" + name + ">" + str + "</" + name + ">";
        return ret;
    }

    public static String addXml(String name, Float str) {
        String ret = "<" + name + ">" + str + "</" + name + ">";
        return ret;
    }

    public static String addXml(String name, Double str) {
        String ret = "<" + name + ">" + str + "</" + name + ">";
        return ret;
    }

    public static String addXml(String name, Integer str) {
        String ret = "<" + name + ">" + str + "</" + name + ">";
        return ret;
    }

    /**
     * 使用正则表达式解析tag标签值
     * 
     * @param tagName
     * @param content
     * @return
     */
    public static String getTagValue(String tagName, String content) {
        String compileStr = new StringBuffer("<").append(tagName).append(">").append("(.*?)").append("</").append(tagName).append(">").toString();
        Pattern p = Pattern.compile(compileStr);
        Matcher mat = p.matcher(content);
        if (mat.find()) {
            return mat.group(1);
        } else {
            return "";
        }
    }

    /**
     * 
     * 功能描述：根据不同的类型 ， 对数据进行脱密处理
     * 
     * @param type 只能是 phone , idCode, mail ,name
     * @return 返回脱密处理后的String
     * @throw 异常描述
     */
    public static String encodeMsg(String s, String type) {
        StringBuilder result = new StringBuilder();
        try {
            if ("phone".equals(type)) {
                String firThird = s.substring(0, 3);
                result.append(firThird);
                String mid = "*******";
                result.append(mid);
                result.append(s.charAt(s.length() - 1));
                return result.toString();
            }
            if ("mail".equals(type)) {
                String fir = s.substring(0, s.indexOf("@") - 2);
                String mid = "**";
                String last = s.substring(s.indexOf("@"));
                result.append(fir);
                result.append(mid);
                result.append(last);
                return result.toString();
            }
            if ("idCode".equals(type)) {
                String fir = s.substring(0, 1);
                String last = s.substring(s.length() - 1);
                String mid = "****************";
                result.append(fir);
                result.append(mid);
                result.append(last);
                return result.toString();
            }
            if ("name".equals(type)) {
                String fir = s.substring(0, 1);
                String left;
                if (s.length() == 2) {
                    left = "*";
                } else {
                    left = "**";
                }
                result.append(fir);
                result.append(left);
                return result.toString();
            }
        } catch (Exception e) {
            log.error(e);
            return "";
        }
        return "";
    }



    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 随机生成一个6位数
     * 
     * @return
     */
    public static String random() {
        Random rd = new Random();
        int ret = rd.nextInt(900000) + 100000;
        return Integer.toString(ret);
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 生成短信随即验证码：6位
     * 
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String randomCode() {
        StringBuilder random = new StringBuilder();
        Random rd = new Random();
        random.append(rd.nextInt(9)).append(rd.nextInt(9)).append(rd.nextInt(9)).append(rd.nextInt(9)).append(rd.nextInt(9)).append(rd.nextInt(9));
        return random.toString();
    }

    /**
     * 如果是空字符，则返回null
     * 
     * @param value
     * @return
     * @author zhangdeyuan
     */
    public static String isBlankToNull(String value) {
        if (value == null || value.trim().length() == 0) {
            return null;
        }
        return value;
    }

    /**
     * 去除Xml中的回车和空格
     * 
     * @param string
     * @return
     */
    public static String replaceBlank(String string) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(string);
        String after = m.replaceAll("");
        return after;
    }

    /**
     * 更新字符串指定位的值
     * 
     * @param index 从0开始
     */
    public static String replaceIndexValue(String str, int index, char c) {
        if (isEmpty(str)) {
            return str;
        }
        char[] charAry = str.toCharArray();
        charAry[index] = c;
        return String.valueOf(charAry);
    }

    /**
     * 为空返 null
     * @param value
     * @return
     */
    public static String emptyAsNull(String value) {
        if (value == null) {
            return value;
        }
        if ("".equals(value.trim())) {
            return null;
        } else {
            return value;
        }
    }

    /**
     *
     * @param removeNodeNames
     * @param sourceXml
     * @return
     */
    public static String removeNodes(String[] removeNodeNames, String sourceXml) {
        String result = sourceXml;
        if (removeNodeNames != null) {
            for (String nodeName : removeNodeNames) {
                String remove1 = "<" + nodeName + ">";
                String remove2 = "</" + nodeName + ">";
                String remove3 = "<" + nodeName + "/>";
                result = result.replaceAll(remove1, "");
                result = result.replaceAll(remove2, "");
                result = result.replaceAll(remove3, "");
            }
        }
        return result;
    }

    /**
     * <p>
     * 如果字符串是null,"","  ","null"则都认为是EmptyOrNull
     * </p>
     * 
     * <pre>
     * StringUtil.defaultIfEmptyOrNull(null, "NULL")  = "NULL"
     * StringUtil.defaultIfEmptyOrNull("", "NULL")    = "NULL"
     * StringUtil.defaultIfEmptyOrNull("bat", "NULL") = "bat"
     * StringUtil.defaultIfEmptyOrNull("  ", null)      = null
     * StringUtil.defaultIfEmptyOrNull("null", null)      = null
     * </pre>
     */
    public static String defaultIfEmptyOrNull(String str, String defaultStr) {
        return StringUtil.isEmpty(str) ? defaultStr : ("null".equalsIgnoreCase(str) ? defaultStr : str);
    }

    public static String defaultIfEmptyOrNull(String str) {
        return StringUtil.isEmpty(str) ? "" : ("null".equalsIgnoreCase(str) ? "" : str);
    }

    public static boolean isEmptyContainsNull(String cs) {
        return cs == null || cs.length() == 0 || "null".equals(cs);
    }


    /**
     * 将Object类型对象转化为String类型
     */
    public static String valueOf(Object obj) {
        if (obj == null) {
            return "";
        }
        String str = String.valueOf(obj).trim();
        if ("null".equalsIgnoreCase(str)) {
            return "";
        }
        return str;
    }

    /**
     * 字符串补齐
     * 
     * @param source 源字符串
     * @param fillLength 补齐长度
     * @param fillChar 补齐的字符
     * @param isLeftFill true为左补齐，false为右补齐
     * @return
     * @author zhangdeyuan
     */
    public static String stringFill(String source, int fillLength, char fillChar, boolean isLeftFill) {
        if (source == null || source.length() >= fillLength) {
            return source;
        }
        StringBuilder result = new StringBuilder(fillLength);
        int len = fillLength - source.length();
        if (isLeftFill) {
            for (; len > 0; len--) {
                result.append(fillChar);
            }
            result.append(source);
        } else {
            result.append(source);
            for (; len > 0; len--) {
                result.append(fillChar);
            }
        }
        return result.toString();
    }

    /**
     * 判断字符串中是否有空格
     */
    public static boolean hasWhitespace(String str) {
    	String string = str;
        if (string == null || "".equals(string = string.trim())) {
            return true;
        }
        int temp = string.indexOf(" ");
        return temp > 0;
    }

    /**
     * 截取字符串
     * 
     * @param oldStr 原字符串
     * @param maxLength 截取长度
     * @param endWith 超过截取长度的结尾
     * @return
     */
    public static String StringTruncat(String oldStr, int maxLength, String endWith) {
        if (StringUtil.isEmpty(oldStr)) {
            return "";
        }
        if (oldStr.length() > maxLength) {
            String strTmp = oldStr.substring(0, maxLength);
            if (StringUtil.isEmpty(endWith)) {
                return strTmp;
            } else {
                return strTmp + endWith;
            }
        }
        return oldStr;
    }

    /**
     * 去除特殊字符（导出文件使用）
     * 
     * @param input
     * @return
     */
    public static String removeSpecialString(String input) {
        if (isEmpty(input)) {
            return "";
        }
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(input);
        String output = m.replaceAll("").replaceAll(",", "，").replaceAll("\"", "“");
        return output;
    }

    /**
     * 去除字符串前面的0，如果不符合则返回原字符串
     * 
     * @param value
     * @return
     * @author zhangdeyuan
     */
    public static String removePreZero(String value) {
        String temp = value;
        try {
            if (StringUtils.isNotBlank(temp)) {
                while (true) {
                    if (temp.startsWith("0")) {
                        temp = temp.substring(1);
                    } else {
                        break;
                    }
                }
            }
            return temp;
        } catch (Exception e) {
            log.error(e);
        }
        return value;
    }

    /**
     * 将字符串转换为数字比较大小
     * 
     * @param versionNo
     * @param versionNo2
     * @return
     */
    public static boolean Contrast(String versionNo, String versionNo2) {
        if (StringUtil.stringToInteger(versionNo) > StringUtil.stringToInteger(versionNo2)) {
            return true;
        }
        return false;
    }

    /**
     * 将导入错误的行,增加错误信息转成CSV的行
     * 
     * @param context
     * @param errorMsg
     * @param errInfo
     */
    public static void addErrRecord2Csv(String[] context, StringBuilder errorMsg, String errInfo) {
        if (null == context || context.length == 0) {
            return;
        }
        for (int i = 0; i < context.length; i++) {
            errorMsg.append(StringUtil.addCsv(context[i]));
        }
        errorMsg.append(StringUtil.addCsv(errInfo));
    }

    /**
     * 判断是否为数字和字母组成的字符串
     * 
     * @param context
     * @return
     */
    public static boolean checkDigitalAndCharactor(String context) {
        if (null == context)
            return false;
        return context.matches("[0-9a-zA-Z]+");
    }

    /**
     * 空字符串转化为-
     * 
     * @param str
     * @return
     */
    public static String emptyToLine(String str) {
        if (null == str || 0 == str.length()) {
            return "-";
        }
        return str;
    }

    public static String urlconnparam(String url, String param) {
    	String urlStr;
        if (url.lastIndexOf("?") >= 0) {
        	urlStr = url + "&" + param;
        } else {
        	urlStr = url + "?" + param;
        }
        return urlStr;
    }




}
