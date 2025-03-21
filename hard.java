import java.util.*;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private static boolean[] seats = new boolean[TOTAL_SEATS];

    public synchronized boolean bookSeat(int seatNumber, String user) {
        if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
            System.out.println(user + " attempted to book an invalid seat.");
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(user + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(user + " attempted to book an already occupied seat " + seatNumber);
            return false;
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;
    private String user;

    public BookingThread(TicketBookingSystem system, int seatNumber, String user, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.user = user;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, user);
    }
}

public class TicketBookingDemo {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        List<Thread> threads = new ArrayList<>();
        
        // Creating VIP bookings (higher priority)
        threads.add(new BookingThread(system, 2, "VIP User 1", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, 4, "VIP User 2", Thread.MAX_PRIORITY));
        
        // Creating regular bookings (normal priority)
        threads.add(new BookingThread(system, 2, "Regular User 1", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(system, 4, "Regular User 2", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(system, 5, "Regular User 3", Thread.NORM_PRIORITY));

        // Starting threads
        for (Thread t : threads) {
            t.start();
        }

        // Waiting for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
