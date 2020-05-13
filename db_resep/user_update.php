<?php 
 require_once './config/koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	
 //parameter post
 $id = $_POST['id'];
 $name = $_POST['name'];
 $jk = $_POST['jk'];
 $image = $_POST['image'];
 $email = $_POST['email'];
 $username = $_POST['username'];


//update
 	/*echo "UPDATE  tb_resep 
 	SET nama_resep = '$nama_resep',
 	detail = '$detail', 
 	gambar = '$gambar'
 	 WHERE id_resep='$id_resep'";*/
 	// UPDATE `tb_user` SET `name`='Basri Umar',`jk`='perempuan',`image`='',`email`='',`username`='' WHERE id =1
     
     $query = "UPDATE tb_user 
 	SET name = '$name',
 	jk = '$jk', 
 	image = '$image',
 	email = '$email',
 	username = '$username'
 	 WHERE id='$id'";

 	$exeQuery = mysqli_query($con, $query); 

     echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'data berhasil update')) 
     :  json_encode(array('kode' =>2, 'pesan' => 'data gagal diupdate'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>
