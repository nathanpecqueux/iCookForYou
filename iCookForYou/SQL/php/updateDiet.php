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

$idDiet = $_POST['idDiet'];
$mail = $_POST['mail'];
$action = $_POST['action'];

if (strcmp($action, "add") == 0) {
    $Sql_Query = "insert into TUserDiet (idUserUD,idDietUD) values ((SELECT idUser FROM TUser where mail = '$mail'),'$idDiet')";
} elseif (strcmp($action, "del") == 0) {
    $Sql_Query = "DELETE ud.* FROM TUserDiet ud
        INNER JOIN TUser u
        ON idUserUD = idUser
        WHERE u.mail='$mail'
        and ud.idDietUD = '$idDiet'";
}

if (mysqli_query($conn, $Sql_Query)) {
    echo 'Data Inserted Successfully';
} else {
    echo 'Try Again';
}
mysqli_close($conn);

?>


