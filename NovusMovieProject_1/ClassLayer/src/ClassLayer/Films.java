package ClassLayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Films extends ArrayList<Film>{
    
    public Films() { }

    public Films(List<Film> films){
        this.addAll(films);
    }
    
    
    public Films getFilmsFilteredSubset(String filmID, String directorID, String actorID, String filmYear, String filmRating){
        Films tmpFilms = new Films();
        // Add all the films to tmpFilms where it equals the passed parameter or
        // any if passed in null.
        // For directors and actors return any film which has any of the films 
        // or directors.
        // Then we sort the list.
        tmpFilms.addAll(this.stream().filter(f -> f.filmID.equals((filmID == null) ? f.filmID : filmID)) 
                                     .filter(f -> f.filmYear.equals((filmYear == null) ? f.filmYear : filmYear))
                                     .filter(f -> f.imdbRating.equals((filmRating == null) ? f.imdbRating : filmRating))
                                     .filter(f -> f.directors.stream().anyMatch(p -> p.getID().equals((directorID == null) ? p.getID() : directorID)))
                                     .filter(f -> f.actors.stream().anyMatch(p -> p.getID().equals((actorID == null) ? p.getID() : actorID)))
                                     .sorted(Comparator.comparing(f -> f.getFilmName()))
                                     .collect(Collectors.toList()));
        return tmpFilms;
    }
    
    
    public List<SimplisticFilm> toListSimplisticFilm(){
        // Create a sorted list of all the films of the SimplisticFilm type
        return this.stream().sorted(Comparator.comparing(fi -> fi.getFilmName()))
                            .collect(Collectors.toList());
        
    }
    
    public List<SimplisticFilm> getDistinctSimplisticFilm(String filmID){
        // Create a list of all the films which have the id of filmID,
        // of the SimplisticFilm type.
        return this.stream().filter(f -> f.filmID.equals(filmID))
                            .collect(Collectors.toList());
    }
    
    
    public List<Director> toListDistinctDirector(){
        List <Director> tmpList = new ArrayList();
         
        // Adds all directors to tmpList which are not already in the list
        this.stream().flatMap(film -> film.directors.stream()
                    .filter(dir -> tmpList.stream()
                            .noneMatch(di -> di.getID().equals(dir.getID())))
                    .map(nDir -> tmpList.add(nDir)))
                    .collect(Collectors.toList());

        // Sorts Directors by name
        tmpList.sort(Comparator.comparing(c -> c.getName()));

        return tmpList;   
    }
    
    public List<Director> getDistinctDirector(String directorID){
        List <Director> tmpList = new ArrayList();

        // Adds all directors to tmpList which are not already in the list and
        // finds and filters all the directors with ID equal to directorID
        this.stream().flatMap(film -> film.directors.stream()
                    .filter(dir -> tmpList.stream()
                            .noneMatch(di -> di.getID().equals(dir.getID())) && dir.getID().equals(directorID))
                    .map(nDir -> tmpList.add(nDir)))
                    .collect(Collectors.toList());

        return tmpList;  
    }
    
    
    public List<Actor> toListDistinctActor(){
        List <Actor> tmpList = new ArrayList();
            
        // Adds all actors to tmpList which are not already in the list
        this.stream().flatMap(film -> film.actors.stream()
                    .filter(act -> tmpList.stream()
                            .noneMatch(ac -> ac.getID().equals(act.getID())))
                    .map(nAct -> tmpList.add(nAct)))
                    .collect(Collectors.toList());

        // Sort the list by name
        tmpList.sort(Comparator.comparing(c -> c.getName()));

        return tmpList;   
    }
    
    public List<Actor> getDistinctActor(String actorID){
         List <Actor> tmpList = new ArrayList();
        // Adds all actors to tmpList which are not already in the list and
        // finds and filters all the directors with ID equal to directorID
        this.stream().flatMap(film -> film.actors.stream()
                    .filter(act -> tmpList.stream()
                            .noneMatch(ac -> ac.getID().equals(act.getID())) && act.getID().equals(actorID))
                    .map(nAct -> tmpList.add(nAct)))
                    .collect(Collectors.toList());

        return tmpList;  
    }
    
    
    public List<String> toListDistinctYear(){
        List <String> tmpList = new ArrayList();
        // Adds all years to to tmpList in flims without duplicates
        this.stream()
                .filter(f -> tmpList.stream().noneMatch(y -> y.equals(f.filmYear)))
                .map(f -> tmpList.add(f.filmYear))
                .collect(Collectors.toList());
        
        // Sort films
        Collections.sort(tmpList);
                
        return tmpList;  
    }
    
    public List<String> getDistinctYear(String year){
        List <String> tmpList = new ArrayList();
        
        this.stream()
                .filter(f -> tmpList.stream().noneMatch(y -> y.equals(f.filmYear) && f.filmYear.equals(year)))
                .map(f -> tmpList.add(f.filmYear))
                .collect(Collectors.toList());
        
        Collections.sort(tmpList);
                
        return tmpList;   
    }
    
    
    public List<String> toListDistinctFilmRatings(){
        List <String> tmpList = new ArrayList();

         this.stream()
                 .filter(f -> tmpList.stream().noneMatch(y -> y.equals(f.imdbRating)))
                 .map(f -> tmpList.add(f.imdbRating))
                 .collect(Collectors.toList());

         Collections.sort(tmpList);

         return tmpList;
    }
    
    public List<String> getDistinctFilmRating(String imdbRating){
        List <String> tmpList = new ArrayList();
        
        this.stream()
                .filter(f -> tmpList.stream().noneMatch(y -> y.equals(f.imdbRating) && f.imdbRating.equals(imdbRating)))
                .map(f -> tmpList.add(f.imdbRating))
                .collect(Collectors.toList());
        
        Collections.sort(tmpList);
                
        return tmpList;
    }
}
