package db;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Elyor on 8/16/2014.
 */
@Entity
@Table(name = "users", schema = "", catalog = "simplestnote")
public class UsersEntity {
    private long id;
    private String firstName;
    private String lastName;
    private String surname;
    private String eMail;
    private Date birthDay;
    private String userName;
    private String password;
    private String gender;
    private Boolean isVerified;
    private String verificationCode;
    private String address;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "surname", nullable = true, insertable = true, updatable = true, length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "e_mail", nullable = false, insertable = true, updatable = true, length = 50)
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "birth_day", nullable = false, insertable = true, updatable = true)
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Basic
    @Column(name = "user_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "gender", nullable = false, insertable = true, updatable = true, length = 5)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "is_verified", nullable = true, insertable = true, updatable = true)
    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Basic
    @Column(name = "verification_code", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity entity = (UsersEntity) o;

        if (id != entity.id) return false;
        if (birthDay != null ? !birthDay.equals(entity.birthDay) : entity.birthDay != null) return false;
        if (eMail != null ? !eMail.equals(entity.eMail) : entity.eMail != null) return false;
        if (firstName != null ? !firstName.equals(entity.firstName) : entity.firstName != null) return false;
        if (gender != null ? !gender.equals(entity.gender) : entity.gender != null) return false;
        if (isVerified != null ? !isVerified.equals(entity.isVerified) : entity.isVerified != null) return false;
        if (lastName != null ? !lastName.equals(entity.lastName) : entity.lastName != null) return false;
        if (password != null ? !password.equals(entity.password) : entity.password != null) return false;
        if (surname != null ? !surname.equals(entity.surname) : entity.surname != null) return false;
        if (userName != null ? !userName.equals(entity.userName) : entity.userName != null) return false;
        if (verificationCode != null ? !verificationCode.equals(entity.verificationCode) : entity.verificationCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (isVerified != null ? isVerified.hashCode() : 0);
        result = 31 * result + (verificationCode != null ? verificationCode.hashCode() : 0);
        return result;
    }
}
