package concurrent;



/**
 * Created by Administrator on 2017/1/3.
 */
public class Customer implements Runnable {
    private Restrant restrant;
    public Customer(Restrant restrant) {
        this.restrant = restrant;
    }

    @Override
    public void run() {
        try {
            while (true) {
                restrant.order(Thread.currentThread().getName());
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
