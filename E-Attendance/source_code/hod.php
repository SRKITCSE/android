<?php
    $host='sql301.byethost33.com';
	$uname='b33_15999105';
	$pwd='******';
	$db='b33_15999105_myeten';
	//$fid=$_POST['fid'];
	//$fname=$_POST['fname'];
	//$phno=$_POST['phno'];
	//$role=$_POST['role'];
	$op=$_POST['op'];
	
	$con = mysql_connect($host,$uname,$pwd); 
	mysql_select_db($db,$con); 
    
	if($op=='list'){
	    $date=date_create($_POST['date']);
	    $date=date_format($date, 'Y-m-d');
	    $batch=$_POST['batch'];
	    //$branch=$_POST['branch'];
	    $sec=$_POST['sec'];
    	$period=$_POST['period'];
    	$phno=$_POST['phno'];
    	$branch='';
	
    $dept=mysql_query("select branch from faculty where phno=$phno",$con) or die("Cannot execute the query $sql".mysql_error());
    
    	while($row=mysql_fetch_array($dept))
    	{	
	    	$branch=$row[0];
    	}
	    //echo $branch.$date.$batch.$sec.$period;
    	$absent=mysql_query("select abs_list,fphno from absentees where date='$date' AND branch='$branch' AND batch=$batch AND sec='$sec' AND period=$period",$con) or die("Cannot execute the query ".mysql_error());
    	$num=mysql_query("SELECT FOUND_ROWS()",$con) or die("Cannot execute the query hello ".mysql_error());
    	while($row=mysql_fetch_array($num))
    	{
    		
    		$number=($row[0]);
    	}
      
	    if($number==0)
    	{
    		echo $number;
    	}
	    else
    	{
	    	while($row=mysql_fetch_array($absent))
	    	{
		    	$abslist=($row[0]);
                $fphno=$row[1];
		    } 
		    $r=mysql_query("select fname from faculty where phno=$fphno",$con) or die("Cannot execute the query hii ".mysql_error());
	    	while($row=mysql_fetch_array($r))
	    	{
		    	$fname=($row[0]);
		    }
	    	echo "$abslist ZQ$fname";
    	}       
    
	}
	elseif($op=='group')
	{
		$abs=$_POST['abs'];
		$batch=$_POST['batch'];
		$sec=$_POST['sec'];
		$phno=$_POST['phno'];
		$dept=mysql_query("select branch from faculty where phno=$phno",$con) or die("Cannot execute the query $sql".mysql_error());
	
		while($row=mysql_fetch_array($dept))
		{		
			$branch=$row[0];
		}

		
		$group=explode(",",$abs);
		$r=mysql_query("select uname,password from admin") or die("Cannot execute query".mysql_error());
		while($row=mysql_fetch_array($r))
		{
			$mname=$row[0];
			$password=$row[1];
		}
		$msg=$_POST['message'];
	
		foreach($group as $rno){
		    $r=mysql_query("select name,phno from student where rollno=$rno AND batch=$batch AND branch='$branch' AND sec='$sec'",$con) or die("Cannot execute query".mysql_error());
            $num=mysql_query("SELECT FOUND_ROWS()",$con) or die("Cannot execute the query hello ".mysql_error());
            while($row=mysql_fetch_array($num))
        	{	
		         $number=($row[0]);
     	    }
             
            if($number!=0){
	        	while($row=mysql_fetch_array($r))
	        	{
		        	$name=$row[0];
		        	$phno=$row[1];	
			       $url="http://api.clickatell.com/http/sendmsg?user=$mname&password=$password&api_id=3529570&to=91$phno&text=$msg";
		        	$ret = file($url);
		        	echo "<br/><br/>Messages sent to $rno-".$name.",";
		        }
            }
		   
            else{
              echo "<br/><br/>Rollno-$rno not found. Make sure class-teacher entered student into database.";
            }
        }
		
	}
	//print(json_encode($absent));
	//echo $abslist.",$number,$absent";
	//echo "0,$number,$row";
	mysql_close($con);
?>