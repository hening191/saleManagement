$(function(){
	$("#header_product_buy_id").siblings().removeClass("active");
	$("#header_product_buy_id").addClass("active"); 
	
	Buy.findRecordList();
	
	$(window).keydown(function(event){
		if(event.keyCode==13){
			Buy.searchSaleName(); 
		}
	});
})

var Buy = {
	
	search_name:'',
	
	findRecordList:function(){
		var mask = layer.open({
			type:3,
			area: '60px',
			content:'加载中...',
			shade:0
		});

        
		$.ajax({
			type: "post",
			url : ctxPath + "/buy/sale.findBuyList.html",
			data : {
				"sale.productName":Buy.search_name,
				"sale.b_date":$('#saleStartDate').val(),
				"sale.d_date":$('#saleEndDate').val()
			},
			success : function(res){
				var list = res.list;
				var html = '';
				var sum = 0;
				html +=
					'<tr trtype="header">'+
		                '<th width="60px"></th>'+
		                '<th width="150px">产品</th>'+
		                '<th width="90px">数量</th>'+
		                '<th width="170px">价格</th>'+
		                '<th width="170px">优惠价</th>'+
		                '<th width="100px">操作员</th>'+
		                '<th width="100px">来源</th>'+
		                '<th width="190px">日期</th>'+
		            '</tr>'
				for(var i=0;i<list.length;i++){
					
					if(i%2==1){
						html += '<tr class="bg-col" pro_id='+list[i].pro_id+' pro_name='+list[i].productName+'>';
					}else{
						html += '<tr pro_id='+list[i].pro_id+' pro_name='+list[i].productName+'>';
					}
					html +=
						'<td></td>'+
                        '<td><div class="box1 txt-over">'+(Buy.highLightField((list[i].productName)||''))+'</div></td>'+
                        '<td><div class="box2 txt-over">'+((list[i].count)||'')+'</div></td>'+
                        '<td><div class="box3 txt-over">'+((list[i].price)||'')+'</div></td>'+
                        '<td><div class="box3 txt-over">'+((list[i].prePrice)||'无')+'</div></td>'+
                        '<td><div class="box4 txt-over">'+((list[i].operator)||'')+'</div></td>'+
                        '<td><div class="box5 txt-over">'+((list[i].customer)||'')+'</div></td>'+
                        '<td><div class="box5 txt-over">'+((list[i].recordDate)||'')+'</div></td>'+
                    '</tr>';
					sum += parseInt(list[i].totalPrice);
				}
				$("#pro_list").html(html);
            	$("#sumSalePrice").html("销售总额："+sum);
            	layer.close(mask);
			},error:function(){
				layer.close(mask);
			}
		})
	},
	
	

	searchSaleName : function(){
		Buy.search_name = $("#pro_name").val();
		Buy.findRecordList();
	},
	
	highLightField : function( field ){
		if( field==undefined ){
			return "";
		}
		var highLight = $("#pro_name").val().trim(); 
		if(highLight==''){
			return field; 
		}
		if( field.indexOf( highLight )!=-1 ){
			var regStr = "/"+highLight+"/g";
			var reg = eval(regStr);
			field = field.replace( reg,"<span style='color:red'>"+highLight+"</span>" );
		}
		return field; 
	}, 
}