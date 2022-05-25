<?php 
	include "connect.php"
	// SELECT don_hang.DH_id, don_hang.tong_tien, chi_tiet_don_hang.so_luong,san_pham.don_gia FROM don_hang INNER JOIN chi_tiet_don_hang ON don_hang.DH_id = chi_tiet_don_hang.DH_id INNER JOIN san_pham ON chi_tiet_don_hang.SP_id = san_pham.SP_id WHERE don_hang.DH_id = 4;
 ?>