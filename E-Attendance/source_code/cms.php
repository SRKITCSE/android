<?php
	$host='sql301.byethost33.com';
	$uname='b33_15999105';
	$pwd='******';
	$db='b33_15999105_myeten';
	$rollno=$_POST['sno'];
	$batch=$_POST['batch'];
	$sec=$_POST['sec'];
	$phno=$_POST['phno'];
	$op=$_POST['op'];
	$con = mysql_connect($host,$uname,$pwd); 
	mysql_select_db($db,$con);
	$dept=mysql_query("select branch from faculty where phno=$phno") or die("Cannot execute the query hii".mysql_error());
	
		while($row=mysql_fetch_array($dept))
		{		
			$branch=$row[0];
		}

	
	$r=mysql_query("select name,phno from student where rollno=$rollno AND batch=$batch AND branch='$branch' AND sec='$sec'",$con) or die("Cannot execute query hello".mysql_error());
        $num=mysql_query("SELECT FOUND_ROWS()",$con) or die("Cannot execute the query ".mysql_error());
         while($row=mysql_fetch_array($num))
	{
		
		$number=($row[0]);
	}
if($number==0)
{
echo $number;
}
else{
	while($row=mysql_fetch_array($r))
	{
		$name=$row[0];
		$sphno=$row[1];
	}
	if($op=='name')
	{
	echo $name.",".$sphno;
	}
	elseif($op=='msg')
	{
		$msg=$_POST['message'];
		//$msg=str_replace(" ", "_", "$msg");
		$r=mysql_query("select uname,password from admin") or die("Cannot execute query".mysql_error());
		while($row=mysql_fetch_array($r))
		{
			$mname=$row[0];
			$password=$row[1];
		}
		$url="http://api.clickatell.com/http/sendmsg?user=$mname&password=$password&api_id=3529570&to=91$sphno&text=$msg";
		$ret = file($url);
		echo "Message sent successfully";
	}
}
	mysql_close($con);
?>
	