<?php
include('session.php');

if ($_SESSION['username'] == '') {
    header("location: index.php");
}

$username = $_SESSION['username'];

$tableData = $db -> query("SELECT challenge_id, finished_challenge.complete_date, challenge.name, challenge.pts, challenge.initial_date, challenge.description FROM user
JOIN user_challenge ON user_id = user.id
JOIN finished_challenge ON user_challenge.finished_challenge_id = finished_challenge.id
JOIN challenge ON finished_challenge.challenge_id = challenge.id
JOIN proof ON finished_challenge.proof_id = proof.id WHERE username = '$username' ORDER BY finished_challenge.id ASC");

$page = '';

while ($data = $tableData -> fetch_array()) {
    $page = $page . '<tr>
<td>' . $data['challenge_id']  . '</td>
<td>' . $data['name']  . '</td>
<td>' . $data['description']  . '</td>
<td>' . $data['initial_date']  . '</td>
<td>' . $data['complete_date']  . '</td>
<td>' . $data['pts']  . '</td></tr>';
}

$db -> close();

?>

<html>
    <head>
        <title>CSC 490 Dashboard ::  My Challenges</title>
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
                        <h1 class="mt-4">My Challenges</h1><br>
                        <code>Here are your completed challenges.</code><br><br>
                        <table style="width: 80%; border: 1px solid black; text-align:center;" cellpadding="15">
                            <tr>
                                <th>ID</th>
                                <th>NAME</th>
                                <th>DESCRIPTION</th>
                                <th>POST DATE</th>
                                <th>COMPLETE DATE</th>
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
