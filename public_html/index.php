<?php
include('session.php');

$error = 'Enter some details about the challenge.';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = $_POST['name'];
    $description = $_POST['description'];
    $pts = $_POST['pts'];

    if ($name == '') {
        $error = 'Challenge name is empty!';
    } else if ($description == '') {
        $error = 'Challenge description is empty!';
    } else {
        $stmt = $db -> prepare("INSERT INTO challenge (name, description, pts, initial_date) VALUE
        ('$name', '$description', '$pts', NOW())");

        if($stmt -> execute()) {
            header("location: posted.php");
        }

    }

}

$tableData = $db -> query("SELECT challenge_id, finished_challenge.complete_date, challenge.name, challenge.pts, challenge.initial_date, challenge.description FROM user
JOIN user_challenge ON user_id = user.id
JOIN finished_challenge ON user_challenge.finished_challenge_id = finished_challenge.id
JOIN challenge ON finished_challenge.challenge_id = challenge.id
JOIN proof ON finished_challenge.proof_id = proof.id WHERE username = 'Jimmy' ORDER BY finished_challenge.id ASC");

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


$tableData2 = $db -> query("SELECT * FROM challenge");

$pager = '';

while ($data2 = $tableData2 -> fetch_array()) {
    $pager = $pager . '<tr>
<td>' . $data2['id']  . '</td>
<td>' . $data2['name']  . '</td>
<td>' . $data2['description']  . '</td>
<td>' . $data2['initial_date']  . '</td>
<td>' . $data2['pts']  . '</td></tr>';
}


//$db -> close();

?>

<html>
    <head>
        <title>CSC 490 :: Submit a Challenge</title>
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
                        <code><?php echo $error; ?></code><br><br>
                        <table style="width: 80%; border: 1px solid black; text-align:center;" cellpadding="15">
                            <tr>
                                <th>CHALLENGE NAME</th>
                                <th>DESCRIPTION</th>
                                <th>PTS</th>
                                <th></th>
                            </tr>

                            <tr>
                                <form method="post">
                                <td><input type="text" name="name"></td>
                                <td><input type="text" name="description"></td>
                                <td>
                                    <select name="pts">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                    </select>
                                </td>
                                <td><input type="submit" value ="Post Challenge"></td>
                                </form>
                            </tr>
                        </table>
                        <br><br><br>


                        <h1 class="mt-4">Completed  Challenges</h1><br>
                        <code>These are challenges already done.</code><br><br>
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


                        <h1 class="mt-4">All Challenges</h1><br>
                        <code>Here are challenges available to be completed.</code><br><br>
                        <table style="width: 80%; border: 1px solid black; text-align:center;" cellpadding="15">
                            <tr>
                                <th>ID</th>
                                <th>NAME</th>
                                <th>DESCRIPTION</th>
                                <th>DATE POSTED</th>
                                <th>PTS</th>
                            </tr>
                            <?php echo $pager; ?>
                        </table>
                        <br><br><br>

                    </center>
                </div>


            </div>
        </div>
        </script>
    </body>
</html>
