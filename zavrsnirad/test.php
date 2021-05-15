<?php 
require "spoj.php";
$spoj->set_charset('utf8');
if(isset($_GET["id_pr_get"]) && isset($_GET["id_kor_get"])){
    $id_pr_get = $_GET["id_pr_get"];
    $id_kor_get = $_GET["id_kor_get"];
    $str_get = "Uspjeh GET!!! odgovor id_pr_get je: $id_pr_get, a id_kor_get je: $id_kor_get";
    echo $str_get;
}
if(isset($_POST["id_pr_post"]) && isset($_POST["id_kor_post"])){
    $id_pr_post = $_POST["id_pr_post"];
    $id_kor_post = $_POST["id_kor_post"];
    $str_post = "Uspjeh POST!!! odgovor id_pr_post je: $id_pr_post, a id_kor_post je: $id_kor_post";
    $echo = json_encode($str_post);
    $id = 1;
    $mysql_upit1 = "SELECT `id_prijave`, `anon`, `id_korisnik`, `datum`, "
        . "`latituda`, `longituda`, `adresa`, `zupanija`, `poruka`, "
        . "`ima_slika`, `odgovor` FROM `prijave` WHERE id_korisnik = '$id' ORDER BY `id_prijave` DESC;";
    $rezultat1 = mysqli_query($spoj, $mysql_upit1);
    $row1 = mysqli_fetch_all($rezultat1);
    if($rezultat1 == TRUE) {
        $str1 = json_encode($row1);
        $str2 = "uspjesno_dohvat_lista//$str1"; 

    
    echo $str2;
    }
}
?>