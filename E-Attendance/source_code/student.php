<?php
	$host='sql301.byethost33.com';
	$uname='b33_15999105';
	$pwd='******';
	$db='b33_15999105_myeten';
	$rollno=$_POST['rollno'];
	$name=$_POST['name'];
	$phno=$_POST['phno'];
	$batch=$_POST['batch'];
	$branch=$_POST['branch'];
	$sec=$_POST['sec'];
	$con = mysql_connect($host,$uname,$pwd); 
	mysql_select_db($db,$con);
	$r=mysql_query("insert into student values('$rollno','$name',$batch,'$branch','$sec',$phno)",$con);// or die("Cannot execute the query ".mysql_error());
	if (strpos(mysql_error(),'phno') !== false) {
	 echo 'Phone Number already exists.Try a new number.';
	}

	elseif (strpos(mysql_error(),'PRIMARY') !== false) {
		echo 'Student already exists.';
	}

	else{
		echo $r;
	}
	mysql_close($con);
?>
		
