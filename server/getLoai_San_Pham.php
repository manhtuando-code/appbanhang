<?php
	include "connect.php";

	$query = "SELECT * FROM loai_san_pham";
	$resouter = mysqli_query($conn,$query);

	$temparray = array();
	$total_records = mysqli_num_rows($resouter);

	if ($total_records >= 1) {
		while ($row = mysqli_fetch_assoc($resouter)) {
	        $temparray[] = $row;
	    } 
	}   

	echo json_encode($temparray);
 ?>