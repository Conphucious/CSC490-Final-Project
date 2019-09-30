<?php

include('session.php');

?>

<div class="bg-light border-right" id="sidebar-wrapper">
    <div class="sidebar-heading"><center><font color="#013220"><b>OSWEGO<br><font size="1px">STATE UNIVERSITY OF NEW YORK</font></b></font></center></div>

    <div class="list-group list-group-flush">

        <a href="dashboard.php" class="list-group-item list-group-item-action bg-light">Dashboard</a>
        <?php echo $menu; ?>
        <a href="view-challenges.php" class="list-group-item list-group-item-action bg-light">View Challenges</a>
        <a href="submit-challenge.php" class="list-group-item list-group-item-action bg-light">Submit Challenge</a>
        <a href="my-challenges.php" class="list-group-item list-group-item-action bg-light">Completed Challenges</a>

        <a href="logout.php" class="list-group-item list-group-item-action bg-light">Sign out</a>

    </div>
</div>
