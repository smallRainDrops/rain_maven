$(function() {
	//修改默认的jquery校验依赖的属性class为validate，修改后标签属性只依赖于validate属性匹配规则
	$.metadata.setType("attr", "validate");

	/**
	 *计算两个日期天数差的函数，通用 
	 *@param sDate1 为一定格式的日期 例如2014-12-11
	 *@param sDate2 为一定格式的日期 例如2014-12-12
	 *@param splitStr 日期传分割符号 例如2014-12-12 的分割符号为"-"
	 */
	dateDistance = function(sDate1, sDate2,splitStr) {  //sDate1和sDate2是yyyy-MM-dd格式
	  var aDate, oDate1, oDate2, iDays;
	  aDate = sDate1.split(splitStr);
	  oDate1 = new Date(aDate[0] + '-' + aDate[1] + '-' + aDate[2]);  //转换为yyyy-MM-dd格式
	  aDate = sDate2.split(splitStr);
	  oDate2 = new Date(aDate[0] + '-' + aDate[1] + '-' + aDate[2]);
	  iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数
	  return iDays;  //返回相差天数
	};
	
	/**
	 * 校验开始时间是否大于结束时间
	 * @param sDate 为开始时间 格式为yyyy-MM-dd 
	 * @param eDate 为结束时间 格式为yyyy-MM-dd
	 * @returns true :开始日期小于等于结束日期 false :开始日期大于结束日期异常
	 * 
	 */
	validateStartEndDate = function(sDate,eDate){
		var date1 = new Date(Date.parse(sDate.replace(/\-/g, "/")));
        var date2 = new Date(Date.parse(eDate.replace(/\-/g, "/")));
        return date1<=date2;
	}

	/**
	 * 根据指定的一个日期和相差的天数，获取另外一个日期
	 * @param dateParameter 为指定已经存在的日期yyyy-MM-dd 
	 * @param num 为相差天数为整型
	 * 
	 */
	addByTransDate =  function(dateParameter, num) {
	  var translateDate = "", dateString = "", monthString = "", dayString = "";
	  translateDate = dateParameter.replace("-", "/").replace("-", "/"); ;
	  var newDate = new Date(translateDate);
	  newDate = newDate.valueOf();
	  newDate = newDate + num * 24 * 60 * 60 * 1000;  //备注 如果是往前计算日期则为减号 否则为加号
	  newDate = new Date(newDate);
	  //如果月份长度少于2，则前加 0 补位  
	  if ((newDate.getMonth() + 1).toString().length == 1) {
	      monthString = 0 + "" + (newDate.getMonth() + 1).toString();
	  } else {
	      monthString = (newDate.getMonth() + 1).toString();
	  }
	  //如果天数长度少于2，则前加 0 补位  
	  if (newDate.getDate().toString().length == 1) {
	      dayString = 0 + "" + newDate.getDate().toString();
	  } else {
	      dayString = newDate.getDate().toString();
	  }
	  dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString;
	  return dateString;
	};
	//拓展jquey.validate框架的方法
	//校验开始时间是否大于结束时间 格式为yyyy-MM-dd
	/**
	 * 校验开始时间是否大于结束时间 格式为yyyy-MM-dd
	 * @param param 为开始时间元素ID 格式为yyyy-MM-dd 
	 * @param value 为结束时间 格式为yyyy-MM-dd
	 */
	jQuery.validator.addMethod("endDateGtStartDate", function(value,element, param) {
		var startDate = $(param).val();
		var endDate = value;
		return validateStartEndDate(startDate,endDate);   
	}, $.validator.format("开始时间不能大于结束时间！"));
	
	//添加校验两个日期只差不小于7天的验证
	jQuery.validator.addMethod("dayNeedOverSeven", function(value,element, param) {
		var startDate = $(param).val();
		var endDate = value;
		return dateDistance(startDate,endDate,"-") >= 7;   
	}, $.validator.format("计划时间间隔不可小于7天！"));
	
	//只允许汉字、英文字母、数字及下划线。
	jQuery.validator.addMethod("wrongChar", function(value,element, param) {
	    var re = /^[\u0391-\uFFE5\w]+$/;
	    if (re.test(value)) {  
			 return true;
	    }
	    return false;
	}, $.validator.format("请勿录入特殊字符！"));
	
	
});