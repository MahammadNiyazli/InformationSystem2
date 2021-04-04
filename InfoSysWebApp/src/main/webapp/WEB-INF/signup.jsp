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
    <title>SignUp</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

   <div class="container-fluid">
    <div class="row"  >
        <div class="col-1"></div>
        <div class="col-4" style="margin-top: 150px">
            <div class="box">
            <div style="width: 80%">
                <form method="POST" action="sigunp">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control input" name="name" placeholder="Enter name" id="name" required>
                    </div>
                    <div class="form-group">
                        <label for="surname">Surname:</label>
                        <input type="text" class="form-control input" name="surname" placeholder="Enter surname" id="surname" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control input" name="email" placeholder="name@mail.ru" id="email" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control input" name="password" placeholder="password" id="password" required>
                    </div>

                    <input type="submit" name="submit" class="btn btn-success input" value="Submit">
                </form>
            </div>
        </div>
        </div>

        <div class="col-7" style="margin-top: 150px">
            <img src="https://www.photovoltaic-conference.com/images/2020/conference/Teamwork_Illustration_VectorForFree_mk_registration_web.jpg" alt="img" width="850px" height="auto">
        </div>

    </div>
</div>

</body>
</html>
