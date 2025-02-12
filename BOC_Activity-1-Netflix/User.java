import java.util.*;

public class User {
    private String name;
    private Map<String, Integer> genreWatchTime; // Genre -> Watch time
    private Map<Movie, Integer> watchedMovies;   // Movie -> Watch time

    public User(String name) {
        this.name = name;
        this.genreWatchTime = new HashMap<>();
        this.watchedMovies = new HashMap<>();
    }

    public void watchMovie(Movie movie, int minutes) {
        watchedMovies.put(movie, watchedMovies.getOrDefault(movie, 0) + minutes);
        genreWatchTime.put(movie.getGenre(), genreWatchTime.getOrDefault(movie.getGenre(), 0) + minutes);
    }

    public List<Map.Entry<Movie, Integer>> getRankedWatchHistory() {
        List<Map.Entry<Movie, Integer>> sortedMovies = new ArrayList<>(watchedMovies.entrySet());
        sortedMovies.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Sort descending by watch time
        return sortedMovies;
    }

    public String getMostWatchedGenre() {
        return genreWatchTime.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");
    }

    public Set<String> getWatchedMovieTitles() {
        Set<String> watchedTitles = new HashSet<>();
        for (Movie movie : watchedMovies.keySet()) {
            watchedTitles.add(movie.getTitle());
        }
        return watchedTitles;
    }

    public String getName() {
        return name;
    }
}
