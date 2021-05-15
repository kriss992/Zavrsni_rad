<!DOCTYPE html>
<?php
session_start();
require "spoj.php";
$spoj->set_charset('utf8');
if(isset($_SESSION["prijavljen"]) && $_SESSION["prijavljen"] === true){
    header("Location: lista.php");
}
if(isset ($_POST['korime']) && isset($_POST['pass'])){
    $korisnicko_ime = $_POST["korime"];
    $lozinka = $_POST["pass"];
    $mysql_upit = "select id, admin from korisnik where korisnicko_ime "
            . "= '$korisnicko_ime' and lozinka = '$lozinka';";
    $rezultat = mysqli_query($spoj, $mysql_upit);
    $row = mysqli_fetch_row($rezultat);
    if(mysqli_num_rows($rezultat) > 0) {
        $_SESSION["id"] = $row[0];
        $_SESSION["prijavljen"] = true;
        $_SESSION["admin"] = $row[1];
        header("Location: lista.php");
    }
}
?>
<html lang="hr-HR">
    <head>
    <meta charset="UTF-8"/>
    <title>Prijava u sustav</title>
    <link rel="stylesheet" 
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style type="text/css">
        body{ font: 14px sans-serif; }
        .wrapper{ width: 350px; padding: 20px; }
    </style>
</head>
<header style="color:blue;">
    <h1 style="font-size:50px;" align="center">Stranica za pregled prijava</h1>
</header>
<div align="center">
    <body>
        <div class="wrapper">
            <h2>Prijava u sustav</h2>
            <form method="post">
                <div class="form-group">
                    <label>Korisničko ime</label>
                     <input type="text" name="korime" class="form-control">
                </div>    
                <div class="form-group">
                    <label>Lozinka</label>
                    <input type="password" name="pass" class="form-control">
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" 
                           value="Prijava">
                </div>
            </form>
        </div>    
    </body>
</div>
</html>