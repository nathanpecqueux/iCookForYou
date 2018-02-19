<?php

// database constants
define('DB_HOST', '127.0.0.1');
define('DB_USER', 'root');
define('DB_PASS', '3FYB3LDd');
define('DB_NAME', 'icookforyou');

// connecting to database and getting the connection object
$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME, 0, '/var/run/mysqld/mysqld.sock');

// Checking if any error occured while connecting
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    die();
}

$mail = $_POST['mail'];

// creating a query
$stmt = $conn->prepare("select f.name, f.quantity, u.name
from TFood f
inner join TUnit u
on f.idUnitF = u.idUnit
inner join TStock s
on f.idFood = s.idFood
inner join TUser us
on s.idUserS = us.idUser
where us.mail = '$mail';");

// executing the query
$stmt->execute();

// binding results to the query
$stmt->bind_result($nameFood, $quantity, $nameUnit);

$food = array();

// traversing through all the result
while ($stmt->fetch()) {
    $temp = array();
    $temp['nameFood'] = $nameFood;
    $temp['quantity'] = $quantity;
    $temp['nameUnit'] = $nameUnit;
    array_push($food, $temp);
}

// displaying the result in json format
echo json_encode($food);
