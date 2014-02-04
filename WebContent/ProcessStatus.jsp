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
	var count = document.all("count");
	if(!count.value.match(/^[0-9]*$/)){
		var alertPopUp ='';
		alertPopUp+= '<a class="b-close">x<a/>';
		alertPopUp+=' <center> Count Should be Numeric </center>';
		$("#element_to_pop_up").html(alertPopUp);
		  $('#element_to_pop_up').bPopup();
		  return false;
	}
	else if(count.value == null || count.value==""){
		var alertPopUp ='';
		alertPopUp+= '<a class="b-close">x<a/>';
		alertPopUp+=' <center> Count cannot be empty </center>';
		$("#element_to_pop_up").html(alertPopUp);
		  $('#element_to_pop_up').bPopup();
		  return false;
	}
	return true;
}
$( document ).ready(function() {
	$("#addGrid").click(function(){
		var item=document.all("items");
		var activity = document.all("activity");
		var count = document.all("count");
		var status = document.all("status");
		if(validateGrid()){
			document.all("gridLoadFlag").value= 'Y';
	      if(firtTimeAddFlag==false){
	    	  	var constructTable ='';
	    		constructTable+='<div class="CSSTableGenerator"><center><table id="addedGrid"><th> Item </th><th>Activity</th> <th> count </th><th>status</th>';
	    		constructTable+= '<tr><td>'+item.value+'</td><td>'+activity.value+'</td><td>'+count.value+'</td><td>'+status.value+'</td></tr>';
	    		
	    		constructTable+='</table></center></div>';
	    		$('#grid').html(constructTable);
	        	
	        	firtTimeAddFlag= true;
	      }
	      else {
	    	    var table = $("#addedGrid");
	    	  var constructTable ='';
	    		constructTable+='<div class="CSSTableGenerator"><center><table id="addedGrid"><th> Item </th><th>Activity</th> <th> count </th><th>status</th>';
	    		
	    		  table.find('tr').each(function (i) {
	                  var $tds = $(this).find('td'),
	                      item = $tds.eq(0).text(),
	                      activity = $tds.eq(1).text(),
	                      status = $tds.eq(2).text(),
	                  	count = $tds.eq(3).text();
	                  		                  // do something with productId, product, Quantity
	  					if(i>=1){                
	                  	constructTable+='<tr><td>'+item+'</td>'+'<td>'+activity+'</td>'+'<td>'+status+'</td>'+'<td>'+count+'</td></tr>';
	                  //	totalHours=parseFloat(totalHours)+parseFloat(hourSpend);
	  					}
	                  
	                  
	                  //alert('Row ' + (i + 1) + ':\nId: ' + date
	                    //    + '\nProduct: ' + activity
	                      //  + '\nQuantity: ' + isPart);
	              });
	    		  
	    		  constructTable+= '<tr><td>'+item.value+'</td><td>'+activity.value+'</td><td>'+count.value+'</td><td>'+status.value+'</td></tr>';
	    		  
	    		  constructTable+='</table></center></div>';
		    		$('#grid').html(constructTable);
		        	
	      }
	      $.ajax({
	     		
	             type : 'GET',
	              /* url : 'FatcaSearchDataAction.do', */
	              url : 'ProcessStatusDataAction.do',
	              data : {
	              	gridLoadFlag : document.all("gridLoadFlag").value,hiddenItem : item.value , hiddenActivity : activity.value ,hiddenCount : count.value,hiddenStatus :status.value 
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
          url : 'ProcessStatusAction.do',
          data : {
          //	gridLoadFlag : document.all("gridLoadFlag").value,hiddenItem : item.value , hiddenActivity : activity.value ,hiddenCount : count.value,hiddenStatus :status.value 
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
		<center>Process Status</center>
	</h3>	
<center><input type="button" class="button" value="Add" id="addGrid">
<input type="button" class="button" value="Submit" id="submit" onclick="fnSubmit()">
</center>
<br/>
	<html:form action="/ProcessStatusDataAction">
<table width="100%" border="0" align="center" cellspacing="0"
			cellpadding="0">
			<tr>
			<td>Item :</td>
			<td><html:select property="items">
						<html:options name="itemListKeys" labelName="itemListValues" />
					</html:select></td>
			<td>Activity</td>
			<td><html:select property="activity">
						<html:options name="processActivityListKeys" labelName="processActivityListValues" />
					</html:select></td>
			<td>Count</td>
			<td><html:text name ="ProcessStatusForm" property="count" size="1"></html:text></td>
			<td>Status</td>
			<td><html:select property="status">
						<html:options name="statusListKeys" labelName="statusListValues" />
					</html:select></td>
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
        <input type="hidden" name="hiddenItem" id="hiddenItem" >
	    <input type="hidden" name="hiddenActivity" id="hiddenActivity" >
	     <input type="hidden" name="hiddenCount" id="hiddenCount"> 
	     <input type="hidden" name="hiddenStatus" id="hiddenStatus">
     
			</html:form>
			</body>
<footer>
<p class="credits">&copy;2013 Resource Management</p>
</footer>
</html>