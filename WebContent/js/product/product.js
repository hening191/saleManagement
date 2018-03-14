$(function(){
	$("#header_product_sock_id").siblings().removeClass("active");
	$("#header_product_sock_id").addClass("active"); 
	
	Product.findProductList();
	
})

var Product = {
	
	pro_name:'',
	search_name:'',
	pro_id:0,
	pro_sock:0,
	pro_price:0,
	pro_prePrice:0,
	source:'',
	edit_pro_id:0,  //该属性为编辑功能启用时使用的属性
	/**************************************************列表查询开始******************************************************/
	findProductList:function(){
		var mask = layer.open({
			type:3,
			area: '60px',
			content:'加载中...',
			shade:0
		});
		$.ajax({
			type: "post",
			url : ctxPath + "/product/sale.findProductList.html",
			data : {
				"product.productName":Product.search_name
			},
			success : function(res){
				var list = res.list;
				var html = '';
				html +=
					'<tr trtype="header">'+
		                '<th width="60px"><a href="javascript:;"></a></th>'+
		                '<th width="150px">产品</th>'+
		                '<th width="110px">数量</th>'+
		                '<th width="170px">单价</th>'+
		                '<th width="110px">会员价</th>'+
		            '</tr>'
				for(var i=0;i<list.length;i++){
					
					if(i%2==1){
						html += '<tr class="bg-col" pro_id='+list[i].productId+' pro_name='+list[i].productName+' pro_sock='+list[i].sock+' pro_price='+list[i].price+' pro_prePrice='+list[i].prePrice+'>';
					}else{
						html += '<tr pro_id='+list[i].productId+' pro_name='+list[i].productName+' pro_sock='+list[i].sock+' pro_price='+list[i].price+' pro_prePrice='+list[i].prePrice+'>';
					}
					html +=
						'<td><a href="javascript:;"></a></td>'+
                        '<td><div class="box1 txt-over">'+(Product.highLightField((list[i].productName)||''))+'</div></td>'+
                        '<td><div class="box2 txt-over">'+((list[i].sock)||'')+'</div></td>'+
                        '<td><div class="box3 txt-over">'+((list[i].price)||'')+'</div></td>'+
                        '<td><div class="box4 txt-over">'+((list[i].prePrice)||'')+'</div></td>'+
                    '</tr>';
				}
				$("#edit_pro").removeClass("active"); 
				$("#delete_pro").removeClass("active");
				
				$("#edit_pro").css("cursor", "not-allowed"); 
				$("#delete_pro").css("cursor", "not-allowed");
				
				$("#edit_pro").unbind();
				$("#delete_pro").unbind();
                $("#pro_list").html(html);
                
                Product.pro_id = 0;
				Product.pro_name = '';
				Product.pro_sock = 0;
				Product.pro_price = 0;
				Product.pro_prePrice = 0;
				
				//该属性为编辑功能启用时使用属性
				Product.edit_pro_id = 0,
				
                Product.initListClick();
            	
            	layer.close(mask);
			},error:function(){
				layer.close(mask);
			}
		})
	},
	
	searchName:function(){
		Product.search_name = $("#pro_name").val();
		Product.findProductList();
	},
	
	highLightField : function( field ){
		if( field==undefined ){
			return "";
		}
		var highLight = Product.search_name.trim(); 
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
	/**************************************************列表查询结束******************************************************/
	
	/**************************************************列表选中事件开始******************************************************/
	initListClick : function(){
		$("#pro_list tr").bind('click', function(event) {
			if( $(this).attr("trtype")!="header" ){
				$(this).toggleClass('active').siblings('').removeClass('active');;
				
				if( $(this).hasClass('active') ){
					$("#edit_pro").addClass("active"); 
					$("#delete_pro").addClass("active");
					
					$("#edit_pro").css("cursor", "pointer"); 
					$("#delete_pro").css("cursor", "pointer");
					
					Product.pro_id = $(this).attr("pro_id");
					Product.pro_name = $(this).attr("pro_name");
					Product.pro_sock = $(this).attr("pro_sock");
					Product.pro_price = $(this).attr("pro_price");
					Product.pro_prePrice = $(this).attr("pro_prePrice");
					
					$("#edit_pro").bind('click',function(){Product.showEdit();});
					$("#delete_pro").bind('click',function(){Product.deleteProConfirm();});
				}else{
					$("#edit_pro").removeClass("active"); 
					$("#delete_pro").removeClass("active");
					
					$("#edit_pro").css("cursor", "not-allowed"); 
					$("#delete_pro").css("cursor", "not-allowed");
					
					$("#edit_pro").unbind();
					$("#delete_pro").unbind();
					
					Product.pro_id = 0;
					Product.pro_name = '';
					Product.pro_sock = 0;
					Product.pro_price = 0;
					Product.pro_prePrice = 0;
				}
				
			}
		});
	},
	/**************************************************列表选中事件结束******************************************************/
	
	/**************************************************产品入库事件开始******************************************************/
	showAdd:function(){
		//编辑框而更改为入库框
		$("#dailogTitle").html("入库");
		$("#sale_price_div").css("display","block");       //单价
		$("#sale_prePrice_div").css("display","block");    //优惠价
		$("#sale_source_div").css("display","block");	   //来源
		$("#sale_totalPrice_div").css("display","none");   //总价
		$("#sale_sock_div").css("display","none");		   //库存
		$("#sale_customer_div").css("display","none");	   //客户
		

		$("#product").off().on('input',function(){
			Product.pro_name = $("#product").val();
			Product.searchProductByName();
			if(Product.pro_price!=0){
				$("#pro_price").val(Product.pro_price);
			}else{
				$("#pro_price").val("");
			}
			if(Product.pro_prePrice!=0){
				$("#pro_prePrice").val(Product.pro_prePrice);
			}else{
				$("#pro_prePrice").val("");
			}
		})
		
		//赋值情况
		if(Product.pro_id!=0){
			$("#product").val(Product.pro_name);
			$("#pro_price").val(Product.pro_price);
			$("#pro_prePrice").val(Product.pro_prePrice);
		}else{
			$("#product").val("");
			$("#pro_price").val("");
			$("#pro_prePrice").val("");
		}
		$("#add_count").val("");
		$("#pro_source").val("");
		$("#savePro").unbind().bind('click',function(){Product.addSavePro()});
		$("#proDailog").css('display','block');
	},
	
	
	addSavePro:function(){
		var pro_name = $("#product").val();
		var add_count = $("#add_count").val();
		var pro_price = $("#pro_price").val();
		var pro_prePrice = $("#pro_prePrice").val();
		var pro_source = $("#pro_source").val();
		
		var countReg = /^[0-9]*$/;
		var priceReg = new RegExp('^[0-9]+(.[0-9]{2})?$');
		
		if(pro_name == null || '' == pro_name){
			layer.msg('产品名称不能为空');
			return ; 
		}
		if(add_count){
			if( !countReg.test( add_count ) ){
				layer.msg('产品数量格式不正确');
				return ; 
			}
		}else{
			layer.msg('产品数量不能为空');
			return ; 
		}
		
		if( !priceReg.test( pro_price ) ){
			layer.msg('产品价格格式不正确');
			return ; 
		}
		
		if(pro_prePrice){
			if( !priceReg.test( pro_prePrice ) ){
				layer.msg('产品优惠价格式不正确');
				return ; 
			}
		}
		
		$.ajax({
			type: "post",
			url : ctxPath + "/product/sale.addProduct.html",
			async:false,
			data : {
				"product.productName":pro_name,
				"product.sock":add_count, 
				"product.price":pro_price, 
				"product.prePrice" : pro_prePrice,
				"product.source":pro_source
			},
			success : function(res){
				layer.msg(res.info);
			}
		});
		Product.closeDailog();
	},
	/**************************************************产品入库事件结束******************************************************/
	
	/**************************************************产品出售事件开始******************************************************/
	showSale:function(){
		//编辑框而更改为入库框
		$("#dailogTitle").html("出售");
		$("#sale_price_div").css("display","none");        //单价
		$("#sale_prePrice_div").css("display","none");     //优惠价
		$("#sale_source_div").css("display","none");	   //来源
		$("#sale_totalPrice_div").css("display","block");  //总价
		$("#sale_sock_div").css("display","block");		   //库存
		$("#sale_customer_div").css("display","block");	   //客户
		

		$("#product").off().on('input',function(){
			Product.pro_name = $("#product").val();
			Product.searchProductByName();
			if(Product.pro_sock!=0){
				$("#pro_sock").val(Product.pro_sock);
			}else{
				$("#pro_sock").val("");
			};
			$("#add_count").val("");
			$("#pro_totalPrice").val("");
		})
		
		$("#add_count").focus(function(){
			if(parseInt(Product.pro_id)>0){
				if(parseInt(Product.pro_sock)>0){
					$("#add_count").on('input',function(){
						var total = $("#add_count").val()*Product.pro_price;
						$("#pro_totalPrice").val(total);
					})
					$("#add_count").blur(function(){
						if(parseInt($("#add_count").val())>parseInt(Product.pro_sock)){
							setTimeout(function () {
								$("#add_count").focus();
							},100);
							layer.msg('商品库存不足');
						}
					})
				}else{
					layer.msg('商品暂无库存');
					$("#add_count").blur();
				}
			}else{
				layer.msg('请先输入库存中已有商品');
				$("#product").focus();
			}
		})
		
		//赋值情况
		if(Product.pro_id!=0){
			$("#product").val(Product.pro_name);
			$("#pro_sock").val(Product.pro_sock);
		}else{
			$("#product").val("");
			$("#pro_sock").val("");
		}
		$("#add_count").val("");
		$("#pro_totalPrice").val("");
		$("#pro_customer").val("");
		$("#savePro").unbind().bind('click',function(){Product.saleSavePro()});
		$("#proDailog").css('display','block');
	},
	
	//出售提交
	saleSavePro:function(){
		var pro_id = Product.pro_id;
		var pro_name = $("#product").val();
		var add_count = $("#add_count").val();
		var total_price = $("#pro_totalPrice").val();
		var pro_customer = $("#pro_customer").val();
		
		var countReg = /^[0-9]*$/;
		var priceReg = new RegExp('^[0-9]+(.[0-9]{2})?$');
		
		if(pro_name == null || '' == pro_name){
			layer.msg('产品名称不能为空');
			return ; 
		}
		if(add_count){
			if( !countReg.test( add_count ) ){
				layer.msg('产品数量格式不正确');
				return ; 
			}else{
				if(parseInt(add_count)>parseInt(Product.pro_sock)){
					layer.msg('产品库存不足');
					return ; 
				}else if(parseInt(add_count)==0){
					layer.msg('产品出售至少一件');
					return ; 
				}
			}
		}else{
			layer.msg('产品数量不能为空');
			return ; 
		}
		
		if( !priceReg.test( total_price ) ){
			layer.msg('产品总价格式不正确');
			return ; 
		}
		
		
		$.ajax({
			type: "post",
			url : ctxPath + "/product/sale.saleProduct.html",
			async:false,
			data : {
				"sale.productId":parseInt(pro_id),
				"sale.productName":pro_name,
				"sale.count":add_count, 
				"sale.totalPrice":total_price, 
				"sale.customer":pro_customer
			},
			success : function(res){
				layer.msg(res.info);
				
			}
		});
		Product.closeDailog();
	},
	
	/**************************************************产品出售事件结束******************************************************/
	
	//根据产品名称查找库内产品相关数据
	searchProductByName:function(){
		$.ajax({
			type: "post",
			url : ctxPath + "/product/sale.findProductByName.html",
			data : {
				"product.productName":Product.pro_name
			},
			async:false,
			success : function(res){
				if(res.product){
					Product.pro_price = res.product.price;
					Product.pro_prePrice = res.product.prePrice;
					Product.pro_name = res.product.productName;
					Product.pro_sock = res.product.sock;
					Product.pro_id = res.product.productId;
					Product.source = res.product.source;
				}else{
					Product.pro_price = 0;
					Product.pro_prePrice = 0;
					Product.pro_name = '';
					Product.pro_sock = 0;
					Product.pro_id = 0;
					Product.source = '';
				}
			}
		});
	},
	
	//关闭入库或出售框
	closeDailog:function(){
		$("#proDailog").css('display','none');
		$("#product").off();
		$("#add_count").off();
		
		Product.findProductList();
		
		$("#product").val("");
		$("#pro_sock").val("");
		$("#add_count").val("");
		$("#pro_price").val("");
		$("#pro_totalPrice").val("");
		$("#pro_prePrice").val("");
		$("#pro_source").val("");
		$("#pro_customer").val("");
	},
	
	/**************************************************产品删除事件开始******************************************************/
	deleteProConfirm : function(){
		commonUtil.confirm(
				"提示", 
				"确定删除当前产品？", 
				true, 
				"确定", 
				function(){
					Product.deleteProduct(); 
				} , 
				"取消", 
				null
			); 
	}, 
	
	deleteProduct:function(){
		$.ajax({
			type: "post",
			url : ctxPath + "/product/sale.deleteProduct.html",
			data : {
				"sale.productId":Product.pro_id
			},
			success : function(res){
				
				layer.msg(res.info);
				Product.findProductList();
				
				$("#delete_pro").removeClass("active");
				
				$("#delete_pro").css("cursor", "not-allowed");
				
				$("#delete_pro").unbind();
			}
			
		});
	},
	
	/**************************************************产品删除事件结束******************************************************/
	
	/**************************************************产品编辑事件开始******************************************************/
	/*****************************************该功能作为预留在暗格的应急功能，不开放*******************************************/
	showEdit:function(){
		//改为编辑框
		$("#dailogTitle").html("编辑");
		$("#sale_price_div").css("display","block");       //单价
		$("#sale_prePrice_div").css("display","block");    //优惠价
		$("#sale_source_div").css("display","none");	   //来源
		$("#sale_totalPrice_div").css("display","none");   //总价
		$("#sale_sock_div").css("display","none");		   //库存
		$("#sale_customer_div").css("display","none");	   //客户
		
		//查询现有产品信息
		Product.searchProductByName();
		
		var temp_pro_price = Product.pro_price;
		var temp_pro_prePrice = Product.pro_prePrice;
		var temp_pro_name = Product.pro_name;
		var temp_pro_sock = Product.pro_sock;
		var temp_source = Product.source;
		Product.edit_pro_id = Product.pro_id;

		$("#product").off().on('input',function(){
			Product.pro_name = $("#product").val();
			Product.searchProductByName();
			if(Product.pro_id!=0 && Product.pro_id != Product.edit_pro_id){
				layer.msg('产品名称不可与已有产品重复');
				$("#product").blur(function(){
					if(Product.pro_id!=0 && Product.pro_id != Product.edit_pro_id){
						setTimeout(function () {
							$("#product").focus();
						},100);
						layer.msg('产品名称不可与已有产品重复');
					}
				})
			}
		})
		
		//赋值情况
		
		$("#product").val(temp_pro_name);
		$("#pro_price").val(temp_pro_price);
		$("#pro_prePrice").val(temp_pro_prePrice);
		$("#add_count").val(temp_pro_sock);
		$("#pro_source").val(temp_source);
		$("#savePro").unbind().bind('click',function(){Product.editSavePro()});
		$("#proDailog").css('display','block');
	},
	
	
	editSavePro:function(){
		var pro_name = $("#product").val();
		var add_count = $("#add_count").val();
		var pro_price = $("#pro_price").val();
		var pro_prePrice = $("#pro_prePrice").val();
		var pro_source = $("#pro_source").val();
		
		var countReg = /^[0-9]*$/;
		var priceReg = new RegExp('^[0-9]+(.[0-9]{2})?$');
		
		if(pro_name == null || '' == pro_name){
			layer.msg('产品名称不能为空');
			return ; 
		}
		Product.pro_name = pro_name;
		var temp_pro_id = Product.pro_id;
		Product.searchProductByName();
		if(Product.pro_id!=0 && Product.pro_id != Product.edit_pro_id){
			return;
		}
		if(add_count){
			if( !countReg.test( add_count ) ){
				layer.msg('产品数量格式不正确');
				return ; 
			}
		}else{
			layer.msg('产品数量不能为空');
			return ; 
		}
		
		if( !priceReg.test( pro_price ) ){
			layer.msg('产品价格格式不正确');
			return ; 
		}
		
		if(pro_prePrice){
			if( !priceReg.test( pro_prePrice ) ){
				layer.msg('产品优惠价格式不正确');
				return ; 
			}
		}
		
		$.ajax({
			type: "post",
			url : ctxPath + "/product/sale.editProduct.html",
			async:false,
			data : {
				"product.productId":Product.edit_pro_id,
				"product.productName":pro_name,
				"product.sock":add_count, 
				"product.price":pro_price, 
				"product.prePrice" : pro_prePrice
			},
			success : function(res){
				layer.msg(res.info);
			}
		});
		Product.closeDailog();
	}
	
	
	
	/**************************************************产品编辑事件结束******************************************************/
}