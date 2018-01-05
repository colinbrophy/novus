package ClassLayer;

import java.util.ArrayList;
import java.util.List;
import ApplicationVariables.AppVariables;


// Film with added directors, actors, year and IMDB rating
public class Film extends SimplisticFilm {
    public String imdbRating, filmYear;
    public List<Director> directors;
    public List<Actor> actors;
    
    public Film(){
        directors = new ArrayList<Director>();
        actors = new ArrayList<Actor>();
    }
    
    public Film(String filmID, String filmName, String imdbRating, String filmYear){
        super(filmID, filmName);
        this.imdbRating = imdbRating;
        this.filmYear = filmYear;
        directors = new ArrayList<Director>();
        actors = new ArrayList<Actor>();
    }
    
    public Film(String filmID, String filmName, String imdbRating, 
            List<Director> directors, List<Actor> actors, String filmYear){
        super(filmID, filmName);
        this.imdbRating = imdbRating;
        this.directors = directors;
        this.actors = actors;
        this.filmYear = filmYear;
    }
    
    /* not needed because Film is a subclass of SimplisticFilm
    public SimplisticFilm toSimplisticFilm(){
        return (SimplisticFilm)this;
    }*/
    
    // Getters and Setters
    public String getFilmRating(){return imdbRating;}
    public String getFilmYear(){return filmYear;}
    public List<Director> getDirectors(){return directors;}
    public List<Actor> getActors(){return actors;}
   
    public Director getDirector() { return directors.get(0);}
 //   public Actor getActor() { return actors.get(0); }
   // public String getDirectorImdbLink() {return String.format(AppVariables.WebProperties.imdbProfileURL,  getDirector().personID);}
 //   public String getActorImdbLink() { return String.format(AppVariables.WebProperties.imdbProfileURL, getActor().personID);}
    
    
    public void setFilmRatig(String imdbRating){this.imdbRating = imdbRating;}
    public void setFilmYear(String filmYear){this.filmYear = filmYear;}
    public void setDirectorList(List<Director> directors){this.directors = directors;}
    public void setActorList(List<Actor> actors){this.actors = actors;}
}
