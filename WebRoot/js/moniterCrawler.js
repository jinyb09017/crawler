
$(function(){
	$("#crawler").submit(function(){
		alert("this is a test");
		crawl();
		//monitor();
	});
	
});
function monitor(){
	alert("213");
	$('#processBar').text("data.moniterCrawlerHistory.parserSuccess");
	setInterval(push(),5000);//10����ѯһ�ΰɡ�
};
function crawl(){
	var handle=null;
	$.ajax({
		url:'/crawler/crawler/crawler_startCrawler',
		type:'post',
		data:{},
		dataType:'json',
		async:false,//ע�����������һ��ͬ�����󣬲�Ȼ��ִ�г����ˡ�ԭ��δ֪��
		error:function(a,b){
			alert(a.readyState);
			alert(a.status);
			alert(b);
			alert("exception��");				
		},
		beforeSend:function(){
			//alert("first");
			 handle=setInterval(push(),50);
		},
		complete:function(){
			clearInterval(handle);
		},
		success:function(data){
			alert(data.msg);
		}
	}
	);
};
function push()
{
	$.ajax({
		url:'/crawler/crawler/crawler_moniter',
		data:'{}',
		type:'post',
		dataType:'json',
		async:false,
		beforeSend:function(){
			alert("before ִ��");
			$('#processBar').text("data.moniterCrawlerHistory.parserSuccess");
		},
		success:function(data){
			//alert("great");
			var i=0;
			$('#processBar').text(i++);
		},
		error:function(xmlhttp,textstatus,excepton){
			//alert(xmlhttp.readyState);
			//alert(xmlhttp.status);
			//alert(textstatus);
			$('#moniterData').text(textstatus);
			
			
		}
	
	});
};

