package com.company.controller;

import com.company.dao.inter.*;
import com.company.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController {
    @Autowired
    UsersDaoInter usersDao;

    @Autowired
    VideoDaoInter videoDao;

    @Autowired
    UserVideoDaoInter userVideoDao;

    @Autowired
    StorageDaoInter storageDao;

    @Autowired
    ImageDaoInter imageDao;

    public static final String DIRVideo = "D:\\InformationSystem2\\InfoSysWebApp\\src\\main\\webapp\\files\\video\\";
    public static final String DIRImage = "D:\\InformationSystem2\\InfoSysWebApp\\src\\main\\webapp\\files\\image\\";


    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView index(HttpServletRequest request,@RequestParam(value = "submit" , required = false) String submit){

        ModelAndView mv = new ModelAndView("video");
        return mv;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public void index(@RequestParam(value = "submit" , required = false) String submit
            ,HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "videoId" , required = false) Integer videoId,
                      @RequestParam(value = "description" , required = false) String description){

          if(submit!=null && submit.equals("download")){

              Video video = videoDao.getById(videoId);
              PrintWriter out = null;
              try {
                  out = response.getWriter();

              String filename = video.getName();
              String filepath = DIRVideo;
              response.setContentType("APPLICATION/OCTET-STREAM");
              response.setHeader("Content-Disposition", "attachment; filename=\""
                      + filename + "\"");

              FileInputStream fileInputStream = new FileInputStream(filepath
                      + filename);

              int i;
              while( (i = fileInputStream.read()) != -1 )
              {
                  out.write(i);
              }
              fileInputStream.close();
              out.close();


              } catch (IOException exception) {
            exception.printStackTrace();
             }

          }else if(submit!=null && submit.equals("upload")){

              String videoName = "";
              String imageName ="";

              try {
                  for (Part part : request.getParts()) {
                      String fileName = part.getSubmittedFileName();
                      if(fileName==null){
                          break;
                      }
                       String savePath;
                      if(fileName.endsWith("mp4")){
                           savePath = DIRVideo + fileName;
                           videoName = fileName;
                      }else{
                          savePath = DIRImage + fileName;
                          imageName = fileName;
                      }

                      part.write(savePath + File.separator );
                  }

                  Image image = new Image();
                  image.setDescription(description);
                  image.setName(imageName);
                  image.setUploadDate(new Date(new java.util.Date().getTime()));
                  image.setStorageId(storageDao.getById(2));

                  imageDao.addImage(image);

                  Video video = new Video();
                  video.setName(videoName);
                  video.setDescription(description);
                  video.setUploadDate(new Date(new java.util.Date().getTime()));
                  video.setStorageId(storageDao.getById(4));
                  video.setImageId(imageDao.findByName(imageName));

                  videoDao.addVideo(video);

              } catch (IOException exception) {
                  exception.printStackTrace();
              } catch (ServletException e) {
                  e.printStackTrace();
              }


          }else if(submit!=null && submit.equals("save")){

              Video video = videoDao.getById(videoId);
              Users user = usersDao.findByEmail(request.getUserPrincipal().getName());
              UserVideo userVideo = new UserVideo();
              userVideo.setVideoId(video);
              userVideo.setUserId(user);

              userVideoDao.addUserVideo(userVideo);


          }else if(submit!=null && submit.equals("delete")){
              Video video = videoDao.getById(videoId);
              String fileName = video.getName();
              String path = DIRVideo;
              File myFile = new File(path+fileName);
              myFile.delete();
               videoDao.removeVideo(videoId);

          }

        try {
            response.sendRedirect("base");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
