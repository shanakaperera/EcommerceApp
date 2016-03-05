/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.dbmodel;

import java.io.Serializable;

/**
 *
 * @author shanaka
 */
public class SiteGeneral implements Serializable {

    private Integer id;
    private String siteAddress;
    private String tel;
    private String siteMail;
    private String aboutUsFooter;
    private String aboutUsMain;
    private String usrAcntDesc;

    public SiteGeneral() {
    }

    public SiteGeneral(Integer id, String siteAddress, String tel, String siteMail, String aboutUsFooter, String aboutUsMain, String usrAcntDesc) {
        this.id = id;
        this.siteAddress = siteAddress;
        this.tel = tel;
        this.siteMail = siteMail;
        this.aboutUsFooter = aboutUsFooter;
        this.aboutUsMain = aboutUsMain;
        this.usrAcntDesc = usrAcntDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSiteMail() {
        return siteMail;
    }

    public void setSiteMail(String siteMail) {
        this.siteMail = siteMail;
    }

    public String getAboutUsFooter() {
        return aboutUsFooter;
    }

    public void setAboutUsFooter(String aboutUsFooter) {
        this.aboutUsFooter = aboutUsFooter;
    }

    public String getAboutUsMain() {
        return aboutUsMain;
    }

    public void setAboutUsMain(String aboutUsMain) {
        this.aboutUsMain = aboutUsMain;
    }

    public String getUsrAcntDesc() {
        return usrAcntDesc;
    }

    public void setUsrAcntDesc(String usrAcntDesc) {
        this.usrAcntDesc = usrAcntDesc;
    }

}
