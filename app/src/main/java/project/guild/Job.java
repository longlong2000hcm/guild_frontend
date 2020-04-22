package project.guild;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {
    String title, description, location, phone, salary, id;
   // Date date;

    public Job(String title, String description, String location, String phone, String salary, String id) {
        this.title = title;
        this.description = description;
        this.location= location;
        this.phone = phone;
        this.salary = salary;
        this.id = id;
      //  this.date= date;
    }
    public String getTitle() {
        return title;
    }
    public String getLocation() {
        return location;
    }
    public String getDescription() {
        return description;
    }
    public String getPhone() {
        return phone;
    }
    public String getSalary() {
        return salary;
    }
   // public Date getDate(){
     //   return date; }
}