<%@ page import="java.security.Principal" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.Journal" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.company.dao.inter.JournalDaoInter" %>
<%@ page import="com.company.dao.impl.JournalDaoImpl" %>
<%@ page import="com.company.dao.inter.AudioDaoInter" %>
<%@ page import="com.company.dao.impl.AudioDaoImpl" %>
<%@ page import="com.company.entity.Audio" %><%--
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

    <style>
        .card-img-top {
            width: 100%;
            height: 15vw;
            object-fit: cover;
        }
    </style>

</head>
<body>
<%
    AudioDaoImpl audioDao = (AudioDaoImpl) request.getAttribute("audioDao");
    List<Audio> audioList = audioDao.getAll();


%>

<div class="row row-cols-1 row-cols-md-3 g-4">
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

                <div class="dropdown-menu">
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
                    <form method="post" action="audio" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="delete">Delete</button>
                        <input type="hidden" name="audioId" value=<%=a.getId()%> >
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
                <small class="text-muted">Tarix : <%=a.getUploadDate()%></small>
            </div>
        </div>
    </div>
    <%}%>
</div>
</body>
</html>
