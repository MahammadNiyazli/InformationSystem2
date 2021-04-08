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
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    UsersDaoInter usersDao;

    @Autowired
    DocumentDaoInter documentDao;

    @Autowired
    UserDocumentDaoInter userDocumentDao;

    @Autowired
    StorageDaoInter storageDao;

    @Autowired
    ImageDaoInter imageDao;

    public static final String DIRDocument = "D:\\InformationSystem2\\InfoSysWebApp\\src\\main\\webapp\\files\\document\\";
    public static final String DIRImage = "D:\\InformationSystem2\\InfoSysWebApp\\src\\main\\webapp\\files\\image\\";


    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView index(HttpServletRequest request,@RequestParam(value = "submit" , required = false) String submit){

        ModelAndView mv = new ModelAndView("document");
        return mv;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public void index(@RequestParam(value = "submit" , required = false) String submit
            ,HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "documentId" , required = false) Integer documentId,
                      @RequestParam(value = "description" , required = false) String description){

          if(submit!=null && submit.equals("download")){

              Document document = documentDao.getById(documentId);
              PrintWriter out = null;
              try {
                  out = response.getWriter();

              String filename = document.getName();
              String filepath = DIRDocument;
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

              String documentName = "";
              String imageName ="";

              try {
                  for (Part part : request.getParts()) {
                      String fileName = part.getSubmittedFileName();
                      if(fileName==null){
                          break;
                      }
                       String savePath;
                      if(fileName.endsWith("doc") || fileName.endsWith("docx")){
                           savePath = DIRDocument + fileName;
                           documentName = fileName;
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

                  Document document = new Document();
                  document.setName(documentName);
                  document.setDescription(description);
                  document.setUploadDate(new Date(new java.util.Date().getTime()));
                  document.setStorageId(storageDao.getById(5));
                  document.setImageId(imageDao.findByName(imageName));
                  document.setRepetedWords(findMostRepetedWords(DIRDocument + documentName));

                  Thread.sleep(5000);
                  documentDao.addDocument(document);

              } catch (IOException exception) {
                  exception.printStackTrace();
              } catch (ServletException e) {
                  e.printStackTrace();
              } catch (Exception exception) {
                  exception.printStackTrace();
              }


          }else if(submit!=null && submit.equals("save")){

              Document document = documentDao.getById(documentId);
              Users user = usersDao.findByEmail(request.getUserPrincipal().getName());
              UserDocument userDocument = new UserDocument();
              userDocument.setDocumentId(document);
              userDocument.setUserId(user);

              userDocumentDao.addUserDocument(userDocument);


          }else if(submit!=null && submit.equals("delete")){
               documentDao.removeDocument(documentId);

          }

        try {
            response.sendRedirect("base");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public String findMostRepetedWords(String path) throws Exception {
        try {
            String line, word = "";
            int count = 0, maxCount = 0;
            ArrayList<String> words = new ArrayList<String>();

            FileReader file = new FileReader(path);
            BufferedReader br = new BufferedReader(file);

            while ((line = br.readLine()) != null) {
                String string[] = line.toLowerCase().split(" ");

                for (String s : string) {
                    words.add(s);
                }
            }

            for (int i = 0; i < words.size(); i++) {
                count = 1;
                for (int j = i + 1; j < words.size(); j++) {
                    if (words.get(i).equals(words.get(j))) {
                        count++;
                    }
                }
                if (count > maxCount) {
                    maxCount = count;
                    word = words.get(i);
                }
            }

            br.close();
            System.out.println("tekrarlanan soz = "+word);
            return word;
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

}
