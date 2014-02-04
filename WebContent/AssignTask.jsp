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
function validateGrid(){
	
	var description = document.getElementsByName("taskDescription")[0];
	var hoursSpend = document.all("hoursSpend");
	var totalHours = hoursSpend.value;
	if(firtTimeAddFlag==true){
	 var table = $("#addedGrid");
	 table.find('tr').each(function (i) {
         var $tds = $(this).find('td'),
         hourSpend = $tds.eq(5).text();
         if(i>=1){ 
        	 totalHours=parseFloat(totalHours)+parseFloat(hourSpend);
         }
	  });
	 if(totalHours>=11){
		 var alertPopUp ='';
			alertPopUp+= '<a class="b-close">x<a/>';
			alertPopUp+=' <center> You are overloaded </center>';
			$("#element_to_pop_up").html(alertPopUp);
			  $('#element_to_pop_up').bPopup();
			  return false; 
	 }
	}
	if(description.value==null || description.value==""){
		var alertPopUp ='';
		alertPopUp+= '<a class="b-close">x<a/>';
		alertPopUp+=' <center> Description Cannot be Blank </center>';
		$("#element_to_pop_up").html(alertPopUp);
		  $('#element_to_pop_up').bPopup();
		return false;
	}
	else if (hoursSpend.value==null || hoursSpend.value==""){
		var alertPopUp ='';
		alertPopUp+= '<a class="b-close">x<a/>';
		alertPopUp+=' <center> Hours Spent Cannot be Blank </center>';
		$("#element_to_pop_up").html(alertPopUp);
		  $('#element_to_pop_up').bPopup();
		  return false;
	}
	else if (hoursSpend.value>=11){
		var alertPopUp ='';
		alertPopUp+= '<a class="b-close">x<a/>';
		alertPopUp+=' <center> You are overloaded </center>';
		$("#element_to_pop_up").html(alertPopUp);
		  $('#element_to_pop_up').bPopup();
		  return false;
	}
	else if (!hoursSpend.value.match(/^[0-9]*$/)){
		var alertPopUp ='';
		alertPopUp+= '<a class="b-close">x<a/>';
		alertPopUp+=' <center> Hours Spend Should be Numeric </center>';
		$("#element_to_pop_up").html(alertPopUp);
		  $('#element_to_pop_up').bPopup();
		  return false;
	}
    return true;
    
}

	$(function() {
		$("#datepicker").datepicker({
			showButtonPanel : false
		});
	});
	
	$( document ).ready(function() {
	$("#addGrid").click(function(){
		//  $.ajax({url:"demo_test.txt",success:function(result){
		  //  $("#div1").html(result);
		  //}});
			
		    
		if(validateGrid()){
		var item=document.all("items");
		var activity = document.all("activity");
		var isPartOfTask = document.all("isPartOfAssignedTask");
		var description = document.getElementsByName("taskDescription")[0];
		var hoursSpend = document.all("hoursSpend");
		
		var day = $("#datepicker").datepicker('getDate').getDate();                 
        var month = $("#datepicker").datepicker('getDate').getMonth() + 1;             
        var year = $("#datepicker").datepicker('getDate').getFullYear();
        var totalHours = hoursSpend.value;
        
        var finalDate = day+"/"+month+"/"+year;
     //  $.ajax({url:"demo_test.txt",success:function(result){
  		//   $("#div1").html(result);
  		  //}});
        
        if(firtTimeAddFlag==false){
        	//Construct Grid Values
        	var constructTable ='';
        	constructTable+='<div class="CSSTableGenerator"><center><table id="addedGrid"><tr><th>Date</th><th>Item</th><th>Activity</th><th>Is Part of Assigned Task</th><th>Description</th><th>Hours Spent</th>';
        	
        	constructTable+='<tr><td>'+finalDate+'</td>'+'<td>'+item.value+'</td>'+'<td>'+activity.value+'</td>'+'<td>'+isPartOfTask.value+'</td>'+
        	'<td>'+description.value+'</td>'+'<td>'+hoursSpend.value+'</td></tr>';
        	
        	constructTable+='</table></center></div>';
        	
        	$('#grid').html(constructTable);
        	
        	firtTimeAddFlag= true;
        	
        	
        }
        else{
        	var item=document.all("items");
    		var activity = document.all("activity");
    		var isPartOfTask = document.all("isPartOfAssignedTask");
    		var description = document.getElementsByName("taskDescription")[0];
    		var hoursSpend = document.all("hoursSpend");
    		
    		var day = $("#datepicker").datepicker('getDate').getDate();                 
            var month = $("#datepicker").datepicker('getDate').getMonth() + 1;             
            var year = $("#datepicker").datepicker('getDate').getFullYear();
            
            var finalDate = day+"/"+month+"/"+year;
            var table = $("#addedGrid");
            var constructTable ='';
        	constructTable+='<div class="CSSTableGenerator"><center><table id="addedGrid"><tr><th>Date</th><th>Item</th><th>Activity</th><th>Is Part of Assigned Task</th><th>Description</th><th>Hours Spent</th>';
        	
            table.find('tr').each(function (i) {
                var $tds = $(this).find('td'),
                    date = $tds.eq(0).text(),
                    item = $tds.eq(1).text(),
                    activity = $tds.eq(2).text(),
                	isPart = $tds.eq(3).text(),
                	description = $tds.eq(4).text(),
            		hourSpend = $tds.eq(5).text();
                // do something with productId, product, Quantity
					if(i>=1){                
                	constructTable+='<tr><td>'+date+'</td>'+'<td>'+item+'</td>'+'<td>'+activity+'</td>'+'<td>'+isPart+'</td>'+
                	'<td>'+description+'</td>'+'<td>'+hourSpend+'</td></tr>';
                	totalHours=parseFloat(totalHours)+parseFloat(hourSpend);
					}
                
                
                //alert('Row ' + (i + 1) + ':\nId: ' + date
                  //    + '\nProduct: ' + activity
                    //  + '\nQuantity: ' + isPart);
            });
            constructTable+='<tr><td>'+finalDate+'</td>'+'<td>'+item.value+'</td>'+'<td>'+activity.value+'</td>'+'<td>'+isPartOfTask.value+'</td>'+
        	'<td>'+description.value+'</td>'+'<td>'+hoursSpend.value+'</td></tr>';
        	
        	constructTable+='</table></center></div>';
        	$('#grid').html(constructTable);
        	
        	

    		
        }
           	
    	var constructTotalHours='';
    	constructTotalHours+='<p align="right">Total Hours Spent:&nbsp;';
    	constructTotalHours+='<font color="#DE5E60">'+totalHours+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></p>';
    	$('#totalHours').html(constructTotalHours);
    	 document.all("gridLoadFlag").value ='Y';
    	 document.all("hidenActivity").value = activity.value;
    	 document.all("hiddenItem").value = item.value;
    	 document.all("hiddenIsPartOfAssignedTask").value = isPartOfTask.value;
    	 document.all("hiddenDescription").value = description.value;
    	 document.all("hiddenHourSpent").value = hoursSpend.value;
    	 document.all("hiddenDate").value = finalDate;
    	 $.ajax({
     		
             type : 'GET',
              /* url : 'FatcaSearchDataAction.do', */
              url : 'AssignTaskDataAction.do',
              data : {
              	gridLoadFlag : document.all("gridLoadFlag").value,hidenActivity : document.all("hidenActivity").value,hiddenItem :   document.all("hiddenItem").value,hiddenIsPartOfAssignedTask :   document.all("hiddenIsPartOfAssignedTask").value,
              	hiddenDescription : document.all("hiddenDescription").value,hiddenHourSpent : document.all("hiddenHourSpent").value,hiddenDate :document.all("hiddenDate").value
           },
              dataType : 'JSON',
              cache : false,
              success : function(j) {
              }
        });
    	
		}
        
	});
		});
	function doLoading(){
		$( "#datepicker" ).datepicker( "setDate", new Date());
		
	}
	
	function fnSubmit(){
		var alertPopUp ='';
		alertPopUp+= '<a class="b-close">x<a/>';
		alertPopUp+=' <center> Wait ! File is getting updated in Excel ! </center>';
		$("#element_to_pop_up").html(alertPopUp);
		  $('#element_to_pop_up').bPopup(); 
		  
		 $.ajax({
	     		
             type : 'GET',
              /* url : 'FatcaSearchDataAction.do', */
              url : 'AssignTaskSubAction.do',
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
	function callProcess(){
		window.location.href="ProcessStatusDataAction.do";
	}
	function callLeaveApply(){
		window.location.href= "LeaveApplyDataAction.do";
	}
	function logout(){
		window.location.href= "AssignTaskDataAction.do";
	}
</script>

</head>
<body onload="doLoading();">

	<header>
	<div class="sticky-nav">
		<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>

		<div id="logo">
			<a id="goUp" href="#home-slider"
				title="Resource Management">Resource Management</a>
		</div>

		<nav id="menu">
		<ul id="menu-nav">
			<li class="current"><a href="#home-slider">Home</a></li>
			<li><a href="" onclick="callProcess();">Process Status</a></li>
			<li><a href="" onclick="callLeaveApply();">Leave Apply</a></li>
			<li><a href="" onclick="logout();" >Logout</a></li>
			
		</ul>
		</nav>

	</div>
	</header>
	<br/>
	<h3 class="spec">
		<center>Daily Activities</center>
	</h3>
	<div id="totalHours">
	<p align="right">Total Hours Spent:&nbsp;<font color="#DE5E60">0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></p>
	</div>
	<html:form action="/AssignTaskDataAction">
		<table width="100%" border="0" align="center" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>Date:</td>
				<td><input type="text" id="datepicker" readonly="readonly"/></td>
				<td>Item:</td>
				<td><html:select property="items">
						<html:options name="itemListKeys" labelName="itemListValues" />
					</html:select></td>
			</tr>
			<tr>
				<td>Activity:</td>
				<td><html:select property="activity">
						<html:options name="activityListKeys"
							labelName="activityListValues" />
					</html:select></td>
				<td>Is Part Of Assigned Task:</td>
				<td><html:select property="isPartOfAssignedTask">
						<html:options name="partOfAssignedListKeys"
							labelName="partOfAssignedListValues" />
					</html:select></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><html:textarea name="AssignTaskForm" property="taskDescription"></html:textarea></td>
				<td>Hours Spent:</td>
				<td><html:text name ="AssignTaskForm" property="hoursSpend"></html:text></td>
			</tr>
			<tr>
				<center>
					<input type="button" class="button" value="Add" id="addGrid">
					<input type="button" class="button" value="Submit" id="submit" onclick="fnSubmit()">
				</center>
			</tr>
		</table>
		<br/>
		<div id="grid">
		
		</div>
		<div id="element_to_pop_up">
    <a class="b-close">x<a/>
   <center> Description Cannot be Blank </center>
</div>
		<br/><br/>
	</html:form>
	 <input type="hidden" name="hiddenDate" id="hiddenDate" >
	  <input type="hidden" name="hidenActivity" id="hidenActivity" >
	   <input type="hidden" name="hiddenItem" id="hiddenItem" >
	    <input type="hidden" name="hiddenIsPartOfAssignedTask" id="hiddenIsPartOfAssignedTask" >
	     <input type="hidden" name="hiddenDescription" id="hiddenDescription"> 
	     <input type="hidden" name="hiddenHourSpent" id="hiddenHourSpent">
	     <input type="hidden" name="gridLoadFlag" id ="gridLoadFlag">
</body>
<footer>
<p class="credits">&copy;2013 Resource Management</p>
</footer>
</html>