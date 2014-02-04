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
<script>
var firtTimeAddFlag = false;
function doLoading(){
	//document.getElementById("taskAssigned").value='Enter tasks with Comma seperated';
} 
function validateGrid(){
	var task = document.all("taskAssigned");
	var curResource = document.all("resource");
	var resourceFlag = false;
	if(task.value==null || task.value==""){
		var alertPopUp ='';
		alertPopUp+= '<a class="b-close">x<a/>';
		alertPopUp+=' <center> Tasks Cannot be Empty </center>';
		$("#element_to_pop_up").html(alertPopUp);
		  $('#element_to_pop_up').bPopup();
		  return false; 
	}
	else if(curResource.value!=null){
	
		var table = $("#addedGrid");
		 table.find('tr').each(function (i) {
             var $tds = $(this).find('td'),
             resource = $tds.eq(0).text();
             if(resource==curResource.value){
         		 resourceFlag = true;
             }
                  
		   });
		 
		 if(resourceFlag==true){
			 var alertPopUp ='';
      		alertPopUp+= '<a class="b-close">x<a/>';
      		alertPopUp+=' <center>You are adding task to the same resource who is already added to the grid</center>';
      		$("#element_to_pop_up").html(alertPopUp);
      		  $('#element_to_pop_up').bPopup();
      		  return false;
		 }
		 else{
			 return true;
		 }
			 
	}
	
}
$( document ).ready(function() {
	$("#addGrid").click(function(){
		var resource = document.all("resource");
		var task = document.all("taskAssigned");
		if(validateGrid()){
        if(firtTimeAddFlag==false){
        	var constructTable ='';
        	constructTable+='<div class="CSSTableGenerator"><center><table id="addedGrid"><tr><th>Resource</th><th>Task</th></tr>';
        	constructTable+='<tr><td>'+resource.value+'</td><td>'+task.value+'</td></tr>';
        	constructTable+='</table></center></div><br/><br/>';
        	constructTable+='<center><input type="button" class="button" value="Submit" id="submit" onclick="fnSubmit()"></center>';
        	$('#grid').html(constructTable);
        	firtTimeAddFlag= true;
        	
        }
        else{
        	var constructTable = '';
        	var table = $("#addedGrid");
        	constructTable+='<div class="CSSTableGenerator"><center><table id="addedGrid"><tr><th>Resource</th><th>Task</th></tr>';
        	   table.find('tr').each(function (i) {
                   var $tds = $(this).find('td'),
                   resource = $tds.eq(0).text(),
                   task = $tds.eq(1).text();
               	if(i>=1){ 
               		constructTable+='<tr><td>'+resource+'</td><td>'+task+'</td></tr>';
               	}
        	   });
        	   constructTable+='<tr><td>'+resource.value+'</td><td>'+task.value+'</td></tr>';
           	constructTable+='</table></center></div><br/><br/>';
           	constructTable+='<center><input type="button" class="button" value="Submit" id="submit" onclick="fnSubmit()"></center>';
           	$('#grid').html(constructTable);
        }
        document.all("gridLoadFlag").value ='Y';
   	 document.all("hidenResource").value = resource.value;
   	 document.all("hiddenTask").value = task.value;
   	 $.ajax({
  		
         type : 'GET',
          /* url : 'FatcaSearchDataAction.do', */
          url : 'AllotTaskDataAction.do',
          data : {
        	  gridLoadFlag : document.all("gridLoadFlag").value,hidenResource :  document.all("hidenResource").value,hiddenTask: document.all("hiddenTask").value
       },
          dataType : 'JSON',
          cache : false,
          success : function(j) {
          }
    });
        
        
		}
	});
});

function fnSubmit(){
	var alertPopUp ='';
	alertPopUp+= '<a class="b-close">x<a/>';
	alertPopUp+=' <center> Wait ! File is getting updated in Excel ! </center>';
	$("#element_to_pop_up").html(alertPopUp);
	  $('#element_to_pop_up').bPopup(); 
	  
	 $.ajax({
     		
         type : 'GET',
          /* url : 'FatcaSearchDataAction.do', */
          url : 'AllotTaskAction.do',
          data : {
          
       },
          dataType : 'JSON',
          cache : false,
          success : function(j) {
        	  for(var k=0;k<j.length;k++){
        		  if(j[k]=='success'){
        			  var alertPopUp ='';
              		alertPopUp+= '<a class="b-close">x<a/>';
              		alertPopUp+=' <center> Data updated Successfully</center>';
              		$("#element_to_pop_up").html(alertPopUp);
              		  $('#element_to_pop_up').bPopup();  
        		  }
        	  }
          }
    });
	
	
	
}

</script>
</head>
<body onload="doLoading()">
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
			<li class="current"><a href="#work">Assign Task</a></li>
			<li><a href="#about">About Us</a></li>
			<li><a href="#contact">Contact</a></li>
			<li><a href="shortcodes.html" class="external">Logout</a></li>
		</ul>
		</nav>

	</div>
	</header>
	<br/>
<h3 class="spec">
		<center>Assign Weekly Activities</center>
	</h3>	
<html:form action="/AllotTaskDataAction">
<table width="100%" border="0" align="center" cellspacing="0"
			cellpadding="0">
			<tr>
			<td>Resource List:</td>
			<td><html:select property="resource">
						<html:options name="resourceListKeys" labelName="resourceListValues" />
					</html:select>
					</td>
					<td>Task:</td>
					<td><html:text property="taskAssigned" size="7"></html:text>  </td>
					<td><input type="button" class="button button-mini" value="Add" id="addGrid"></td>
			</tr>
			<tr>
			<td> </td><td> </td><td> </td>
			<td>Enter multiple task with comma seperated(For ex : GCB Coding,IB 7874)</td>
			</tr>
			</table>
			<br/>
			<div id="grid">
		
		</div>
		<div id="element_to_pop_up">
    <a class="b-close">x<a/>
   <center> Description Cannot be Blank </center>
</div>
</html:form>
<input type="hidden" name="hiddenDate" id="hiddenTask" >
	  <input type="hidden" name="hidenActivity" id="hidenResource" >
	      <input type="hidden" name="gridLoadFlag" id ="gridLoadFlag">
</body>
<footer>
<p class="credits">&copy;2013 Resource Management</p>
</footer>
</html>