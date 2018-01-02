package ApplicationVariables;


public class AppVariables {
    
    // Enum of keys for the data
    public static int filmID = 0;
    public static int filmName = 1;
    public static int imdbRating = 2;
    public static int filmYear = 7;
    public static int directorID = 3;
    public static int directorName = 4;
    public static int actorID = 5;
    public static int actorName = 6;
        
    public static class CSV{    
        // Paths to the data of the movies
        public static String FILE_PATH = "/home/colin/code/novus/NovusMovieProject_1/Data/TestData.csv";
        public static String EXTENDED_FILE_PATH = "/home/colin/code/novus/NovusMovieProject_1/Data/ExtendedTestData.csv";
    } 
      
    public static class WebProperties{
        // Strings used for the links to actors and films on IMDB
        public static String imdbFilmURL = "http://www.imdb.com/title/tt%s";
        public static String imdbProfileURL = "http://www.imdb.com/name/nm%s";
        
        // Stores the displayed string for the inital dropdown option
        public static final String dropDownDefault = "--SELECT--";
        
        // Years in which the film are from, currently not in use
        public static String yearMin = "2000";
        public static String yearMax = "2017";
    }   
}
