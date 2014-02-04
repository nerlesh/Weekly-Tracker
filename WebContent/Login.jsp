<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resource Activities</title>
<!-- Bootstrap -->
<link href="_include/css/bootstrap.min.css" rel="stylesheet">

<!-- Main Style -->
<link href="_include/css/main.css" rel="stylesheet">

<!-- Supersized -->
<link href="_include/css/supersized.css" rel="stylesheet">
<link href="_include/css/supersized.shutter.css" rel="stylesheet">


<!-- FancyBox -->
<link href="_include/css/fancybox/jquery.fancybox.css" rel="stylesheet">

<!-- Font Icons -->
<link href="_include/css/fonts.css" rel="stylesheet">

<!-- Shortcodes -->
<link href="_include/css/shortcodes.css" rel="stylesheet">

<!-- Responsive -->
<link href="_include/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="_include/css/responsive.css" rel="stylesheet">

<!-- Supersized -->
<link href="_include/css/supersized.css" rel="stylesheet">
<link href="_include/css/supersized.shutter.css" rel="stylesheet">

<!-- Google Font -->
<link
	href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900'
	rel='stylesheet' type='text/css'>

<!-- Fav Icon -->
<link rel="shortcut icon" href="#">

<link rel="apple-touch-icon" href="#">
<link rel="apple-touch-icon" sizes="114x114" href="#">
<link rel="apple-touch-icon" sizes="72x72" href="#">
<link rel="apple-touch-icon" sizes="144x144" href="#">


</style>

<!-- Modernizr -->
<script src="_include/js/modernizr.js"></script>

<link rel="stylesheet" href="_include/css/jquery-ui.css" />
<script src="_include/js/jquery-1.9.1.js"></script>
<script src="_include/js/jquery-ui.js"></script>
<script src="_include/js/jquery.bpopup.min.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script type="text/javascript">
function newUserCall(){
	var newPass = document.getElementById("newPassword");
	var confirmpass = document.getElementById("confirmPassword");
	var newUserFlag = document.getElementById("hiddenNewUserFlag");
	if(!(newPass.value=='' &&confirmpass.value=='')){
	if(newPass.value==confirmpass.value){
		 $.ajax({
	     		
             type : 'GET',
              /* url : 'FatcaSearchDataAction.do', */
              url : 'LoginAction.do',
              data : {
            	  hiddenUserName : document.getElementById("username").value,hiddenPassWord : newPass.value,newUserFlag : document.getElementById("hiddenNewUserFlag").value='Y'
           },
              dataType : 'JSON',
              cache : false,
              success : function(j) {
            	
              }
        });
	}
	else{
		 var newUserPopUp ='';
		  newUserPopUp+= '<a class="b-close">x<a/>';
		  newUserPopUp+=' <center> New User (Password does not match)</center>';
		  newUserPopUp+=' <center><table><tr><td>Password : </td><td><input type="password" id="newPassword"></td></tr>      <tr><td>Confirm Password : </td><td><input type="password" id="confirmPassword"></td></tr>  <tr><td>&nbsp;</td><td colspan="2">	<input type="button" class="button button-mini" value="Submit" onClick="newUserCall();"></td></tr></table></center>';
			$("#element_to_pop_up").html(newUserPopUp);
			  $('#element_to_pop_up').bPopup();
	}
	}
}
$( document ).ready(function() {
	$("#login").click(function(){
		  $.ajax({
	     		
	             type : 'GET',
	              /* url : 'FatcaSearchDataAction.do', */
	              url : 'LoginAction.do',
	              data : {
	            	  hiddenUserName : document.getElementById("username").value,hiddenPassWord : document.getElementById("password").value
	           },
	              dataType : 'JSON',
	              cache : false,
	              success : function(j) {
	            	  for(var k=0;k<j.length;k++){
	            		  if(j[k]=='invalid'){
	            			  var alertPopUp ='';
	            				alertPopUp+= '<a class="b-close">x<a/>';
	            				alertPopUp+=' <center> Invalid Username / Password </center>';
	            				$("#element_to_pop_up").html(alertPopUp);
	            				  $('#element_to_pop_up').bPopup();
	            		  }else if(j[k]=='newUser'){
	            			  var newUserPopUp ='';
	            			  newUserPopUp+= '<a class="b-close">x<a/>';
	            			  newUserPopUp+=' <center> New User </center>';
	            			  newUserPopUp+=' <center><table><tr><td>Password : </td><td><input type="password" id="newPassword"></td></tr>      <tr><td>Confirm Password : </td><td><input type="password" id="confirmPassword"></td></tr>  <tr><td>&nbsp;</td><td colspan="2">	<input type="button" class="button button-mini" value="Submit" onClick="newUserCall();"></td></tr></table></center>';
	            				$("#element_to_pop_up").html(newUserPopUp);
	            				  $('#element_to_pop_up').bPopup();
	            		  }
	            		  else if(j[k]=='notPart'){
	            			  var alertPopUp ='';
	            				alertPopUp+= '<a class="b-close">x<a/>';
	            				alertPopUp+=' <center> You are not part of this team</center>';
	            				$("#element_to_pop_up").html(alertPopUp);
	            				  $('#element_to_pop_up').bPopup();
	            		  }
	            		  else if(j[k]=='trueUser'){
	            			  callAction();
	            			  
	            		  }
	            		  
	            	  }
	              }
	        });
		
	});
});
function callAction(){
	document.forms[0].action = "AssignTaskDataAction.do";
	  document.forms[0].submit();
}
</script>
</head>
<body background="_include/image01.jpg">
<br/><br/><br/><br/><br/><br/>
<center><h1>Resource<font color="#DE5E60"> Activities</font></h1></center>
<form name="action">
<center>
<table><tr><td>User Name:</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type="text" id="username"></td></tr>
<tr> <td> Password:</td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type="password" id="password"></td></tr>
<tr><td>&nbsp;</td></tr>
<tr ><td>&nbsp;</td><td colspan="2"><input type="button" class="button" value="Login" id="login"></td></tr>
</center>
</form>
</table>
<div id="element_to_pop_up">
    <a class="b-close">x<a/>
   <center> Description Cannot be Blank </center>
</div>
   <input type="hidden" name="hiddenUserName" id ="hiddenUserName">
        <input type="hidden" name="hiddenPassWord" id="hiddenPassWord" >  
         <input type="hidden" name="hiddenNewUserFlag" id="hiddenNewUserFlag" > 
</body>
</html>