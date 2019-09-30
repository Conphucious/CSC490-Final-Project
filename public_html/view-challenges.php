<?php
include('session.php');

if ($_SESSION['username'] == '') {
    header("location: index.php");
}

$username = $_SESSION['username'];

$tableData = $db -> query("SELECT * FROM challenge
WHERE NOT EXISTS (
SELECT	* FROM	finished_challenge
JOIN	user_challenge ON user_challenge.finished_challenge_id = finished_challenge.id
JOIN 	user ON user.id = user_challenge.user_id
WHERE	challenge.id = finished_challenge.challenge_id AND user.username = '$username' AND is_pending = 0 ORDER BY challenge.id ASC)");

$page = '';

while ($data = $tableData -> fetch_array()) {
    $page = $page . '<tr>
<td>' . $data['id']  . '</td>
<td>' . $data['name']  . '</td>
<td>' . $data['description']  . '</td>
<td>' . $data['initial_date']  . '</td>
<td>' . $data['pts']  . '</td></tr>';
}

$db -> close();

?>

<html>
    <head>
        <title>CSC 490 Dashboard :: View available challenges</title>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/simple-sidebar.css" rel="stylesheet">
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <?php include('nav.php'); ?>
            <div id="page-content-wrapper">
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom"></nav>

                <div class="container-fluid" id="dashboard">
                    <center>
                        <h1 class="mt-4">View Available Challenges</h1><br>
                        <code>Here are challenges available to be completed.<br>Navigate challenges on your device to complete them.</code><br><br>
                        <table style="width: 80%; border: 1px solid black; text-align:center;" cellpadding="15">
                            <tr>
                                <th>ID</th>
                                <th>NAME</th>
                                <th>DESCRIPTION</th>
                                <th>DATE POSTED</th>
                                <th>PTS</th>
                            </tr>
                            <?php echo $page; ?>
                        </table>
                        <br><br><br>

                    </center>
                </div>


            </div>
        </div>
        </script>
    </body>
</html>
