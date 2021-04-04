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
@Table(name = "audio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Audio.findAll", query = "SELECT a FROM Audio a"),
    @NamedQuery(name = "Audio.findById", query = "SELECT a FROM Audio a WHERE a.id = :id"),
    @NamedQuery(name = "Audio.findByName", query = "SELECT a FROM Audio a WHERE a.name = :name"),
    @NamedQuery(name = "Audio.findByUploadDate", query = "SELECT a FROM Audio a WHERE a.uploadDate = :uploadDate")})
public class Audio implements Serializable {

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
    @OneToMany(mappedBy = "audioId")
    private List<UserAudio> userAudioList;
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @ManyToOne
    private Image imageId;
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Storage storageId;

    public Audio() {
    }

    public Audio(Integer id) {
        this.id = id;
    }

    public Audio(Integer id, String name, Date uploadDate) {
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

    @XmlTransient
    public List<UserAudio> getUserAudioList() {
        return userAudioList;
    }

    public void setUserAudioList(List<UserAudio> userAudioList) {
        this.userAudioList = userAudioList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Audio)) {
            return false;
        }
        Audio other = (Audio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.mavenproject3.main.entity.Audio[ id=" + id + " ]";
    }
    
}
