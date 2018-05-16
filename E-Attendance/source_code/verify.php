<?php
	$host='sql301.byethost33.com';
	$uname='b33_15999105';
	$pwd='******';
	$db='b33_15999105_myeten';
	$phno=$_POST['phno'];
	$code=$_POST['code'];
	$role=$_POST['role'];

	$con = mysql_connect($host,$uname,$pwd); 
	mysql_select_db($db,$con); 
	if($role=='ADMIN')
	{
		$r=mysql_query("select code from admin where phno=$phno",$con) or die("Cannot execute the query".mysql_error());
		while($row=mysql_fetch_array($r))
		{
			$code_returned=$row[0];
		}
		if($code_returned==$code)
		{
			echo "success";
		}
		else
		{
			echo "Enter a valid code.";
		}

	}
	else{

	$r=mysql_query("select code,designation from faculty where phno=$phno",$con);
	

	while($row=mysql_fetch_array($r))
	{
		$code_returned=$row[0];
		$designation=$row[1];
	}
	if($code_returned==$code)
	{
		echo $designation;
	}
	else
	{
		echo "Enter a valid code.";
	}
	}
	mysql_close($con);
?>