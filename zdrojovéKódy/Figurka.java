import java.util.ArrayList;
/**
 * Trieda slúži v prvom rade ako spôsob, ako ukladať referencie tried jednotlivých figúriek do premmených rovnakého typu. Uskladňuje
 * spoločné atribúty všetkých figúriek. Keďže na pristupovanie ku jednotlivým figúrkam používam iba triedu Figurka, obsahuje všetky metódy
 * všetkých jednotlivých figuriek. Ak sú metódy prázdne, tak sú preťažované figurkami (podtriedami).
 * 
 * @author Martin Šimko 
 * @version 1.0.0
 */
public class Figurka {
    private boolean jeOznacena = false;
    private boolean jeBiela;
    private int hodnotaFigurky;
    private Policko polickoKdeJeFigurka;
    private ArrayList<int[]> mozneTahy;
    private ArrayList<int[]> ohrozenePolicka;
    private boolean pinKolmo = false;
    private boolean pinVodorovne = false;
    private boolean pinDoleVlavo = false;
    private boolean pinHoreVlavo = false;
    
/**
* Slúži na vytvorenie referencií pre atribúty objektových typov.
*/   
    public Figurka() {
        this.mozneTahy = new ArrayList<int[]>();
        this.ohrozenePolicka = new ArrayList<int[]>();
    }
/**
* Nastaví farbu figurky na bielu.
*/   
    public void setFarbaFigurkyCierna() {
        this.jeBiela = false;
    }
/**
* Nastaví farbu figurky na čiernu.
*/   
    public void setFarbaFigurkyBiela() {
        this.jeBiela = true;
    }
/**
 * vygeneruje a vráti všetky možné ťahy figurky (preťažovaná).
 */    
    public ArrayList<int[]> vygenerujMozneTahy() {
        return this.mozneTahy;
    }
/**
 * Označí figúrku.
 */       
    public void oznacFigurku() {
        this.jeOznacena = true;
        
    }
/**
* Vráti hodnotu true ak je figúrka označená, false ak nie.
*/   
    public boolean getJeOznacena() {
        return this.jeOznacena;
        
    }
/**
 * Odoznačí figúrku.
 */    
    public void odoznacFigurku() {
        this.jeOznacena = false;
        
    }
/**
 * Vráti všetky možné ťahy figúrky.
 */    
    public ArrayList<int[]> getMozneTahy() {
        return this.mozneTahy;
    }
/**
 * Vráti materiálovú hodnotu danej figurky.
 */       
    public int getHodnotaFigurky() {
        return this.hodnotaFigurky;
    }
/**
 * Nastaví hodnotu figurky na hodnotu x.
 * @param x materiálová hodnota figurky
 */     
    public void setHodnotaFigurky(int x) {
        this.hodnotaFigurky = x;
    }
/**
 * Presunie figurku zo starého na nové políčko.
 * @param novePolicko referencia na políčko, kam sa má figúrka presunúť.
 */    
    public void presunFigurku(Policko novePolicko) {
        this.polickoKdeJeFigurka.odidZPolicka();
        this.setPolickoKdeJeFigurka(novePolicko);
        this.polickoKdeJeFigurka.priradFigurku(this);
        this.zmenPolohuObrazka(this.polickoKdeJeFigurka.getSurXpreFigurku(), this.polickoKdeJeFigurka.getSurYpreFigurku());
    }
/**
 * Vráti farbu danej figúrky. Biela = true, čierna = false.
 */    
    public boolean getJeBiela() {
        return this.jeBiela;
    }
/**
 * Nastaví ku danej figúrke políčko, na ktorom sa práve nachádza.
 */    
    public void setPolickoKdeJeFigurka(Policko policko) {
        this.polickoKdeJeFigurka = policko;
    }
/**
 * Zmení polohu grafiky figúrky na plátne na súradnice x, y. Preťažovaná
 * @param x nová súradnica x grafiky figúrky
 * @param x nová súradnica y grafiky figúrky 
 */    
    public void zmenPolohuObrazka(int x, int y) {
        
    }
/**
 * Vymaže obrázok figurky z plátna. (Preťažovaná)
 */    
    public void vyhodFigurku() {
        
    }
/**
 * Vygeneruje a vráti všetky políčka, ktoré daná figúrka ohrozuje. (Preťažovaná)
 */     
    public ArrayList<int[]> vygenerujOhrozenePolicka() {
        return this.ohrozenePolicka;
    }
/**
 * Vygeneruje a vráti všetky možné ťahy figurky (preťažovaná).
 */
    public ArrayList<int[]> getOhrozenePolicka() {
        return this.ohrozenePolicka;
    }
/**
 * Vráti či je figúrka posunutá
 */    
    public boolean getJePosunuta() {
        return false;
    }
/**
 * Nastaví premennú na posunutú. (preťažovaná)
 */     
    public void setJePosunuta() {
            
    }
/**
 * Nastaví premennú na posunutú. (preťažovaná)
 */    
    public Policko getPolickoKdeJeFigurka() {
        return this.polickoKdeJeFigurka;
    }
/**
 * Vráti string či je daná figúrka kôň. V tomto prípade vráti že nie
 */    
    public String getTypFigurky() {
        return "nie kon";
    }
/**
 * Pinuje figúrky (Preťažovaná).
 */    
    public void pinujFigurky() {
        
    }
/**
* Nastaví pin kolmo na true.
*/   
    public void setPinKolmo(boolean value) {
        this.pinKolmo = value;
    }
/**
* Nastaví pin vodorovne na true.
*/    
    public void setPinVodorovne(boolean value) {
        this.pinVodorovne = value;
    }
/**
* Nastaví pin diagonály v smere od ľavého horného rohu k opačnému na true.
*/    
    public void setPinHoreVlavo(boolean value) {
        this.pinHoreVlavo = value; // diagonala z horneho laveho rohu ku opacnemu
    }
/**
* Nastaví pin diagonály v smere od ľavého dolného rohu k opačnému na true.
*/    
    public void setPinDoleVlavo(boolean value) {
        this.pinDoleVlavo = value; // diagonala z dolneho laveho rohu ku opacnemu
    }
/**
* vráti či je figúrka pinutá kolmo.
*/    
    public boolean getPinKolmo() {
        return this.pinKolmo;
    }
/**
* vráti či je figúrka pinutá vodorovne.
*/        
    public boolean getPinVodorovne() {
        return this.pinVodorovne;
    }
/**
* vráti či je figúrka pinutá v smere od ľavého horného rohu k opačnému.
*/     
    public boolean getPinHoreVlavo() {
        return this.pinHoreVlavo;
    }
/**
* vráti či je figúrka pinutá v smere od ľavého dolného rohu k opačnému.
*/   
    public boolean getPinDoleVlavo() {
        return this.pinDoleVlavo;
    }
/**
* Nastaví možnosť vyhodiť figúrku en passantom na true. (Preťažovaná)
*/    
    public void setEnPassantTrue() {

    }
/**
* Nastaví možnosť vyhodiť figúrku en passantom na false. (Preťažovaná)
*/    
    public void setEnPassantFalse() {

    }
/**
* Vráti hodnotu, či sa dá preskočiť figúrka en passantom. (Preťažovaná)
*/    
    public boolean getEnPassant() {
        return false;
    }
    
    
}
