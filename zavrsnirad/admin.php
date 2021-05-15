<?php 
require "spoj.php";
$spoj->set_charset('utf8');
$tip = isset($_GET['tip']) ? $_GET['tip'] : '';
$id_pr = isset($_GET['id_pr']) ? $_GET['id_pr'] : '';
$por = isset($_GET['por']) ? $_GET['por'] : '';
if($tip=="brisi"){
    $mysql_upit1 = "SELECT `ima_slika`FROM `prijave` WHERE id_prijave = "
            . "'$id_pr';";
    $rezultat1 = mysqli_query($spoj, $mysql_upit1);
    $row1 = mysqli_fetch_all($rezultat1);
    if($row1[0][0] == "da"){
        $mysql_upit2 = "SELECT `slika_add` FROM `slike` WHERE id_prijenos = '"
                . "$id_pr';";
        $rezultat2 = mysqli_query($spoj, $mysql_upit2);
        $row2 = mysqli_fetch_all($rezultat2);
        for($i = 0; $i < sizeof($row2); $i++){
            $put = "slike/".$row2[$i][0]."";
            unlink($put);
        }
        $mysql_upit3 = "DELETE FROM `slike` WHERE id_prijenos = '$id_pr';";
        $rezultat3 = mysqli_query($spoj, $mysql_upit3);
    }
    $mysql_upit4 = "DELETE FROM `prijave` WHERE id_prijave = '$id_pr';";
    $rezultat4 = mysqli_query($spoj, $mysql_upit4);
    if($rezultat4 == true) {echo "uspjeh_brisanje";}
    $spoj->close();
}
if($tip=="odgovor"){
    $mysql_upit1 = "UPDATE `prijave` SET `odgovor` = '$por' WHERE "
            . "`prijave`.`id_prijave` = '$id_pr';";
    $rezultat1 = mysqli_query($spoj, $mysql_upit1);
    if($rezultat1 == true) {
        echo "uspjeh_odgovor";
    }
    $spoj->close();
}
?>