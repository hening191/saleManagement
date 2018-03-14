/************登录页面************/

$(function(){
    // 记住密码
    $('.chose_wrap .left_btn').click(function(event) {
      $(this).toggleClass('active');
    });

    $('.add-people .check a').click(function(event) {
      $(this).toggleClass('active');
    });


})


$(function(){
     // 导航条选中
    $('.header .hd-inner .nav ul li').click(function(event) {
      $(this).addClass('active').siblings('').removeClass('active');
    });

         // 子导航条选中
    $('.c-subNav ul li').click(function(event) {
      $(this).addClass('active').siblings('').removeClass('active');
    });


         // 搜索框
    $('.input-right input').click(function(event) {

       $(this).parent().toggleClass('active');;

    });

          // 下拉框
    $('.select-wrap ul.main li').click(function(event) {
      var txt = $(this).text();
      $(this).parent().siblings('.txt').text(txt);
      $(this).parent().slideToggle(500);
    });

             // 下拉框
    $('.select-wrap .ul-wrap ul li').click(function(event) {
      var txt = $(this).text();
      $(this).parent().parent().siblings('.txt').text(txt);
      $(this).parent().parent().slideToggle(500);
      $('.select-wrap .btn-arrow').toggleClass('active');
      
      his.hisTrajectory();

    });



    $('.select-wrap .btn-arrow').click(function(event) {
          $(this).toggleClass('active');
          if($(this).hasClass('active')){
        	  $("#dep_emps").show();
          }else{
        	  $("#dep_emps").hide();
          }
    });





          // 下拉框
    $('.choose-people  ul li').click(function(event) {
      var txt = $(this).text();
     
      $(this).parent().siblings('.txt').text(txt);
      $(this).parent().slideToggle(500);
    });



    $('.choose-people  .btn-arrow').click(function(event) {
          $(this).toggleClass('active');
          $(this).siblings('ul').slideToggle(500);
    });
    

})