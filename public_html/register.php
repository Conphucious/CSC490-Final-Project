<?php

include("session.php");

if ($_SESSION['username'] != '') {
    header("location: dashboard.php");
}

if($_SERVER["REQUEST_METHOD"] == "POST") {
    // username and password sent from form
    $username = mysqli_real_escape_string($db, $_POST['username']);
    $password = mysqli_real_escape_string($db, $_POST['password']);
    $confirmPassword = mysqli_real_escape_string($db, $_POST['cpassword']);

    // Form validation
    if (!preg_match("/^[a-zA-Z0-9]*$/", $username))
        $error = "Only letters allowed!";
    elseif (strlen($username) > 45)
        $error = "Username must be less than 45 characters!";
    elseif (strlen($password) < 3)
        $error = "Password must be longer than 3 characters and less than 25 characters!";
    elseif ($username == '')
        $error = "Username field is empty!";
    elseif ($password == '')
        $error = "Password field is empty!";
    elseif ($password != $confirmPassword)
        $error = "Passwords do not match!";
    elseif (mysqli_num_rows(mysqli_query($db, "SELECT id FROM user WHERE username = '$username'")) == 1)
        $error = "Username already in use!";
    else {
        $pass = password_hash($password, PASSWORD_BCRYPT);
        $stmt = $db -> prepare("INSERT INTO user (username, password, reg_date) VALUE ('$username', '$pass', NOW())");

        if($stmt -> execute()) {
            header("location: dashboard.php");
            $error = 'Account successfully registered!';
        } else
        $error = "Something went wrong. Please try again later.";

        $stmt -> close();
        $db -> close();
    }
}
?>



<html>
	  <head>
		    <title>CSC480 Final Project</title>
		</head>
			  <table width="100%" height="100%" cellpadding="4" cellspacing="0" border="0" align="center" style="font-family:Arial, Helvetica, sans-serif; font-size:12px;">
				    <tr>
					      <td>&nbsp;</td>
					      <td width="500" align="center" valign="middle">
						        <fieldset style="font-weight:bold; background-color: white"><br>
								        <p align="center"><img src="images/logo.png" /></p>
								        <p align="center">
                            <?php echo $error; ?>
                        </p>

								        <form method="post">
									          <table width="80%" cellpadding="4" cellspacing="0" border="0" align="center" style="font-family:Arial, Helvetica, sans-serif; font-size:12px;">
										            <tr>
											              <td width="35%" align="right">Username:</td>
											              <td width="40%"><input name="username" type="text" value="<?php if (isset($_POST['studentid'])) echo $_POST['studentid']; ?>" /></td>
											              <td width="25%"></td>
										            </tr>
										            <tr>
											              <td align="right">Password:</td>
											              <td><input name="password" type="password" /></td>
											              <td>&nbsp;</td>
										            </tr>
                                <tr>
											              <td align="right">Confirm Password:</td>
											              <td><input name="cpassword" type="password" /></td>
											              <td>&nbsp;</td>
										            </tr>
										            <tr>
                                    <td colspan="3" align="center"><input name="Submit" type="submit" value="Register Account" /></td>
										            </tr>
									          </table>
								        </form>

							      </fieldset>

							      <p align="center"><b><font color="red">NOTICE:</font></b> If you are having trouble logging in, please <a href="help.html" target="_blank">click here</a> for further assistance.</p>
							      <p align="center">&copy;<script type="text/javascript">var date = new Date();document.write(date.getFullYear());</script> Phuc Nguyen - CSC490 Final Project</p>
					      </td>
					      <td>&nbsp;</td>
				    </tr>
			  </table>
		</body>
</html>



