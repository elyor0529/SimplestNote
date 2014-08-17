package beans;

import db.UsersEntity;

import java.io.Serializable;

/**
 * Created by Elyor on 8/10/2014.
 */
public class LoginBean implements Serializable {

    private String userName;
    private String password;
    private long id;
    private String fullName;
    private String email;

    public static LoginBean getLoginBean(UsersEntity entity) {
        LoginBean loginBean = new LoginBean();

        loginBean.setFullName(entity.getFirstName() + " " + entity.getLastName());
        loginBean.setEmail(entity.geteMail());
        loginBean.setUserName(entity.getUserName());
        loginBean.setPassword(entity.getPassword());
        loginBean.setId(entity.getId());

        return loginBean;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
