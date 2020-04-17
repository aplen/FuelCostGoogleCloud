/**
 * 
 */
package io.github.plindzek.lang;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Adam
 * model/encja bazodanowa
 */
@Data
@Entity
@Table(name = "languages")
public class Lang {

    @Setter(AccessLevel.PACKAGE)
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Column(name = "langid")
    private Integer langId;
    @Setter
    @Column(name = "langcode")
    private String langCode;
    @Setter
    @Column(name = "welcomemsg")
    private String welcomeMsg;
    @Setter
    @Column(name = "carname")
    private String carName;
    @Setter
    @Column(name = "lpgon100km")
    private String lpgOn100Km;
    @Setter
    @Column(name = "lpgprice")
    private String lpgPrice;
    @Setter
    @Column(name = "kmonlpg")
    private String kmOnLpg;
    @Setter
    @Column(name = "pbon100km")
    private String pbOn100Km;
    @Setter
    @Column(name = "pbprice")
    private String pbPrice;
    @Setter
    @Column(name = "kmonpb")
    private String kmOnPb;
    @Setter
    @Column(name = "costdsc")
    private String costDsc;
    @Setter
    @Column(name = "exitbutton")
    private String exitButton;
    @Setter
    @Column(name = "solvebutton")
    private String solveButton;
    @Setter
    @Column(name = "saveprofilebutton")
    private String saveProfileButton;
    @Setter
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


}
