import java.util.ArrayList;
import fri.shapesge.Obrazok;
/**
 * Upravuje metódy z triedy Figurka, aby sa inštancia choval ako strelec. Navyše zodpovedá za vykresľovanie obrázku na plátno.
 * 
 * @author Martin Šimko
 * @version 1.0.0
 */
public class Strelec extends Figurka {
    
    private Obrazok panacikObrazok;
    private String panacikBielaCesta = "pics/bielyStrelec.png";
    private String panacikCiernaCesta = "pics/ciernyStrelec.png";   
    private boolean jeOznacena = false;
    private GameController gameController;
/**
*   Slúži na nastavenie materiálovej hodnoty príslušnej figúrke, priradí sa referencia na gameController a na políčko, kde sa figurka na začiatku hry
*   pôvodne nachádza. Podľa pozície sa nastaví farba figúrky a obrázok figurky sa vykreslí na plátno.
*   @param povodnePolicko políčko, kde sa figúrka nachádza na začiatku hry
*   @param gameController referencia na gameController
*/
    public Strelec(Policko povodnePolicko, GameController gameController) {
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
        if (this.getJeBiela() == this.gameController.getBielaNaTahu() && !this.getPinKolmo() && !this.getPinVodorovne()) {
            int posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
            int posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1];
            while (posX <= 7 && posY <= 7 && !this.getPinHoreVlavo()) {
                if (this.gameController.getSachovnica()[posX][posY].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX][posY].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                        break;
                    } else {
                        this.getMozneTahy().add(new int[] {posX + 1, posY + 1});
                        break;
                    }
                    
                } else {
                    this.getMozneTahy().add(new int[] {posX + 1, posY + 1});
                    posX++;
                    posY++;
                }
            }           
            posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
            posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1];            
            while (posX >= 0 && posY >= 0 && !this.getPinHoreVlavo()) {
                if (posX - 2 < 0 || posY - 2 < 0) { 
                    break;
                }
                if (this.gameController.getSachovnica()[posX - 2][posY - 2].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 2][posY - 2].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                        break;
                    } else {
                        this.getMozneTahy().add(new int[] {posX - 2 + 1, posY - 2 + 1});
                        break;
                    }
                    
                } else {
                    this.getMozneTahy().add(new int[] {posX - 2 + 1, posY - 2 + 1});
                    posX--;
                    posY--;
                }
            }
            
            posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
            posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1];
            
            while (posX <= 7 && posY >= 0 && !this.getPinDoleVlavo()) {
                if (posY - 2 < 0) { 
                    break;
                }
                if (this.gameController.getSachovnica()[posX][posY - 2].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX][posY - 2].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                        break;
                    } else {
                        this.getMozneTahy().add(new int[] {posX + 1, posY - 2 + 1});
                        break;
                    }
                    
                } else {
                    this.getMozneTahy().add(new int[] {posX + 1, posY - 2 + 1});
                    posX++;
                    posY--;
                }
            }
            
            posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
            posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1];
            
            while (posX >= 0 && posY <= 7 && !this.getPinDoleVlavo()) {
                if (posX - 2 < 0) { 
                    break;
                }
                if (this.gameController.getSachovnica()[posX - 2][posY].getJeObsadene()) {
                    if (this.gameController.getSachovnica()[posX - 2][posY].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                        break;
                    } else {
                        this.getMozneTahy().add(new int[] {posX - 2 + 1, posY + 1});
                        break;
                    }
                    
                } else {
                    this.getMozneTahy().add(new int[] {posX - 2 + 1, posY + 1 });
                    posX--;
                    posY++;
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
        while (posX <= 7 && posY <= 7) {
            if (this.gameController.getSachovnica()[posX][posY].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX][posY].getFigurkaNaPolicku().getHodnotaFigurky() == 0) {
                    this.getOhrozenePolicka().add(new int[] {posX + 1, posY + 1});
                    posX++;
                    posY++;
                    continue;
                }
                this.getOhrozenePolicka().add(new int[] {posX + 1, posY + 1});
                break;
                    
            } else {
                this.getOhrozenePolicka().add(new int[] {posX + 1, posY + 1});
                posX++;
                posY++;
            }
        }
            
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1];
            
        while (posX >= 0 && posY >= 0) {
            if (posX - 2 < 0 || posY - 2 < 0) { 
                break;
            }
            if (this.gameController.getSachovnica()[posX - 2][posY - 2].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX - 2][posY - 2].getFigurkaNaPolicku().getHodnotaFigurky() == 0) {
                    this.getOhrozenePolicka().add(new int[] {posX - 2 + 1, posY - 2 + 1});
                    posX--;
                    posY--;
                    continue;
                }
                this.getOhrozenePolicka().add(new int[] {posX - 2 + 1, posY - 2 + 1});
                break;                    
            } else {
                this.getOhrozenePolicka().add(new int[] {posX - 2 + 1, posY - 2 + 1});
                posX--;
                posY--;
            }
        }        
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1]; 
        while (posX <= 7 && posY >= 0) {
            if (posY - 2 < 0) { 
                break;
            }
            if (this.gameController.getSachovnica()[posX][posY - 2].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX][posY - 2].getFigurkaNaPolicku().getHodnotaFigurky() == 0) {
                    this.getOhrozenePolicka().add(new int[] {posX + 1, posY - 2 + 1});
                    posX++;
                    posY--;
                    continue;
                }
                this.getOhrozenePolicka().add(new int[] {posX + 1, posY - 2 + 1});
                break;                   
            } else {
                this.getOhrozenePolicka().add(new int[] {posX + 1, posY - 2 + 1});
                posX++;
                posY--;
            }
        }        
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0];
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1];           
        while (posX >= 0 && posY <= 7) {
            if (posX - 2 < 0) { 
                break;
            }
            if (this.gameController.getSachovnica()[posX - 2][posY].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX - 2][posY].getFigurkaNaPolicku().getHodnotaFigurky() == 0) {
                    this.getOhrozenePolicka().add(new int[] {posX - 2 + 1, posY + 1});
                    posX--;
                    posY++;
                    continue;
                }
                this.getOhrozenePolicka().add(new int[] {posX - 2 + 1, posY + 1});
                break;
                    
            } else {
                this.getOhrozenePolicka().add(new int[] {posX - 2 + 1, posY + 1 });
                posX--;
                posY++;
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
}
