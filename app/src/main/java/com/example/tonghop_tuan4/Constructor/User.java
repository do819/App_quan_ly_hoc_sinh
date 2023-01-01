package com.example.tonghop_tuan4.Constructor;

public class User  {

    private String id;
    private String name;
    private String mail;
    private String imageURL;
    private String password;

    public User(String id){
        this.id = id;
    }

//    public User(String name, String mail, String imageURL, String password) {
//
//        this.name = name;
//        this.mail = mail;
//        this.imageURL = imageURL;
//        this.password = password;
//    }


    public User(String id, String name, String mail, String imageURL, String password) {

        this.id = id;
        this.name = name;
        this.mail = mail;
        this.imageURL = imageURL;
        this.password = password;
    }

//    public User(String idKhoaHocRegistered, String idClassRegistered, String id, String name, String mail, String imageURL, String password) {
//        super(idKhoaHocRegistered, idClassRegistered);
//        this.id = id;
//        this.name = name;
//        this.mail = mail;
//        this.imageURL = imageURL;
//        this.password = password;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Map<key, value>(){
//
//    }
    }
