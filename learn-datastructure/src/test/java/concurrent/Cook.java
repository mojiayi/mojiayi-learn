package concurrent;

/**
 * Created by Administrator on 2017/1/3.
 */
public class Cook implements Runnable {
    private Restrant restrant;
    public Cook(Restrant restrant) {
        this.restrant = restrant;
    }

    @Override
    public void run() {
        try {
            while (true) {
                restrant.cook();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
