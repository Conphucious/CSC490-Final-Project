<?php
include('session.php');

$db -> close();

?>

<html>
    <head>
<title>CSC 490 Dashboard :: Posted Challenge</title>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/simple-sidebar.css" rel="stylesheet">
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <div id="page-content-wrapper">
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom"></nav>

                <div class="container-fluid" id="dashboard">
                    <center>
                        <h1 class="mt-4">Submit a Challenge</h1><br>
                        <code>Your submission has been posted.<br>Challenge is pending review from an administrator.<br>(that part isn't true unless pending is default 1)</code><br><br>

                        <a href="index.php">Go back</a> 
                        <br><br><br>
                    </center>
                </div>


            </div>
        </div>
        </script>
    </body>
</html>
