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

mysqli_set_charset($conn, "utf8");

// creating a query
$stmt = $conn->prepare("SELECT idAllergy, name FROM TAllergy;");

// executing the query
$stmt->execute();

// binding results to the query
$stmt->bind_result($idAllergy, $name);

$allergy = array();

// traversing through all the result
while ($stmt->fetch()) {
    $temp = array();
    $temp['name'] = $name;
    array_push($allergy, $temp);
}

// displaying the result in json format
echo json_encode($allergy, JSON_UNESCAPED_UNICODE);

?>
