import java.util.ArrayList;
import fri.shapesge.Obrazok;
/**
 * Upravuje metódy z triedy Figurka, aby sa inštancia chovala ako kráľ. Navyše zodpovedá za vykresľovanie obrázku na plátno a za pinovanie figúriek.
 * 
 * @author Martin Šimko
 * @version 1.0.0
 */
public class Kral extends Figurka {
    /**
     * Constructor for objects of class Kral
     */
    private Obrazok panacikObrazok;
    private String panacikBielaCesta = "pics/bielyKral.png";
    private String panacikCiernaCesta = "pics/ciernyKral.png";
    private boolean jeOznacena = false;
    private boolean jeNepohnuty = true;
    private boolean jeOhrozeny = false;
    private GameController gameController;
    private ArrayList<int[]> vsetkyOhrozenePolicka;
    private ArrayList<int[]> noveMozneTahy;
    private boolean neohrozenePolickaNaRosaduK = true;
    private boolean neohrozenePolickaNaRosaduQ = true;
    private boolean jeNepohnuta = true;

/**
*   Slúži na nastavenie materiálovej hodnoty príslušnej figúrke, priradení priradí sa referencia na gameController a na políčko, kde sa figurka na začiatku hry
*   pôvodne nachádza. Podľa pozície sa nastaví farba figurky a obrazok figurky sa vykreslí na plátno.
*   @param povodnePolicko políčko, kde sa figúrka nachádza na začiatku hry
*   @param gameController referencia na gameController
*/    
    public Kral(Policko povodnePolicko, GameController gameController) {
        this.setHodnotaFigurky(0);
        this.setPolickoKdeJeFigurka(povodnePolicko);
        this.gameController = gameController;
        this.noveMozneTahy = new ArrayList<int[]>();
        this.vsetkyOhrozenePolicka = new ArrayList<int[]>();
        this.neohrozenePolickaNaRosaduK = true;
        this.neohrozenePolickaNaRosaduQ = true;
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
        this.vsetkyOhrozenePolicka.clear();
        this.noveMozneTahy.clear();
        if (this.getJeBiela() == this.gameController.getBielaNaTahu()) {
            int posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
            int posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
            if (posY < 7 && !this.gameController.getSachovnica()[posX][posY + 1].getJeObsadene()) {
                this.getMozneTahy().add(new int[] {posX + 1, posY + 1 + 1});  
            }
            if (posY < 7 && this.gameController.getSachovnica()[posX][posY + 1].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX][posY + 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getMozneTahy().add(new int[] {posX + 1, posY + 1 + 1});
                }
            }
            if (posY < 7 && posX < 7 && !this.gameController.getSachovnica()[posX + 1][posY + 1].getJeObsadene()) {
                this.getMozneTahy().add(new int[] {posX + 1 + 1, posY + 1 + 1});  
            }
            if (posY < 7 && posX < 7 && this.gameController.getSachovnica()[posX + 1][posY + 1].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX + 1][posY + 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getMozneTahy().add(new int[] {posX + 1 + 1, posY + 1 + 1});
                }
            }
            if (posX < 7 && !this.gameController.getSachovnica()[posX + 1][posY].getJeObsadene()) {
                this.getMozneTahy().add(new int[] {posX + 1 + 1, posY + 1});  
            }
            if (posX < 7 && this.gameController.getSachovnica()[posX + 1][posY].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX + 1][posY].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getMozneTahy().add(new int[] {posX + 1 + 1, posY + 1});
                }  
            }
            if (posX > 0 && !this.gameController.getSachovnica()[posX - 1][posY].getJeObsadene()) {
                this.getMozneTahy().add(new int[] {posX - 1 + 1, posY + 1});  
            }
            if (posX > 0 && this.gameController.getSachovnica()[posX - 1][posY].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX - 1][posY].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getMozneTahy().add(new int[] {posX - 1 + 1, posY + 1});
                }  
            }
            if (posX > 0 && posY > 0 && !this.gameController.getSachovnica()[posX - 1][posY - 1].getJeObsadene()) {
                this.getMozneTahy().add(new int[] {posX - 1 + 1, posY - 1 + 1});  
            }
            if (posX > 0 && posY > 0 && this.gameController.getSachovnica()[posX - 1][posY - 1].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX - 1][posY - 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getMozneTahy().add(new int[] {posX - 1 + 1, posY - 1 + 1});
                }  
            }
            if (posX < 7 && posY > 0 && !this.gameController.getSachovnica()[posX + 1][posY - 1].getJeObsadene()) {
                this.getMozneTahy().add(new int[] {posX + 1 + 1, posY - 1 + 1});  
            }
            if (posX < 7 && posY > 0 && this.gameController.getSachovnica()[posX + 1][posY - 1].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX + 1][posY - 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getMozneTahy().add(new int[] {posX + 1 + 1, posY - 1 + 1});
                }  
            }
            if (posX > 0 && posY < 7 && !this.gameController.getSachovnica()[posX - 1][posY + 1].getJeObsadene()) {
                this.getMozneTahy().add(new int[] {posX - 1 + 1, posY + 1 + 1});  
            }
            if (posX > 0 && posY < 7 && this.gameController.getSachovnica()[posX - 1][posY + 1].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX - 1][posY + 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getMozneTahy().add(new int[] {posX - 1 + 1, posY + 1 + 1});
                }  
            }
            if (posY > 0 && !this.gameController.getSachovnica()[posX][posY - 1].getJeObsadene()) {
                this.getMozneTahy().add(new int[] {posX + 1, posY - 1 + 1});  
            }
            if (posY > 0 && this.gameController.getSachovnica()[posX][posY - 1].getJeObsadene()) {
                if (this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()) {
                    this.getMozneTahy().add(new int[] {posX + 1, posY - 1 + 1});
                }  
            }
        }
        
        if (this.getJeBiela()) { // vymazanie moznych tahov krala na policka ktore su ohrozene (tah by bol nelegalny)
            this.vsetkyOhrozenePolicka = this.gameController.getOhrozenePolickaCierne();
        } else {
            this.vsetkyOhrozenePolicka = this.gameController.getOhrozenePolickaBiele();
        } 
        for (int[] suradniceMozT : this.getMozneTahy()) {
            boolean b = true;
            this.neohrozenePolickaNaRosaduK = true;
            this.neohrozenePolickaNaRosaduQ = true;
            for (int[] suradniceOhrP : this.vsetkyOhrozenePolicka) {
                if (suradniceMozT[0] == suradniceOhrP[0] && suradniceMozT[1] == suradniceOhrP[1]) {
                    b = false;  
                }
                if (!this.getJeBiela()) {
                    if (suradniceOhrP[0] == 5 && suradniceOhrP[1] == 8 || 
                        suradniceOhrP[0] == 6 && suradniceOhrP[1] == 8 ||
                        suradniceOhrP[0] == 7 && suradniceOhrP[1] == 8) {
                        this.neohrozenePolickaNaRosaduK = false;
                    }
                }
                if (this.getJeBiela()) {
                    if (suradniceOhrP[0] == 5 && suradniceOhrP[1] == 1 || 
                        suradniceOhrP[0] == 6 && suradniceOhrP[1] == 1 ||
                        suradniceOhrP[0] == 7 && suradniceOhrP[1] == 1) {
                        this.neohrozenePolickaNaRosaduK = false;
                    }
                }
                if (!this.getJeBiela()) {
                    if (suradniceOhrP[0] == 5 && suradniceOhrP[1] == 8 || 
                        suradniceOhrP[0] == 4 && suradniceOhrP[1] == 8 ||
                        suradniceOhrP[0] == 3 && suradniceOhrP[1] == 8 ||
                        suradniceOhrP[0] == 2 && suradniceOhrP[1] == 8) {
                        this.neohrozenePolickaNaRosaduQ = false;
                    }
                }
                if (this.getJeBiela()) {
                    if (suradniceOhrP[0] == 5 && suradniceOhrP[1] == 1 || 
                        suradniceOhrP[0] == 4 && suradniceOhrP[1] == 1 ||
                        suradniceOhrP[0] == 3 && suradniceOhrP[1] == 1 ||
                        suradniceOhrP[0] == 2 && suradniceOhrP[1] == 1) {
                        this.neohrozenePolickaNaRosaduQ = false;
                    }
                }
            }
            if (b) {
                this.noveMozneTahy.add(suradniceMozT);
            }
        }
        if (this.moznaKingSideRosada()) {
            if (this.getJeBiela() && this.getJeBiela() == this.gameController.getBielaNaTahu()) {
                this.noveMozneTahy.add(new int[] {8, 1, 81}); // tretie cislo mam aby som dokazal odlisit bezne tahy od specialnych, ako napr rosada
            }
            if (!this.getJeBiela() && this.getJeBiela() == this.gameController.getBielaNaTahu()) {
                this.noveMozneTahy.add(new int[] {8, 8, 88});
            }
        }
        if (this.moznaQueenSideRosada()) {
            if (this.getJeBiela() && this.getJeBiela() == this.gameController.getBielaNaTahu()) {
                this.noveMozneTahy.add(new int[] {1, 1, 11}); // tretie cislo mam aby som dokazal odlisit bezne tahy od specialnych, ako napr rosada
            }
            if (!this.getJeBiela() && this.getJeBiela() == this.gameController.getBielaNaTahu()) {
                this.noveMozneTahy.add(new int[] {1, 8, 18});
            }
        }
        return this.noveMozneTahy;
    }
