<?php 
	include "connect.php";
	$ho_tenKH = $_POST['ho_tenKH'];
	$email = $_POST['email'];
	$sdt = $_POST['sdt'];
	$dia_chi = $_POST['dia_chi'];
	if (strlen($ho_tenKH) > 0 && strlen($email) > 0 && strlen($sdt) > 0 && strlen($dia_chi) > 0) {
		$query = "INSERT INTO khach_hang(KH_id,ho_tenKH,email,sdt,dia_chi) VALUES (null,'$ho_tenKH','$email','$sdt','$dia_chi')";
		if (mysqli_query($conn,$query)) {
			$idKH = $conn->insert_id;
			$sql = "INSERT INTO don_hang(DH_id,tam_tinh,tong_tien,trang_thai,KH_id) VALUES (null,null,null,0,$idKH)";
			if(mysqli_query($conn,$sql)){
				$iddh = $conn->insert_id;
				echo $iddh;
			}else{
				echo "thất bại đơn hàng";
			}
		}else{
			echo "Thất bại khach hang";
		}
	} else {
		echo "Bạn hãy kiểm tra lại các dữ liệu";
	}
	

 ?>