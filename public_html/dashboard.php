<?php
include('session.php');

if ($_SESSION['username'] == '') {
    header("location: index.php");
}

$username = $_SESSION['username'];

$tableData = $db -> query("SELECT user.id, reg_date, finished_challenge_id, challenge_id, proof_id, complete_date, pts FROM user
JOIN user_challenge ON user_id = user.id
JOIN finished_challenge ON user_challenge.finished_challenge_id = finished_challenge.id
JOIN challenge ON finished_challenge.challenge_id = challenge.id
WHERE username = '$username'");

$page = '';


$ccx = 0;
$pts = 0;

while ($data = $tableData -> fetch_array()) {
    $id = $data['id'];
    $reg_date = $data['reg_date'];
    $ccx++;
    $pts += $data['pts'];
}

$page = '<code>User ID:</code> ' . $id .
        '<br><code>Username:</code> ' . $username .
        '<br><code>Registration Date:</code> ' . $reg_date .
        '<br><br><code>Total Pts:</code> ' . $pts .
        '<br><code>Challenges Completed:</code> ' . $ccx;

$db -> close();

?>

<html>
    <head>
        <title>CSC 490 Dashboard</title>
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
                        <h1 class="mt-4">Dashboard</h1><br>
                        <?php echo $page; ?>
                        <br><br><br>

                    </center>
                </div>
            </div>
        </div>
        </script>
    </body>
</html>
