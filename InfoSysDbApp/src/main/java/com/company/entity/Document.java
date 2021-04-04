/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
@Entity
@Table(name = "document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findById", query = "SELECT d FROM Document d WHERE d.id = :id"),
    @NamedQuery(name = "Document.findByName", query = "SELECT d FROM Document d WHERE d.name = :name"),
    @NamedQuery(name = "Document.findByUploadDate", query = "SELECT d FROM Document d WHERE d.uploadDate = :uploadDate")})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "upload_date")
    @Temporal(TemporalType.DATE)
    private Date uploadDate;
    @Lob
    @Column(name = "repeted_words")
    private String repetedWords;
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @ManyToOne
    private Image imageId;
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Storage storageId;
    @OneToMany(mappedBy = "documentId")
    private List<UserDocument> userDocumentList;

    public Document() {
    }

    public Document(Integer id) {
        this.id = id;
    }

    public Document(Integer id, String name, Date uploadDate) {
        this.id = id;
        this.name = name;
        this.uploadDate = uploadDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getRepetedWords() {
        return repetedWords;
    }

    public void setRepetedWords(String repetedWords) {
        this.repetedWords = repetedWords;
    }

    public Image getImageId() {
        return imageId;
    }

    public void setImageId(Image imageId) {
        this.imageId = imageId;
    }

    public Storage getStorageId() {
        return storageId;
    }

    public void setStorageId(Storage storageId) {
        this.storageId = storageId;
    }

    @XmlTransient
    public List<UserDocument> getUserDocumentList() {
        return userDocumentList;
    }

    public void setUserDocumentList(List<UserDocument> userDocumentList) {
        this.userDocumentList = userDocumentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.mavenproject3.main.entity.Document[ id=" + id + " ]";
    }
    
}
