<?php 
require "spoj.php";
$spoj->set_charset('utf8');
$ime = $_POST["ime"];
$prezime = $_POST["prezime"];
$oib = $_POST["oib"];
$korisnicko_ime = $_POST["korisnicko_ime"];
$lozinka = $_POST["lozinka"];
$mysql_upit = "INSERT INTO `korisnik` (`id`, `oib`, `ime`, `prezime`, "
        . "`korisnicko_ime`, `lozinka`) "
        . "VALUES (NULL, '$oib', '$ime', '$prezime', '$korisnicko_ime', "
        . "'$lozinka');";
$rezultat = mysqli_query($spoj, $mysql_upit);
if($rezultat === TRUE) { 
    echo "uspjesno_reg";
}
else {
    echo "neuspjesno_reg";
}
$spoj->close();
?>