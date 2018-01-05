package ClassLayer;

import ApplicationVariables.AppVariables;

// Film with only an ID and a name
public class SimplisticFilm{
    public String filmID, filmName;
    
    public SimplisticFilm(){ }
    public SimplisticFilm(String filmID, String filmName){
        this.filmID = filmID;
        this.filmName = filmName;
    }
    
    
    public boolean isValid(){
        // Needs to have an ID
        if(this.filmID == null || this.filmID.isEmpty()){
            return false;
        }else if(this.filmName == null || this.filmName.isEmpty()){
            // Needs to have a name
            return false;
        }else{
            return true;
        }
    }
    
    public String getFilmID(){return filmID;}
    public String getFilmName(){return filmName;}
    public void setFilmID(String filmID){this.filmID = filmID;}
    public void setFilmName(String filmName){this.filmName = filmName;}
    
    public String getFilmImdbLink() {return String.format(AppVariables.WebProperties.imdbFilmURL, filmID);}

}
