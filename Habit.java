public class Habit {
    private final String name;
    private int streak;
    private boolean completedToday;

    public Habit(String name) {
        this.name = name;
        this.streak = 0;
        this.completedToday = false;
    }

    public String getName() { return name; }
    public int getStreak() { return streak; }
    public boolean isCompletedToday() { return completedToday; }

    public void markComplete() {
        if (!completedToday) {
            streak++;
            completedToday = true;
        }
    }

    public void resetDaily() {
        completedToday = false;
    }
}