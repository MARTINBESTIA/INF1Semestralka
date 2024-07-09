
import java.util.ArrayList;
import fri.shapesge.Obrazok;
/**
 * Upravuje metódy z triedy Figurka, aby sa inštancia chovala ako pešiak. Navyše zodpovedá za vykresľovanie obrázku na plátno.
 * 
 * @author Martin Šimko
 * @version 1.0.0
 */
public class Pesiak extends Figurka {
    /**
     * Constructor for objects of class Pesiak
     */
    private Obrazok panacikObrazok;
    private String panacikBielaCesta = "pics/bielyPesiak.png";
    private String panacikCiernaCesta = "pics/ciernyPesiak.png";
    private boolean pesiakPrvyTah = true;
    private int posunutieNaStredX = 180;
    private int posunutieNaStredY = 560;
    private GameController gameController;
    private int povodnaY;
    private boolean enPassantable;
    
/**
*   Slúži na nastavenie materiálovej hodnoty príslušnej figúrke, priradí sa referencia na gameController a na políčko, kde sa figúrka na začiatku hry
*   pôvodne nachádza. Podľa pozície sa nastaví farba figúrky a obrazok figúrky sa vykreslí na plátno.
*   @param povodnePolicko políčko, kde sa figúrka nachádza na začiatku hry
*   @param gameController referencia na gameController
*/    
    public Pesiak(Policko povodnePolicko, GameController gameController) {
        this.enPassantable = false;
        this.setHodnotaFigurky(1);        
        this.setPolickoKdeJeFigurka(povodnePolicko);
        this.gameController = gameController;      
        this.povodnaY = povodnePolicko.getSuradniceXY()[1];
        if (this.getPolickoKdeJeFigurka().getSuradniceXY()[1] < 5) {
            this.setFarbaFigurkyBiela();
            this.panacikObrazok = new Obrazok(this.panacikBielaCesta, this.getPolickoKdeJeFigurka().getSurXpreFigurku(), this.getPolickoKdeJeFigurka().getSurYpreFigurku());
  
        } else {
            this.setFarbaFigurkyCierna();
            this.panacikObrazok = new Obrazok(this.panacikCiernaCesta, this.getPolickoKdeJeFigurka().getSurXpreFigurku(), this.getPolickoKdeJeFigurka().getSurYpreFigurku());
        }
        this.panacikObrazok.zobraz();            
    }
/**
*   Vráti true ak má pešiak prvý ťah, false ak už prvý ťah urobil.
*/
    public boolean getPesiakPrvyTah() {  
        return this.pesiakPrvyTah;
    }
/**
 * vygeneruje a vráti všetky možné ťahy konkrétnej figúrky. 
 */     
    public ArrayList<int[]> vygenerujMozneTahy() {
        if (this.getPolickoKdeJeFigurka().getSuradniceXY()[1] != this.povodnaY) {
            this.pesiakPrvyTah = false; 
        }
        this.getMozneTahy().clear();
        if (this.getJeBiela() == this.gameController.getBielaNaTahu()) {
            int posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
            int posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1];          
            if (this.getJeBiela()) {
                if (!this.gameController.getSachovnica()[posX - 1][posY].getJeObsadene() && !this.getPinDoleVlavo() && !this.getPinHoreVlavo()) {
                    this.getMozneTahy().add(new int[] {posX, posY + 1});
                    if (this.getPesiakPrvyTah()) {
                        if (!this.gameController.getSachovnica()[posX - 1][posY + 1].getJeObsadene() && !this.getPinKolmo() && !this.getPinVodorovne()) {
                            this.getMozneTahy().add(new int[] {posX, posY + 2, 99});
                        }
                    }
                }               
                if (posX < 8 && this.gameController.getSachovnica()[posX][posY].getJeObsadene() && !this.getPinKolmo() && !this.getPinVodorovne() && !this.getPinHoreVlavo()) {
                    if (this.gameController.getSachovnica()[posX][posY].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX + 1, posY + 1});
                    }
                }
                if (posX > 1 && this.gameController.getSachovnica()[posX - 2][posY].getJeObsadene() && !this.getPinKolmo() && !this.getPinVodorovne() && !this.getPinDoleVlavo()) {
                    if (this.gameController.getSachovnica()[posX - 2][posY].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX - 1, posY + 1});
                    }
                }
                if (posX > 1 && this.gameController.getSachovnica()[posX - 2][posY - 1].getJeObsadene() ) {
                    if (this.gameController.getSachovnica()[posX - 2][posY - 1].getFigurkaNaPolicku().getEnPassant() && !this.getPinKolmo() && !this.getPinVodorovne() && !this.getPinDoleVlavo()) {
                        this.getMozneTahy().add(new int[] {posX - 1, posY + 1, 1});
                    }
                }
                if (posX < 8 && this.gameController.getSachovnica()[posX][posY - 1].getJeObsadene() ) {
                    if (this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getEnPassant() && !this.getPinKolmo() && !this.getPinVodorovne() && !this.getPinHoreVlavo()) {
                        this.getMozneTahy().add(new int[] {posX + 1, posY + 1, 1});
                    }
                }
            } else {
                if (!this.gameController.getSachovnica()[posX - 1][posY - 2].getJeObsadene() && !this.getPinDoleVlavo() && !this.getPinHoreVlavo()) {
                    this.getMozneTahy().add(new int[] {posX, posY - 1});
                    if (this.getPesiakPrvyTah()) {
                        if (!this.gameController.getSachovnica()[posX - 1][posY - 3].getJeObsadene()) {
                            this.getMozneTahy().add(new int[] {posX, posY - 2, 99});
                        }
                    }
                }
                if (posX < 8 && this.gameController.getSachovnica()[posX][posY - 2].getJeObsadene() && !this.getPinKolmo() && !this.getPinVodorovne() && !this.getPinDoleVlavo()) {
                    if (this.gameController.getSachovnica()[posX][posY - 2].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX + 1, posY - 1});
                    }
                }
                if (posX > 1 && this.gameController.getSachovnica()[posX - 2][posY - 2].getJeObsadene() && !this.getPinKolmo() && !this.getPinVodorovne() && !this.getPinHoreVlavo()) {
                    if (this.gameController.getSachovnica()[posX - 2][posY - 2].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX - 1, posY - 1});
                    }
                }
                if (posX > 1 && this.gameController.getSachovnica()[posX - 2][posY - 1].getJeObsadene() && !this.getPinKolmo() && !this.getPinVodorovne() && !this.getPinHoreVlavo()) {
                    if (this.gameController.getSachovnica()[posX - 2][posY - 1].getFigurkaNaPolicku().getEnPassant()) {
                        this.getMozneTahy().add(new int[] {posX - 1, posY - 1, 1});
                    }
                }
                if (posX < 8 && this.gameController.getSachovnica()[posX][posY - 1].getJeObsadene() && !this.getPinKolmo() && !this.getPinVodorovne() && !this.getPinDoleVlavo()) {
                    if (this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getEnPassant()) {
                        this.getMozneTahy().add(new int[] {posX + 1, posY - 1, 1});
                    }
                }
            }
            
        } 
        if (this.gameController.getJeSach() && this.gameController.getFarbaVSachu() == this.getJeBiela()) {
            ArrayList<int[]> noveMozneTahy = new ArrayList<int[]>();
            for (int[] povoleneTahy : this.gameController.getTahyPocasSachu()) {
                for (int[] mozneTahy : this.getMozneTahy()) {
                    if (povoleneTahy[0] == mozneTahy[0] && povoleneTahy[1] == mozneTahy[1]) {
                        noveMozneTahy.add(new int[] {povoleneTahy[0], povoleneTahy[1]});
                    }                 
                }
            }
            return noveMozneTahy;
        
        }        
        return this.getMozneTahy();    
    }
