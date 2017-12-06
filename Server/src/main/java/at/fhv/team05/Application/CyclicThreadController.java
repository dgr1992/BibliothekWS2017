package at.fhv.team05.Application;

import at.fhv.team05.Utility.StoppableThread;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CyclicThreadController {

    private static CyclicThreadController mInstance;
    private HashMap<String, StoppableThread> _threadHashMap;
    private ControllerFacade _controllerFacade;

    public CyclicThreadController() {
        _threadHashMap = new HashMap<>();
        _controllerFacade = ControllerFacade.getInstance();
    }

    public static CyclicThreadController getInstance() {
        if (mInstance == null) {
            mInstance = new CyclicThreadController();
        }
        return mInstance;
    }

    public void addThread(String name, StoppableThread thread) {
        _threadHashMap.put(name, thread);
    }

    public void deleteThread(String name) {
        StoppableThread thread = _threadHashMap.remove(name);
        thread.setRunning(false);
    }

    public void startThread(String name) {
        Thread thread = _threadHashMap.get(name);
        if (!thread.isAlive()) {
            thread.start();
        }
    }

    public void stopThread(String name) {
        StoppableThread thread = _threadHashMap.get(name);
        if (!thread.isAlive()) {
            thread.setRunning(false);
        }
    }

    public List<String> getRunningThreads() {
        ArrayList<String> list = new ArrayList<>();
        _threadHashMap.keySet().stream().filter(str -> _threadHashMap.get(str).isAlive()).forEach(list::add);
        return list;
    }

    /**
     * Creates the request for payment thread, automatically starts it and saves it in the hashMap.
     *
     * @return the created thread
     */
    public StoppableThread createRequestForPaymentThread() {
        StoppableThread thread = new StoppableThread() {
            @Override
            public void runThread() {
                try {
                    _controllerFacade.checkRentals();
                    ZoneId z = ZoneId.systemDefault();
                    ZonedDateTime now = ZonedDateTime.now(z);
                    LocalDate tomorrow = now.toLocalDate().plusDays(1);
                    ZonedDateTime tomorrowStart = tomorrow.atStartOfDay(z);

                    long timeUntilMidnight = Duration.between(now, tomorrowStart).toMillis();

                    System.out.println("Sleeping for: " + timeUntilMidnight + " ms.(" + timeUntilMidnight / 1000 / 60 + " min)");
                    //Thread sleeps until next midnight
                    sleep(timeUntilMidnight);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        addThread("requestForPaymentThread", thread);
        return thread;
    }
}
