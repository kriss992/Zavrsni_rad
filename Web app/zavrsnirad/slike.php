<?php
require "spoj.php"; 
$spoj->set_charset('utf8');
 if(isset($_POST["slika_str"])){
    $slika_str = $_POST["slika_str"];
    $ime = $_POST["ime"].getBrojSlike().".jpg";
    $id_prijenos = $_POST["id_prijenos"];
    $dekodirani_string = base64_decode($slika_str);
    $add = 'slike/'.$ime;
    $server_ip = gethostbyname(gethostname());
    $file = fopen($add, 'wb');
    $gotovo = fwrite($file, $dekodirani_string);
    fclose($file);
    if($gotovo > 0) {
        $mysql_upit1 = "INSERT INTO `slike` (`id_slika`, `id_prijenos`, "
                . "`slika_add`) VALUES (NULL, '$id_prijenos', '$ime');";
	$rez = mysqli_query($spoj, $mysql_upit1) ;
	if($rez){
            echo "uspjesno_slika";
	}else{
            echo "neuspjesno_slika";
	}
	mysqli_close($spoj);
    }
 }
 function getBrojSlike(){
		$spoj = mysqli_connect('localhost', 'root', '','zavrsni_rad');
		$mysql_upit2 = "SELECT max(id_slika) as id_slika FROM slike";
		$rez = mysqli_fetch_array(mysqli_query($spoj,$mysql_upit2));
		mysqli_close($spoj);
                if($rez['id_slika']==null){return 1;}
                else{return ++$rez['id_slika'];}  
	}
?>