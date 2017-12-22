package PresentationLayer;

import ApplicationVariables.AppVariables;
import BusinessLayer.MovieBusinessLayer;
import ClassLayer.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

// Defines the lifetime of the object in this case as long as you stay on the same page
@ViewScoped
// Defines the name which can be used in the JavaSever faces XHTML
@Named("bean")
public class Beans extends BaseBean implements Serializable{
   
    private MovieBusinessLayer mbl = new MovieBusinessLayer();
    // Strings representing the currently selected entry
    private String selectedFilm, selectedDirector, selectedActor, selectedYear, selectedRating;
    
    List<Director> directors;
    List<Actor> actors;
    List<SimplisticFilm> sFilms;
    List<String> filmYears, filmRatings;
    // isSubmitted currently does nothing
    private boolean isSubmitted = false;
    // isAllSelected is true if there has been something selected byt the user
    private boolean isAllSelected = false;
    
    // Called at object construction time
    @PostConstruct
    protected void load(){
        // Is this the initial request or a new one? 
        if (this.isPostback()){
            // Set the IDs to the user selection
            String filmID = (selectedFilm == null ? null : selectedFilm);
            String directorID = (selectedDirector == null ? null : selectedDirector);
            String actorID = (selectedActor == null ? null : selectedActor);
            String filmYear = (selectedYear == null ? null : selectedYear);
            String filmRating = (selectedRating == null ? null : selectedRating);
            
            // Filter the options based on the current selection
            populateDropDownsWithFilteredData(filmID, directorID, actorID, filmYear, filmRating);
        }else{
            // By default put all the distinct names as options
            populateDropDownsWithOriginalData();
        }
    }
    
    //populate dropdown lists with ALL data retrieved
    private void populateDropDownsWithOriginalData(){
        try{
            Films films = mbl.getFilms();
            // Get all unique options
            directors = mbl.getDistinctDirectorsFromFilms(films);
            actors = mbl.getDistinctActorsFromFilms(films);
            sFilms = mbl.getDistinctSimplisticFilmsFromFilms(films);
            filmYears = mbl.getDistinctYearsFromFilms(films);
            filmRatings = mbl.getDistinctRatingsFromFilms(films);
        }catch(Exception e){
           e.printStackTrace();
        }
    }
    
    //populate dropdown based on filters currently in place
    private void populateDropDownsWithFilteredData(String filmID, String directorID, String actorID, String filmYear, String filmRating){
        Films films = mbl.getFilms(); 
        
        // Subset of films which satisfy the entered IDs
        Films tmp = mbl.getFilmsSubset(filmID, directorID, actorID, filmYear, filmRating, films);
        
        // If any of strings are null then get all otherwise filter based on the choices made
        actors = (actorID == null) ? mbl.getDistinctActorsFromFilms(tmp) : mbl.getDistinctActor(tmp, actorID);
        directors = (directorID == null) ? mbl.getDistinctDirectorsFromFilms(tmp) : mbl.getDistinctDirector(tmp, directorID);
        sFilms = (filmID == null) ? mbl.getDistinctSimplisticFilmsFromFilms(tmp) : tmp.getDistinctSimplisticFilm(filmID);
        filmYears = (filmYear == null) ? mbl.getDistinctYearsFromFilms(tmp) : mbl.getDistinctYear(tmp, filmYear);
        filmRatings = (filmRating == null) ? mbl.getDistinctRatingsFromFilms(tmp) : tmp.getDistinctFilmRating(filmID); 
        
        //display table of data once all values are selected
        if(sFilms.size() == 1 && directors.size() == 1 && actors.size() == 1 && filmYears.size() == 1){
            isAllSelected = true;
            // Put the first film in the list in
            this.populateFields(sFilms.get(0).filmID, directors.get(0).personID, actors.get(0).personID);
        }
    }
    
    //populate and return a list of data based on what the films list currently holds
    public List getFilms(){ return populateDropDownList(this.sFilms);}
    public List getDirectors(){ return populateDropDownList(this.directors); }
    public List getActors(){ return populateDropDownList(this.actors); }
    public List getFilmYears(){ return populateDropDownList(this.filmYears); }
    public List getFilmRatings(){ return populateDropDownList(this.filmRatings); }
    
    
    //http://ruleoftech.com/2012/jsf-1-2-and-getting-selected-value-from-dropdown
    public void filmValueChanged(ValueChangeEvent e){
        // Is this the initial request or a new one? 
        if(isPostback()){
            // Reload the table based on the new value
            selectedFilm = e.getNewValue().toString();
            this.load();
        }
    }
    
    public void directorValueChanged(ValueChangeEvent e){
        if(isPostback()){
            selectedDirector = e.getNewValue().toString();
            this.load();
        }
    }
    
    public void actorValueChanged(ValueChangeEvent e){
        if(isPostback()){
            selectedActor = e.getNewValue().toString();
            this.load();
        }
    }
    
    public void yearValueChanged(ValueChangeEvent e){
        if(isPostback()){
            selectedYear = e.getNewValue().toString();
            this.load();
        }
    }
    
    public void ratingValueChanged(ValueChangeEvent e){
        if(isPostback()){
            selectedRating = e.getNewValue().toString();
            this.load();
        }
    }
            
    //refresh the page to initial state
    public void reset() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    
    
    //check if status of page is postback
    public static boolean isPostback() {
       return FacesContext.getCurrentInstance().isPostback();
    }
    
    //+getters, +setters
    public String getSelectedFilm(){return this.selectedFilm;}
    public void setSelectedFilm(String si){this.selectedFilm = si;}
    public String getSelectedDirector(){return this.selectedDirector;}
    public void setSelectedDirector(String si){this.selectedDirector = si;}
    public String getSelectedActor(){return this.selectedActor;}
    public void setSelectedActor(String si){this.selectedActor = si;}
    public String getSelectedYear(){return this.selectedYear;}
    public void setSelectedYear(String si){this.selectedYear = si;}
    public String getSelectedRating(){return this.selectedRating;}
    public void setSelectedRating(String si){this.selectedRating = si;}
    public boolean getIsSubmitted(){return this.isSubmitted;}
    public void setIsSubmitted(Boolean isSubmitted){this.isSubmitted = isSubmitted;}
    public boolean getIsAllSelected(){return this.isAllSelected;}
    public void setIsAllSelected(Boolean isAllSelected){this.isAllSelected = isAllSelected;}
    
    
    //-------------------------------------------------
    //   Populating strings with selected film data
    //-------------------------------------------------
    private Film film;
    private Director director;
    private Actor actor;
    // Put the fields in the webpage for JSF
    public void populateFields(String filmID, String directorID, String actorID){
        this.film = mbl.getFilmFromSimplisticFilm(filmID);
        this.director = mbl.getDirectorFromSimplisticFilm(film, directorID);
        this.actor = mbl.getActorFromSimplisticFilm(film, actorID);
    }
    
    //JSF read access to fields
    // JSF will call these functions
    public Film getFilm(){return film;}
    public Director getDirector(){return director;}
    public Actor getActor(){return actor;}
    public String getFilmImdbLink() {return String.format(AppVariables.WebProperties.imdbFilmURL, film.filmID);}
    public String getDirectorImdbLink() {return String.format(AppVariables.WebProperties.imdbProfileURL, director.personID);}
    public String getActorImdbLink() {return String.format(AppVariables.WebProperties.imdbProfileURL, actor.personID);}
}