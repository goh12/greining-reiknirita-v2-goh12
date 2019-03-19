package v2;

import java.util.Random;

/**
 *
 * @author gudmu
 */
public class V2 {
    private static int arraySize = 5000;
    
    public static int[] createArray() {
        int[] arr = new int[arraySize];
        
        int j = 0;
        for (int i = 1; i<arraySize*2; i=i+2) {
            arr[j] = i;
            j++;
        }
        
        return arr;
    }
    
    private static int[] shuffleArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        
        return array;
    }
    
    /**
     * stokkar n/10 sinnum
     * @param array
     * @return 
     */
    private static int[] shuffleArrayAlmostSorted(int[] array) {
        int index, temp;
        Random random = new Random();
        
        // for lykkjan framkvæmir n/10 swap
        for (int i = (array.length - 1)/5; i > 0; i--) {
            index = random.nextInt(array.length - 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        
        return array;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // búum til fylki með 5000 stökum
        int[] arr = createArray();
        
        int[] shuffledArr = shuffleArray(arr);       
        // gagnagrind búin til og innsetning fylkis
        long start = System.currentTimeMillis();
        BST<Integer, Integer> bst = new BST<>();
        for (int i = 0; i<arraySize; i++) {
            bst.insert(shuffledArr[i], shuffledArr[i]);
        }
        long time = System.currentTimeMillis() - start;
        
        System.out.println("Tvíleitartré: Stokkað fylki, smíði og innsetning á 5000 stökum tók:" + time + " ms.");
        
        // nýtt tvíleitartré
        int[] shuffledArrAlmostSorted = shuffleArrayAlmostSorted(arr);

        start = System.currentTimeMillis();
        // smíði og innsetning fylkis
        BST<Integer, Integer> bst1 = new BST<>();

        for (int i = 0; i<arraySize; i++) {
            bst1.insert(shuffledArrAlmostSorted[i], shuffledArrAlmostSorted[i]);
        }        
        time = System.currentTimeMillis() - start;
        System.out.println("Tvíleitartré: Næstum raðað fylki, 10% stokkað. Smíði og innsetning á 5000 stökum tók: " + time + " ms.");
                
        
        // nýtt treap
        // gagnagrind búin til og innsetning fylkis
        start = System.currentTimeMillis();
        Treap<Integer, Integer> treap = new Treap<>();

        for (int i = 0; i<arraySize; i++) {
            treap.insert(shuffledArr[i], shuffledArr[i]);
        }
        time = System.currentTimeMillis() - start;
        
        System.out.println("Treap: Stokkað fylki, smíði og innsetning á 5000 stökum tók:" + time + " ms.");
        

        // nýtt treap
        // smíði og innsetning fylkis
        start = System.currentTimeMillis();
        Treap<Integer, Integer> treap1 = new Treap<>();


        for (int i = 0; i<arraySize; i++) {
            treap1.insert(shuffledArrAlmostSorted[i], shuffledArrAlmostSorted[i]);
        }        
        time = System.currentTimeMillis() - start;
        System.out.println("Treap: Næstum raðað fylki, 10% stokkað. Smíði og innsetning á 5000 stökum tók: " + time + " ms.");

                
    }
    
}
