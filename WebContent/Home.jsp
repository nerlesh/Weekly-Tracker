<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resource Management</title>
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

var firtTimeAddFlag = false;
function validateGrid (){
	return true;
}
$( document ).ready(function() {
	$("#generate").click(function(){
		document.all("gridLoadFlag").value= 'Y';
		var constructTable ='';
		constructTable += '<div class="CSSTableGenerator"><center><table id="addedGrid"><th> Task </th><th>Percentage Completion</th> <th> Reason for Delay </th>';
	      $.ajax({
	     		
	             type : 'GET',
	              /* url : 'FatcaSearchDataAction.do', */
	              url : 'HomeAction.do',
	              data : {
	              	gridLoadFlag : document.all("gridLoadFlag").value
	           },
	              dataType : 'JSON',
	              cache : false,
	              success : function(j) {
	            	  for(var k=0;k<j.length;k++){
	            		  alert(j[k]);
	            		  
	            		  constructTable += '<tr><td>'+j[k]+'</td>';
	            		  constructTable +='<td><input type="text"></td><td><input type="text"></td>';
	            		  constructTable +='</tr>';
	            	  }
	              }
	           
	           
	        });
	     
	    
	});
	 alert(constructTable);
     $('#grid').html(constructTable); 
});

$(function() {
	$("#datepicker").datepicker({
		showButtonPanel : false
	});
});




</script>
</head>
<body>
<header>
	<div class="sticky-nav">
		<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>

		<div id="logo">
			<a id="goUp" href="#home-slider"
				title="Resource Management">Resource Management</a>
		</div>

		<nav id="menu">
		<ul id="menu-nav">
			<li ><a href="#home-slider">Home</a></li>
			<li><a href="#about">Assign Task</a></li>
				<li class="current"><a href="#work">Process Status</a></li>
			<li><a href="#contact">Contact</a></li>
			<li><a href="shortcodes.html" class="external">Logout</a></li>
		</ul>
		</nav>

	</div>
	</header>
	<br/>
	
<center><input type="button" class="button" value="Generate Assigned Task" id="generate">
</center>
<br/>

<table width="100%" border="0" align="center" cellspacing="0"
			cellpadding="0">
			<tr>
			</tr>
			</table>
			<br/>
			<div id="grid">
		
		</div>
			<div id="element_to_pop_up">
    <a class="b-close">x<a/>
   <center> Description Cannot be Blank </center>
</div>
     
     
			<input type="hidden" name="gridLoadFlag" id ="gridLoadFlag">
			</body>
<footer>
<p class="credits">&copy;2013 Resource Management</p>
</footer>
</html>