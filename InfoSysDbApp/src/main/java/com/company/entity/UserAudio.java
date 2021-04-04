/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author User
 */
@Entity
@Table(name = "user_audio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAudio.findAll", query = "SELECT u FROM UserAudio u"),
    @NamedQuery(name = "UserAudio.findById", query = "SELECT u FROM UserAudio u WHERE u.id = :id")})
public class UserAudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "audio_id", referencedColumnName = "id")
    @ManyToOne
    private Audio audioId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public UserAudio() {
    }

    public UserAudio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Audio getAudioId() {
        return audioId;
    }

    public void setAudioId(Audio audioId) {
        this.audioId = audioId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof UserAudio)) {
            return false;
        }
        UserAudio other = (UserAudio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.mavenproject3.main.entity.UserAudio[ id=" + id + " ]";
    }
    
}
