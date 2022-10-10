package edu.msudenver.gamerconnect;
import com.google.firebase.database.IgnoreExtraProperties;



public class  User {
    private  String firstName ;
    private  String lastName;
    private  String email;
    private  String city;
    private  int zipCode;

    public User (String firstName, String lastName, String email, String city, int zipCode){

         this.firstName = firstName;
         this.lastName = lastName;
         this.email = email;
         this.city = city;
         this.zipCode = zipCode;
    }
   public  User() {}


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
