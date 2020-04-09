package project.guild;

import java.util.Date;

public class Job {
    String title, description, location;
   // Date date;

    public Job(String title, String description, String location) {
        this.title = title;
        this.description = description;
        this.location= location;
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
   // public Date getDate(){
     //   return date; }
}