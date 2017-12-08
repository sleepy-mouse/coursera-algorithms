import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String... args) {
        final int k = Integer.valueOf(args[0]);
        String[] tokens = StdIn.readString().split("\\s");
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        for (String token : tokens) {
            randomizedQueue.enqueue(token);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.sample());
        }
    }
}
