<?php

class DB
{
	public static function connect() 
	{
		$host= "localhost";
		$user="root";
		$pwd="";
		$database="bookshop";
		//--
		$pdo = new PDO('mysql:host='.$host.';dbname='.$database, $user, $pwd);
		return $pdo;
	}
}
