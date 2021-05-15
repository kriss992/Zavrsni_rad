<?php 
require "spoj.php";
$spoj->set_charset('utf8');
$anon = $_POST["anon"];
$korisnik_id = $_POST["korisnik_id"];
$latituda = $_POST["latituda"];
$longituda = $_POST["longituda"];
$adresa = $_POST["adresa"];
$zupanija = $_POST["zupanija"];
$poruka = $_POST["poruka"];
$datum = $_POST["datum"];
$ima_slika = $_POST["ima_slika"];
$ip_usr = $_SERVER['REMOTE_ADDR'];
$mysql_upit1 = "INSERT INTO `prijave` (`id_prijave`, `anon`, `id_korisnik`, "
        . "`datum`, `latituda`, `longituda`, `adresa`, `zupanija`, `poruka`, "
        . "`ima_slika`, `odgovor`, `ip_korisnik`) VALUES (NULL, '$anon', "
        . "'$korisnik_id', '$datum', '$latituda', '$longituda', '$adresa', "
        . "'$zupanija', '$poruka', '$ima_slika', '', '$ip_usr');";
$rezultat1 = mysqli_query($spoj, $mysql_upit1);
$id_prijave = getIDprijave();
$mysql_upit2 = "select * from prijave where id_prijave = '$id_prijave';";
$rezultat2 = mysqli_query($spoj, $mysql_upit2);
$row = mysqli_fetch_row($rezultat2);
if($rezultat1 == TRUE) { 
    $str = json_encode($row);
    $string = "uspjesno_prijava//$str"; 
    echo $string;
}
else {
    echo "neuspjesno_prijava";
}
$spoj->close();
	function getIDprijave(){		
                $spoj = mysqli_connect(HOST,USER,PASS,DB);
		$sql = "SELECT max(id_prijave) as id_prijave FROM prijave";
		$result2 = mysqli_fetch_array(mysqli_query($spoj,$sql));
		mysqli_close($spoj);
		return $result2['id_prijave']; 
	}
?>