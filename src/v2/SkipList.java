package v2;

/**
 * 
 * @author Guðmundur Óskar Halldórsson
 * @param <Key>
 * @param <Value> 
 */
public class SkipList<Key extends Comparable<Key>, Value> {

    private Node head;
    private int maxLevel;
    private int numOfElements;

    // Nested class sem táknar hnút í gagnagrindinni
    private class Node {

        private final Key key;
        private Value value;
        private final int level;
        private Node right; // hægri hnútur í núverandi level
        private Node down; // bendir á næsta level fyrir neðan

        public Node(Key key, Value value, int level, Node right, Node down) {
            this.key = key;
            this.value = value;
            this.level = level;
            this.right = right;
            this.down = down;
        }
    }

    /**
     * reiknar hæstu hæð hnútar fer ekki hærra en maxLevel
     * Notar coinFlip = 50% líkur á nýrri hæð
     */
    private int level() {
        int level = 0;
        while (level <= maxLevel && coinflip()) {
            level++;
        }

        return level;
    }

    public SkipList() {
        this.head = new Node(null, null, 0, null, null);
        this.maxLevel = 0;
        this.numOfElements = 0;
    }

    /**
     * bætir nýjum hnút í listann
     *
     * @param key
     * @param value
     */
    public void insert(Key key, Value value) {
        int newNodeLevel = level();

        /**
         * ef hæsta level hnútsins sem við ætlum að búa til er hærra heldur en
         * head (fremsti hnúturinn) færum við head upp á það level. Head geymir
         * engin gögn. Látum head benda niður á head í hæðinni fyrir neðan sig.
         * Mesta lagi 1 leveli hærra en head var svo það virkar
         */
        if (newNodeLevel > head.level) {
            head = new Node(null, null, newNodeLevel, null, head);
        }

        Node current = head; // lefmost node, highest level
        Node last = null;

        /**
         * svipuð lykkja og í search, við leitum að gildinu í listanum ef það er
         * ekki bætum við því í listann
         */
        while (current != null) {
            // úti á enda, eða næsti lykill er stærri en núv. lykill
            if (current.right == null || current.right.key.compareTo(key) > 0) {

                if (newNodeLevel >= current.level) {
                    // búum til nýjan hnút
                    Node newNode = new Node(key, value, current.level, current.right, null);

                    // ef ekki á neðsta leveli bætum nýja hnút líka 
                    // fyrir neðan
                    if (last != null) {
                        last.down = newNode;
                    }

                    // bætt við í keðjuna
                    current.right = newNode;
                    last = newNode;
                }

                // ferðumst niður í listanum
                current = current.down;
                continue;
            } else if (current.right.key.compareTo(key) == 0) {
                current.right.value = value; // lykill er í lista, uppfærum gildið
                return;
            }

            // ferðumst annars til hægri á núv. leveli
            current = current.right;
        }

        this.numOfElements++;
        // setjum þak á fjölda leyfilegra level-a
        // stækkum max levels ef (int)log_2(n) > maxLevels
        if (log(this.numOfElements, 2) > this.maxLevel) {
            this.maxLevel++;
        }
    }

    /**
     * scan through each level as far as we can without passing the target value
     * key, and then proceed down to the next level. The search ends when we
     * either reach a node with search key key or fail to find key on the lowest
     * level.
     *
     * @param key
     * @return 
     */
    public Value search(Key key) {
        Node current = head; // leftmost node, highest level

        while (current != null) {

            /**
             * ef úti á enda (right = null) eða lykillinn til hægri er stærri en
             * lykillinn sem er leitað að. Færum okkur þá niður
             */
            if (current.right == null || current.right.key.compareTo(key) > 0) {
                current = current.down;
                continue; // aftur efst í lykkju
            } // ef lykill er sá sami og lykill til hægri, gildi fundið
            else if (current.right.key.compareTo(key) == 0) {
                return current.right.value;
            }

            // ferðumst annars til hægri á núv. leveli
            current = current.right;
        }

        return null; // ekki í lista
    }

    /**
     * hermir peningakast 50% líkur á að búa til nýtt level í listanum
     */
    private boolean coinflip() {
        double rand = Math.random();
        return rand < 0.5;
    }
    
    /**
     * reiknar logarithma og kastar í int gildi
     */
    private int log(int x, int base) {
        return (int) (Math.log(x) / Math.log(base));
    }
}

