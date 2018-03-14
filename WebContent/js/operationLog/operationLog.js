$(function(){
	$("#header_product_operationLog_id").siblings().removeClass("active");
	$("#header_product_operationLog_id").addClass("active"); 
	
	OperationLog.findOperationLogList();
	
})

var OperationLog = {
	
	findOperationLogList:function(){
		var mask = layer.open({
			type:3,
			area: '60px',
			content:'加载中...',
			shade:0
		});

        
		$.ajax({
			type: "post",
			url : ctxPath + "/operationLog/sale.findOperationLogList.html",
			data : {
				"oplog.b_date":$('#saleStartDate').val(),
				"oplog.d_date":$('#saleEndDate').val()
			},
			success : function(res){
				var list = res.list;
				var html = '';
				html +=
					'<tr trtype="header">'+
						'<th width="60px"></th>'+
		                '<th width="600px" style="text-align:center">操作日志</th>'+
		                '<th width="180px" style="text-align:center">日期</th>'+
		            '</tr>';
				var num = 1;
				for(var i=0;i<list.length;i++){
					
					if(i%2==1){
						html += '<tr class="bg-col">';
					}else{
						html += '<tr>';
					}
					html +=
						'<td><div class="lognum txt-over">'+num+++'.</div></td>'+
                        '<td><div class="logbox1 txt-over"><a title="'+((list[i].name)||'')+((list[i].operation)||'')+'">'+((list[i].name)||'')+((list[i].operation)||'')+'</a></div></td>'+
                        '<td><div class="logbox2 txt-over"><a>'+((list[i].operationDate)||0)+'</a></div></td>'+
                    '</tr>';
				}
				$("#log_list").html(html);
            	layer.close(mask);
			},error:function(){
				layer.close(mask);
			}
		})
	}
}