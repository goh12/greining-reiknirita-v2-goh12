/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2;

/**
 *
 * @author gudmu
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {
    
    private Node root; // rót tvíleitartrésins
    
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
        
        // köllum á aðferð sem sér um að bæta nýjum hnút í tréð
        root = insert(root, key, value);
    }
    
    /**
     * aðferðin uppfærir tréð og skilar því
     * Því verður skilað í root breytu klasans
     * @param root
     * @param key
     * @param value
     * @return 
     */
    private Node insert(Node curr, Key key, Value value) {
        // ef núverandi hnútur er tómur, búum til nýjan
        if (curr == null) {
            return new Node(key, value);
        }
        
         /**
         * Notum compareTo, þar gildir:
         * if s1 > s2, it returns positive number 
         * if s1 < s2, it returns negative number
         * if s1 == s2, it returns 0 
         */
        int compare = key.compareTo(curr.key);
        
        // ferðumst niður hægra hluttré
        if (compare > 0) {
            curr.right = insert(curr.right, key, value);
        }
        // ferðumst niður vinstra hluttré
        else if (compare < 0) {
            curr.left = insert(curr.left, key, value);
        } else {
            curr.value = value;
        }
        
        return curr;
    }
    
    /**
     * skilar gildi bakvið lykil ef hann er í trénu
     * @param key
     * @return 
     */
    public Value search(Key key) {
        return search(this.root, key);
    }
    
    private Value search(Node curr, Key key) {
        if (curr == null) {
            return null; // erum komin út á enda, ekkert gildi fundið
        }
        
        int compare = key.compareTo(curr.key);
        
        if (compare > 0) {
            // ferðumst niður hægra hluttré, höldum leit áfram
            return search(curr.right, key);
        } else if (compare < 0) {
            // ferðumst niður vinstra hluttré, höldum leit áfram
            return search(curr.left, key);
        } else {
            return curr.value; // gildi fundið
        }
    }
}
