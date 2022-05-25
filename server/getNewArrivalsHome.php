<?php 
	include "connect.php";
	$mangnewarrivals = array();
	$query = "SELECT * FROM san_pham WHERE SP_code like '%-S' ORDER BY SP_id DESC LIMIT 8";
	$data = mysqli_query($conn,$query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangnewarrivals, new NewArrivals(
			$row['SP_id'],
			$row['SP_code'],
			$row['tenSP'],
			$row['hinh_anh'],
			$row['so_luong_ton'],
			$row['don_gia'],
			$row['mo_ta'],
			$row['loaiSP_id']));
	}
	echo json_encode($mangnewarrivals);

	class NewArrivals{
		function __construct($SP_id, $SP_code, $tenSP, $hinh_anh, $so_luong_ton, $don_gia, $mo_ta, $loaiSP_id)
		{
			$this->SP_id = $SP_id;
			$this->SP_code = $SP_code;
			$this->tenSP = $tenSP;
			$this->hinh_anh = $hinh_anh;
			$this->so_luong_ton = $so_luong_ton;
			$this->don_gia = $don_gia;
			$this->mo_ta = $mo_ta;
			$this->loaiSP_id = $loaiSP_id;
		}
	}
 ?>