<?php 
 include './config/koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
   $name = $_POST['name'];
   $jk = $_POST['jk'];
   $image = $_POST['image'];
   $email = $_POST['email'];
   $username = $_POST['username'];
   $password = $_POST['password'];
   //query ke database
    $query = "INSERT INTO tb_user (name,jk, image,email,username,password) VALUES
     ('$name','$jk','$image','$email','$username','$password')";

 	$exeQuery = mysqli_query($con, $query); 

	 echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil menambahkan data')) 
	 :  json_encode(array('kode' =>2, 'pesan' => 'data gagal ditambahkan'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>
