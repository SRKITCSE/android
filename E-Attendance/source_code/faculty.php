<?php
	$host='sql301.byethost33.com';
	$uname='b33_15999105';
	$pwd='******';
	$db='b33_15999105_myeten';
	$fid=$_POST['fid'];
	$fname=$_POST['fname'];
	$phno=$_POST['phno'];
	$role=$_POST['role'];
	$branch=$_POST['branch'];
	if($role=='FACULTY')
	{
		$batch=0;
        $sec='';
		$branch='';
	}
	elseif($role=='HOD')
    {
        $batch=0;
        $sec='';
    }
    else{
            $batch=$_POST['batch'];
            $sec=$_POST['sec'];
    }
	$number=0;	

	$con = mysql_connect($host,$uname,$pwd); 
	mysql_select_db($db,$con);
	
	if($role=='HOD' OR $role=='CLASS_TEACHER')//if condition is true check wheather hod or class teacher already exists or not for respective branch and class. 
	{
		$r=mysql_query("SELECT batch,branch,sec from faculty where designation='$role' AND branch='$branch' AND batch=$batch AND sec='$sec'") or die("Cannot execute the query ".mysql_error());
		$num=mysql_query("SELECT FOUND_ROWS()",$con) or die("Cannot execute the query ".mysql_error());
	
	while($row=mysql_fetch_array($num))
	{
		
		$number=($row[0]);
	}

	}
	if($number!=0)//if condition is true then hod or classteacher already exists.
	{
		//echo $role." already exists for $batch,$branch,$sec,".$num;
		mysql_close($con);
		exit($role." already exists for $batch,$branch,$sec,");
	}


	$r=mysql_query("insert into faculty values('$fid','$fname',$phno,'$role',$batch,'$branch','$sec','')",$con);// or die("Cannot execute the query ");

	
	if (strpos(mysql_error(),'phno') !== false) {
	 echo 'Phone Number already exists.Try a new number.';
	}

	elseif (strpos(mysql_error(),'PRIMARY') !== false) {
		echo 'Faculty ID already exists .Try a new ID.';
	}

	else{
		echo $r;
	}
	
	
	mysql_close($con);
?>