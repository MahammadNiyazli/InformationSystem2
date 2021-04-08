<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.dao.inter.UsersDaoInter" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/4/2021
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!doctype html>
<html lang="en">
<head>


    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <title>Hello, world!</title>
</head>
<body>

<%
    UsersDaoInter usersDao = (UsersDaoInter) request.getAttribute("usersDao");
    Users user = usersDao.findByEmail(request.getUserPrincipal().getName());
%>


<div class="container-fluid">
    <div style="position:sticky;top: 0;z-index: 3">
        <div class="row" >
        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="width:100%;">
            <div class="container-fluid mx-5">
                <form action="base" method="get" style="margin-bottom: -4px">
                <button class="navbar-brand" style="border: none;font-family:Papyrus;font-weight:bold"> <img src="https://upload.wikimedia.org/wikipedia/commons/e/e7/Fist_.svg" width="20px"> Dəmir Yumruq</button>
                </form>
                <div class="navbar-nav" style="float:right;">
                    <div class="btn-group">
                        <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="far fa-user-circle"></i>
                            <%=user.getName()+" "+ user.getSurname()%>
                        </button>
                        <div class="dropdown-menu">
                            <form method="post" action="logout" style="margin-bottom: -3px">
                            <button class="dropdown-item"  >Log out</button>
                            </form>
                        </div>
                    </div>
                    <div class="btn-group ml-1">
                        <button type="button" class="btn btn-outline-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Saved
                        </button>
                        <div class="dropdown-menu">
                            <div class="nav nav-tabs">
                            <a class="dropdown-item" data-toggle='tab' href="#savedJournal" >Journals</a>
                            <a class="dropdown-item" data-toggle='tab' href="#savedDocument" >Documents</a>
                            <a class="dropdown-item" data-toggle='tab' href="#savedAudio" >Audios</a>
                            <a class="dropdown-item" data-toggle='tab' href="#savedVideo" >Videos</a>
                            <a class="dropdown-item" data-toggle='tab' href="#savedImage" >Images</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </nav>
    </div>
    <div class="row" style="height:80px;">
        <div class="col" style="height:100%;">
            <form class="d-flex bg-info" style="height:100%;" method="get" action="search">
                <input class="form-control me-2 bg-light border-0 m-3" style="width:90%; height:60%; font-size:120%;" type="search" name="word" placeholder="Search" aria-label="Search">
                <button class="btn btn-info" style="width:5%" type="submit"><i class="fas fa-search"></i></button>
            </form>
        </div>
    </div>
    </div>
    <div class="row">
        <div class="col-2">
            <div class="list-group m-3 nav nav-tabs" id="list-tab" role="tablist" style="position: fixed;width: 13%">
                <a class="list-group-item list-group-item-action" id="list-journals-list" data-bs-toggle="list" data-toggle='tab' href="#journal" role="tab" aria-controls="jounals">JOURNALS</a>
                <a class="list-group-item list-group-item-action" id="list-documents-list" data-bs-toggle="list" data-toggle='tab' href="#document" role="tab" aria-controls="documents">DOCUMENTS</a>
                <a class="list-group-item list-group-item-action" id="list-images-list" data-bs-toggle="list" data-toggle='tab' href="#image" role="tab" aria-controls="images">IMAGES</a>
                <a class="list-group-item list-group-item-action" id="list-videos-list" data-bs-toggle="list" data-toggle='tab' href="#video" role="tab" aria-controls="videos">VIDEOS</a>
                <a class="list-group-item list-group-item-action" id="list-audios-list" data-bs-toggle="list" data-toggle='tab' href="#audio" role="tab" aria-controls="audios">AUDIOS</a>
            </div>
        </div>

        <div class="allFiles col-10 my-3">

            <div class="tab-content">
                <div class="tab-pane in active" id="journal">
                    <jsp:include page="journal.jsp" />
                </div>

                <div class="tab-pane fade" id="document">
                    <jsp:include page="document.jsp" />
                </div>

                <div class="tab-pane fade" id="image">
                    <jsp:include page="image.jsp" />
                </div>

                <div class="tab-pane fade" id="video">
                    <jsp:include page="video.jsp" />
                </div>

                <div class="tab-pane fade" id="audio">
                    <jsp:include page="audio.jsp" />
                </div>
                <div class="tab-pane fade" id="savedJournal">
                    <jsp:include page="savedJournal.jsp" />
                </div>
                <div class="tab-pane fade" id="savedDocument">
                    <jsp:include page="savedDocument.jsp" />
                </div>
                <div class="tab-pane fade" id="savedAudio">
                    <jsp:include page="savedAudio.jsp" />
                </div>
                <div class="tab-pane fade" id="savedVideo">
                    <jsp:include page="savedVideo.jsp" />
                </div>
                <div class="tab-pane fade" id="savedImage">
                    <jsp:include page="savedImage.jsp" />
                </div>
            </div>
        </div>
    </div>
        <footer class="mb-0 bg-info">
            <p style="text-align:center;border: 2px solid black;color: white">Copyright © 2021 Developed by Mahammad Niyazli</p>
        </footer>

</div>


</body>
</html>
</body>
</html>
