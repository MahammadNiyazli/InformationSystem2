<%@ page import="java.security.Principal" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.company.dao.inter.JournalDaoInter" %>
<%@ page import="com.company.dao.inter.UsersDaoInter" %>
<%@ page import="com.company.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/3/2021
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>

    <style>
        .card-img-top {
            width: 100%;
            height: 15vw;
            object-fit: cover;
        }
    </style>

    <script>

        $(".case").live('click', function(){
            $.fancybox({
                openEffect: 'elastic',
                closeEffect: 'elastic'
            });
        });

    </script>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>

</head>
<body>
<%

    UsersDaoInter usersDao = (UsersDaoInter) request.getAttribute("usersDao");
    System.out.println("searchdeki userdao = "+usersDao);
    System.out.println("email = "+request.getUserPrincipal().getName());
    Users user = usersDao.findByEmail(request.getUserPrincipal().getName());

    List<Audio> audioList = (List<Audio>) request.getAttribute("audioList");
    List<Video> videoList = (List<Video>) request.getAttribute("videoList");
    List<Image> imageList = (List<Image>) request.getAttribute("imageList");
    List<Document> documentList = (List<Document>) request.getAttribute("documentList");
    List<Journal> journalList = (List<Journal>) request.getAttribute("journalList");


%>

<div class="container-fluid">

