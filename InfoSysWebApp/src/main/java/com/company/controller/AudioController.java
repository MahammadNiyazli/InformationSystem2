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
@RequestMapping("/audio")
public class AudioController {
    @Autowired
    UsersDaoInter usersDao;

    @Autowired
    AudioDaoInter audioDao;

    @Autowired
    UserAudioDaoInter userAudioDao;

    @Autowired
    StorageDaoInter storageDao;

    @Autowired
    ImageDaoInter imageDao;

    public static final String DIRAudio = "D:\\InformationSystem2\\InfoSysWebApp\\src\\main\\webapp\\files\\audio\\";
    public static final String DIRImage = "D:\\InformationSystem2\\InfoSysWebApp\\src\\main\\webapp\\files\\image\\";


    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView index(HttpServletRequest request){

        ModelAndView mv = new ModelAndView("audio");
        return mv;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public void index(@RequestParam(value = "submit" , required = false) String submit
            ,HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "audioId" , required = false) Integer audioId,
                      @RequestParam(value = "description" , required = false) String description){

          if(submit!=null && submit.equals("download")){

              Audio audio = audioDao.getById(audioId);
              PrintWriter out = null;
              try {
                  out = response.getWriter();

              String filename = audio.getName();
              String filepath = DIRAudio;
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

              String audioName = "";
              String imageName ="";

              try {
                  for (Part part : request.getParts()) {
                      String fileName = part.getSubmittedFileName();
                      if(fileName==null){
                          break;
                      }
                       String savePath;
                      if(fileName.endsWith("mp3")){
                           savePath = DIRAudio + fileName;
                           audioName = fileName;
                      }else{
                          savePath = DIRImage + fileName;
                          imageName = fileName;
                      }

                      System.out.println("1 defe yazdim ");
                      part.write(savePath + File.separator );
                  }

                  Image image = new Image();
                  image.setDescription(description);
                  image.setName(imageName);
                  image.setUploadDate(new Date(new java.util.Date().getTime()));
                  image.setStorageId(storageDao.getById(2));

                  imageDao.addImage(image);

                  Audio audio = new Audio();
                  audio.setName(audioName);
                  audio.setDescription(description);
                  audio.setUploadDate(new Date(new java.util.Date().getTime()));
                  audio.setStorageId(storageDao.getById(3));
                  audio.setImageId(imageDao.findByName(imageName));

                  audioDao.addAudio(audio);

              } catch (IOException exception) {
                  exception.printStackTrace();
              } catch (ServletException e) {
                  e.printStackTrace();
              }


          }else if(submit!=null && submit.equals("save")){

              Audio audio = audioDao.getById(audioId);
              Users user = usersDao.findByEmail(request.getUserPrincipal().getName());
              UserAudio userAudio = new UserAudio();
              userAudio.setAudioId(audio);
              userAudio.setUserId(user);

              userAudioDao.addUserAudio(userAudio);


          }else if(submit!=null && submit.equals("delete")){

               Audio audio = audioDao.getById(audioId);
               String fileName = audio.getName();
               String path = DIRAudio;
               File myFile = new File(path+fileName);
               myFile.delete();
               audioDao.removeAudio(audioId);

          }

        try {
            response.sendRedirect("base");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
