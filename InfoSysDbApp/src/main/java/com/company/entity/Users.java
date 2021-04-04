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
import java.util.List;

/**
 *
 * @author User
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findBySurname", query = "SELECT u FROM Users u WHERE u.surname = :surname"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "userId")
    private List<UserImage> userImageList;
    @OneToMany(mappedBy = "userId")
    private List<UserAudio> userAudioList;
    @OneToMany(mappedBy = "userId")
    private List<UserJournal> userJournalList;
    @OneToMany(mappedBy = "userId")
    private List<UserDocument> userDocumentList;
    @OneToMany(mappedBy = "userId")
    private List<UserVideo> userVideoList;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String name, String surname, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlTransient
    public List<UserImage> getUserImageList() {
        return userImageList;
    }

    public void setUserImageList(List<UserImage> userImageList) {
        this.userImageList = userImageList;
    }

    @XmlTransient
    public List<UserAudio> getUserAudioList() {
        return userAudioList;
    }

    public void setUserAudioList(List<UserAudio> userAudioList) {
        this.userAudioList = userAudioList;
    }

    @XmlTransient
    public List<UserJournal> getUserJournalList() {
        return userJournalList;
    }

    public void setUserJournalList(List<UserJournal> userJournalList) {
        this.userJournalList = userJournalList;
    }

    @XmlTransient
    public List<UserDocument> getUserDocumentList() {
        return userDocumentList;
    }

    public void setUserDocumentList(List<UserDocument> userDocumentList) {
        this.userDocumentList = userDocumentList;
    }

    @XmlTransient
    public List<UserVideo> getUserVideoList() {
        return userVideoList;
    }

    public void setUserVideoList(List<UserVideo> userVideoList) {
        this.userVideoList = userVideoList;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.mavenproject3.main.entity.Users[ id=" + id + " ]";
    }
    
}
