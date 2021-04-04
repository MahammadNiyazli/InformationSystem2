<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/28/2020
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="assets/css/login.css" type="text/css">
</head>
<body>
<div class="container-fluid">
    <div class="row"  >
        <div class="col-6" style="margin-top: 100px">
            <img src="https://assets.materialup.com/uploads/6970de0b-c0d4-4971-9628-fb66bac86874/preview.jpg" alt="img" width="850px" height="700px">
        </div>

        <div class="col-6" style="margin-top: 180px">
            <div style="width: 60%" >
            <form method="POST" action="login">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control inputStyle" name="username" placeholder="name@mail.ru" id="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control inputStyle" name="password" placeholder="password" id="password" required>
                </div>

                <input style="display: block" type="submit" name="submit" class="btn btn-info " value="Login">
            </form>

                <form method="GET" action="sigunp"><input type="submit" name="submit" class="btn btn-success" value="Sign Up"></form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
