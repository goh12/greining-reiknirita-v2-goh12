package v2;

import java.util.Random;

/**
 *
 * @author Guðmundur Óskar Halldórsson
 */
public class V2 {
    private static final int arraySize = 5000;
    private static final int searchMultiplier = 5000;
    private static long start;
    private static long time;
    
    public static int[] createArray() {
        int[] arr = new int[arraySize];
        
        int j = 0;
        for (int i = 1; i<arraySize*2; i=i+2) {
            arr[j] = i;
            j++;
        }
        
        return arr;
    }
    
    /**
     * stokkar öllu fylkinu úr inntakinu og skilar því
     * @param array
     * @return 
     */
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
        for (int i = (array.length)/10; i > 0; i--) {
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
        
        /**
         * a-liður, innsetning í gagnagrindur
         * 5000 stök, stokkuð og 10% stokkuð
         */
        
        // búum til fylki með 5000 stökum
        int[] arr = createArray();
        // búum til stokkað fylki
        int[] shuffledArr = shuffleArray(arr);
        // búum til 10% stokkað fylki, næstum raðað
        int[] shuffledArrAlmostSorted = shuffleArrayAlmostSorted(arr);
     
        System.out.println("============ Innsetning ===============");
        
        // gagnagrind búin til og innsetning fylkis
        start = System.currentTimeMillis();
        BST<Integer, Integer> bst = new BST<>();
        for (int i = 0; i<arraySize; i++) {
            bst.insert(shuffledArr[i], shuffledArr[i]);
        }
        time = System.currentTimeMillis() - start;
        System.out.println("Tvíleitartré - Stokkað fylki, smíði og innsetning á " + arraySize + " stökum tók: " + time + " ms.");
        

        start = System.currentTimeMillis();
        // smíði og innsetning fylkis
        BST<Integer, Integer> bst1 = new BST<>();

        for (int i = 0; i<arraySize; i++) {
            bst1.insert(shuffledArrAlmostSorted[i], shuffledArrAlmostSorted[i]);
        }        
        time = System.currentTimeMillis() - start;
        System.out.println("Tvíleitartré - Næstum raðað fylki, 10% stokkað. Smíði og innsetning á " + arraySize + " stökum tók: " + time + " ms.");

        // nýtt treap
        // gagnagrind búin til og innsetning fylkis
        start = System.currentTimeMillis();
        Treap<Integer, Integer> treap = new Treap<>();

        for (int i = 0; i<arraySize; i++) {
            treap.insert(shuffledArr[i], shuffledArr[i]);
        }
        time = System.currentTimeMillis() - start;
        
        System.out.println("Treap - Stokkað fylki, smíði og innsetning á " + arraySize + " stökum tók: " + time + " ms.");
        
        // nýtt treap
        // smíði og innsetning fylkis
        start = System.currentTimeMillis();
        Treap<Integer, Integer> treap1 = new Treap<>();

        for (int i = 0; i<arraySize; i++) {
            treap1.insert(shuffledArrAlmostSorted[i], shuffledArrAlmostSorted[i]);
        }        
        time = System.currentTimeMillis() - start;
        System.out.println("Treap - Næstum raðað fylki, 10% stokkað. Smíði og innsetning á " + arraySize + " stökum tók: " + time + " ms.");

        // nýr SkipList
        // smíði og innsetning fylkis
        start = System.currentTimeMillis();
        SkipList<Integer, Integer> skipList = new SkipList<>();
        
        for (int i = 0; i<arraySize; i++) {
            skipList.insert(shuffledArr[i], shuffledArr[i]);
        }
        time = System.currentTimeMillis() - start;
        System.out.println("Skiplist - Stokkað fylki, smíði og innsetning á " + arraySize + " stökum tók: " + time + " ms.");
        
        // nýr SkipList
        // smíði og innsetning fylkis
        start = System.currentTimeMillis();
        SkipList<Integer, Integer> skipList1 = new SkipList<>();
        
        for(int i = 0; i<arraySize; i++) {
            skipList1.insert(shuffledArrAlmostSorted[i], shuffledArrAlmostSorted[i]);
        }
        time = System.currentTimeMillis() - start;
        System.out.println("Skiplist - Næstum raðað fylki, smíði og innsetning á " + arraySize + " stökum tók: " + time + " ms.");
        

        System.out.println("============== Leit =================");
        /**
         * b-liður, leit
         * Tvíleitartré
         */
        start = System.currentTimeMillis();
        // Slembin innsetning, aðeins leitað að lyklum í gagnagrindinni
         // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 1; i<arraySize*2; i=i+2) {
                bst.search(i);
            }
        }
        time = System.currentTimeMillis() - start;
        System.out.println("Tvíleitartré - slembin innsetning. Leit að " + arraySize * searchMultiplier + " stökum, öll gildi í gagnagrind tók: " + time + " ms.");
               
 
        // Slembin innsetning, helmingur leitana árangurslaus
        start = System.currentTimeMillis();
        // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 0; i<arraySize; i++) {
                bst.search(i);
            }            
        }

        time = System.currentTimeMillis() - start;
        System.out.println("Tvíleitartré - slembin innsetning. Leit að " + arraySize * searchMultiplier + " stökum, helmingur gilda í gagnagrind tók: " + time + " ms.");

        // Næstum því röðuð innsetning, aðeins leitað að lyklum í gagnagrindinni
        start = System.currentTimeMillis();
        // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 1; i<arraySize*2; i=i+2) {
                bst1.search(i);
            }            
        }
        
        time = System.currentTimeMillis() - start;
        System.out.println("Tvíleitartré - næstum röðuð innsetning. Leit að " + arraySize * searchMultiplier + " stökum, öll gildí gagnagrind tók: " + time + " ms.");

        
        // Næstum því röðuð innsetning, helmingur leitana árangurslaus
        start = System.currentTimeMillis();
        // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 0; i<arraySize; i++) {
                bst1.search(i);
            }            
        }

        time = System.currentTimeMillis() - start;
        System.out.println("Tvíleitartré - næstum röðuð innsetning. Leit að " + arraySize * searchMultiplier + " stökum, helmingur gilda gagnagrind tók: " + time + " ms.");

      
        /**
         * b-liður, leit
         * Treap
         */
        
        start = System.currentTimeMillis();
        // Slembin innsetning, aðeins leitað að lyklum í gagnagrindinni
         // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 1; i<arraySize*2; i=i+2) {
                treap.search(i);
            }
        }
        time = System.currentTimeMillis() - start;
        System.out.println("Treap - slembin innsetning. Leit að " + arraySize * searchMultiplier + " stökum, öll gildi í gagnagrind tók: " + time + " ms.");
        
        // Slembin innsetning, helmingur leitana árangurslaus
        start = System.currentTimeMillis();
        // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 0; i<arraySize; i++) {
                treap.search(i);
            }            
        }

        time = System.currentTimeMillis() - start;
        System.out.println("Treap - slembin innsetning. Leit að " + arraySize * searchMultiplier + " stökum, helmingur gilda í gagnagrind tók: " + time + " ms.");

        // Næstum því röðuð innsetning, aðeins leitað að lyklum í gagnagrindinni
        start = System.currentTimeMillis();
        // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 1; i<arraySize*2; i=i+2) {
                treap1.search(i);
            }            
        }
        
        time = System.currentTimeMillis() - start;
        System.out.println("Treap - næstum röðuð innsetning. Leit að " + arraySize * searchMultiplier + " stökum, öll gildi í gagnagrind tók: " + time + " ms.");

        
        // Næstum því röðuð innsetning, helmingur leitana árangurslaus
        start = System.currentTimeMillis();
        // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 0; i<arraySize; i++) {
                treap1.search(i);
            }            
        }

        time = System.currentTimeMillis() - start;
        System.out.println("Treap - næstum röðuð innsetning. Leit að " + arraySize * searchMultiplier + " stökum, helmingur gilda gagnagrind tók: " + time + " ms.");


        /**
         * b-liður, leit
         * SkipList
         */
        
        start = System.currentTimeMillis();
        // Slembin innsetning, aðeins leitað að lyklum í gagnagrindinni
         // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 1; i<arraySize*2; i=i+2) {
                skipList.search(i);
            }
        }

        time = System.currentTimeMillis() - start;
        System.out.println("SkipList - slembin innsetning. Leit að " + arraySize * searchMultiplier + " stökum, öll gildi í gagnagrind tók: " + time + " ms.");
        
        // Slembin innsetning, helmingur leitana árangurslaus
        start = System.currentTimeMillis();
        // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 0; i<arraySize; i++) {
                skipList.search(i);
            }            
        }

        time = System.currentTimeMillis() - start;
        System.out.println("SkipList - slembin innsetning. Leit að " + arraySize * searchMultiplier + " stökum, helmingur gilda í gagnagrind tók: " + time + " ms.");

        // Næstum því röðuð innsetning, aðeins leitað að lyklum í gagnagrindinni
        start = System.currentTimeMillis();
        // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 1; i<arraySize*2; i=i+2) {
                skipList1.search(i);
            }            
        }
        
        time = System.currentTimeMillis() - start;
        System.out.println("SkipList - næstum röðuð innsetning. Leit að " + arraySize * searchMultiplier + " stökum, öll gildi í gagnagrind tók: " + time + " ms.");

        
        // Næstum því röðuð innsetning, helmingur leitana árangurslaus
        start = System.currentTimeMillis();
        // lykkjan endurtekur leitina
        for (int j = 0; j<searchMultiplier; j++) {
            for (int i = 0; i<arraySize; i++) {
                skipList1.search(i);
            }            
        }

        time = System.currentTimeMillis() - start;
        System.out.println("SkipList - næstum röðuð innsetning. Leit að " + arraySize * searchMultiplier + " stökum, helmingur gilda gagnagrind tók: " + time + " ms.");

    }
    
}
