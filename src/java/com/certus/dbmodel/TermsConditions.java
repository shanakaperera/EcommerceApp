/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.dbmodel;

/**
 *
 * @author shanaka
 */
public class TermsConditions  implements java.io.Serializable {


     private Integer id;
     private String mainSec;
     private String securitySec;
     private String serviceOffered;
     private String privacyPolicy;
     private String usrCnductRules;
     private String compensationPolicy;
     private String extnessGrnteed;
     private String limitationOfLiability;
     private String pricing;
     private String delivery;
     private String serviceFees;

    public TermsConditions() {
    }

    public TermsConditions(String mainSec, String securitySec, String serviceOffered, String privacyPolicy, String usrCnductRules, String compensationPolicy, String extnessGrnteed, String limitationOfLiability, String pricing, String delivery, String serviceFees) {
       this.mainSec = mainSec;
       this.securitySec = securitySec;
       this.serviceOffered = serviceOffered;
       this.privacyPolicy = privacyPolicy;
       this.usrCnductRules = usrCnductRules;
       this.compensationPolicy = compensationPolicy;
       this.extnessGrnteed = extnessGrnteed;
       this.limitationOfLiability = limitationOfLiability;
       this.pricing = pricing;
       this.delivery = delivery;
       this.serviceFees = serviceFees;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMainSec() {
        return this.mainSec;
    }
    
    public void setMainSec(String mainSec) {
        this.mainSec = mainSec;
    }
    public String getSecuritySec() {
        return this.securitySec;
    }
    
    public void setSecuritySec(String securitySec) {
        this.securitySec = securitySec;
    }
    public String getServiceOffered() {
        return this.serviceOffered;
    }
    
    public void setServiceOffered(String serviceOffered) {
        this.serviceOffered = serviceOffered;
    }
    public String getPrivacyPolicy() {
        return this.privacyPolicy;
    }
    
    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }
    public String getUsrCnductRules() {
        return this.usrCnductRules;
    }
    
    public void setUsrCnductRules(String usrCnductRules) {
        this.usrCnductRules = usrCnductRules;
    }
    public String getCompensationPolicy() {
        return this.compensationPolicy;
    }
    
    public void setCompensationPolicy(String compensationPolicy) {
        this.compensationPolicy = compensationPolicy;
    }
    public String getExtnessGrnteed() {
        return this.extnessGrnteed;
    }
    
    public void setExtnessGrnteed(String extnessGrnteed) {
        this.extnessGrnteed = extnessGrnteed;
    }
    public String getLimitationOfLiability() {
        return this.limitationOfLiability;
    }
    
    public void setLimitationOfLiability(String limitationOfLiability) {
        this.limitationOfLiability = limitationOfLiability;
    }
    public String getPricing() {
        return this.pricing;
    }
    
    public void setPricing(String pricing) {
        this.pricing = pricing;
    }
    public String getDelivery() {
        return this.delivery;
    }
    
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
    public String getServiceFees() {
        return this.serviceFees;
    }
    
    public void setServiceFees(String serviceFees) {
        this.serviceFees = serviceFees;
    }




}