<div style="position:sticky;top: 0;z-index: 3;margin-bottom: 10px">
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



    <div class="row row-cols-1 row-cols-md-3 g-4">

        <%for (Video v :videoList){ %>
        <div class="col" style="margin-bottom:35px">
            <div class="card h-100" style="position:relative;">
                <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                    <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                    </button>

                    <%
                        String pathVideo = v.getStorageId().getPath();
                        String videoName = v.getName();

                        String videoNameArr[] = videoName.split(" ");
                        for(int i1=0;i1<videoNameArr.length;i1++){
                            pathVideo+=videoNameArr[i1];
                            if(i1< videoNameArr.length-1){
                                pathVideo+="%20";
                            }
                        }
                        pathVideo+="#t=1";
                        System.out.println("path =" + pathVideo);
                        String cardTitle = v.getName().split("\\.")[0];
                    %>

                    <div class="dropdown-menu dropdown-menu-right">
                        <form style="margin-bottom: -5px">
                            <div class="case">
                                <a class="dropdown-item" href=<%=pathVideo%> rel="case" target="_blank">Look</a>
                            </div>
                        </form>
                        <form method="post" action="video" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="download">Download</button>
                            <input type="hidden" name="videoId" value=<%=v.getId()%> >
                        </form>
                        <form method="post" action="video" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="save">Save</button>
                            <input type="hidden" name="videoId" value=<%=v.getId()%> >
                        </form>
                        <%if(user.getRole().equals("ADMIN")){%>
                        <form method="post" action="video" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="delete">Delete</button>
                            <input type="hidden" name="videoId" value=<%=v.getId()%> >
                        </form>
                        <%}%>
                    </div>
                </div>


                <video class="img-fluid" controls>
                    <source src=<%=pathVideo%> type="video/mp4">
                </video>
                <div class="card-body">
                    <h5 class="card-title"><%=cardTitle%></h5>
                    <p class="card-text"><%=v.getDescription()%></p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">Tarix : <%=v.getUploadDate()%></small>
                </div>
            </div>
        </div>
        <%}%>

        <%for (Audio a :audioList){ %>
        <div class="col" style="margin-bottom:35px">
            <div class="card h-100" style="position:relative;">
                <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                    <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                    </button>

                    <%
                        String pathAudio = a.getStorageId().getPath();
                        String AudioName = a.getName();

                        String path = a.getImageId().getStorageId().getPath();
                        String name = a.getImageId().getName();
                        String nameArr[] = name.split(" ");
                        String audioNameArr[] = AudioName.split(" ");
                        for(int i=0;i<nameArr.length;i++){
                            pathAudio+=audioNameArr[i];
                            path+=nameArr[i];
                            if(i< nameArr.length-1){
                                path+="%20";
                                pathAudio+="%20";
                            }
                        }
                        System.out.println("path =" + path);
                        String cardTitle = a.getName().split("\\.")[0];
                    %>

                    <div class="dropdown-menu dropdown-menu-right">
                        <form style="margin-bottom: -5px">
                            <div class="case">
                                <a class="dropdown-item" href=<%=pathAudio%> rel="case" target="_blank">Look</a>
                            </div>
                        </form>
                        <form method="post" action="audio" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="download">Download</button>
                            <input type="hidden" name="audioId" value=<%=a.getId()%> >
                        </form>
                        <form method="post" action="audio" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="save">Save</button>
                            <input type="hidden" name="audioId" value=<%=a.getId()%> >
                        </form>
                        <%if(user.getRole().equals("ADMIN")){%>
                        <form method="post" action="audio" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="delete">Delete</button>
                            <input type="hidden" name="audioId" value=<%=a.getId()%> >
                        </form>
                        <%}%>
                    </div>
                </div>


                <img class="card-img-top" style="width:100%" src=<%=path%> alt="..." >
                <div class="card-body">
                    <h5 class="card-title"><%=cardTitle%></h5>
                    <audio controls>
                        <source src=<%=pathAudio%> type="audio/mpeg">
                    </audio>
                </div>
                <div class="card-footer">
                    <small class="text-muted">Tarix : <%=a.getUploadDate()%></small>
                </div>
            </div>
        </div>
        <%}%>

        <%for (Document d :documentList){ %>
        <div class="col" style="margin-bottom:35px">
            <div class="card h-100" style="position:relative;">
                <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                    <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                    </button>

                    <%
                        String pathDocument = d.getStorageId().getPath();
                        String documentName = d.getName();

                        String path = d.getImageId().getStorageId().getPath();
                        String name = d.getImageId().getName();
                        String nameArr[] = name.split(" ");
                        String documentNameArr[] = documentName.split(" ");
                        for(int i=0;i<nameArr.length;i++){
                            pathDocument+=documentNameArr[i];
                            path+=nameArr[i];
                            if(i< nameArr.length-1){
                                path+="%20";
                                pathDocument+="%20";
                            }
                        }
                        System.out.println("path =" + path);
                        System.out.println("pathDocument="+pathDocument);
                        String cardTitle = d.getName().split("\\.")[0];
                    %>

                    <div class="dropdown-menu dropdown-menu-right">
                        <form style="margin-bottom: -5px">
                            <a class="dropdown-item" href=<%=pathDocument%>>Look</a>
                        </form>
                        <form method="post" action="document" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="download">Download</button>
                            <input type="hidden" name="documentId" value=<%=d.getId()%> >
                        </form>
                        <form method="post" action="document" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="save">Save</button>
                            <input type="hidden" name="documentId" value=<%=d.getId()%> >
                        </form>
                        <%if(user.getRole().equals("ADMIN")){%>
                        <form method="post" action="document" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="delete">Delete</button>
                            <input type="hidden" name="documentId" value=<%=d.getId()%> >
                        </form>
                        <%}%>
                    </div>
                </div>


                <img class="card-img-top" style="width:100%" src=<%=path%> alt="..." >
                <div class="card-body">
                    <h5 class="card-title"><%=cardTitle%></h5>
                    <p class="card-text"><%=d.getDescription()%> (.docx)</p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">Tarix : <%=d.getUploadDate()%></small>
                </div>
            </div>
        </div>
        <%}%>

        <%for (Image i :imageList){ %>
        <div class="col" style="margin-bottom:35px">
            <div class="card h-100" style="position:relative;">
                <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                    <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                    </button>

                    <%
                        String pathImage = i.getStorageId().getPath();
                        String imageName = i.getName();

                        String imageNameArr[] = imageName.split(" ");
                        for(int i1=0;i1<imageNameArr.length;i1++){
                            pathImage+=imageNameArr[i1];
                            if(i1< imageNameArr.length-1){
                                pathImage+="%20";
                            }
                        }
                        System.out.println("path =" + pathImage);
                        String cardTitle = i.getName().split("\\.")[0];
                    %>

                    <div class="dropdown-menu dropdown-menu-right">
                        <form style="margin-bottom: -5px">
                            <div class="case">
                                <a class="dropdown-item" href=<%=pathImage%> rel="case" target="_blank">Look</a>
                            </div>
                        </form>
                        <form method="post" action="image" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="download">Download</button>
                            <input type="hidden" name="imageId" value=<%=i.getId()%> >
                        </form>
                        <form method="post" action="image" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="save">Save</button>
                            <input type="hidden" name="imageId" value=<%=i.getId()%> >
                        </form>
                        <%if(user.getRole().equals("ADMIN")){%>
                        <form method="post" action="image" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="delete">Delete</button>
                            <input type="hidden" name="imageId" value=<%=i.getId()%> >
                        </form>
                        <%}%>
                    </div>
                </div>


                <img class="card-img-top" style="width:100%" src=<%=pathImage%> alt="..." >
                <div class="card-body">
                    <h5 class="card-title"><%=cardTitle%></h5>
                    <p class="card-text"><%=i.getDescription()%> (.png)</p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">Tarix : <%=i.getUploadDate()%></small>
                </div>
            </div>
        </div>
        <%}%>

        <%for (Journal j :journalList){ %>
        <div class="col" style="margin-bottom:35px">
            <div class="card h-100" style="position:relative;">
                <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                    <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                    </button>

                    <%
                        String pathJournal = j.getStorageId().getPath();
                        String journalName = j.getName();

                        String path = j.getImageId().getStorageId().getPath();
                        String name = j.getImageId().getName();
                        String nameArr[] = name.split(" ");
                        String journalNameArr[] = journalName.split(" ");
                        for(int i=0;i<nameArr.length;i++){
                            pathJournal+=journalNameArr[i];
                            path+=nameArr[i];
                            if(i< nameArr.length-1){
                                path+="%20";
                                pathJournal+="%20";
                            }
                        }
                        System.out.println("path =" + path);
                        String cardTitle = j.getName().split("\\.")[0];
                    %>

                    <div class="dropdown-menu dropdown-menu-right">
                        <form style="margin-bottom: -5px">
                            <div class="case">
                                <a class="dropdown-item" href=<%=pathJournal%> rel="case" target="_blank">Look</a>
                            </div>
                        </form>
                        <form method="post" action="journal" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="Download">Download</button>
                            <input type="hidden" name="journalId" value=<%=j.getId()%> >
                        </form>
                        <form method="post" action="journal" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="Save">Save</button>
                            <input type="hidden" name="journalId" value=<%=j.getId()%> >
                        </form>
                        <%if(user.getRole().equals("ADMIN")){%>
                        <form method="post" action="journal" style="margin-bottom: -5px">
                            <button class="dropdown-item btn btn-light" type="submit" name="submit" value="Delete">Delete</button>
                            <input type="hidden" name="journalId" value=<%=j.getId()%> >
                        </form>
                        <%}%>
                    </div>
                </div>


                <img class="card-img-top" style="width:100%" src=<%=path%> alt="..." >
                <div class="card-body">
                    <h5 class="card-title"><%=cardTitle%></h5>
                    <p class="card-text"><%=j.getDescription()%> (.pdf)</p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">Tarix : <%=j.getUploadDate()%></small>
                </div>
            </div>
        </div>
        <%}%>

    </div>


    <footer class="mb-0 bg-info">
        <p style="text-align:center;border: 2px solid black;color: white">Copyright © 2021 Developed by Mahammad Niyazli</p>
    </footer>

</div>
</body>
</html>
