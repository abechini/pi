package com.esprit.bankPi.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.esprit.bankPi.util.Patterns;
import com.esprit.bankPi.util.SystemMessages;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class UserProfile {
	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    @Column(nullable = false, updatable = false)
	    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	    private Long id;
	    @NotEmpty(message = SystemMessages.USER_FIRST_NAME_REQUIRED)
	    private String firstName;
	    @NotEmpty(message = SystemMessages.USER_LAST_NAME_REQUIRED)
	    private String lastName;
	    @NotEmpty(message = SystemMessages.USER_TITLE_REQUIRED)
	    @Pattern(regexp = Patterns.USER_TITLE, message = SystemMessages.USER_TITLE_FORMAT)
	    private String title;
	    @NotEmpty(message = SystemMessages.USER_GENDER_REQUIRED)
	    @Pattern(regexp = Patterns.USER_GENDER, message = SystemMessages.USER_GENDER_FORMAT)
	    private String gender;
	    @NotEmpty(message = SystemMessages.USER_CNSS_REQUIRED)
	    @Pattern(regexp = Patterns.USER_CNSS, message = SystemMessages.USER_CNSS_FORMAT)
	    @Column(nullable = false, unique = true)
	    private String cnss;
	    @NotNull(message = SystemMessages.USER_DOB_REQUIRED)
	    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
	    private Date dob;
	    @DateTimeFormat(pattern = Patterns.DATE_FORMAT)
	    private Date dom;
	    @NotEmpty(message = SystemMessages.USER_EMAIL_REQUIRED)
	    @Pattern(regexp = Patterns.USER_EMAIL, message = SystemMessages.USER_EMAIL_FORMAT)
	    @Column(nullable = false, unique = true)
	    private String emailAddress;
	    @NotEmpty(message = SystemMessages.USER_PHONE_HOME_REQUIRED)
	    @Pattern(regexp = Patterns.USER_PHONE_REQ, message = SystemMessages.USER_PHONE_HOME_FORMAT)
	    private String homePhone;
	    @Pattern(regexp = Patterns.USER_PHONE_NOT_REQ, message = SystemMessages.USER_PHONE_MOBILE_FORMAT)
	    private String mobilePhone;
	    @Pattern(regexp = Patterns.USER_PHONE_NOT_REQ, message = SystemMessages.USER_PHONE_WORK_FORMAT)
	    private String workPhone;
	    @NotEmpty(message = SystemMessages.USER_ADDRESS_REQUIRED)
	    private String address;
	    @NotEmpty(message = SystemMessages.USER_LOCALITY_REQUIRED)
	    private String locality;
	    @NotEmpty(message = SystemMessages.USER_REGION_REQUIRED)
	    private String region;
	    @NotEmpty(message = SystemMessages.USER_POSTAL_CODE_REQUIRED)
	    private String postalCode;
	    @NotEmpty(message = SystemMessages.USER_COUNTRY_REQUIRED)
	    private String country;

	    public UserProfile() {
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public String getCnss() {
	        return cnss;
	    }

	    public void setCnss(String cnss) {
	        this.cnss = cnss;
	    }

	    public Date getDob() {
	        return dob;
	    }

	    public void setDob(Date dob) {
	        this.dob = dob;
	    }

	    public Date getDom() {
	        return dom;
	    }

	    public void setDom(Date dom) {
	        this.dom = dom;
	    }

	    public String getEmailAddress() {
	        return emailAddress;
	    }

	    public void setEmailAddress(String emailAddress) {
	        this.emailAddress = emailAddress;
	    }

	    public String getHomePhone() {
	        return homePhone;
	    }

	    public void setHomePhone(String homePhone) {
	        this.homePhone = homePhone;
	    }

	    public String getMobilePhone() {
	        return mobilePhone;
	    }

	    public void setMobilePhone(String mobilePhone) {
	        this.mobilePhone = mobilePhone;
	    }

	    public String getWorkPhone() {
	        return workPhone;
	    }

	    public void setWorkPhone(String workPhone) {
	        this.workPhone = workPhone;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getLocality() {
	        return locality;
	    }

	    public void setLocality(String locality) {
	        this.locality = locality;
	    }

	    public String getRegion() {
	        return region;
	    }

	    public void setRegion(String region) {
	        this.region = region;
	    }

	    public String getPostalCode() {
	        return postalCode;
	    }

	    public void setPostalCode(String postalCode) {
	        this.postalCode = postalCode;
	    }

	    public String getCountry() {
	        return country;
	    }

	    public void setCountry(String country) {
	        this.country = country;
	    }
}
