package com.testmall.Model;

import com.testmall.Tools.CharsetTool;

public class Userinfo {
    CharsetTool cstool = new CharsetTool();
    String userAccount;
    String userPassword;
    String userName;
    String userPhone;
    String userEmail;
    String userAddress;
    String userMsg;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        if (cstool.isEncoding(userAccount, "ISO-8859-1"))
            this.userAccount = cstool.iso2utf8(userAccount);
        else
            this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        if (cstool.isEncoding(userPassword, "ISO-8859-1"))
            this.userPassword = cstool.iso2utf8(userPassword);
        else
            this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (cstool.isEncoding(userName, "ISO-8859-1"))
            this.userName = cstool.iso2utf8(userName);
        else
            this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        if (cstool.isEncoding(userPhone, "ISO-8859-1"))
            this.userPhone = cstool.iso2utf8(userPhone);
        else
            this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        if (cstool.isEncoding(userEmail, "ISO-8859-1"))
            this.userEmail = cstool.iso2utf8(userEmail);
        else
            this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        if (cstool.isEncoding(userAddress, "ISO-8859-1"))
            this.userAddress = cstool.iso2utf8(userAddress);
        else
            this.userAddress = userAddress;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        if (cstool.isEncoding(userMsg, "ISO-8859-1"))
            this.userMsg = cstool.iso2utf8(userMsg);
        else
            this.userMsg = userMsg;
    }

    public Userinfo(String userAccount, String userPassword, String userName, String userPhone, String userEmail, String userAddress, String userMsg) {
        setUserAccount(userAccount);
        setUserPassword(userPassword);
        setUserName(userName);
        setUserPhone(userPhone);
        setUserEmail(userEmail);
        setUserAddress(userAddress);
        setUserMsg(userMsg);
    }
}
