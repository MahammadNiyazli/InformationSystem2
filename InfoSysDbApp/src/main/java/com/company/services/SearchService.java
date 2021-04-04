package com.company.services;

import com.company.dao.inter.*;
import com.company.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService  {

    @Autowired
    AudioDaoInter audioDao;
    @Autowired
    DocumentDaoInter documentDao;
    @Autowired
    ImageDaoInter imageDao;
    @Autowired
    JournalDaoInter journalDao;
    @Autowired
    VideoDaoInter videoDao;

    public List<Audio> searchAudio(String word) throws Exception {
        List<Audio> returnAudioList = new ArrayList<>();
        List<Audio> audioList = audioDao.getAll();

        for (Audio audio : audioList){
            if(audio.getName().toLowerCase().equals(word)){
                returnAudioList.add(audio);
            }else if(audio.getDescription().contains(word)){
                returnAudioList.add(audio);
            }else if(audio.getUploadDate().toString().equals(word)){
                returnAudioList.add(audio);
            }
        }
        return returnAudioList;
    }

    public List<Document> searchDocument(String word) throws Exception {
        List<Document> returnDocumentList = new ArrayList<>();
        List<Document> documentList = documentDao.getAll();

        for (Document document : documentList){
            if(document.getName().toLowerCase().equals(word)){
                returnDocumentList.add(document);
            }else if(document.getDescription().contains(word)){
                returnDocumentList.add(document);
            }else if(document.getUploadDate().toString().equals(word)){
                returnDocumentList.add(document);
            }else if(document.getRepetedWords().contains(word)){
                returnDocumentList.add(document);
            }
        }
        return returnDocumentList;
    }

    public List<Image> searchImage(String word) throws Exception {
        List<Image> returnImageList = new ArrayList<>();
        List<Image> imageList = imageDao.getAll();

        for (Image image : imageList){
            if(image.getName().toLowerCase().equals(word)){
                returnImageList.add(image);
            }else if(image.getDescription().contains(word)){
                returnImageList.add(image);
            }else if(image.getUploadDate().toString().equals(word)){
                returnImageList.add(image);
            }
        }
        return returnImageList;
    }

    public List<Journal> searchJournal(String word) throws Exception {
        List<Journal> returnJournalList = new ArrayList<>();
        List<Journal> journalList = journalDao.getAll();

        for (Journal journal : journalList){
            if(journal.getName().toLowerCase().equals(word)){
                returnJournalList.add(journal);
            }else if(journal.getDescription().contains(word)){
                returnJournalList.add(journal);
            }else if(journal.getUploadDate().toString().equals(word)){
                returnJournalList.add(journal);
            }
        }
        return returnJournalList;
    }

    public List<Video> searchVideo(String word) throws Exception {
        List<Video> returnVideoList = new ArrayList<>();
        List<Video> videoList = videoDao.getAll();

        for (Video video : videoList){
            if(video.getName().toLowerCase().equals(word)){
                returnVideoList.add(video);
            }else if(video.getDescription().contains(word)){
                returnVideoList.add(video);
            }else if(video.getUploadDate().toString().equals(word)){
                returnVideoList.add(video);
            }
        }
        return returnVideoList;
    }

}
