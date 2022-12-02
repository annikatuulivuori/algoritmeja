
/**
 * 
 * Tallentaa kokonaislukuja taulukkoon käyttäen hajautusta
 * (avoin osoitteenmuodostus)
 *
 */
public class KokonaislukujenHajautus {
    
    private static int m = 10;
    private static int ind = 0;
    
    /**
     * Testipääohjelma
     * Kokonaislukutaulukossa 0 == VAPAA, -1 == POISTETTU
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        int[] t = new int[m];
        
        alustaTaulukko(t);
        
        lisaa(t, 23);
        lisaa(t, 47);
        lisaa(t, 17);
        lisaa(t, 75);
        lisaa(t, 53);
        lisaa(t, 33);
        lisaa(t, 62);
        lisaa(t, 18);
        
        System.out.println("======== Lisäyksien jälkeen ========");
        for (int i = 0; i < t.length; i++) {
            System.out.println("[ " + i + ", " + t[i] + " ]");
        }
        
        System.out.println("======== Poistojen jälkeen ========");
        
        poista(t, 53);
        poista(t, 17);
        poista(t, 62);

        for (int i = 0; i < t.length; i++) {
            System.out.println("[ " + i + ", " + t[i] + " ]");
        }
    }
    
    
    /**
     * Alustaa taulukon kaikki paikat vapaiksi (0 == VAPAA)
     * @param t taulukko, joka alustetaan
     */
    public static void alustaTaulukko(int[] t) {
        for (int i = 0; i < t.length; i++) {
            t[i] = 0;
        }
    }
    
    
    /**
     * Lisää kokonaislukutaulukkoon hajautuksella
     * @param t taulukko, johon lisätään kokonaisluku
     * @param luku luku, joka lisätään
     * @example
     * <pre name="test">
     *      int[] t = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
     *      lisaa(t, 23);
     *      lisaa(t, 47);
     *      t[3] == 23 === true;
     *      t[2] == 23 === false;
     *      t[1] == 0 === true;
     * </pre>
     */
    public static void lisaa(int[] t, int luku) {
        int paikka = (luku + ind) % m;
        boolean vapaa = onkoVapaa(t, paikka);
        
        if (vapaa) {
            t[paikka] = luku;
            ind = 0;
        } else {
            ind++;
            lisaa(t, luku);
        }
    }
    
    
    /**
     * @param t taulukko, josta halutaan poistaa
     * @param luku luku, joka halutaan poistaa
     * @example
     * <pre name="test">
     *      int[] t = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
     *      lisaa(t, 23);
     *      lisaa(t, 47);
     *      poista(t, 23);
     *      poista(t, 47);
     *      poista(t, 68);   // ei tee mitään, koska ei ole taulukossa
     *      t[3] == -1 === true;
     *      t[7] == -1 === true;
     * </pre>
     */
    public static void poista(int[] t, int luku) {
        boolean taulukossa = onkoTaulukossa(t, luku);
        
        if (taulukossa == false) return;
        
        for (int i = 0; i < t.length; i++) {
            if (t[i] == luku) {
                t[i] = -1;
            }
        }
    }
    
    /**
     * Tarkistaa, onko luku taulukossa
     * @param t taulukko, josta tarkistetaan
     * @param luku haettava luku 
     * @return true jos löytyy, muuten false
     * @example
     * <pre name="test">
     *      int[] t = {0, 0, 62, 23, 53, 75, 33, 47, 17, 18};
     *      onkoTaulukossa(t, 62) === true;
     *      onkoTaulukossa(t, 99) === false;
     * </pre>
     */
    public static boolean onkoTaulukossa(int[] t, int luku) {
        for (int a : t) {
            if (a == luku) return true;
        }
        return false;
    }


    /**
     * Tarkistaa, onko taulukon paikka vapaa vai ei
     * @param t taulukko, jota tarkastellaan
     * @param paikka taulukon paikka, jota tarkastellaan
     * @return true jos paikka on vapaa, muuten false
     * @example
     * <pre name="test">
     *      int[] t = {0, 0, 62, 23, 53, 75, -1, 47, 17, 18};
     *      onkoVapaa(t, 1) === true;
     *      onkoVapaa(t, 3) === false;
     *      onkoVapaa(t, 6) === true;
     * </pre>     
     */
    public static boolean onkoVapaa(int[] t, int paikka) {
        if (t[paikka] == 0 || t[paikka] == -1) return true;
        return false;
    }

}
