<?php 
 include './config/koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
  
   $email = $_POST['email'];
   $password = $_POST['password'];
   //query ke database
   
    $data = mysqli_query($con,"select * from tb_user where email='$email' and password='$password'");

    $cek = mysqli_num_rows($data);
     
    // $exeQuery = mysqli_query($con, $query); 
    $response=array();
  
    if($cek >0){
    
         $response["pesan"]="berhasil Login";
         $response["kode"]=1;   
        echo json_encode($response);
    }else{
        $response["pesan"]="Gagal Login";
        $response["kode"]=2;
        echo json_encode($response);
    } 

 }
 ?>
