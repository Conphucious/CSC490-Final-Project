<?php
session_start();

if(session_destroy()) {
    unset($_SESSION);
    header( "location:index.php" );
}
?>
