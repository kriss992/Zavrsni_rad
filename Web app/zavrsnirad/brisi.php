<?php 
require "spoj.php";
$server_ip = gethostbyname(gethostname());
$id_pr = $_GET["id_pr"];
$mysql_upit1 = "DELETE FROM `prijave` WHERE id_prijave = '$id_pr';";
$rezultat1 = mysqli_query($spoj, $mysql_upit1);
if($rezultat1 == true) {
    echo "uspjeh";
}
$spoj->close();
?>