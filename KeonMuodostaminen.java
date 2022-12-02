import java.util.Random;

/**
 * 
 * Muodostaa keon satunnaisista luvuista ja lajittelee sen
 * suuruusjärjestykseen (laskeva)
 * 
 */
public class KeonMuodostaminen {

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        int[] t = new int[11];
        luoRandomTaulukko(t);
        
        int[] keko = new int[11];
        
        for (int i = 1; i < t.length; i++) {
            lisaaKekoon(keko, t[i]);
        }
        System.out.println("Keko taulukossa: ");
        for (int luku : keko) System.out.println(luku);
        
        kekolajittelu(keko, keko.length -1);
        System.out.println("-------");
        System.out.println("Taulukko suuruusjärjestyksessä: ");
        for (int i = 1; i < keko.length; i++) System.out.println(keko[i]);
    }
    
    
    /**
     * Lisää kekoon luvun
     * @param a keko
     * @param alkio lisättävä luku
     */
    public static void lisaaKekoon(int[] a, int alkio) {
        a[0]++;
        int i = a[0];
        
        while ((i > 1) && (a[i/2] > alkio)) {
            a[i] = a[i/2];
            i = i/2;
        }
        a[i] = alkio;
    }
    
    
    /**
     * Lisää taulukkoon satunnaiset luvut
     * @param t taulukko johon lisätään lukuja
     */
    public static void luoRandomTaulukko(int[] t) {
        for (int i = 1; i < t.length; i++) {
            Random rand = new Random();
            int luku = rand.nextInt(50);
            t[i] = luku; t[0]++;
        }
    }
    
    
    /**
     * Lajittelee keon (heapsort)
     * @param t taulukko johon keko toteutettu
     * @param n ;
     */
    public static void kekolajittelu(int [] t, int n) {
        t[0] = n;
        teeKeko(t);
        for (int i = n; i > 1; i--) {
            int temp = t[1]; t[1] = t[i]; t[i] = temp;
            t[0]--;
            korjaaKeko(t, 1);
        }
    }

    
    /**
     * korjaa keon oikeanlaiseksi
     * @param a taulukko johon keko toteutettu
     * @param i indeksi
     */
    public static void korjaaKeko(int[] a, int i) {
        if (2*i > a[0]) return;
        
        int j = 2*i;
        int alkio = a[i];
        
        while (j <= a[0]) {
            if ((j < a[0]) && a[j] > a[j+1]) j= j+1;
            if (alkio <= a[j]) break;
            a[j/2] = a[j];
            j = 2*j;
        }
        a[j/2] = alkio;
    }
    
    
    /**
     * tekee keon rekursiivisesti
     * @param a taulukko johon keko tehdään
     */
    public static void teeKeko(int[] a) {
        for (int i = a[0]/2; i >= 1; i--) {
            korjaaKeko(a, i);
        }
    }
}
