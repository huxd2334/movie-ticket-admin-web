package com.binhlc.adminweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "user", schema = "movie_ticket")
public class UserEntity {

    private int id;
    private String email, password, lastName, name, address, phone, avatar, token;
    private Integer type, gender, balance, point, createBy, updateBy;
    private java.sql.Timestamp timeCreate, timeUpdate, birthday;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "first_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "gender")
    public Integer getGender() {
        return gender;
    }
    // male = 1, female = 2
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Column(name = "balance")
    public Integer getBalance() {
        return balance;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Column(name = "point")
    public Integer getPoint() {
        return point;
    }
    public void setPoint(Integer point) {
        this.point = point;
    }

    @Column(name = "created_by")
    public Integer getCreateBy() {
        return createBy;
    }
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "timed_create")
    public java.sql.Timestamp getTimeCreate() {
        return timeCreate;
    }
    public void setTimeCreate(java.sql.Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_update")
    public java.sql.Timestamp getTimeUpdate() {
        return timeUpdate;
    }
    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    @Column(name = "update_by")
    public Integer getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "birthday")
    public java.sql.Timestamp getBirthday() {
        return birthday;
    }
    public void setBirthday(java.sql.Timestamp birthday) {
        this.birthday = birthday;
    }

}
