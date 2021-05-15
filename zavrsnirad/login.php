<?php 
header("Access-Control-Allow-Origin: *");

require "spoj.php";
$spoj->set_charset('utf8');
$korisnicko_ime = $_POST["korisnicko_ime"];
$lozinka = $_POST["lozinka"];
$mysql_upit = "select `id`, `oib`, `ime`, `prezime`, `korisnicko_ime`, "
        . "`lozinka`, `admin` from korisnik where korisnicko_ime = '"
        . "$korisnicko_ime' and lozinka = '$lozinka';";
$rezultat = mysqli_query($spoj, $mysql_upit);
$row = mysqli_fetch_row($rezultat);
if(mysqli_num_rows($rezultat) > 0) {
    $str = json_encode($row);
    $string = "uspjesno_login//$str";  
    echo $string;
}
else {
    echo "neuspjesno_login";
}
?>