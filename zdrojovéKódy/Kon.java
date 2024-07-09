import java.util.ArrayList;
import fri.shapesge.Obrazok;
/**
 * Upravuje metódy z triedy Figurka, aby sa inštancia chovala ako kôň. Navyše zodpovedá za vykresľovanie obrázku na plátno.
 * 
 * @author Martin Šimko
 * @version 1.0.0
 */
public class Kon extends Figurka {
    /**
     * Constructor for objects of class Kon
     */
    private Obrazok panacikObrazok;
    private String panacikBielaCesta = "pics/bielyKon.png";
    private String panacikCiernaCesta = "pics/ciernyKon.png";   
    private boolean jeOznacena = false;
    private GameController gameController;
/**
*   Slúži na nastavenie materiálovej hodnoty príslušnej figúrke, priradí sa referencia na gameController a na políčko, kde sa figurka na začiatku hry
*   pôvodne nachádza. Podľa pozície sa nastaví farba figurky a obrazok figurky sa vykreslí na plátno. Priradia sa referencie pre zvyšné atribúty
*   @param povodnePolicko políčko, kde sa figúrka nachádza na začiatku hry
*   @param gameController referencia na gameController
*/    
    public Kon(Policko povodnePolicko, GameController gameController) {       
        this.setHodnotaFigurky(3);
        this.setPolickoKdeJeFigurka(povodnePolicko);
        this.gameController = gameController;

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
 * vygeneruje a vráti všetky možné ťahy konkrétnej figúrky. 
 */     
    public ArrayList<int[]> vygenerujMozneTahy() {
        this.getMozneTahy().clear();
        if (this.getJeBiela() == this.gameController.getBielaNaTahu() &&
            !this.getPinKolmo() && !this.getPinVodorovne() &&
            !this.getPinHoreVlavo() && !this.getPinDoleVlavo()) {
            int posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
            int posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1];
            if (posX - 1 + 2 >= 0 && posX - 1 + 2 <= 7 && posY - 1 + 1 >= 0 && posY - 1 + 1 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
                if (!this.gameController.getSachovnica()[posX - 1 + 2][posY - 1 + 1].getJeObsadene()) {
                    this.getMozneTahy().add(new int[] {posX + 2, posY + 1});
                
                }
                if (this.gameController.getSachovnica()[posX - 1 + 2][posY - 1 + 1].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 1 + 2][posY - 1 + 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX + 2, posY + 1});
                    }
                }
            }
            if (posX - 1 + 1 >= 0 && posX - 1 + 1 <= 7 && posY - 1 + 2 >= 0 && posY - 1 + 2 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
                if (!this.gameController.getSachovnica()[posX - 1 + 1][posY - 1 + 2].getJeObsadene()) {
                    this.getMozneTahy().add(new int[] {posX + 1, posY + 2});
                }
                if (this.gameController.getSachovnica()[posX - 1 + 1][posY - 1 + 2].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 1 + 1][posY - 1 + 2].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX + 1, posY + 2});
                    }
                }
            }
            if (posX - 1 - 1 >= 0 && posX - 1 - 1 <= 7 && posY - 1 + 2 >= 0 && posY - 1 + 2 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
                if (!this.gameController.getSachovnica()[posX - 1 - 1][posY - 1 + 2].getJeObsadene()) {
                    this.getMozneTahy().add(new int[] {posX - 1, posY + 2});
                }
                if (this.gameController.getSachovnica()[posX - 1 - 1][posY - 1 + 2].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 1 - 1][posY - 1 + 2].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX - 1, posY + 2});
                    }
                }
            }
            if (posX - 1 - 1 >= 0 && posX - 1 - 1 <= 7 && posY - 1 - 2 >= 0 && posY - 1 - 2 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
                if (!this.gameController.getSachovnica()[posX - 1 - 1][posY - 1 - 2].getJeObsadene()) {
                    this.getMozneTahy().add(new int[] {posX - 1, posY - 2});
                }
                if (this.gameController.getSachovnica()[posX - 1 - 1][posY - 1 - 2].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 1 - 1][posY - 1 - 2].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX - 1, posY - 2});
                    }
                }
            }
            if (posX - 1 + 2 >= 0 && posX - 1 + 2 <= 7 && posY - 1 - 1 >= 0 && posY - 1 - 1 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
                if (!this.gameController.getSachovnica()[posX - 1 + 2][posY - 1 - 1].getJeObsadene()) {
                    this.getMozneTahy().add(new int[] {posX + 2, posY - 1});
                }
                if (this.gameController.getSachovnica()[posX - 1 + 2][posY - 1 - 1].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 1 + 2][posY - 1 - 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX + 2, posY - 1});
                    }
                }
            }
            if (posX - 1 + 1 >= 0 && posX - 1 + 1 <= 7 && posY - 1 - 2 >= 0 && posY - 1 - 2 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
                if (!this.gameController.getSachovnica()[posX - 1 + 1][posY - 1 - 2].getJeObsadene()) {
                    this.getMozneTahy().add(new int[] {posX + 1, posY - 2});
                }
                if (this.gameController.getSachovnica()[posX - 1 + 1][posY - 1 - 2].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 1 + 1][posY - 1 - 2].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX + 1, posY - 2});
                    }
                }
            }
            if (posX - 1 - 2 >= 0 && posX - 1 - 2 <= 7 && posY - 1 + 1 >= 0 && posY - 1 + 1 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
                if (!this.gameController.getSachovnica()[posX - 1 - 2][posY - 1 + 1].getJeObsadene()) {
                    this.getMozneTahy().add(new int[] {posX - 2, posY + 1});
                }
                if (this.gameController.getSachovnica()[posX - 1 - 2][posY - 1 + 1].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 1 - 2][posY - 1 + 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX - 2, posY + 1});
                    }
                }
            }
            if (posX - 1 - 2 >= 0 && posX - 1 - 2 <= 7 && posY - 1 - 1 >= 0 && posY - 1 - 1 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
                if (!this.gameController.getSachovnica()[posX - 1 - 2][posY - 1 - 1].getJeObsadene()) {
                    this.getMozneTahy().add(new int[] {posX - 2, posY - 1});
                }
                if (this.gameController.getSachovnica()[posX - 1 - 2][posY - 1 - 1].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 1 - 2][posY - 1 - 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                        this.getMozneTahy().add(new int[] {posX - 2, posY - 1});
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
        if (posX - 1 + 2 >= 0 && posX - 1 + 2 <= 7 && posY - 1 + 1 >= 0 && posY - 1 + 1 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
            this.getOhrozenePolicka().add(new int[] {posX + 2, posY + 1});
        }
        if (posX - 1 + 1 >= 0 && posX - 1 + 1 <= 7 && posY - 1 + 2 >= 0 && posY - 1 + 2 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
            this.getOhrozenePolicka().add(new int[] {posX + 1, posY + 2});
        }
        if (posX - 1 - 1 >= 0 && posX - 1 - 1 <= 7 && posY - 1 + 2 >= 0 && posY - 1 + 2 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
            this.getOhrozenePolicka().add(new int[] {posX - 1, posY + 2});
        }
        if (posX - 1 - 1 >= 0 && posX - 1 - 1 <= 7 && posY - 1 - 2 >= 0 && posY - 1 - 2 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
            this.getOhrozenePolicka().add(new int[] {posX - 1, posY - 2});
        }
        if (posX - 1 + 2 >= 0 && posX - 1 + 2 <= 7 && posY - 1 - 1 >= 0 && posY - 1 - 1 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
            this.getOhrozenePolicka().add(new int[] {posX + 2, posY - 1});
        }
        if (posX - 1 + 1 >= 0 && posX - 1 + 1 <= 7 && posY - 1 - 2 >= 0 && posY - 1 - 2 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
            this.getOhrozenePolicka().add(new int[] {posX + 1, posY - 2});
        }
        if (posX - 1 - 2 >= 0 && posX - 1 - 2 <= 7 && posY - 1 + 1 >= 0 && posY - 1 + 1 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
            this.getOhrozenePolicka().add(new int[] {posX - 2, posY + 1});
        }
        if (posX - 1 - 2 >= 0 && posX - 1 - 2 <= 7 && posY - 1 - 1 >= 0 && posY - 1 - 1 <= 7) {   // overenie ci nehladame tah mimo hracej plochy
            this.getOhrozenePolicka().add(new int[] {posX - 2, posY - 1});
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
 * Vráti string či je daná figúrka kôň. V tomto prípade vráti že je kôň
 */    
    public String getTypFigurky() {
        return "kon";
    }
    
}
