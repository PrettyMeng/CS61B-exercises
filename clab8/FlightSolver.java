import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    private Comparator<Flight> startComparator = (flight1, flight2) -> flight1.startTime - flight2.startTime;
    private Comparator<Flight> endComparator = (flight1, flight2) -> flight1.endTime - flight2.endTime;
    private PriorityQueue<Flight> start_queue;
    private PriorityQueue<Flight> end_queue;


    public FlightSolver(ArrayList<Flight> flights) {
        start_queue = new PriorityQueue<>(startComparator);
        end_queue = new PriorityQueue<>(endComparator);
        for (Flight flight : flights) {
            start_queue.add(flight);
            end_queue.add(flight);
        }
    }

    public int solve() {
        int cur_passengers = 0, max_passengers = -1;
        // no need to check end_queue, since the landning
        // will just decrease the number of passengers.
        while (start_queue.peek() != null) {
            Flight takeOffFlight = start_queue.peek();
            Flight landingFlight = end_queue.peek();
            int min_start = takeOffFlight.startTime;
            int min_end = landingFlight.endTime;
            if (min_start < min_end) {
                cur_passengers += takeOffFlight.passengers;
                if (cur_passengers > max_passengers) {
                    max_passengers = cur_passengers;
                }
                start_queue.remove(takeOffFlight);
            } else {
                cur_passengers -= landingFlight.passengers;
                end_queue.remove(landingFlight);
            }
        }

        return max_passengers;
    }

}
