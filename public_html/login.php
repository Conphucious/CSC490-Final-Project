<?php

include('session.php');
$error = 'Please login to the platform to use our services.';

if ($_SESSION['username'] != '') {
    header("location: dashboard.php");
}

// if (isset($_POST['studentid']))
//     echo '<body onLoad="document.login.studentpin.focus();">';
// else
//     echo '<body onLoad="document.login.studentid.focus();">';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = mysqli_real_escape_string($db, $_POST['username']);
    $password = mysqli_real_escape_string($db, $_POST['password']);
    $row = ($db -> query("SELECT username, password FROM user WHERE username = '$username'")) -> fetch_array();



    if (password_verify($password, $row['password'])) {
        $_SESSION['username'] = $row['username'];
        header("location: dashboard.php");
        #exit();
    } else
    $error = '<div style="color:#cc0000; margin-top:10px">Username or password is invalid</div>';

    $db -> close();
}

?>

<html>
	  <head>
		    <title>Student Evaluation Login</title>
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
                                    <td colspan="3" align="center"><input name="Submit" type="submit" value="Login" /></td>
										            </tr>
									          </table>
								        </form>

							      </fieldset>

							      <p align="center"><b><font color="red">NOTICE:</font></b> If you are having trouble logging in, please <a href="help.html" target="_blank">click here</a> for further assistance.</p>
							      <p align="center">&copy;<script type="text/javascript">var date = new Date();document.write(date.getFullYear());</script> Phuc Nguyen - CSC 490 Final Project</p>
					      </td>
					      <td>&nbsp;</td>
				    </tr>
			  </table>
		</body>
</html>
