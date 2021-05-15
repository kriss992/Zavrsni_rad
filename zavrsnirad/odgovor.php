<?php 
session_start();
require "spoj.php";
$server_ip = gethostbyname(gethostname());
$id_pr = isset($_GET['id_pr']) ? $_GET['id_pr'] : '';
$por = isset($_GET['por']) ? $_GET['por'] : '';
//$id_pr = $_GET["id_pr"];
//$por = $_GET["por"];
echo $id_pr;
echo "----------------------------------------------------------------------";
echo $por;
?>