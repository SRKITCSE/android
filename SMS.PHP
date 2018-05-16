SMS.php
<?php
$host='sql301.byethost33.com';
$uname='b33_15999105';
$pwd='********';
$db='b33_15999105_myeten';
$phno=$_POST['usernum'];
$role=$_POST['role'];
functiongen_random($length=32)
{
	$final_rand='';
	for($i=0;$i< $length;$i++)
	{
		$final_rand .= rand(0,9); 
	} 
	return $final_rand;
}
$msg=urlencode(gen_random(5));
$con = mysql_connect($host,$uname,$pwd); 
mysql_select_db($db,$con); 
if($role=='ADMIN')
{
	$name=$_POST['uname'];
	$password=$_POST['pwd'];
	$ad=mysql_query("insert  into admin values($phno,$msg,'$name','$password')",$con);
	if (strpos(mysql_error(),'PRIMARY') !== false) {
		$ad=mysql_query("update admin set code=$msg where phno=$phno") or die("Cannot execute query".mysql_error());
	}
	$rm=mysql_query("select uname,password from admin where phno=$phno") or die("Cannot execute query".mysql_error());
		while($row=mysql_fetch_array($rm))
		{
			$mname=$row[0];
			$password=$row[1];
		}
$url="http://api.clickatell.com/http/sendmsg?user=$mname&password=$password&api_id=3529570&to=91$phno&text=$msg";
$ret = file($url);
}
else
{
	$r=mysql_query("UPDATE faculty SET code=$msg where phno=$phno") or die("Cannot execute the query ".mysql_error());
$r=mysql_affected_rows();
if($r!=0){
$rm=mysql_query("select uname,password from admin") or die("Cannot execute query".mysql_error());
		while($row=mysql_fetch_array($rm))
		{
			$mname=$row[0];
			$password=$row[1];
		}
$url="http://api.clickatell.com/http/sendmsg?user=$mname&password=$password&api_id=3529570&to=91$phno&text=$msg";
$ret = file($url);
}
//echo $ret[0];
echo $r;
}
mysql_close($con);
?>

