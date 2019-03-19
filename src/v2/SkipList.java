package v2;

/**
 * Hnútar í SkipList geta innihaldið fleiri en einn pointer
 * því þeir geta verið partur í fleiri en einum lista
 * @author gudmu
 * @param <Key>
 * @param <Value>
 */
public class SkipList<Key extends Comparable<Key>, Value>  {
    
    private Node header; // haus listans
    private Node bottom = null;
    private Node tail = null;
    private int infinity;
    
    // útfærum tréð þ.a. það geymi vinstri og hægri hnút
    // þeir verða þá vinsta og hægra hluttré
    private class Node {
        private final Key key;
        private Value value;
        private Node right; // næsti hnútur til hægri
        private Node down; // næsti hnútur fyrir neðan
        
        public Node(Key key, Value value) {
            this(key, value, null, null);
        }
        
        public Node(Key key, Value value, Node right, Node down) {
            this.key = key;
            this.value = value;
            this.right = right;
            this.down = down;
        }
    }
    
    public SkipList(int inf) {
        this.infinity = inf;
        //bottom = new Node(0, 0);
                ;
        bottom.right = bottom.down = bottom;
    }
    
}
