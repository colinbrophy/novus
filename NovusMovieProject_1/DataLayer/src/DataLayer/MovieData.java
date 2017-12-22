package DataLayer;

import ClassLayer.*;
import ApplicationVariables.AppVariables;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;


public class MovieData {
    
    String message = "";
    
    //read data from CSV file - path provided as param
    public Films getFilmData(String csvPath){
        Films films = new Films();
        String[] line;
        
        // Open the csv file
        try(CSVReader csv = new CSVReader(new FileReader(csvPath));){
            String[] headers = csv.readNext(); //read first line for header strings
           
            // Read all of the lines into the films object
            while((line = csv.readNext()) != null){
                films = storeLine(line, films);
            }
        }catch(IOException ex){
            // Called if file opening fails
            ex.printStackTrace();
        }
        
        return films;
    }
    
    
    
    private Films storeLine(String[] line, Films films){
        Films tmpFilms = films;
        
        // If any of the films are in the Films object match the film in the line
        if(tmpFilms.stream().anyMatch(item -> item.filmID.equals(line[AppVariables.filmID]))){
            // Get the first film that matches the ID
            Film tmpFilm = tmpFilms.stream().filter(item -> item.filmID.equals(line[AppVariables.filmID])).findFirst().get();//.collect(Collectors.toList()).get(0);

             // If any of the directors are in the Films object match the directors in the line
            if(tmpFilm.directors.stream().anyMatch(item -> item.getID().equals(line[AppVariables.directorID]))){

            }else{
                Director director = this.getDirectorFromData(line);
                tmpFilm.directors.add(director);
            }
            // If any of the actors are in the Films object match the actors in the line
            if(tmpFilm.actors.stream().anyMatch(item -> item.getID().equals(line[AppVariables.actorID]))){

            }else{
                // Add if not found
                Actor actor = this.getActorFromData(line);
                tmpFilm.actors.add(actor);
            }
        }else{
            // Add if not found
            Film film = this.getFilmFromData(line);
            tmpFilms.add(film);
        }
        
        return tmpFilms;
    }
    
    private Director getDirectorFromData(String[] line){
        // Create an Director object using the directorID and directorName from 
        // the lineString array
        Director director = new Director(line[AppVariables.directorID].trim(), 
                                         line[AppVariables.directorName].trim());
        return director;
    }
    
    private Actor getActorFromData(String[] line){
        // Create an Actor object using the actorID and actorName from
        // the line String array
        Actor actor = new Actor(line[AppVariables.actorID].trim(), 
                                line[AppVariables.actorName].trim());
        return actor;
    }
    
    private Film getFilmFromData(String[] line){
        
        Director director = this.getDirectorFromData(line);
        Actor actor = this.getActorFromData(line);
        
        // Create an Actor object using the filmID, filmName, imdbRating and 
        // filmYear from the line String array
        Film film = new Film(line[AppVariables.filmID].trim(),
                             line[AppVariables.filmName].trim(),
                             line[AppVariables.imdbRating].trim(),
                             line[AppVariables.filmYear].trim());
        film.directors.add(director);
        film.actors.add(actor);
        
        return film;
    }
    
   

    
    public String getResultMessage(){
        return message;
    }
}
