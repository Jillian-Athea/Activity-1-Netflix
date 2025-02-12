import java.util.*;

public class StreamingService {
    private List<Movie> movies;
    private EventBus eventBus;

    public StreamingService(EventBus eventBus) {
        this.eventBus = eventBus;
        this.movies = new ArrayList<>();
        generateMovies(); 
    }

    private void generateMovies() {
        movies.add(new Movie("Inception", "Sci-Fi"));
        movies.add(new Movie("Avengers", "Action"));
        movies.add(new Movie("Titanic", "Romance"));
        movies.add(new Movie("Interstellar", "Sci-Fi"));
        movies.add(new Movie("The Notebook", "Romance"));
        movies.add(new Movie("The Dark Knight", "Action"));
        movies.add(new Movie("Forrest Gump", "Drama"));
        movies.add(new Movie("Parasite", "Thriller"));
        movies.add(new Movie("The Conjuring", "Horror"));
        movies.add(new Movie("Coco", "Animation"));
        movies.add(new Movie("The Godfather", "Crime"));
        movies.add(new Movie("Joker", "Drama"));
        movies.add(new Movie("John Wick", "Action"));
        movies.add(new Movie("Frozen", "Animation"));
        movies.add(new Movie("A Quiet Place", "Horror"));
        movies.add(new Movie("The Matrix", "Sci-Fi"));
        movies.add(new Movie("Shrek", "Animation"));
        movies.add(new Movie("Black Panther", "Action"));
        movies.add(new Movie("La La Land", "Musical"));
        movies.add(new Movie("The Grand Budapest Hotel", "Comedy"));
    }

    public void displayMovies() {
        Collections.shuffle(movies); 

        System.out.println("\nAvailable Movies:");
        for (Movie movie : movies) {
            System.out.println("- " + movie.getTitle() + " (" + movie.getGenre() + ")");
        }
    }

    public void watchMovie(User user, String title, int minutes) {
        title = title.trim().toLowerCase();
        List<String> suggestions = new ArrayList<>();
    
        for (Movie movie : movies) {
            String storedTitle = movie.getTitle().trim().toLowerCase();
            
            if (storedTitle.contains(title)) { 
                user.watchMovie(movie, minutes);
                System.out.println("\nYou watched " + movie.getTitle() + " for " + minutes + " minutes.");
                return;
            }
    
            
            if (storedTitle.startsWith(title.substring(0, Math.min(3, title.length())))) {
                suggestions.add(movie.getTitle());
            }
        }
    
       
        System.out.println("\nMovie not found! Did you mean:");
        for (String suggestion : suggestions) {
            System.out.println("- " + suggestion);
        }
    }
    
    public List<Movie> getMoviesByGenre(String genre, Set<String> watchedMovies) {
        List<Movie> recommendations = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre) && !watchedMovies.contains(movie.getTitle())) {
                recommendations.add(movie);
            }
        }
        Collections.shuffle(recommendations);
        return recommendations.subList(0, Math.min(3, recommendations.size())); 
    }
}
