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

$mail = $_POST['mail'];
$nameFood = $_POST['nameFood'];

$Sql_Query = "
DELETE s FROM TStock s
        INNER JOIN
    TUser u ON s.idUserS = u.idUser
        INNER JOIN
    TFood f ON s.idFood = f.idFood 
WHERE
    u.mail = '$mail'
    AND f.name = '$nameFood';
";

if (mysqli_query($conn, $Sql_Query)) {
    echo 'Data Inserted Successfully';
} else {
    echo 'Try Again';
}

mysqli_close($conn);

?>
