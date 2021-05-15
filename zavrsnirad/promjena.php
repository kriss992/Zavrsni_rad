<?php 
require "spoj.php";
$spoj->set_charset('utf8');
$tip = $_POST["tip"];
if($tip==="promjena"){
    $id = $_POST["id"];
    $ime = $_POST["ime"];
    $prezime = $_POST["prezime"];
    $oib = $_POST["oib"];
    $korisnicko_ime = $_POST["korisnicko_ime"];
    $lozinka = $_POST["lozinka"];
    $mysql_upit1 = "UPDATE `korisnik` SET `oib` = '$oib', `ime` = '$ime', "
            . "`prezime` = '$prezime', `korisnicko_ime` = '$korisnicko_ime', "
            . "`lozinka` = '$lozinka' WHERE `korisnik`.`id` = $id;";
    $mysql_upit2 = "select * from korisnik where id = '$id';";
    $rezultat1 = mysqli_query($spoj, $mysql_upit1);
    $rezultat2 = mysqli_query($spoj, $mysql_upit2);
    $row = mysqli_fetch_row($rezultat2);
    if($rezultat1 === TRUE) {
        $str = json_encode($row);
        $string = "uspjesno_promjena//$str"; 
        echo $string;
    }
    else {
        echo "neuspjesno_promjena";
    }
    $spoj->close();
}
if($tip==="brisanje"){
    $id = $_POST["id"];
    $mysql_upit = "DELETE FROM `korisnik` WHERE `korisnik`.`id` = $id";
    $rezultat = mysqli_query($spoj, $mysql_upit);
    if($rezultat === TRUE) {
        echo "uspjesno_brisanje"; 
    }
    else {
        echo "neuspjesno_brisanje";
    }
    $spoj->close();
}
?>