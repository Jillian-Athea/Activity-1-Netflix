public class Movie {
    private String title;
    private String genre;
    private int watchTime; 

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.watchTime = 0;
    }

    public void addWatchTime(int minutes) {
        this.watchTime += minutes;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getWatchTime() {
        return watchTime;
    }
}