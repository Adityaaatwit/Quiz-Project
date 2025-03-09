package Quiz_project;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class TimeManager {
    private ScheduledExecutorService scheduler;
    private Timer timer;
    private boolean timeUp;
    
    public TimeManager() {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.timer = new Timer();
        this.timeUp = false;
    }
    
    public void startTimer(int seconds) {
        timeUp = false;
        System.out.println("You have " + seconds + " seconds to answer.");
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp = true;
                System.out.println("Time's up! Moving to the next question.");
            }
        }, seconds * 1000);
    }
    
    public void stopTimer() {
        timer.cancel();
        timer = new Timer(); // Reset the timer instance
    }
    
    public boolean isTimeUp() {
        return timeUp;
    }
}
