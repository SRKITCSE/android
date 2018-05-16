<?php
	$host='sql301.byethost33.com';
	$uname='b33_15999105';
	$pwd='******';
	$db='b33_15999105_myeten';
	$fid=$_POST['fid'];
	$con = mysql_connect($host,$uname,$pwd); 
	mysql_select_db($db,$con);
	$r=mysql_query("DELETE FROM faculty WHERE fid='$fid'") or die("Cannot execute the query ".mysql_error());
	$r=mysql_affected_rows();
	echo $r;
	mysql_close($con);
	?>