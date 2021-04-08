<%@ page import="java.security.Principal" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.Journal" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.company.dao.inter.JournalDaoInter" %>
<%@ page import="com.company.dao.impl.VideoDaoImpl" %>
<%@ page import="com.company.entity.Video" %>
<%@ page import="com.company.dao.inter.VideoDaoInter" %>
<%@ page import="com.company.dao.inter.UsersDaoInter" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/3/2021
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    VideoDaoInter videoDao = (VideoDaoInter) request.getAttribute("videoDao");
    List<Video> videoList = videoDao.getAll();

    UsersDaoInter usersDao = (UsersDaoInter) request.getAttribute("usersDao");
    Users user = usersDao.findByEmail(request.getUserPrincipal().getName());
%>

<%if(user.getRole().equals("ADMIN")){%>
<div class="row">
    <button class="btn btn-secondary mx-3 mb-3" data-toggle="modal" data-target="#uploadVideo" name="upload" style="width:100%">Upload <i class="fas fa-upload"></i></button>
</div>
<%}%>

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

                <div class="dropdown-menu">
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
</div>

<div class="modal fade" id="uploadVideo" tabindex="-1" role="dialog" aria-labelledby="UploadModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="UploadModalLabel">Upload</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="video" method="POST" enctype="multipart/form-data" >
                <div class="modal-body">
                    <div class="form-group ">
                        <label for="chAudio">Choose video:</label>
                        <input type="file" class="form-control" name="chFile"  id="chAudio" multiple >
                    </div>

                    <div class="form-group ">
                        <label for="chImage">Choose image:</label>
                        <input type="file" class="form-control" name="chImage"  id="chImage" multiple >
                    </div>

                    <div class="form-group ">
                        <label for="desc"> Description:</label>
                        <input type="text"  class="form-control" name="description"  id="desc">
                    </div>
                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-success" data-dismiss="modal">close</button>
                    <button type="submit" name="submit" value="upload" class="btn btn-danger" >upload</button>

                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
