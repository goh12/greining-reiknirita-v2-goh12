package v2;

/**
 * 
 * @author Guðmundur Óskar Halldórsson
 * @param <Key>
 * @param <Value> 
 */
public class BST<Key extends Comparable<Key>, Value> {
    
    private Node root; // rót tvíleitartrésins
    
    // Nested class sem táknar hnút í gagnagrindinni
    // útfærum tréð þ.a. það geymi vinstri og hægri hnút
    // þeir verða þá vinsta og hægra hluttré
    private class Node {
        private final Key key;
        private Value value;
        private Node left; // vinstra hluttré
        private Node right; // hægra hluttré
        
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
    
    // tómur smiður, býr til nýtt tvíleitartré
    public BST() { }
    
    /**
     * innsetningaraðferð inn í gagnagrindina
     * @param key
     * @param value 
     */
    public void insert(Key key, Value value) {
        if (key == null) {
            System.out.println("verður að vera lykill");
            return;
        }
        
        // köllum á aðferð sem sér um að bæta nýjum hnút í tréð, byrjum í rót
        this.root = insert(this.root, key, value);
    }
    
    /**
     * aðferðin uppfærir tréð og skilar nýja hnútinum
     * Því verður skilað í root breytu klasans
     * @param root
     * @param key
     * @param value
     * @return 
     */
    private Node insert(Node current, Key key, Value value) {
        // ef núverandi hnútur er tómur, búum til nýjan
        if (current == null) {
            return new Node(key, value);
        }
        
         /**
         * Notum compareTo, þar gildir:
         * if s1 > s2, it returns positive number 
         * if s1 < s2, it returns negative number
         * if s1 == s2, it returns 0 
         */
        int compare = key.compareTo(current.key);
        
        // ferðumst niður hægra hluttré
        if (compare > 0) {
            current.right = insert(current.right, key, value);
        }
        // ferðumst niður vinstra hluttré
        else if (compare < 0) {
            current.left = insert(current.left, key, value);
        } else {
            current.value = value; // gildi er nú þegar í tré
        }
        
        return current;
    }
    
    /**
     * skilar gildi bakvið lykil ef hann er í trénu
     * @param key
     * @return 
     */
    public Value search(Key key) {
        return search(this.root, key);
    }
    
    private Value search(Node current, Key key) {
        if (current == null) {
            return null; // erum komin út á enda, ekkert gildi fundið
        }
        
        int compare = key.compareTo(current.key);
        
        if (compare > 0) {
            // ferðumst niður hægra hluttré, höldum leit áfram
            return search(current.right, key);
        } else if (compare < 0) {
            // ferðumst niður vinstra hluttré, höldum leit áfram
            return search(current.left, key);
        } else {
            return current.value; // gildi fundið
        }
    }
    
    
    /**
     * aðferð sem prentar út tréð í stærðarröð.
     * Nota þetta til að athuga hvort innsetning hafi
     * heppnast rétt
     * @param node 
     */
    private void printNode(Node node) {
        if (node == null) {
            return;
        }
        
        printNode(node.left);
        System.out.println(node.key);
        printNode(node.right);
    }
    
    public void printNode() {
        this.printNode(this.root);
    }
}
