package concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Administrator on 2017/1/3.
 */
public class Restrant {
    public ArrayBlockingQueue<String> dishList = new ArrayBlockingQueue<String>(10);

    public static void main(String[] args) {
        Restrant restrant = new Restrant();

        Thread customer = new Thread(new Customer(restrant), "customer01");
        Thread customer2 = new Thread(new Customer(restrant), "customer02");
        Thread cook = new Thread(new Cook(restrant));
        customer.start();
        customer2.start();
        cook.start();
    }

    public synchronized void cook() throws InterruptedException {
//        synchronized (dishList) {
            if (dishList.size() > 0) {
                for (String dish : dishList) {
                    System.out.println("cook dish " + dish);
                }
                dishList.clear();
                notify();
            } else {
                wait(3000);
            }
//        }
    }

    public synchronized void order(String customer) throws InterruptedException {
//         (dishList) {
            if (dishList.size() == 0) {
                String order = null;
                for (int index = 0; index < 3;index++) {
                    order = customer + "order " + Double.valueOf(Math.random() * 100).intValue();
                    dishList.add(order);
                    System.out.println(" order dish " + order);
                }
                notify();
            } else {
                wait(3000);
            }
//        }
    }
}
