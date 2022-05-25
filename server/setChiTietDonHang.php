<?php 
	include "connect.php";
	$json = $_POST['json'];
	$data = json_decode($json,true);
	foreach ($data as $value) {
		$DH_id = $value['DH_id'];
		$SP_code = $value['SP_code'];
		$so_luong = $value['so_luong'];
		$sql = "SELECT SP_id FROM san_pham WHERE SP_code = '$SP_code'";
		$timSP_id = mysqli_query($conn, $sql);
		if (mysqli_num_rows($timSP_id) > 0) 
		{
		    // Sử dụng vòng lặp while để lặp kết quả
		    while($row = mysqli_fetch_assoc($timSP_id)) {
		        $SP_id = $row["SP_id"];
		    }
		} 
		else {
		    echo "Không có record nào";
		}
		$query = "INSERT INTO chi_tiet_don_hang VALUES ('$DH_id','$SP_id', '$so_luong')";
		$Dta = mysqli_query($conn,$query);
	}
	if ($Dta) {
		echo "1";
	}else{
		echo "0";
	}


 ?>