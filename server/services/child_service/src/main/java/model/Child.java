package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Child {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   private String firstName;
   private String lastName;
   private String ParentEmail;
   private LocalDate Birthday;


    public Child() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getParentEmail() {
        return ParentEmail;
    }

    public void setParentEmail(String parentEmail) {
        ParentEmail = parentEmail;
    }

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate birthday) {
        Birthday = birthday;
    }

    public Child(String firstName, String lastName, String parentEmail, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        ParentEmail = parentEmail;
        Birthday = birthday;
    }

    public int getAge(){
        return LocalDate.now().getYear() - Birthday.getYear();
    }
}
