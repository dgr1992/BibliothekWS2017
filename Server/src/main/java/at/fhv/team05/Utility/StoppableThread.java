package at.fhv.team05.Utility;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StoppableThread extends Thread {
    protected boolean _running;

    public StoppableThread() {
        _running = true;
    }

    public boolean isRunning() {
        return _running;
    }

    public void setRunning(boolean running) {
        _running = running;
    }

    @Override
    public void run() {
        _running = true;
        while (_running) {
            runThread();
        }
    }

    protected void runThread() {
        throw new NotImplementedException();
    }
}
