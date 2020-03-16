/**
 * 
 */
package io.github.plindzek.lang;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Adam
 * model/encja bazodanowa
 */
@Entity
@Table(name = "languages")
public class Lang {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Column(name = "langid")
    private Integer langId;
    @Column(name = "langcode")
    private String langCode;
    @Column(name = "welcomemsg")
    private String welcomeMsg;
    @Column(name = "carname")
    private String carName;
    @Column(name = "lpgon100km")
    private String lpgOn100Km;
    @Column(name = "lpgprice")
    private String lpgPrice;
    @Column(name = "kmonlpg")
    private String kmOnLpg;
    @Column(name = "pbon100km")
    private String pbOn100Km;
    @Column(name = "pbprice")
    private String pbPrice;
    @Column(name = "kmonpb")
    private String kmOnPb;
    @Column(name = "costdsc")
    private String costDsc;
    @Column(name = "exitbutton")
    private String exitButton;
    @Column(name = "solvebutton")
    private String solveButton;
    @Column(name = "saveprofilebutton")
    private String saveProfileButton;
    @Column(name = "loadprofilebutton")
    private String loadProfileButton;

    /*
     * Spring needs this constructor
     */
    Lang() {
    }

    public Lang(Integer langId, String welcomeMsg, String langCode, String carName, String lpgOn100Km, String lpgPrice,
	    String kmOnLpg, String pbOn100Km, String pbPrice, String kmOnPb, String costDsc, String solveButton,
	    String exitButton, String saveProfileButton, String loadProfileButton) {
	this.langId = langId;
	this.welcomeMsg = welcomeMsg;
	this.langCode = langCode;
	this.carName = carName;
	this.lpgOn100Km = lpgOn100Km;
	this.lpgPrice = lpgPrice;
	this.kmOnLpg = kmOnLpg;
	this.pbOn100Km = pbOn100Km;
	this.pbPrice = pbPrice;
	this.kmOnPb = kmOnPb;
	this.costDsc = costDsc;
	this.solveButton = solveButton;
	this.exitButton = exitButton;
	this.saveProfileButton = saveProfileButton;
	this.loadProfileButton = loadProfileButton;
    }

    public Integer getLangId() {
	return langId;
    }
    public String getWelcomeMsg() {
	return welcomeMsg;
    }
    public void setWelcomeMsg(String welcomeMsg) {
	this.welcomeMsg = welcomeMsg;
    }

    public String getLangCode() {
	return langCode;
    }

    public void setLangCode(String langCode) {
	this.langCode = langCode;
    }

    public String getCarName() {
	return carName;
    }

    public void setCarName(String carName) {
	this.carName = carName;
    }

    public String getLpgOn100Km() {
	return lpgOn100Km;
    }

    public void setLpgOn100Km(String lpgOn100Km) {
	this.lpgOn100Km = lpgOn100Km;
    }

    public String getLpgPrice() {
	return lpgPrice;
    }

    public void setLpgPrice(String lpgPrice) {
	this.lpgPrice = lpgPrice;
    }

    public String getKmOnLpg() {
	return kmOnLpg;
    }

    public void setKmOnLpg(String kmOnLpg) {
	this.kmOnLpg = kmOnLpg;
    }

    public String getPbOn100Km() {
	return pbOn100Km;
    }

    public void setPbOn100Km(String pbOn100Km) {
	this.pbOn100Km = pbOn100Km;
    }

    public String getPbPrice() {
	return pbPrice;
    }

    public void setPbPrice(String pbPrice) {
	this.pbPrice = pbPrice;
    }

    public String getKmOnPb() {
	return kmOnPb;
    }

    public void setKmOnPb(String kmOnPb) {
	this.kmOnPb = kmOnPb;
    }

    public String getCostDsc() {
	return costDsc;
    }

    public void setCostDsc(String costDsc) {
	this.costDsc = costDsc;
    }

    public String getSolveButton() {
	return solveButton;
    }

    public void setSolveButton(String solveButton) {
	this.solveButton = solveButton;
    }

    public String getExitButton() {
	return exitButton;
    }

    public void setExitButton(String exitButton) {
	this.exitButton = exitButton;
    }

    public String getSaveProfileButton() {
	return saveProfileButton;
    }

    public void setSaveProfileButton(String saveProfileButton) {
	this.saveProfileButton = saveProfileButton;
    }

    public String getLoadProfileButton() {
	return loadProfileButton;
    }

    public void setLoadProfileButton(String loadProfileButton) {
	this.loadProfileButton = loadProfileButton;
    }



}
