<%@ page import="com.company.entity.Audio" %>
<%@ page import="com.company.dao.inter.AudioDaoInter" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.dao.inter.UsersDaoInter" %>
<%@ page import="com.company.dao.impl.UserAudioDaoImpl" %>
<%@ page import="com.company.entity.UserAudio" %><%--
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


   List<UserAudio> userAudioList = user.getUserAudioList();


%>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <%for (UserAudio ua :userAudioList){ %>
    <div class="col" style="margin-bottom:35px">
        <div class="card h-100" style="position:relative;">
            <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                </button>

                <%
                    String pathAudio = ua.getAudioId().getStorageId().getPath();
                    String AudioName = ua.getAudioId().getName();

                    String path = ua.getAudioId().getImageId().getStorageId().getPath();
                    String name = ua.getAudioId().getImageId().getName();
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
                    String cardTitle = ua.getAudioId().getName().split("\\.")[0];
                %>

                <div class="dropdown-menu dropdown-menu-right">
                    <form style="margin-bottom: -5px">
                        <div class="case">
                            <a class="dropdown-item" href=<%=pathAudio%> rel="case" target="_blank">Look</a>
                        </div>
                    </form>
                    <form method="post" action="audio" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="download">Download</button>
                        <input type="hidden" name="audioId" value=<%=ua.getAudioId().getId()%> >
                    </form>
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
                <small class="text-muted">Tarix : <%=ua.getAudioId().getUploadDate()%></small>
            </div>
        </div>
    </div>
    <%}%>
</div>


</body>
</html>
