<?php
	$host='sql301.byethost33.com';
	$uname='b33_15999105';
	$pwd='******';
	$db='b33_15999105_myeten';
	//$fid=$_POST['fid'];
	//$fname=$_POST['fname'];
	$phno=$_POST['phno'];
	$abslist=$_POST['abslist'];
	//$role=$_POST['role'];
	$batch=$_POST['batch'];
	$branch=$_POST['branch'];
	$sec=$_POST['sec'];
	$date=date_create($_POST['date']);
	$date=date_format($date, 'Y-m-d');
	$time =$_POST['time'];
	//echo $time;

	$con = mysql_connect($host,$uname,$pwd); 
	mysql_select_db($db,$con); 
	if($time>='8:45:00:AM' AND $time<'9:35:00:AM')
	{
		$period=1;
	}
	elseif($time>='9:35:00:AM' AND $time<='10:25:00:AM')
	{
		$period=2;
	}
	elseif($time>='10:30:00:AM' AND $time<'11:25:00:AM')
	{
		$period=3;
	}
	elseif($time>='11:25:00:AM' AND $time<'12:15:00:PM')
	{
		$period=4;
	}
	elseif($time>='1:00:00:PM' AND $time<'1:50:00:PM')
	{
		$period=5;
	}
	elseif($time>='1:50:00:PM' AND $time<'2:40:00:PM')
	{
		$period=6;
	}
	elseif($time>='2:50:00:PM' AND $time<'3:40:00:PM')
	{
		$period=7;
	}
	elseif($time>='3:40:00:PM' AND $time<'4:30:00:PM')
	{
		$period=8;
	}
	else
	{
		mysql_close($con);
		exit("its not correct time to take attendance $time");

	}
	$absent=mysql_query("select abs_list from absentees where date='$date' AND branch='$branch' AND batch=$batch AND sec='$sec' AND period=$period",$con) or die("Cannot execute the query ".mysql_error());

	$num=mysql_query("SELECT FOUND_ROWS()",$con) or die("Cannot execute the query ".mysql_error());
    while($row=mysql_fetch_array($num))
    {
		
		$number=($row[0]);
	}
	if($number==0){
	$r=mysql_query("insert into absentees values($phno,'$abslist',$batch,'$branch','$sec','$date',$period)",$con) or die("Cannot execute the query $sql".mysql_error());
	echo $r.$date.$time.$number;
	}
	else
	{
		echo "Attendance already submitted";
	}
	mysql_close($con);
?>