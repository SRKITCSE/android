<%@include file="CompanyHeader.jsp" %>
<head>
<link rel="stylesheet" type="text/css" media="all" href="jq/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="jq/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="ajax/jquery-1.5.2.min.js"></script>
<script type="text/javascript">
 function performAjaxSubmit(){
     alert('Ajax Submit Entry');
        try{
        var signImage = document.getElementById("sign").files[0];
        var formdata = new FormData();        
        formdata.append("signImage", signImage);
        var xhr = new XMLHttpRequest();	
        xhr.open("POST","FileUpload.jsp",true); 
        xhr.send(formdata);
        xhr.onload = function(e) 
        {
            if (this.status == 200) 
            {  alert("OK--");
               document.getElementById("resp").src=this.responseText;             
            }else{            
	    alert("not ok");
            document.getElementById("resp2").innerHTML=this.responseText;
        }
        }     
        }catch(e)
        {alert(e);
        }
    }
    

 window.onload = function(){
  new JsDatePick({
	useMode:2,
	target:"dob",
	dateFormat:"%d-%M-%Y"
	});
  };

function validate()
{
        var val=document.frm.phone.value;
	var stripped=val.replace(/[\(\)\.\-\ ]/g, '');
        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        var email=document.frm.email.value;
        var conemail=document.frm.conemail.value;

	try{
		if(document.frm.name.value=="")
		{
			alert("Please enter Name"); 
			document.frm.name.focus();
			return false;
	  	}
		
		else if(checkAlphabets(document.frm.name.value))

		{
			alert("The name field contains invalid characters or spaces.");
			document.frm.name.focus();
			return false;
		}				          
		else if(val==0)
		{	        
			alert("Please enter your phone number");
			document.frm.phone.focus();
			return false;
	  	}
                else if(checkNumber(document.frm.phone.value))
		{
			alert("Please enter only number digits for phone number...!!");
			document.frm.phone.focus();
			return false;
		}
                else if (isNaN(parseInt(stripped)))
		{
			alert("The phone number contains illegal characters.\n");
			return false;
		}  
		else if(val.length<10 || val.length>10)
		{	        
			alert("Phone number should contain 10 numbers..!!");
			document.frm.phone.focus();
			return false;
	  	}
                else if(document.frm.email.value==0)
		{	alert(" Please enter a valid email id ");
            			document.frm.email.focus();
             			return false;
	  	}                 
                else  if(!(email.match(mailformat)))
                {  
                    alert("You have entered an invalid email address!");  
                    document.frm.email.focus();
                    return false;  
                }                 
		else if(document.frm.password.value=="")
		{
			alert(" Please enter password"); 
			document.frm.password.focus();
			return false;
	  	}
              
                else if(document.frm.conpassword.value=="")
		{
			alert(" Please enter confirm password"); 
			document.frm.conpassword.focus();
			return false;
	  	}
                else if(!(document.frm.password.value==document.frm.conpassword.value))
                {
                        alert(" Please enter same password for password and confirm password"); 
			document.frm.conpassword.focus();
			return false;
                }               
	  	
            }catch(e)

		{	alert(e);

                    			return false;
		}

	return true;

}
  
  
function checkAlphabets(str)
{
	for (var i = 0; i < str.length; i++)
	{       var ch = str.substring(i, i + 1);
		if(((ch < "a" || "z" < ch) && (ch < "A" || "Z" < ch))&& (ch!=" ")) 

		{return true;
		}
	}
	return false;
}


function checkNumber(str)
{
	for (var i = 0; i < str.length; i++)
	{
		var ch = str.substring(i, i + 1);
		if((ch<"0")||(ch>"9"))
		{
			return true;
		}
	}
	return false;
}
</script>
</head> 
  <div class="body-wrapper" >      
      <br/>
<div class="body-middle" style="min-height: 400px;">   
<br/><br/><br/><center><h3>Driver Registration Form </h3>
<form action="AddCab1.jsp" name="frm" onsubmit="return validate();" method="post">
    <table align="center"  style="border: #ff6666 double thin;padding: 30px;" cellpadding="10px;">
             <tr><td>Car No:</td><td><input type="text" name="carno" required></td></tr>            
             <tr><td>Availability:</td><td><select name="status">
                         <option value="1">available</option>
                         <option value="0">unavailable</option>
                     </select></td></tr>
            <tr>
                <td><center><input type="submit" value="Add Cab"></center></td>
                <td><center><input type="reset" name="reset"></center></td>
            </tr>
    </table>
</form>
</center>
</div>       
  </div><br/><br/>
  <%@include file="CommonFooter.jsp" %>