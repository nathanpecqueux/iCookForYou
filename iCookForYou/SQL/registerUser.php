<?php 
 
 //database constants
 define('DB_HOST', '127.0.0.1');
 define('DB_USER', 'root');
 define('DB_PASS', '3FYB3LDd');
 define('DB_NAME', 'icookforyou');
                         
//connecting to database and getting the connection object
$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME,0,'/var/run/mysqld/mysqld.sock');

 
 //Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
 
 $lastName = $_POST['lastName'];
 $firstName = $_POST['firstName'];
 $mail = $_POST['mail'];
 $password = $_POST['password'];
 
 $Sql_Query = "insert into TUser (lastName,firstName,mail,password) values ('$lastName','$firstName','$mail','$password')";
 
 if(mysqli_query($conn,$Sql_Query)){
 echo 'Data Inserted Successfully';
 }
 else{
 echo 'Try Again';
 }
 mysqli_close($conn);

