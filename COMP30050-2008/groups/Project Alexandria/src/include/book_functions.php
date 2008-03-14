﻿<?php
/****************************************************************
* 																*
* Global variables necessary for the retrieval of books.		*
* 																*
****************************************************************/

//$isbn appears on the pages that call the following functions and so should not be declared here.
$authors =		"";
$binding =		"";
$ddc =			"";
$description =	"";
$largeImg =		"";
$lcc =			"";
$mediumImg =	"";
$noOfPages =	"";
$publisher =	"";
$smallImg =		"";
$title =		"";
$titleLong =	"";

/****************************************************************
* 																*
* fetchBookFromDB(isbn) - 										*
* Produces all data for that ISBN from the “books” database. It	*
* does this through while loop with a sql query of SELECT and	*
* WHERE, the where being where the ISBN matches.				*
* Data being transmitted:										*
* An ISBN is sent and the full details of the book are returned	*
* (these include the ISBN, title, long title, author,			*
* description, DDC No., etc.).									*
* 																*
****************************************************************/

function fetchBookFromDB($isbn){
	global $isbn,$title,$titleLong,$authors,$publisher,$noOfPages,$binding,$ddc,$lcc,$description,$largeImg,$mediumImg,$smallImg;
	//Necessary for editing the variables above (see the PHP manual for more details)
	
	include("connection.php"); //Connects to the database
	
	$result = mysql_query("SELECT * FROM books
		WHERE isbn='$isbn'");

	while($row = mysql_fetch_array($result)){
		$isbn=$row['isbn'];
		$title=$row['title'];
		$titleLong=$row['titleLong'];
		$authors=$row['authors'];
		$publisher=$row['publisher'];
		$noOfPages=$row['noOfPages'];
		$binding=$row['binding'];
		$ddc=$row['ddc'];
		$lcc=$row['lcc'];
		$description=$row['description'];
		$largeImg=$row['largeImg'];
		$mediumImg=$row['mediumImg'];
		$smallImg=$row['smallImg'];
	}
}

/****************************************************************
* 																*
* availability(isbn) - 											*
* Requests the number of copies of a book (using the sql query	*
* WHERE to find the row with the matching ISBN) from “books”. It*
* then does the same with the “books_online” but this time uses	*
* COUNT to get the number of instances of the ISBN and then		*
* compares the numbers, where the numbers are equal it prints	*
* “On Loan” else “Available”.									*
* Data being transmitted:										*
* Sends the same ISBN to the “books” and “books_onloan”			*
* databases and retrieves the number of copies from the former	*
* and from the latter the number of times the ISBN appears.		*
* 																*
****************************************************************/
/*
availability($isbn){
	//TODO - Ryan - See comment
}*/

/****************************************************************
* 																*
* requestBook(isbn, username) - 								*
* Adds the pair “isbn” and “username” to the “books_requested”	*
* database by use of the sql query INSERT.						*
* Data being transmitted:										*
* Sends the ISBN and username to the database “books_requested”.*
* 																*
****************************************************************/
/*
requestBook($isbn, $username){
	//TODO - Ryan - See comment
}*/

/****************************************************************
* 																*
* noOfRequests(isbn)											*
* Uses the sql query COUNT to return the number of rows where	*
* “isbn” occurs as the ISBN.									*
* Data being transmitted:										*
* The ISBN is being sent and the number of instances is being	*
* returned.														*
* 																*
****************************************************************/
/*
noOfRequests($isbn){
	//TODO - Ryan - See comment
}*/

/****************************************************************
* 																*
* getReviewed(isbn, username)									*
* Can be used to return all data from the “books_reviewed”		*
* database where the isbn and username match by use of a while	*
* loop and the sql query WHERE.									*
* Data being transmitted:										*
* Sends the ISBN and username and retrieves the full data where	*
* those match from the rows of “books_reviewed”.				*
* 																*
****************************************************************/
/*
getReviewed($isbn, $username){
	//TODO - Ryan - See comment
}*/

?>