/**
 * Vygeneruje a vráti všetky políčka, ktoré daná figúrka ohrozuje. 
 */    
    public ArrayList<int[]> vygenerujOhrozenePolicka() {
        this.getOhrozenePolicka().clear();
        int posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
        int posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1];
        if (posX < 8 && !this.getJeBiela()) {
            this.getOhrozenePolicka().add(new int[] {posX + 1, posY - 1});
        }
        if (posX > 1 && !this.getJeBiela()) {
            this.getOhrozenePolicka().add(new int[] {posX - 1, posY - 1});
        }
        if (posX < 8 && this.getJeBiela()) {
            this.getOhrozenePolicka().add(new int[] {posX + 1, posY + 1});
        }
        if (posX > 1 && this.getJeBiela()) {
            this.getOhrozenePolicka().add(new int[] {posX - 1, posY + 1});
        }
        return this.getOhrozenePolicka();
    }
/**
 * Zmení polohu grafiky figúrky na plátne na súradnice x, y. 
 * @param x nová súradnica x grafiky figúrky
 * @param x nová súradnica y grafiky figúrky 
 */    
    public void zmenPolohuObrazka(int x, int y) {
        this.panacikObrazok.zmenPolohu(x, y);
    }
/**
 * Vymaže obrázok figurky z plátna. 
 */
    public void vyhodFigurku() {
        this.panacikObrazok.skry();
    }
/**
* Nastaví možnosť vyhodiť figúrku en passantom na true. 
*/    
    public void setEnPassantTrue() {
        this.enPassantable = true;
    }
/**
* Nastaví možnosť vyhodiť figúrku en passantom na false. 
*/    
    public void setEnPassantFalse() {
        this.enPassantable = false;
    }
/**
* Vráti hodnotu, či sa dá preskočiť figúrka en passantom. 
*/    
    public boolean getEnPassant() {
        return this.enPassantable;
    }
    
    
    
}
