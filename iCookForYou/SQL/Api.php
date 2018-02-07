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
 
 //creating a query
 $stmt = $conn->prepare("SELECT idUser, lastName, firstName FROM TUser;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($idUser, $lastName,$firstName);
 
 $user = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['idUser'] = $idUser; 
 $temp['lastName'] = $lastName; 
 $temp['firstName'] = $firstName; 
 array_push($user, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($user);
