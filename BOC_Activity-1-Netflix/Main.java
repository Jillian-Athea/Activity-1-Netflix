import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventBus eventBus = new EventBus();
        StreamingService streamingService = new StreamingService(eventBus);

        System.out.println("");
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName);

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Watch a Movie");
            System.out.println("2. View Watch History & Get Recommendations");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                streamingService.displayMovies();
                System.out.print("\nEnter movie name to watch: ");
                String movieTitle = scanner.nextLine();
                System.out.print("Enter minutes watched: ");
                int minutesWatched = scanner.nextInt();
                scanner.nextLine(); 
            
                streamingService.watchMovie(user, movieTitle, minutesWatched);
           
            }else if (choice == 2) {
                System.out.println("");
                System.out.println(userName+"'s"+"\nRanked Watch History:");
                List<Map.Entry<Movie, Integer>> rankedMovies = user.getRankedWatchHistory();

                if (rankedMovies.isEmpty()) {
                    System.out.println("No movies watched yet!");
                } else {
                    int rank = 1;
                    for (Map.Entry<Movie, Integer> entry : rankedMovies) {
                        System.out.println(rank + ". " + entry.getKey().getTitle() +
                                " (" + entry.getKey().getGenre() + ") - " +
                                entry.getValue() + " mins watched");
                        rank++;
                    }

                   
                    String preferredGenre = user.getMostWatchedGenre();
                    System.out.println("\nYour Preferred Genre: " + preferredGenre);

                    
                    System.out.println("\n[RECOMMENDATION] You might like:");
                    List<Movie> recommendations = streamingService.getMoviesByGenre(preferredGenre, user.getWatchedMovieTitles());
                    
                    if (recommendations.isEmpty()) {
                        System.out.println("No new recommendations available in this genre.");
                    } else {
                        for (Movie movie : recommendations) {
                            System.out.println("- " + movie.getTitle() + " (" + movie.getGenre() + ")");
                        }
                    }
                }

            } else if (choice == 3) {
                System.out.println("Exiting... Thank you for using the Netflix-like System!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
