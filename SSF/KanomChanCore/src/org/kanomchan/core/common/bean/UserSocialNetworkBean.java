package org.kanomchan.core.common.bean;

import java.util.Date;



 

public class UserSocialNetworkBean {

	private String firstName;
	private String lastName;
	
	private Long idUserSocialNetwork;
	
	private Long idSocialNetworkType;
    private Long idUser;
    private String socialProfileId;
    private String socialOauthToken;
    private String socialOauthSecretToken;
    private String socialAccountName;
    private String emailSocialAccount;
    private String status;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;
	
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 9152126227612971046L;
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
	
	 
	public String getSocialAccountName(){
		return this.socialAccountName ;
			
	}
	public Long getIdUserSocialNetwork() {
		return idUserSocialNetwork;
	}
	public void setIdUserSocialNetwork(Long idUserSocialNetwork) {
		this.idUserSocialNetwork = idUserSocialNetwork;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getSocialProfileId() {
		return socialProfileId;
	}
	public void setSocialProfileId(String socialProfileId) {
		this.socialProfileId = socialProfileId;
	}
	public String getSocialOauthToken() {
		return socialOauthToken;
	}
	public void setSocialOauthToken(String socialOauthToken) {
		this.socialOauthToken = socialOauthToken;
	}
	public String getSocialOauthSecretToken() {
		return socialOauthSecretToken;
	}
	public void setSocialOauthSecretToken(String socialOauthSecretToken) {
		this.socialOauthSecretToken = socialOauthSecretToken;
	}
	public String getEmailSocialAccount() {
		return emailSocialAccount;
	}
	public void setEmailSocialAccount(String emailSocialAccount) {
		this.emailSocialAccount = emailSocialAccount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setSocialAccountName(String socialAccountName) {
		this.socialAccountName = socialAccountName;
	}
	public Long getIdSocialNetworkType() {
		return idSocialNetworkType;
	}
	public void setIdSocialNetworkType(Long idSocialNetworkType) {
		this.idSocialNetworkType = idSocialNetworkType;
	}
	
	
}
