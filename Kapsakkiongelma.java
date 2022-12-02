
/**
 * 
 * Kapsäkkiongelma
 * Valitaan tavarat reppuun siten, että niiden hyötyarvojen
 * summa on mahdollisimman suuri, mutta painoraja ei ylity.
 *
 */
public class Kapsakkiongelma {    

    private static int[] hyodyt;
    private static int[] painot;
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        hyodyt = new int[] {0, 36,35,20,9};
        painot = new int[] {0, 4, 7, 5, 3};
        
        int n = hyodyt.length-1;
        int maxW = 10;
        
        System.out.println("===== Kapsäkkiongelma =====");
        for (int i = 0; i < hyodyt.length; i++) {
            System.out.println(i+1 + ": hyötyarvo == " + hyodyt[i]+ ", paino == " + painot[i]);
        }
        System.out.println("==========================");
        System.out.println("Kokonaishyötyarvo: ");
        System.out.println(ratkaiseKapsakki(maxW, painot, hyodyt, n));
    }

    
    /**
     * Ratkaisee kapsäkkiongelman
     * @param maxPaino kapsäkin maksimi paino
     * @param w tavaroiden painot taulukossa
     * @param p tavaroiden hyödyt taulukossa
     * @param n tavaroiden määrä
     * @return kokonaishyötyarvon
     * @example
     * <pre name="test">
     *  int[] hyodyt = new int[] {0, 36,35,20,9};
     *  int[] painot = new int[] {0, 4, 7, 5, 3};
     *  int n = hyodyt.length-1;
     *  int maxW = 10;
     *  
     *  int ratkaisu = ratkaiseKapsakki(maxW, painot, hyodyt, n);
     *  ratkaisu == 56 === true;
     * </pre>
     */
    public static int ratkaiseKapsakki(int maxPaino, int[] w, int[] p, int n) {
        int r, k;
        
        int[][] s = new int[n+1][maxPaino+1];
        
        for (r = 0; r <= maxPaino; r++)
            s[0][r] = 0;
        
        for (k = 0; k <= n; k++)
            s[k][0] = 0;
        
        for (k = 1; k <= n; k++) {
            for (r = 1; r <= maxPaino; r++) {
                if (w[k] > r)
                    s[k][r] = s[k-1][r];
                else
                    s[k][r] = Math.max(s[k-1][r], p[k] + s[k-1][r-w[k]]);
            }
        }
        return s[n][maxPaino];
    } 
}
