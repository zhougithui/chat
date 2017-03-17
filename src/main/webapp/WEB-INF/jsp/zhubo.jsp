<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<%=basePath %>resources/jquery/jquery.min.1.11.2.js"></script>
	<title>Insert title here</title>
	<script type="text/javascript">
	var wsUri ="ws://localhost/chat/echo/${zbName}/${uname}"; 
	var output;  
	
	function init() { 
	    output = document.getElementById("output"); 
	    testWebSocket(); 
	}  
	
	function testWebSocket() { 
	    websocket = new WebSocket(wsUri); 
	   
	    websocket.onopen = function(evt) { 
	        onOpen(evt) 
	    }; 
	    websocket.onclose = function(evt) { 
	        onClose(evt) 
	    }; 
	    websocket.onmessage = function(evt) { 
	        onMessage(evt) 
	    }; 
	    websocket.onerror = function(evt) { 
	        onError(evt) 
	    }; 
	}  
	
	function onOpen(evt) { 
	    writeToScreen("CONNECTED"); 
	}  
	
	function onClose(evt) { 
	    writeToScreen("DISCONNECTED"); 
	}  
	
	function onMessage(evt) { 
		if(evt.data.indexOf("cmt:") != -1){
			$('#cmt').html(evt.data.split('cmt:')[1]);
		}else{
		    writeToScreen('<span style="color: blue;">'+ evt.data+'</span>'); 
		}
	}  
	
	function onError(evt) { 
	    writeToScreen('<span style="color: red;">ERROR:</span> '+ evt.data); 
	}  
	
	function doSend(message) { 
	    websocket.send(message); 
	}  
	
	function writeToScreen(message) { 
	    var pre = document.createElement("p"); 
	    pre.style.wordWrap = "break-word"; 
	    pre.innerHTML = message; 
	    output.appendChild(pre); 
	}  
	
	window.addEventListener("load", init, false);
	
	</script>  
</head>
<body>
<div>当前在线人数：<span id="cmt" style="color: red;">0</span></div>
<input id="msg" type="text" /><button onclick="doSend($('#msg').val());">发送 </button> 
<div id="output"></div>
</body>
</html>