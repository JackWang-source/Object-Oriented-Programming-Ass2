import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Ride implements RideInterface {
    private String rideName;
    private int heightRequirement;
    private Employee operator;
    private Queue<Visitor> queue;
    private LinkedList<Visitor> rideHistory;
    private int maxRider;
    private int numOfCycles;

    public Ride(String rideName, int heightRequirement, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.heightRequirement = heightRequirement;
        this.operator = operator;
        this.maxRider = maxRider;
        this.queue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    @Override
    public void addVisitorToQueue(Visitor visitor) {
        queue.offer(visitor);
        System.out.println(visitor.getName() + " has been added to the queue.");
    }
    @Override
    public void removeVisitorFromQueue(Visitor visitor) {
        queue.remove(visitor);
        System.out.println(visitor.getName() + " has been removed from the queue.");
    }

    @Override
    public void printQueue() {
        System.out.println("Visitors in queue:");
        queue.forEach(visitor -> System.out.println(visitor.getName()));
    }

    @Override
    public void runOneCycle() {
        if (operator == null) {
            System.out.println("Ride cannot operate without an operator.");
            return;
        }
        if (queue.isEmpty()) {
            System.out.println("No visitors in the queue to run the ride.");
            return;
        }

        for (int i = 0; i < maxRider && !queue.isEmpty(); i++) {
            Visitor visitor = queue.poll();
            addVisitorToHistory(visitor);
        }
        numOfCycles++;
        System.out.println("Ride cycle completed. Total cycles: " + numOfCycles);
    }

    @Override
    public void addVisitorToHistory(Visitor visitor) {
        rideHistory.add(visitor);
        System.out.println(visitor.getName() + " has been added to the ride history.");
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        return rideHistory.contains(visitor);
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        System.out.println("Visitors in ride history:");
        for (Visitor visitor : rideHistory) {
            System.out.println(visitor.getName());
        }
    }

    @Override
    public void exportRideHistory() {
        try (FileWriter writer = new FileWriter("ride_history.csv")) {
            for (Visitor visitor : rideHistory) {
                writer.write(visitor.getName() + "," + visitor.getAge() + "," + visitor.getId() + "\n");
            }
            System.out.println("Ride history has been exported to ride_history.csv");
        } catch (IOException e) {
            System.out.println("An error occurred while exporting ride history.");
            e.printStackTrace();
        }
    }

    @Override
    public void importRideHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("ride_history.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] visitorInfo = line.split(",");
                String name = visitorInfo[0];
                int age = Integer.parseInt(visitorInfo[1]);
                String id = visitorInfo[2];
                Visitor visitor = new Visitor(name, age, id, "Unknown", "Unknown");
                addVisitorToHistory(visitor);
            }
            System.out.println("Ride history has been imported from ride_history.csv");
        } catch (IOException e) {
            System.out.println("An error occurred while importing ride history.");
            e.printStackTrace();
        }
    }
    @Override
    public void sortRideHistory() {
        Collections.sort(rideHistory, new VisitorComparator());
    }


}