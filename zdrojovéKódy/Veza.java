import java.util.ArrayList;
import fri.shapesge.Obrazok;
/**
 * Upravuje metódy z triedy Figurka, aby sa inštancia chovala ako veža. Navyše zodpovedá za vykresľovanie obrázku na plátno.
 * 
 * @author Martin Šimko
 * @version 1.0.0
 */
public class Veza extends Figurka {
    /**
     * Constructor for objects of class Veza
     */
    private Obrazok panacikObrazok;
    private String panacikBielaCesta = "pics/bielaVeza.png";
    private String panacikCiernaCesta = "pics/ciernaVeza.png";   
    private boolean jeOznacena = false;
    private boolean jeNepohnuta = true;
    private GameController gameController;
/**
*   Slúži na nastavenie materiálovej hodnoty príslušnej figúrke, priradí sa referencia na gameController a na políčko, kde sa figurka na začiatku hry
*   pôvodne nachádza. Podľa pozície sa nastaví farba figurky a obrazok figurky sa vykreslí na plátno.
*   @param povodnePolicko políčko, kde sa figúrka nachádza na začiatku hry
*   @param gameController referencia na gameController
*/
    public Veza(Policko povodnePolicko, GameController gameController) {
        this.setHodnotaFigurky(5);
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
        if (this.getJeBiela() == this.gameController.getBielaNaTahu() && !this.getPinHoreVlavo() && !this.getPinDoleVlavo()) {
            int posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
            int posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
            while (posY > 0 && !this.getPinVodorovne()) {
                if (this.gameController.getSachovnica()[posX][posY - 1].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                        break;
                    } else {
                        this.getMozneTahy().add(new int[] {posX + 1, posY - 1 + 1});
                        break;
                    }                 
                } else {
                    this.getMozneTahy().add(new int[] {posX + 1, posY - 1 + 1});
                    posY--;

                }
            }   
            posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
            posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
            while (posY < 7 && !this.getPinVodorovne()) {
                if (this.gameController.getSachovnica()[posX][posY + 1].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX][posY + 1].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                        break;
                    } else {
                        this.getMozneTahy().add(new int[] {posX + 1, posY + 1 + 1});
                        break;
                    }
                    
                } else {
                    this.getMozneTahy().add(new int[] {posX + 1, posY + 1 + 1});
                    posY++;

                }
                
            }
            posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
            posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
            while (posX < 7 && !this.getPinKolmo()) {
                if (this.gameController.getSachovnica()[posX + 1][posY].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX + 1][posY].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                        break;
                    } else {
                        this.getMozneTahy().add(new int[] {posX + 2, posY + 1});
                        break;
                    }
                    
                } else {
                    this.getMozneTahy().add(new int[] {posX + 2, posY + 1});
                    posX++;

                }
                
            }
            posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
            posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
            while (posX > 0 && !this.getPinKolmo()) {
                if (this.gameController.getSachovnica()[posX - 1][posY].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 1][posY].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                        break;
                    } else {
                        this.getMozneTahy().add(new int[] {posX - 1 + 1, posY + 1});
                        break;
                    }
                    
                } else {
                    this.getMozneTahy().add(new int[] {posX - 1 + 1, posY + 1});
                    posX--;
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
        int posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        int posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        while (posY > 0) {
            if (this.gameController.getSachovnica()[posX][posY - 1].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getHodnotaFigurky() == 0 &&
                    this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getOhrozenePolicka().add(new int[] {posX + 1, posY - 1 + 1});
                    posY--;
                    continue;
                }
                this.getOhrozenePolicka().add(new int[] {posX + 1, posY - 1 + 1});
                break;                 
            } else {
                this.getOhrozenePolicka().add(new int[] {posX + 1, posY - 1 + 1});
                posY--;
            }
        }   
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        while (posY < 7) {
            if (this.gameController.getSachovnica()[posX][posY + 1].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX][posY + 1].getFigurkaNaPolicku().getHodnotaFigurky() == 0 &&
                    this.gameController.getSachovnica()[posX][posY + 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getOhrozenePolicka().add(new int[] {posX + 1, posY + 1 + 1});
                    posY++;
                    continue;
                }
                this.getOhrozenePolicka().add(new int[] {posX + 1, posY + 1 + 1});
                break;
            } else {
                this.getOhrozenePolicka().add(new int[] {posX + 1, posY + 1 + 1});
                posY++;
            }               
        }
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        while (posX < 7) {
            if (this.gameController.getSachovnica()[posX + 1][posY].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX + 1][posY].getFigurkaNaPolicku().getHodnotaFigurky() == 0 &&
                    this.gameController.getSachovnica()[posX + 1][posY].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getOhrozenePolicka().add(new int[] {posX + 2, posY + 1});
                    posX++;
                    continue;
                }
                this.getOhrozenePolicka().add(new int[] {posX + 2, posY + 1});
                break;                   
            } else {
                this.getOhrozenePolicka().add(new int[] {posX + 2, posY + 1});
                posX++;
            }                
        }
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        while (posX > 0) {
            if (this.gameController.getSachovnica()[posX - 1][posY].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX - 1][posY].getFigurkaNaPolicku().getHodnotaFigurky() == 0 &&
                    this.gameController.getSachovnica()[posX - 1][posY].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getOhrozenePolicka().add(new int[] {posX - 1 + 1, posY + 1});
                    posX--;
                    continue;
                }
                this.getOhrozenePolicka().add(new int[] {posX - 1 + 1, posY + 1});
                break;
                    
            } else {
                this.getOhrozenePolicka().add(new int[] {posX - 1 + 1, posY + 1});
                posX--;
            }                
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
 * Vráti či je figúrka posunutá
 */    
    public boolean getJePosunuta() {
        return this.jeNepohnuta;   
    }
/**
 * Nastaví premennú na posunutú. 
 */    
    public void setJePosunuta() {
        this.jeNepohnuta = false;     
    }
    
    
    
}

