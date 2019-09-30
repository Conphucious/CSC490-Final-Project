<?php
include('session.php');

if ($_SESSION['username'] == '') {
    header("location: index.php");
}

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


$db -> close();

?>

<html>
    <head>
        <title>CSC 490 :: Submit a Challenge</title>
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

                    </center>
                </div>


            </div>
        </div>
        </script>
    </body>
</html>
