package com.detroitlabs.smile.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmailForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String blockid;
    private String name;
    private String email;
    private String category;
    private String message;

    public EmailForm() {
    }

    public EmailForm(String blockid,String name,String email,String category,String message) {
        this.blockid = blockid;
        this.name = name;
        this.email = email;
        this.category = category;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlockid() {
        return blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
