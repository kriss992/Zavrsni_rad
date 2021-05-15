<!DOCTYPE html>
<html lang="hr-HR">
<head>
    <meta charset="UTF-8"/>
    <title>Popis prijava</title>
    <link rel="stylesheet" href=
        "https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style type="text/css">
        body{ font: 14px sans-serif; }
        .wrapper{ width: 350px; padding: 20px; }
    </style>
</head>
<body>
    <div>
        <button type="button" class="btn btn-warning btn-md" name="Odjava"
                align="right" onclick="odjava()">Odjava</button>
    </div>
</body>
</html>
<?php
session_start();
require "spoj.php";
$spoj->set_charset('utf8');
if(!isset($_SESSION["prijavljen"]) && $_SESSION["prijavljeno"] === true){
    header("Location: index.php");
}
 else {
    $id = $_SESSION["id"][0];
    $admin = $_SESSION["admin"][0];
    /**$mysql_upit1 = "SELECT datum, id_prijave, id_korisnik FROM `prijave` "
            . "WHERE id_korisnik = '$id' ORDER BY id_korisnik DESC;";
    $mysql_upit2 = "SELECT datum, id_prijave, id_korisnik FROM `prijave`  "
            . "ORDER BY id_prijave DESC;";**/
    $mysql_upit1 = "SELECT * FROM `prijave` "
            . "WHERE id_korisnik = '$id' ORDER BY `status_obrade` DESC, `id_prijave` DESC;";
    $mysql_upit2 = "SELECT * FROM `prijave` ORDER BY `status_obrade` DESC, `id_prijave` DESC;";

    /*$mysql_upit2 = "SELECT datum, id_prijave, id_korisnik FROM `prijave` ORDER "
            . "BY id_korisnik, id_prijave DESC;";*/
    if($admin==0){$rezultat1 = mysqli_query($spoj, $mysql_upit1);}
    else{$rezultat1 = mysqli_query($spoj, $mysql_upit2);}
    $row1 = mysqli_fetch_all($rezultat1);
    $server_ip = gethostbyname(gethostname());
    $tablica = "<script>function odjava(){window.location.href "
               ."= 'http://".$server_ip."/zavrsnirad/odjava.php';}"
               ."function prikaz(id_pr, id_kor) {var pom1 = id_pr.toString();"
               ."var pom2 = id_kor.toString();var str = 'http://".$server_ip."/"
               ."zavrsnirad/prikaz.php?id_pr='+pom1+'&id_kor='+pom2;window.open"
               ."(str, '_top');}</script>";
    if($rezultat1 == true) {
        $tablica .= "<table class='table table-striped' align='center'><thead>"
                . "<tr><th>Popis prijava</th><th></th><th></th><th></th><th></th></thead><thead>"
                . "<tr><th width='215'>Datum i vrijeme prijave</th>"
                . "<th width='215'>Kategorija prijave</th>"
                . "<th width='215'>Naslov prijave</th>"
                . "<th width='215'>Korisnik</th>"
                . "<th width='215'>Status obrade</th>"
                . "</thead><tbody>";
        if(empty($row1)){
            $tablica = "<script>function odjava(){window.location.href "
                        ."= 'http://".$server_ip."/zavrsnirad/odjava.php';}"
                    . "</script><h2 align='center'>Nemate prijava.</h2>";
            echo $tablica;}
        else{
            for($i = 0; $i < sizeof($row1); $i++){
                $str1 = $row1[$i][0];//id prijave
                $str2 = $row1[$i][1];//anon
                $str3 = $row1[$i][2];//id korisnik
                $str_datum = $row1[$i][3];//datum
                $str_kategorija = $row1[$i][8];//kategorija
                $str_naslov = $row1[$i][9];//naslov
                $str_status = $row1[$i][13];//status
                $mysql_upit3 = "SELECT korisnicko_ime, ime, prezime, oib "
                        . "FROM `korisnik` WHERE id = '$str3';";
                $rezultat1 = mysqli_query($spoj, $mysql_upit3);
                $row2 = mysqli_fetch_row($rezultat1);
                $str4 = $row2[0];//user name
                $str5 = $row2[1];//ime
                $str6 = $row2[2];//prezime
                $str7 = $row2[3];//oib
                if($str3=="0"){
                    $tablica .= "<tr class='clickable-row' onclick='prikaz"
                            . "(".$str1.",".$str3.")'><td>$str_datum</td>"
                            . "</td><td>$str_kategorija</td><td>$str_naslov</td><td>anoniman</td><td>$str_status</tr>";
                }
                else{
                    $tablica .= "<tr class='clickable-row' onclick='prikaz"
                            . "(".$str1.",".$str3.")'><td>$str_datum</td>"
                            . "</td><td>$str_kategorija</td><td>$str_naslov</td>"
                            . "<td>Korisnik: $str4<br> Ime i prezime: $str5 "
                            . "$str6<br> OIB: $str7</td><td>$str_status</tr>";
                }
            }
            $tablica .= "</tbody></table>";
            echo $tablica;
        }
    }
    $spoj->close();
}
?>