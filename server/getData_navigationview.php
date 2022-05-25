<?php 
	class dataNavigationview{
		function __construct($luachon)
		{
			$this->LuaChon = $luachon;
		}
	}

	$temparray = array();
	array_push($temparray, new dataNavigationview("NEW ARRIVALS"));
	array_push($temparray, new dataNavigationview("TOPS"));
	array_push($temparray, new dataNavigationview("OUTERWEAR"));
	array_push($temparray, new dataNavigationview("BOTTOMS"));
	array_push($temparray, new dataNavigationview("SIZE CHART"));
	array_push($temparray, new dataNavigationview("ABOUT APP"));

	echo json_encode($temparray);

 ?>