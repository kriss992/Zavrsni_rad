<html lang="hr-HR">
<head>
    <meta charset="UTF-8"/>
    <title>Prijava</title>
    <link rel="stylesheet" href=
        "https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style type="text/css">
        body{ font: 14px sans-serif; }
        .wrapper{ width: 350px; padding: 20px; }
    </style>
</head>
</html>
<?php 
session_start();
require "spoj.php";
$spoj->set_charset('utf8');
$server_ip = gethostbyname(gethostname());
$id_pr = $_GET["id_pr"];
$id_kor = $_GET["id_kor"];
$admin = $_SESSION["admin"][0];
$mysql_upit1 = "SELECT * FROM `prijave` WHERE id_prijave = '$id_pr';";
$rezultat1 = mysqli_query($spoj, $mysql_upit1);
$row1 = mysqli_fetch_row($rezultat1);
$mysql_upit3 = "SELECT korisnicko_ime, ime, prezime, oib FROM `korisnik` WHERE "
        . "id = '$id_kor';";
$rezultat3 = mysqli_query($spoj, $mysql_upit3);
$row3 = mysqli_fetch_row($rezultat3);       
if($rezultat1 == TRUE) {
    $tablica = "";
    if($admin==1){
        $tablica .= "<script>function odgovor(id_pr){var pom1 = id_pr.toString"
                . "();var por = document.getElementById('odg').value;var xhttp"
                . " = new XMLHttpRequest();xhttp.onreadystatechange = function"
                . "() {if (this.readyState == 4 && this.status == 200) {if(this"
                . ".responseText=='uspjeh_odgovor'){alert('Odgovor je poslan!')"
                . ";}}};xhttp.open('GET', 'http://".$server_ip."/zavrsnirad/"
                . "admin.php?tip=odgovor&id_pr='+pom1+'&por='+por, true);"
                . "xhttp.send();}</script>";
        $tablica .= "<script>function brisi(id_pr) {if (confirm('Da li "
                . "želite izbrisati?')){var pom1 = id_pr.toString();var "
                . "xhttp = new XMLHttpRequest();xhttp.onreadystatechange = "
                . "function() {if (this.readyState == 4 && this.status == 200) "
                . "{if(this.responseText=='uspjeh_brisanje'){window.location."
                . "href ='http://".$server_ip."/zavrsnirad/lista.php';}}};"
                . "xhttp.open('GET', 'http://".$server_ip."/zavrsnirad/admin."
                . "php?tip=brisi&id_pr='+pom1, true);xhttp.send();}else{}}"
                . "</script>";
        $tablica .= "<script>function odjava(){window.location.href "
                . "= 'http://".$server_ip."/zavrsnirad/odjava.php';}"
                . ""
                . "</script>";
        
        $tablica .= "<body><div><button type='button' class='btn btn-danger
                btn-md' id = 'brisi' name='brisi' align='right' onclick='brisi
                (".$id_pr.")'>Brisanje</button><button type='button' 
                class='btn btn-warning btn-md' name='Odjava' align='right' 
                onclick='odjava()'>Odjava</button></div></body>";
    }
    else{
        $tablica .= "<script>function odjava(){window.location.href 
            = 'http://".$server_ip."/zavrsnirad/odjava.php';}</script>";
        $tablica .= "<body><div><button type='button' class='btn btn-warning "
                 . "btn-md' name='Odjava' align='right' onclick='odjava()'>"
                 . "Odjava</button></div></body>";
    }
    $tablica_s = "";
    $pad = "";
    $tablica .= "<table class='table table-striped' align='center'>"
            . "<thead></thead><tbody>";
    if($row1[11] == "da"){
        $mysql_upit2 = "SELECT slika_add FROM slike WHERE id_prijenos = "
                . "'$id_pr'";
        $rezultat2 = mysqli_query($spoj, $mysql_upit2);
        $row2 = mysqli_fetch_all($rezultat2);
        if($rezultat2 == TRUE) {
            for($i = 0; $i < sizeof($row2); $i++){
                $url = "http://".$server_ip."/zavrsnirad/slike/"
                        .$row2[$i][0]."";
                $pad .= "<td></td>";
                $tablica_s .= "<td><img src='".$url."' alt='Trulli' "
                        . "width='500' height='300'></td>";
            }
            $tablica .= "<tr><td>Slike</td>".$pad."</tr><tr>$tablica_s</tr>";
        }
    }
    if($id_kor==0){
        $tablica .= "<tr><td>Prijavio</td><td>anoniman korisnik</td>".$pad.""
                . "</tr><tr>";
    }
    else{
        $tablica .= "<tr><td>Prijavio</td><td>Korisnik: ".$row3[0]." "
            . "Ime i prezime: ".$row3[1]." ".$row3[2]." OIB: ".$row3[3].""
            . "</td>".$pad."</tr>";
    }
    $tablica .= "<tr><td>Vrijeme prijave</td><td>".$row1[3]."</td>".$pad."</tr>"
            . "<tr><td>Koordinate prijave</td><td>".$row1[4]." "
            . "".$row1[5]."</td>".$pad.""
            . "<tr><td>Adresa prijave</td><td>".$row1[6]." "
            . "</td>".$pad.""
            . "<tr><td>Kategorija prijave</td><td>".$row1[8]."</td>".$pad.""
            . "<tr><td>Naslov prijave</td><td>".$row1[9]."</td>".$pad.""
            . "<tr><td>Tekst prijave</td><td>".$row1[10]."</td>".$pad."</tr>"
            . "<tr><td>Status prijave</td><td>".$row1[13]."</td>".$pad."</tr>";
    /*$tablica .= "<tr><td>Vrijeme prijave</td><td>".$row1[3]."</td>".$pad."</tr>"
            . "<tr><td>Koordinate prijave</td><td>".$row1[4]." "
            . "".$row1[5]."</td>".$pad.""
            . "<tr><td>Adresa prijave</td><td>".$row1[6]." "
            . "".$row1[7]."</td>".$pad.""
            . "<tr><td>Tekst prijave</td><td>".$row1[8]."</td>".$pad."</tr>";*/
    if($admin==1){
        $tablica .= "<tr><td>Odgovor administratora</td><td><textarea id = "
                . "'odg' rows='10' cols='50'>".$row1[12]."</textarea></td>"
                . "".$pad."</tr><tr><td></td><td><button type='button' class="
                . "'btn btn-primary btn-md' name='Odgovor' align='right' "
                . "onclick='odgovor(".$id_pr.")'>Odgovor</button></td>".$pad.""
                . "</tr></tbody></table>";
        echo $tablica;
    }
    else{
        $tablica .= "<tr><td>Odgovor administratora</td><td>".$row1[12]."</td>"
                . "".$pad."</tr></tbody></table>";
        echo $tablica;
    }
}
$spoj->close();
?>