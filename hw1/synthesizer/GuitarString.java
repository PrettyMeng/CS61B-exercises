package synthesizer;
import java.util.ArrayList;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int) Math.round(SR / frequency));
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0.);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //       Make sure that your random numbers are different from each
        //       other.
        int capacity = buffer.capacity();
        for (int i = 0; i < capacity; i++) {
            buffer.dequeue();
        }
        ArrayList<Double> randomNumbers = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            double r = Math.random() - 0.5;
            // prevent r to be the same as previous generated random numbers
            while (randomNumbers.contains(r)) {
                r = Math.random() - 0.5;
            }
            buffer.enqueue(r);
            randomNumbers.add(r);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double firstItem = buffer.dequeue();
        double secondItem = buffer.peek();
        buffer.enqueue((firstItem + secondItem) / 2 * DECAY);

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
