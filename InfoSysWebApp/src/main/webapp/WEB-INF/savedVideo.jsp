<%@ page import="com.company.dao.inter.UsersDaoInter" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.UserVideo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/4/2021
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    UsersDaoInter usersDao = (UsersDaoInter) request.getAttribute("usersDao");
    Users user = usersDao.findByEmail(request.getUserPrincipal().getName());


    List<UserVideo> userVideoList = user.getUserVideoList();

%>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <%for (UserVideo uv :userVideoList){ %>
    <div class="col" style="margin-bottom:35px">
        <div class="card h-100" style="position:relative;">
            <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                </button>

                <%
                    String pathVideo = uv.getVideoId().getStorageId().getPath();
                    String videoName = uv.getVideoId().getName();

                    String videoNameArr[] = videoName.split(" ");
                    for(int i1=0;i1<videoNameArr.length;i1++){
                        pathVideo+=videoNameArr[i1];
                        if(i1< videoNameArr.length-1){
                            pathVideo+="%20";
                        }
                    }
                    pathVideo+="#t=1";
                    System.out.println("path =" + pathVideo);
                    String cardTitle = uv.getVideoId().getName().split("\\.")[0];
                %>

                <div class="dropdown-menu dropdown-menu-right">
                    <form style="margin-bottom: -5px">
                        <div class="case">
                            <a class="dropdown-item" href=<%=pathVideo%> rel="case" target="_blank">Look</a>
                        </div>
                    </form>
                    <form method="post" action="video" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="download">Download</button>
                        <input type="hidden" name="videoId" value=<%=uv.getVideoId().getId()%> >
                    </form>
                </div>
            </div>


            <video class="img-fluid" controls>
                <source src=<%=pathVideo%> type="video/mp4">
            </video>
            <div class="card-body">
                <h5 class="card-title"><%=cardTitle%></h5>
                <p class="card-text"><%=uv.getVideoId().getDescription()%></p>
            </div>
            <div class="card-footer">
                <small class="text-muted">Tarix : <%=uv.getVideoId().getUploadDate()%></small>
            </div>
        </div>
    </div>
    <%}%>
</div>

</body>
</html>
