package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")

public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    public Employees(){

    }

    public Employees(String firstname,String lastname,String email){
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