/**
 * Vygeneruje a vráti všetky políčka, ktoré daná figúrka ohrozuje. 
 */    
    public ArrayList<int[]> vygenerujOhrozenePolicka() {
        this.getOhrozenePolicka().clear();
        int posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        int posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        if (posY < 7 ) {
            this.getOhrozenePolicka().add(new int[] {posX + 1, posY + 1 + 1});  
        }
        if (posY < 7 && posX < 7) {
            this.getOhrozenePolicka().add(new int[] {posX + 1 + 1, posY + 1 + 1});  
        }
        if (posX < 7) {
            this.getOhrozenePolicka().add(new int[] {posX + 1 + 1, posY + 1});  
        }
        if (posX > 0) {
            this.getOhrozenePolicka().add(new int[] {posX - 1 + 1, posY + 1});  
        }
        if (posX > 0 && posY > 0) {
            this.getOhrozenePolicka().add(new int[] {posX - 1 + 1, posY - 1 + 1});  
        }
        if (posX < 7 && posY > 0) {
            this.getOhrozenePolicka().add(new int[] {posX + 1 + 1, posY - 1 + 1});  
        }
        if (posX > 0 && posY < 7) {
            this.getOhrozenePolicka().add(new int[] {posX - 1 + 1, posY + 1 + 1});  
        }
        if (posY > 0) {
            this.getOhrozenePolicka().add(new int[] {posX + 1, posY - 1 + 1});  
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
 * Vráti hodnotu true pokiaľ je možné urobiť krátku rošádu (rošádu na stranu kráľa)
 */    
    private boolean moznaKingSideRosada() {
        boolean value = false;
        if (!this.getJeBiela()) {        
            if (!this.gameController.getSachovnica()[6][7].getJeObsadene() &&
                !this.gameController.getSachovnica()[5][7].getJeObsadene() &&
                this.gameController.getSachovnica()[7][7].getJeObsadene() &&
                this.gameController.getSachovnica()[7][7].getFigurkaNaPolicku().getHodnotaFigurky() == 5 &&
                this.gameController.getSachovnica()[7][7].getFigurkaNaPolicku().getJePosunuta() &&
                this.getJePosunuta() && this.neohrozenePolickaNaRosaduK) {
                value = true;
            }
        } else {          
            if (!this.gameController.getSachovnica()[6][0].getJeObsadene() &&
                !this.gameController.getSachovnica()[5][0].getJeObsadene() &&
                this.gameController.getSachovnica()[7][0].getJeObsadene() &&
                this.gameController.getSachovnica()[7][0].getFigurkaNaPolicku().getHodnotaFigurky() == 5 &&
                this.gameController.getSachovnica()[7][0].getFigurkaNaPolicku().getJePosunuta() &&
                this.getJePosunuta() && this.neohrozenePolickaNaRosaduK) {
                value = true;
            }
        }
        return value;
    }
/**
 * Vráti hodnotu true pokiaľ je možné urobiť dlhú rošádu (rošádu na stranu kráľovny)
 */    
    private boolean moznaQueenSideRosada() {
        boolean value = false;
        if (!this.getJeBiela()) {          
            if (!this.gameController.getSachovnica()[3][7].getJeObsadene() &&
                !this.gameController.getSachovnica()[2][7].getJeObsadene() &&
                !this.gameController.getSachovnica()[1][7].getJeObsadene() &&
                this.gameController.getSachovnica()[0][7].getJeObsadene() &&
                this.gameController.getSachovnica()[0][7].getFigurkaNaPolicku().getHodnotaFigurky() == 5 &&
                this.gameController.getSachovnica()[0][7].getFigurkaNaPolicku().getJePosunuta() &&
                this.getJePosunuta() && this.neohrozenePolickaNaRosaduQ) {
                value = true;
            }
        } else {       
            if (!this.gameController.getSachovnica()[3][0].getJeObsadene() &&
                !this.gameController.getSachovnica()[2][0].getJeObsadene() &&
                !this.gameController.getSachovnica()[1][0].getJeObsadene() &&
                this.gameController.getSachovnica()[0][0].getJeObsadene() &&
                this.gameController.getSachovnica()[0][0].getFigurkaNaPolicku().getHodnotaFigurky() == 5 &&
                this.gameController.getSachovnica()[0][0].getFigurkaNaPolicku().getJePosunuta() &&
                this.getJePosunuta() && this.neohrozenePolickaNaRosaduQ) {
                value = true;
            }
        }
        return value;
    }
/**
 * Pinuje figúrky 
 */    
    public void pinujFigurky() {
        int posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        int posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        Figurka adeptNaPin = null;
        while (posX + 1 <= 7) { 
            if (!this.gameController.getSachovnica()[posX + 1][posY].getJeObsadene()) {
                posX++;
            } else {
                if (adeptNaPin == null && this.gameController.getSachovnica()[posX + 1][posY].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                    adeptNaPin = this.gameController.getSachovnica()[posX + 1][posY].getFigurkaNaPolicku();                   
                    posX++;
                } else {
                    if (adeptNaPin != null && this.gameController.getSachovnica()[posX + 1][posY].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()
                        && (this.gameController.getSachovnica()[posX + 1][posY].getFigurkaNaPolicku().getHodnotaFigurky() == 5
                        || this.gameController.getSachovnica()[posX + 1][posY].getFigurkaNaPolicku().getHodnotaFigurky() == 10)) {
                        adeptNaPin.setPinVodorovne(true);
                        break;                       
                    } else {
                        break;
                    }
                }
                
            }
        }
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        adeptNaPin = null;
        while (posX - 1 >= 0) { 
            if (!this.gameController.getSachovnica()[posX - 1][posY].getJeObsadene()) {
                posX--;
            } else {
                if (adeptNaPin == null && this.gameController.getSachovnica()[posX - 1][posY].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                    adeptNaPin = this.gameController.getSachovnica()[posX - 1][posY].getFigurkaNaPolicku();
                    posX--;
                } else {
                    if (adeptNaPin != null && this.gameController.getSachovnica()[posX - 1][posY].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()
                        && (this.gameController.getSachovnica()[posX - 1][posY].getFigurkaNaPolicku().getHodnotaFigurky() == 5
                        || this.gameController.getSachovnica()[posX - 1][posY].getFigurkaNaPolicku().getHodnotaFigurky() == 10)) {
                        adeptNaPin.setPinVodorovne(true);                       
                        break;
                    } else {
                        break;
                    }
                }                
            }
        }
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        adeptNaPin = null;
        while (posY + 1 <= 7) { 
            if (!this.gameController.getSachovnica()[posX ][posY + 1].getJeObsadene()) {
                posY++;
            } else {
                if (adeptNaPin == null && this.gameController.getSachovnica()[posX][posY + 1].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                    adeptNaPin = this.gameController.getSachovnica()[posX][posY + 1].getFigurkaNaPolicku();
                    posY++;
                } else {
                    if (adeptNaPin != null && this.gameController.getSachovnica()[posX][posY + 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()
                        && (this.gameController.getSachovnica()[posX][posY + 1].getFigurkaNaPolicku().getHodnotaFigurky() == 5
                        || this.gameController.getSachovnica()[posX][posY + 1].getFigurkaNaPolicku().getHodnotaFigurky() == 10)) {
                        adeptNaPin.setPinKolmo(true);
                        break;
                    } else {
                        break;
                    }
                }                
            }
        }
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        adeptNaPin = null;
        while (posY - 1 >= 0) {
            if (!this.gameController.getSachovnica()[posX ][posY - 1].getJeObsadene()) {
                posY--;
            } else {
                if (adeptNaPin == null && this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                    adeptNaPin = this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku();
                    posY--;
                } else {
                    if (adeptNaPin != null && this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()
                        && (this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getHodnotaFigurky() == 5
                        || this.gameController.getSachovnica()[posX][posY - 1].getFigurkaNaPolicku().getHodnotaFigurky() == 10)) {
                        adeptNaPin.setPinKolmo(true);
                        break;
                    } else {
                        break;
                    }
                }               
            }
        }
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        adeptNaPin = null;
        while (posX + 1 <= 7 && posY + 1 <= 7) { // diagonala z dolneho laveho rohu ku opacnemu
            if (!this.gameController.getSachovnica()[posX + 1][posY + 1].getJeObsadene()) {
                posY++;
                posX++;
            } else {
                if (adeptNaPin == null && this.gameController.getSachovnica()[posX + 1][posY + 1].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                    adeptNaPin = this.gameController.getSachovnica()[posX + 1][posY + 1].getFigurkaNaPolicku();
                    posY++;
                    posX++;
                } else {
                    if (adeptNaPin != null && this.gameController.getSachovnica()[posX + 1][posY + 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()
                        && (this.gameController.getSachovnica()[posX + 1][posY + 1].getFigurkaNaPolicku().getHodnotaFigurky() == 10
                        || this.gameController.getSachovnica()[posX + 1][posY + 1].getFigurkaNaPolicku().getHodnotaFigurky() == 3)
                        && this.gameController.getSachovnica()[posX + 1][posY + 1].getFigurkaNaPolicku().getTypFigurky().equals("nie kon")) {
                        adeptNaPin.setPinDoleVlavo(true);                        
                        break;
                    } else {
                        break;
                    }
                }
                
            }
        }
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        adeptNaPin = null;
        while (posX - 1 >= 0 && posY - 1 >= 0) { // diagonala z dolneho laveho rohu ku opacnemu
            if (!this.gameController.getSachovnica()[posX - 1][posY - 1].getJeObsadene()) {
                posY--;
                posX--;
            } else {
                if (adeptNaPin == null && this.gameController.getSachovnica()[posX - 1][posY - 1].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                    adeptNaPin = this.gameController.getSachovnica()[posX - 1][posY - 1].getFigurkaNaPolicku();
                    posY--;
                    posX--;
                } else {
                    if (adeptNaPin != null && this.gameController.getSachovnica()[posX - 1][posY - 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()
                        && (this.gameController.getSachovnica()[posX - 1][posY - 1].getFigurkaNaPolicku().getHodnotaFigurky() == 10
                        || this.gameController.getSachovnica()[posX - 1][posY - 1].getFigurkaNaPolicku().getHodnotaFigurky() == 3)
                        && this.gameController.getSachovnica()[posX - 1][posY - 1].getFigurkaNaPolicku().getTypFigurky().equals("nie kon")) {
                        adeptNaPin.setPinDoleVlavo(true);
                        break;
                    } else {
                        break;
                    }
                }
                
            }
        }
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        adeptNaPin = null;
        while (posX + 1 <= 7 && posY - 1 >= 0) { // diagonala z horneho laveho rohu ku opacnemu
            if (!this.gameController.getSachovnica()[posX + 1][posY - 1].getJeObsadene()) {
                posY--;
                posX++;
            } else {
                if (adeptNaPin == null && this.gameController.getSachovnica()[posX + 1][posY - 1].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                    adeptNaPin = this.gameController.getSachovnica()[posX + 1][posY - 1].getFigurkaNaPolicku();
                    posY--;
                    posX++;
                } else {
                    if (adeptNaPin != null && this.gameController.getSachovnica()[posX + 1][posY - 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()
                        && (this.gameController.getSachovnica()[posX + 1][posY - 1].getFigurkaNaPolicku().getHodnotaFigurky() == 10
                        || this.gameController.getSachovnica()[posX + 1][posY - 1].getFigurkaNaPolicku().getHodnotaFigurky() == 3)
                        && this.gameController.getSachovnica()[posX + 1][posY - 1].getFigurkaNaPolicku().getTypFigurky().equals("nie kon")) {
                        adeptNaPin.setPinHoreVlavo(true);
                        break;
                    } else {
                        break;
                    }
                }             
            }
        }
        posX = this.getPolickoKdeJeFigurka().getSuradniceXY()[0] - 1;
        posY = this.getPolickoKdeJeFigurka().getSuradniceXY()[1] - 1;
        adeptNaPin = null;
        while (posX - 1 >= 0 && posY + 1 <= 7) { // diagonala z horneho laveho rohu ku opacnemu<
            if (!this.gameController.getSachovnica()[posX - 1][posY + 1].getJeObsadene()) {
                posY++;
                posX--;
            } else {
                if (adeptNaPin == null && this.gameController.getSachovnica()[posX - 1][posY + 1].getFigurkaNaPolicku().getJeBiela() == this.getJeBiela()) {
                    adeptNaPin = this.gameController.getSachovnica()[posX - 1][posY + 1].getFigurkaNaPolicku();
                    posY++;
                    posX--;
                } else {
                    if (adeptNaPin != null && this.gameController.getSachovnica()[posX - 1][posY + 1].getFigurkaNaPolicku().getJeBiela() != this.getJeBiela()
                        && (this.gameController.getSachovnica()[posX - 1][posY + 1].getFigurkaNaPolicku().getHodnotaFigurky() == 10
                        || this.gameController.getSachovnica()[posX - 1][posY + 1].getFigurkaNaPolicku().getHodnotaFigurky() == 3)
                        && this.gameController.getSachovnica()[posX - 1][posY + 1].getFigurkaNaPolicku().getTypFigurky().equals("nie kon")) {
                        adeptNaPin.setPinHoreVlavo(true);
                        break;
                    } else {
                        break;
                    }
                }
                
            }
        }
        
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
/**
 * Vymaže obrázok figurky z plátna. 
 */    
    public void vyhodFigurku() {
        this.panacikObrazok.skry();
    }
    
}