import java.util.*;

class Project2HuffmanCoding {
    public static void main(String[] args) {
        int[] nValues = {600, 800, 1000, 1200, 1400, 1600, 1800, 2000}; // Different values of n

        for (int n : nValues) { // looping through the array nValues to get different values of n as input
            char[] charactersArray = new char[n];
            int[] charactersFrequency = new int[n];

            for (int i = 0; i < n; i++) {
                charactersArray[i] = (char) ('a' + i % 26);
                charactersFrequency[i] = (int) (Math.random() * 1000);
            }
            // creating a priority queue priorityQueue. This will make a min priority queue(min-heap).
            PriorityQueue<HuffmanNode> huffmanPriorityQueue
                    = new PriorityQueue<HuffmanNode>(
                    n, new HuffmanNodeComparator());

            long startTime = System.nanoTime(); // Starts the time calculation for the huffman algorithm in nanoSeconds
            for (int i = 0; i < n; i++) {
                // creating a Huffman node object and adding it to the priority queue.
                HuffmanNode huffmanNodeObject = new HuffmanNode();

                huffmanNodeObject.c = charactersArray[i];
                huffmanNodeObject.data = charactersFrequency[i];

                huffmanNodeObject.left = null;
                huffmanNodeObject.right = null;
                // add functions adds
                // the huffman node to the queue.
                huffmanPriorityQueue.add(huffmanNodeObject);
            }

            HuffmanNode root = null; // create a root node
            // Here we will extract the two minimum value from the heap each time until its size reduces to 1.
            while (huffmanPriorityQueue.size() > 1) {
                HuffmanNode x = huffmanPriorityQueue.peek(); // first min extract.
                huffmanPriorityQueue.poll();

                HuffmanNode y = huffmanPriorityQueue.peek(); // second min extract.
                huffmanPriorityQueue.poll();

                HuffmanNode f = new HuffmanNode();  // new node f which is equal

                f.data = x.data + y.data; // sum of the frequency of the two nodes and assigning values to the f node.
                f.c = '-';
                f.left = x;  // first extracted node as left child.
                f.right = y;  // second extracted node as the right child.
                root = f;  // marking the f node as the root node.
                huffmanPriorityQueue.add(f);   // add this node to the priority-queue.
            }
            long endTime = System.nanoTime(); // end the time calculation for the huffman algorithm in nanoSeconds
            System.out.println("for N = " + n + " elapsed time: " + (endTime - startTime)); // This will give the time that this algorithm takes to compute
        }
    }
}
// This HuffmanNode class is the basic structure of each node present in the Huffman tree.
class HuffmanNode {
    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}
// comparator class helps to compare the node on the basis of one of its attribute.
class HuffmanNodeComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y)
    {
        return x.data - y.data;
    }
}
