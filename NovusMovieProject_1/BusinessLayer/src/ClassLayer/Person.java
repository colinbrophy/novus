package ClassLayer;

import ApplicationVariables.AppVariables;

// For a Person for example an actor or a director
public class Person {
    public String personID, personName;
    
    public Person(String ID, String name){
        personID = ID;
        personName = name;
    }
    
    public String getID(){return personID;}
    public String getName(){return personName;}
    public void setID(String ID){personID = ID;}
    public void setName(String name){personName = name;}
    public String getPersonImdbLink() {return String.format(AppVariables.WebProperties.imdbProfileURL, personID);}

}
