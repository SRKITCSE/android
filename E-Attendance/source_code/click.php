<?php
	$host='sql301.byethost33.com';
	$uname='b33_15999105';
	$pwd='******';
	$db='b33_15999105_myeten';
	$name=$_POST['uname'];
	$password=$_POST['pwd'];
	$phno=$_POST['phno'];
	$con = mysql_connect($host,$uname,$pwd); 
	mysql_select_db($db,$con); 
	$r=mysql_query("update admin set uname='$name',password='$password' where phno=$phno") or die("Cannot execute the query ".mysql_error());
	$r=mysql_affected_rows();
	if($r!=0)
	echo 'Updated Successfully';
	mysql_close($con);
	?>