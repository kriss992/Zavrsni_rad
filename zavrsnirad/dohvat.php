<?php 
require "spoj.php";
$spoj->set_charset('utf8');
$server_ip = gethostbyname(gethostname());
if(isset($_POST["tip"]) && isset($_POST["id"])){
    $tip = $_POST["tip"];
    if($tip==="dohvat_lista"){
    $id = $_POST["id"];
    $mysql_upit1 = "SELECT `id_prijave`, `anon`, `id_korisnik`, `datum`, "
            . "`latituda`, `longituda`, `adresa`, `zupanija`, `poruka`, `kategorija_prijave`, `naslov_prijave`, "
            . "`ima_slika`, `odgovor`, `status_obrade`  FROM `prijave` WHERE id_korisnik = '$id' ORDER BY `status_obrade` DESC, `id_prijave` DESC;";
    $rezultat1 = mysqli_query($spoj, $mysql_upit1);
    $row1 = mysqli_fetch_all($rezultat1);
    if($rezultat1 == TRUE) {
        $str1 = json_encode($row1);
        $str1 = "uspjesno_dohvat_lista//$str1"; 
        echo $str1;
    }
    else {
        echo "neuspjesno_dohvat_lista//";
    }
    $spoj->close();
    }
    if($tip==="dohvat_slika"){
        $id = $_POST["id"];
        $mysql_upit2 = "SELECT slika_add FROM slike WHERE id_prijenos = '$id'";
        $rezultat2 = mysqli_query($spoj, $mysql_upit2);
        $row1 = mysqli_fetch_all($rezultat2);
        if($rezultat2 == TRUE) {
            for($i = 0; $i < sizeof($row1); $i++){
                $str3 = $row1[$i][0];
                if($i == 0){$str2 = "["; }
                $str2 .= '["http:\/\/'.$server_ip.'\/zavrsnirad\/slike\/'.$str3.'"]';
                if($i != (sizeof($row1)-1)){ $str2 .= ","; }
                if($i == (sizeof($row1)-1)){$str2 .= "]"; }
                }
                $str1 = "uspjesno_dohvat_slika//$str2";
                echo $str1;
        }
        else {
            echo "neuspjesno_dohvat_slika//";
        }
        $spoj->close();
    }
}
?>
