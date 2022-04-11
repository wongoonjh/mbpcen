package com.qry.mbpcen.admin.usr.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UserVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String seqNo;
	@NotNull(message="userID 필드가 null 입니")
	private String userID;
	@NotNull(message="userPW 필드가 null 입니")
	private String userPW;
    private String userName;
    private String userEmail;
    private String userRole;

    
    

	public UserVO() {
    }


	public UserVO(String userID, String userName ,String userRole) {
        this.userID = userID;
        this.userName = userName;
        this.userRole = userRole;
    }
    
    
    public String getUserID() {
		return userID;
	}
    public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    
    
    
    
    
	public String getPw() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRoles() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getSeqNo() {
		return seqNo;
	}


	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
}
