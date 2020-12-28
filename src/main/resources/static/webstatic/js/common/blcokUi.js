$(document).ready(function() {
	// 后台页面窗口弹出
	openInput = function(__popWidth, __popHeight, url) {
		
		$.ajax({
			type : 'post',
			url : url,
			success : function(response) {
				// 窗口打小
				var __widowWidth = $(window).width();
				var __windowHeight = $(window).height();
				// 计算上，左间隔
				var __left = (__widowWidth - __popWidth) / 2 + 'px';
				var __top = (__windowHeight - __popHeight) / 2 + 'px';
				$.blockUI({
					message : response,
					css : {
						width : __popWidth + 'px',
						height : __popHeight + 'px',
						left : __left,
						top : __top,
						position : 'fixed', // 居中
						cursor : "default"
					}
				});
			}
		});
	};
	
	// 打开新增界面
	openCreateInput = function(__popWidth, __popHeight, id) {
		var url = "input.htm";
		if (id != null) {
			url = "input.htm?id=" + id;
		}
		$.ajax({
			type : 'post',
			url : url,
			success : function(response) {
				// 窗口打小
				var __widowWidth = $(window).width();
				var __windowHeight = $(window).height();
				// 计算上，左间隔
				var __left = (__widowWidth - __popWidth) / 2 + 'px';
				var __top = (__windowHeight - __popHeight) / 2 + 'px';
				$.blockUI({
					message : response,
					css : {
						width : __popWidth + 'px',
						height : __popHeight + 'px',
						left : __left,
						top : __top,
						position : 'fixed', // 居中
						cursor : "default"
					}
				});
			}
		});
	};
	

	/**
	 * 弹出DIV窗口
	 * @param __popWidth 弹出DIV宽度
	 * @param __popHeight 弹出DIV高度
	 * @param url 传入弹出内容对应的请求路径，作为DIV展示内容
	 * @param data 通过Post方式传入到请求页面的参数，参数为JSON对象
	 * 
	 */
	openModelDivInput = function(__popWidth, __popHeight, url , data) {
		$.post(url, data, function(response){
			// 窗口打小
			var __widowWidth = $(window).width();
			var __windowHeight = $(window).height();
			// 计算上，左间隔
			var __left = (__widowWidth - __popWidth) / 2 + 'px';
			var __top = (__windowHeight - __popHeight) / 2 + 'px';
			$.blockUI({
				message : response,
				css : {
					width : __popWidth + 'px',
					height : __popHeight + 'px',
					left : __left,
					top : __top,
					position : 'fixed', // 居中
					cursor : "default"
				}
			});
		},'html');
	};
	
	
	/**
	 * 弹出DIV窗口   通过DIV
	 * @param __popWidth 弹出DIV宽度
	 * @param __popHeight 弹出DIV高度
	 * @param DIV  传入弹出内容对应的请求路径，作为DIV展示内容
	 * 
	 */
	openDivInput = function(__popWidth, __popHeight, div) {
			// 窗口打小
			var __widowWidth = $(window).width();
			var __windowHeight = $(window).height();
			// 计算上，左间隔
			var __left = (__widowWidth - __popWidth) / 2 + 'px';
			var __top = (__windowHeight - __popHeight) / 2 + 'px';
			$.blockUI({
				message : div,
				css : {
					width : __popWidth + 'px',
					height : __popHeight + 'px',
					left : __left,
					top : __top,
					position : 'fixed', // 居中
					cursor : "default"
				}
			});
	};
	
	
});