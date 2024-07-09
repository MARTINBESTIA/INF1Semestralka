import fri.shapesge.Manazer;
import java.util.ArrayList;
import fri.shapesge.Obrazok;
import javax.swing.JOptionPane;
/**
 * Trieda sa stará o vytvorenie šachovnice s figúrkami, pohybovanie figúriek prostredníctvom myši, zobrazovanie možných ťahov po kliknutí na figurku, vytvára pre figúrky množiny legálnych a nelegálnych ťahov,
 * ktoré upravujú finálny počet ťahov, kde sa môže daná figúrka pohnúť, zabezpečuje všetky herné stavy (šach, šach-mat, remíza, opakovanie celej hry). 
 * . 
 * 
 * @author Martin Šimko
 * @version 1.0.0
 */


public class GameController {
    
    private Policko[][] sachovnica; 
    private Manazer manazer;
    private GamePanel gamePanel;
    private Figurka oznacenaFigurka;
    private static final String CESTA_OBRAZOK_MOZNE_TAHY = "pics/redCircle.png";
    private ArrayList<int[]> mozneTahy;
    private ArrayList<int[]> ohrozenePolickaBiele;
    private ArrayList<int[]> ohrozenePolickaCierne;
    private ArrayList<int[]> mozneTahyBiele;
    private ArrayList<int[]> mozneTahyCierne;
    private ArrayList<int[]> mozneTahyPocasSachu;
    private ArrayList<int[]> mozneTahyBielychPocasSachu;
    private ArrayList<int[]> mozneTahyCiernychPocasSachu;
    private ArrayList<Obrazok> obrazkyMozneTahy;    
    private boolean bielaNaTahu;
    private boolean farbaVSachu;
    private boolean jeSach = false;
    private boolean freezeGame = false;  
    
/**
 * Dá spravovať inštanciu manažerom, aby dokáza čítať vstupy z myšky, vygeneruje šachovnicu s figúrkami a vytvorí herný panel. 
 */
            
    public GameController() {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.generujSachovnicu();
        this.gamePanel = new GamePanel(this);
    }
/**
 * Po kliknutí myši označí figurku a zobrazí jej možné ťahý. Pokiaľ je už nejaká figurka označená a používateľ klikne na políčko, na ktoré 
 * sa figúrka môže presunúť, presunie sa tam. Taktiež strieda ťahy medzi čiernym a bielym, 
 * @param mysX pozícia po kliknutí myši na osi X
 * @param mysY pozícia po kliknutí myši na osi Y
 */   
    public void oznacPolicko(int mysX, int mysY) {
        this.najdiOhrozenePolickaBiela();
        this.najdiOhrozenePolickaCierna();
        int poziciaPolickaX = 0;
        int poziciaPolickaY = 0;
        if (mysY >= 0 && mysY < 80) { 
            poziciaPolickaY = 8;
        } 
        if (mysY >= 80 && mysY < 160) { 
            poziciaPolickaY = 7;
        }
        if (mysY >= 160 && mysY < 240) { 
            poziciaPolickaY = 6;
        }
        if (mysY >= 240 && mysY < 320) { 
            poziciaPolickaY = 5;
        }
        if (mysY >= 320 && mysY < 400) { 
            poziciaPolickaY = 4;
        }
        if (mysY >= 400 && mysY < 480) { 
            poziciaPolickaY = 3;
        }
        if (mysY >= 480 && mysY < 560) { 
            poziciaPolickaY = 2;
        }
        if (mysY >= 560 && mysY < 640) { 
            poziciaPolickaY = 1;
        }       
        if (mysX >= 180 && mysX < 260) { 
            poziciaPolickaX = 1;
        }
        if (mysX >= 260 && mysX < 340) { 
            poziciaPolickaX = 2;
        }
        if (mysX >= 340 && mysX < 420) { 
            poziciaPolickaX = 3;
        }
        if (mysX >= 420 && mysX < 500) { 
            poziciaPolickaX = 4;
        }
        if (mysX >= 500 && mysX < 580) { 
            poziciaPolickaX = 5;
        }
        if (mysX >= 580 && mysX < 660) { 
            poziciaPolickaX = 6;
        }
        if (mysX >= 660 && mysX < 740) { 
            poziciaPolickaX = 7;
        }
        if (mysX >= 740 && mysX < 820) { 
            poziciaPolickaX = 8;
        }
        if ((mysX >= 10 && mysX <= 110) && (mysY >= 720 && mysY < 760)) {           
            int moznost = JOptionPane.showConfirmDialog(null, "Naozaj chcete hrať odznovu ?");
            switch (moznost) {
                case 0:
                    this.generujSachovnicu();
                    break;
            }
        }
        if (this.freezeGame) {
            poziciaPolickaX = 0;
            poziciaPolickaY = 0;
        }
        if (!this.mozneTahy.isEmpty()) {  // Ak kliknem na policko s moznym tahom ked mam oznacenu figurku, oznacena figurka sa presunie na kliknute policko
            for (int[] sur: this.mozneTahy) {
                if (poziciaPolickaX == sur[0] && poziciaPolickaY == sur[1]) {
                    if (sur.length == 3) {  // mam specialny tah
                        if (sur[2] == 1 && this.bielaNaTahu) {
                            this.vyhodFigurkuPoEnPassant(this.sachovnica[sur[0] - 1][sur[1] - 2]);
                        }
                        if (sur[2] == 1 && !this.bielaNaTahu) {
                            this.vyhodFigurkuPoEnPassant(this.sachovnica[sur[0] - 1][sur[1]]);
                        }
                        if (sur[2] == 99) {
                            this.oznacenaFigurka.setEnPassantTrue();
                        }
                    }
                    if (sur.length == 3 && this.bielaNaTahu) {  
                        if (sur[2] == 81) {
                            this.bielaKingSideRosada();
                            return;
                        }
                    }
                    if (sur.length == 3 && !this.bielaNaTahu) {  
                        if (sur[2] == 88) {
                            this.ciernaKingSideRosada();
                            return;
                        }
                    }
                    if (sur.length == 3 && this.bielaNaTahu) {  
                        if (sur[2] == 11) {
                            this.bielaQueenSideRosada();
                            return;
                        }
                    }
                    if (sur.length == 3 && !this.bielaNaTahu) {  
                        if (sur[2] == 18) {
                            this.ciernaQueenSideRosada();
                            return;
                        }
                    }
                    if (this.sachovnica[poziciaPolickaX - 1][poziciaPolickaY - 1].getJeObsadene()) {
                        this.sachovnica[poziciaPolickaX - 1][poziciaPolickaY - 1].getFigurkaNaPolicku().vyhodFigurku();
                    }
                    this.oznacenaFigurka.presunFigurku(this.sachovnica[poziciaPolickaX - 1][poziciaPolickaY - 1]);
                    this.oznacenaFigurka.odoznacFigurku();
                    if (this.oznacenaFigurka.getHodnotaFigurky() == 5 || this.oznacenaFigurka.getHodnotaFigurky() == 0) { // monitorujem posunutia kvoli rosadam
                        this.oznacenaFigurka.setJePosunuta();
                    }
                    this.oznacenaFigurka = null;
                    this.jeSach = false;
                    this.mozneTahy.clear();                   
                    if (this.bielaNaTahu) {
                        this.setCierneEnPassantsFalse();
                        this.bielaNaTahu = false;                  
                    } else {
                        this.setBieleEnPassantsFalse();
                        this.bielaNaTahu = true;
                    }
                    for (Obrazok obrazok : this.obrazkyMozneTahy) {
                        obrazok.skry();                  
                    }
                    this.checkPesiakPromotion();
                    this.odpinujFigurky();
                    this.pinujFigurkyCierne();
                    this.pinujFigurkyBiele();
                    this.hladajSachBiela();
                    this.hladajSachCierna();
                    this.gamePanel.zobrazStranuNaTahu();
                    this.gamePanel.zobrazMaterialDiff();
                    this.checkRemiza();
                    return;
                }
            }
        }
        this.mozneTahy.clear();       
        for (Obrazok obrazok : this.obrazkyMozneTahy) {
            obrazok.skry();
        }       
        this.obrazkyMozneTahy.clear();        
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getFigurkaNaPolicku() != null) {
                    policko.getFigurkaNaPolicku().odoznacFigurku();
                }
            }
        }
        if (poziciaPolickaX != 0 && poziciaPolickaY != 0) {
            if (this.sachovnica[poziciaPolickaX - 1][poziciaPolickaY - 1].getJeObsadene()) {
                this.sachovnica[poziciaPolickaX - 1][poziciaPolickaY - 1].getFigurkaNaPolicku().oznacFigurku();
                this.oznacenaFigurka = this.sachovnica[poziciaPolickaX - 1][poziciaPolickaY - 1].getFigurkaNaPolicku();
                this.mozneTahy = this.sachovnica[poziciaPolickaX - 1][poziciaPolickaY - 1].getFigurkaNaPolicku().vygenerujMozneTahy();
            }
        }
        for (int[] sur: this.mozneTahy) {
            this.obrazkyMozneTahy.add(new Obrazok(this.CESTA_OBRAZOK_MOZNE_TAHY, 180 + (sur[0] - 1) * 80, 560 - (sur[1] - 1) * 80));
        }     
        for (Obrazok obrazok : this.obrazkyMozneTahy) {
            obrazok.zobraz();
        }
        this.gamePanel.zobrazStranuNaTahu();
    }
/**
 * Vygeneruje figurky na príslušné počiatočné políčka.
 */       
    private void vygenerujFigurky() {
        for (int i = 1; i < 9; i++) {
            for (int e = 1; e < 9; e++) {
                if (e == 2 || e == 7) {
                    this.sachovnica[i - 1][e - 1].priradFigurku(new Pesiak(this.sachovnica[i - 1][e - 1], this));
                }
                if ((i == 3 && e == 1) || (i == 3 && e == 8) || (i == 6 && e == 1) || (i == 6 && e == 8)) {
                    this.sachovnica[i - 1][e - 1].priradFigurku(new Strelec(this.sachovnica[i - 1][e - 1], this));
                }
                if ((i == 2 && e == 1) || (i == 7 && e == 1) || (i == 2 && e == 8) || (i == 7 && e == 8)) {
                    this.sachovnica[i - 1][e - 1].priradFigurku(new Kon(this.sachovnica[i - 1][e - 1], this));
                }
                if ((i == 1 && e == 1) || (i == 1 && e == 8) || (i == 8 && e == 8) || (i == 8 && e == 1)) {
                    this.sachovnica[i - 1][e - 1].priradFigurku(new Veza(this.sachovnica[i - 1][e - 1], this));
                }
                if ((i == 4 && e == 1) || (i == 4 && e == 8)) {
                    this.sachovnica[i - 1][e - 1].priradFigurku(new Kralovna(this.sachovnica[i - 1][e - 1], this));
                }
                if ((i == 5 && e == 1) || (i == 5 && e == 8)) {
                    this.sachovnica[i - 1][e - 1].priradFigurku(new Kral(this.sachovnica[i - 1][e - 1], this));
                }
            }
        }        
    }
/**
 * Vytvorí novú šachovú partiu, starú šachovnicu zahodí a vytvorí novú. Všetko sa nastaví do počiatočných hodnôt.
 */
    private void generujSachovnicu() {
        if (this.sachovnica != null) {
            this.skrySachovnicu();
        }
        this.freezeGame = false;
        this.mozneTahy = new ArrayList<int[]>();
        this.ohrozenePolickaBiele = new ArrayList<int[]>();
        this.ohrozenePolickaCierne = new ArrayList<int[]>();
        this.mozneTahyBiele = new ArrayList<int[]>();
        this.mozneTahyCierne = new ArrayList<int[]>();
        this.mozneTahyPocasSachu = new ArrayList<int[]>();
        this.mozneTahyBielychPocasSachu = new ArrayList<int[]>();
        this.mozneTahyCiernychPocasSachu = new ArrayList<int[]>();
        this.obrazkyMozneTahy = new ArrayList<Obrazok>();
        this.sachovnica = new Policko[][]{{new Policko(1, 1), new Policko(1, 2), new Policko(1, 3), new Policko(1, 4), 
                                           new Policko(1, 5), new Policko(1, 6), new Policko(1, 7), new Policko(1, 8)}, 
                                          {new Policko(2, 1), new Policko(2, 2), new Policko(2, 3), new Policko(2, 4), 
                                           new Policko(2, 5), new Policko(2, 6), new Policko(2, 7), new Policko(2, 8)}, 
                                          {new Policko(3, 1), new Policko(3, 2), new Policko(3, 3), new Policko(3, 4), 
                                           new Policko(3, 5), new Policko(3, 6), new Policko(3, 7), new Policko(3, 8)}, 
                                          {new Policko(4, 1), new Policko(4, 2), new Policko(4, 3), new Policko(4, 4), 
                                           new Policko(4, 5), new Policko(4, 6), new Policko(4, 7), new Policko(4, 8)}, 
                                          {new Policko(5, 1), new Policko(5, 2), new Policko(5, 3), new Policko(5, 4), 
                                           new Policko(5, 5), new Policko(5, 6), new Policko(5, 7), new Policko(5, 8)}, 
                                          {new Policko(6, 1), new Policko(6, 2), new Policko(6, 3), new Policko(6, 4), 
                                           new Policko(6, 5), new Policko(6, 6), new Policko(6, 7), new Policko(6, 8)}, 
                                          {new Policko(7, 1), new Policko(7, 2), new Policko(7, 3), new Policko(7, 4), 
                                           new Policko(7, 5), new Policko(7, 6), new Policko(7, 7), new Policko(7, 8)}, 
                                          {new Policko(8, 1), new Policko(8, 2), new Policko(8, 3), new Policko(8, 4), 
                                           new Policko(8, 5), new Policko(8, 6), new Policko(8, 7), new Policko(8, 8)}};
        this.vygenerujFigurky();
        this.bielaNaTahu = true;
        if (this.gamePanel != null) {
            this.gamePanel.zobrazStranuNaTahu();
            this.gamePanel.zobrazMaterialDiff();
        }
    }
/**
* Vráti šachovnicu (Dvojrozmerné pole políčok).
*/   
    public Policko[][] getSachovnica() {
        return this.sachovnica;
    }
/**
* Vráti hodnotu true pokiaľ dáva nejaká figúrka šach kráľovi a naopak.
*/    
    public boolean getJeSach() {
        return this.jeSach;
    }
/**
* Vráti hodnotu true pokiaľ je biely kráľ v šachu, vráti false pokiaľ je čierny kráľ v šachu.
*/    
    public boolean getFarbaVSachu() { // biela = true, cierna = false
        return this.farbaVSachu;
    }
/**
* Vráti hodnotu true pokiaľ sú biele figúrky na ťahu, vráti false pokiaľ sú čierne figúrky na ťahu.
*/   
    public boolean getBielaNaTahu() {
        return this.bielaNaTahu;
    }
/**
* Vráti ArrayList všetkých možných ťahov ktorými sa môže biely svojími figúrkami pohnúť.
*/    
    public ArrayList<int[]> getMozneTahyBiela() {
        this.mozneTahyBiele.clear();
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {
                    if (policko.getFigurkaNaPolicku().getJeBiela()) {
                        for (int[] surPolicka : policko.getFigurkaNaPolicku().vygenerujMozneTahy()) {
                            this.mozneTahyBiele.add(surPolicka);
                        }
                    }
                }
            }
        }
        return this.mozneTahyBiele;
    }
/**
* Vráti ArrayList všetkých možných ťahov ktorými sa môže čierny svojími figúrkami pohnúť.
*/    
    public ArrayList<int[]> getMozneTahyCierna() {
        this.mozneTahyCierne.clear();
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {
                    if (!policko.getFigurkaNaPolicku().getJeBiela()) {
                        for (int[] surPolicka : policko.getFigurkaNaPolicku().vygenerujMozneTahy()) {
                            this.mozneTahyCierne.add(surPolicka);
                        }
                    }
                }
            }
        }
        return this.mozneTahyCierne;
    }
/**
* Nájde všetky políčka, kam zasahujú biele figúrky, respektíve políčka, kam sa nemôže pohnúť čierny kráľ.
*/    
    private void najdiOhrozenePolickaBiela() {
        this.ohrozenePolickaBiele.clear();
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {
                    if (policko.getFigurkaNaPolicku().getJeBiela()) {
                        for (int[] surPolicka : policko.getFigurkaNaPolicku().vygenerujOhrozenePolicka()) {
                            this.ohrozenePolickaBiele.add(surPolicka);
                        }
                    }
                }
            }
        }
    }
/**
* Nájde všetky políčka, kam zasahujú čierne figúrky, respektíve políčka, kam sa nemôže pohnúť biely kráľ.
*/    
    private void najdiOhrozenePolickaCierna() {
        this.ohrozenePolickaCierne.clear();
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {
                    if (!policko.getFigurkaNaPolicku().getJeBiela()) {
                        for (int[] surPolicka : policko.getFigurkaNaPolicku().vygenerujOhrozenePolicka()) {
                            this.ohrozenePolickaCierne.add(surPolicka);                            
                        }
                    }
                    
                }
            }
        }
    }
/**
* Hľáda, či nejaká biela figúrka zasahuje do čierneho kráľa, pokiaľ áno aktivuje sa šach.
*/    
    private void hladajSachBiela() {
        int pocetFiguriek = 0;
        Figurka figurka = null;
        Figurka kral = null;
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {
                    if (policko.getFigurkaNaPolicku().getJeBiela()) {
                        for (int[] surPolicka : policko.getFigurkaNaPolicku().vygenerujOhrozenePolicka()) {
                            if (this.sachovnica[surPolicka[0] - 1][surPolicka[1] - 1].getJeObsadene() &&
                                this.sachovnica[surPolicka[0] - 1][surPolicka[1] - 1].getFigurkaNaPolicku().getHodnotaFigurky() == 0 &&
                                !this.sachovnica[surPolicka[0] - 1][surPolicka[1] - 1].getFigurkaNaPolicku().getJeBiela()) {
                                pocetFiguriek++;
                                figurka = policko.getFigurkaNaPolicku();
                                kral = this.sachovnica[surPolicka[0] - 1][surPolicka[1] - 1].getFigurkaNaPolicku();
                            }

                        }
                    }
                }
            }
        }
        if (pocetFiguriek > 0) {
            this.aktivujSach(figurka, kral, pocetFiguriek);
        }
    }
/**
* Hľáda, či nejaká čierna figúrka zasahuje do bieleho kráľa, pokiaľ áno aktivuje sa šach.
*/    
    private void hladajSachCierna() {
        int pocetFiguriek = 0;
        Figurka figurka = null;
        Figurka kral = null;
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {
                    if (!policko.getFigurkaNaPolicku().getJeBiela()) {
                        for (int[] surPolicka : policko.getFigurkaNaPolicku().vygenerujOhrozenePolicka()) {
                            if (this.sachovnica[surPolicka[0] - 1][surPolicka[1] - 1].getJeObsadene() &&
                                this.sachovnica[surPolicka[0] - 1][surPolicka[1] - 1].getFigurkaNaPolicku().getHodnotaFigurky() == 0 &&
                                this.sachovnica[surPolicka[0] - 1][surPolicka[1] - 1].getFigurkaNaPolicku().getJeBiela()) {
                                pocetFiguriek++;
                                figurka = policko.getFigurkaNaPolicku();
                                kral = this.sachovnica[surPolicka[0] - 1][surPolicka[1] - 1].getFigurkaNaPolicku();
                            }
                        }
                    }                   
                }
            }
        }
        if (pocetFiguriek > 0) {
            this.aktivujSach(figurka, kral, pocetFiguriek);
        }        
    }
/**
* Hľáda všetky ohrozené políčka čiernych aj bielych figúriek a následne vráti všetky políčka, kam zasahujú čierne figúrky, 
* respektíve políčka, kam sa nemôže pohnúť biely kráľ.
*/    
    public ArrayList<int[]> getOhrozenePolickaCierne() {
        this.najdiOhrozenePolickaCierna();
        this.najdiOhrozenePolickaBiela();
        return this.ohrozenePolickaCierne;
    }
/**
* Hľáda všetky ohrozené políčka čiernych aj bielych figúriek a následne vráti všetky políčka, kam zasahujú biele figúrky, 
* respektíve políčka, kam sa nemôže pohnúť čierny kráľ.
*/   
    public ArrayList<int[]> getOhrozenePolickaBiele() {
        this.najdiOhrozenePolickaCierna();
        this.najdiOhrozenePolickaBiela();
        return this.ohrozenePolickaBiele;
    }
/**
* Uskutočnuje krátku rošádu bieleho kráľa a veže(na kráľovej strane)
*/    
    private void bielaKingSideRosada() {
        this.oznacenaFigurka.presunFigurku(this.sachovnica[6][0]);
        this.oznacenaFigurka.odoznacFigurku();
        this.oznacenaFigurka.setJePosunuta();
        this.oznacenaFigurka = null;
        this.sachovnica[7][0].getFigurkaNaPolicku().presunFigurku(this.sachovnica[5][0]);        
        this.mozneTahy.clear();
        if (this.bielaNaTahu) {
            this.bielaNaTahu = false;
                    
        } else {
            this.bielaNaTahu = true;
        }
        for (Obrazok obrazok : this.obrazkyMozneTahy) {
            obrazok.skry();
        }
    }
/**
* Uskutočnuje krátku rošádu čierneho kráľa a veže(na kráľovej strane)
*/    
    private void ciernaKingSideRosada() {
        this.oznacenaFigurka.presunFigurku(this.sachovnica[6][7]);
        this.oznacenaFigurka.odoznacFigurku();
        this.oznacenaFigurka.setJePosunuta();
        this.oznacenaFigurka = null;
        this.sachovnica[7][7].getFigurkaNaPolicku().presunFigurku(this.sachovnica[5][7]);        
        this.mozneTahy.clear();
        if (this.bielaNaTahu) {
            this.bielaNaTahu = false;
                    
        } else {
            this.bielaNaTahu = true;
        }
        for (Obrazok obrazok : this.obrazkyMozneTahy) {
            obrazok.skry();
        }
    }
/**
* Uskutočnuje dlhú rošádu bieleho kráľa a veže(na strane kráľovnej)
*/    
    private void bielaQueenSideRosada() {
        this.oznacenaFigurka.presunFigurku(this.sachovnica[2][0]);
        this.oznacenaFigurka.odoznacFigurku();
        this.oznacenaFigurka.setJePosunuta();
        this.oznacenaFigurka = null;
        this.sachovnica[0][0].getFigurkaNaPolicku().presunFigurku(this.sachovnica[3][0]);        
        this.mozneTahy.clear();
        if (this.bielaNaTahu) {
            this.bielaNaTahu = false;
                    
        } else {
            this.bielaNaTahu = true;
        }
        for (Obrazok obrazok : this.obrazkyMozneTahy) {
            obrazok.skry();
        }
    }
/**
* Uskutočnuje dlhú rošádu čierneho kráľa a veže(na strane kráľovnej)
*/    
    private void ciernaQueenSideRosada() {
        this.oznacenaFigurka.presunFigurku(this.sachovnica[2][7]);
        this.oznacenaFigurka.odoznacFigurku();
        this.oznacenaFigurka.setJePosunuta();
        this.oznacenaFigurka = null;
        this.sachovnica[0][7].getFigurkaNaPolicku().presunFigurku(this.sachovnica[3][7]);        
        this.mozneTahy.clear();
        if (this.bielaNaTahu) {
            this.bielaNaTahu = false;
                    
        } else {
            this.bielaNaTahu = true;
        }
        for (Obrazok obrazok : this.obrazkyMozneTahy) {
            obrazok.skry();
        }
    }
/**
* Aktivuje stav šachu vo hre. Na základe pozícií figúrky, ktorá šachuje kráľa, a kráľa, vygenerujú políčka, na ktorých môžu ostatné figúrky šach zblokovať,
* čo sú vlastne jediné políčka, kam sa môžu ostatné figúrky okrem kráľa pohnúť. Na konci sa skontroluje či náhodou nie je šach mat.
* @param figurka referencia na figurku ktorá dáva kráľovi šach
* @param kral referencia na kral ktorý je šachovaný
*/    
    private void aktivujSach(Figurka figurka, Figurka kral, int pocetFiguriek) {
        this.jeSach = true;
        this.mozneTahyPocasSachu.clear();
        this.farbaVSachu = kral.getJeBiela();
        if (pocetFiguriek == 1) {
            int posXFigurka = figurka.getPolickoKdeJeFigurka().getSuradniceXY()[0]; 
            int posYFigurka = figurka.getPolickoKdeJeFigurka().getSuradniceXY()[1];
            int posXkral = kral.getPolickoKdeJeFigurka().getSuradniceXY()[0]; 
            int posYkral = kral.getPolickoKdeJeFigurka().getSuradniceXY()[1];
            int vektorX = 0;
            int vektorY = 0;
            if (posXkral - posXFigurka == 0) {
                vektorX = 0;
            
            } else {
                vektorX = (posXkral - posXFigurka) / (Math.abs(posXkral - posXFigurka));
            } 
            if (posYkral - posYFigurka == 0) {
                vektorY = 0;
            
            } else {
                vektorY = (posYkral - posYFigurka) / (Math.abs(posYkral - posYFigurka));
            }
            while (posXFigurka != posXkral || posYFigurka != posYkral) {
                if (figurka.getTypFigurky().equals("kon")) {               
                    this.mozneTahyPocasSachu.add(new int[] {posXFigurka, posYFigurka});
                    break;
                }
                this.mozneTahyPocasSachu.add(new int[] {posXFigurka, posYFigurka});
                System.out.println(posXFigurka + " " + posYFigurka);
                posXFigurka += vektorX;
                posYFigurka += vektorY;
            }
        }
        if (this.farbaVSachu) {            
            this.checkSachMatNaBielu(kral);   
        } else {         
            this.checkSachMatNaCiernu(kral);
        }        
    }
/**
* Aktivuje stav šachu vo hre. Na základe pozícií figúrky, ktorá šachuje kráľa, a kráľa, vygenerujú políčka, na ktorých môžu ostatné figúrky šach zblokovať,
* čo sú vlastne jediné políčka, kam sa môžu ostatné figúrky okrem kráľa pohnúť. Na konci sa skontroluje či náhodou nie je šach mat. 
*/    
    public ArrayList<int[]> getTahyPocasSachu() {
        return this.mozneTahyPocasSachu;
    }
/**
* Kontroluje či má biela počas šachu nejaké legálne ťahy, pokiaľ nie, čierny vyhráva hru.
* @param kral referencia na kráľa ktorý je v Šachu
*/    
    private void checkSachMatNaBielu(Figurka kral) {
        this.mozneTahyBielychPocasSachu.clear();      
        for (int[] suradniceMozT : kral.vygenerujMozneTahy()) {
            this.mozneTahyBielychPocasSachu.add(suradniceMozT);
        }
        for (int[] suradniceTahy : this.getTahyPocasSachu()) {
            for (int[] suradniceMozT : this.getMozneTahyBiela()) {
                if (suradniceTahy[0] == suradniceMozT[0] && suradniceTahy[1] == suradniceMozT[1]) {
                    this.mozneTahyBielychPocasSachu.add(suradniceMozT);
                }
            }
        }
        if (this.mozneTahyBielychPocasSachu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Šach Mat ! Čierny vyhral !");
            this.freezeGame = true; 
        }
    }
/**
* Kontroluje či má čierna počas šachu nejaké legálne ťahy, pokiaľ nie, biely vyhráva hru. 
* @param kral referencia na kráľa ktorý je v Šachu
*/    
    private void checkSachMatNaCiernu(Figurka kral) {
        this.mozneTahyCiernychPocasSachu.clear();      
        for (int[] suradniceMozT : kral.vygenerujMozneTahy()) {
            this.mozneTahyCiernychPocasSachu.add(suradniceMozT);
        }
        for (int[] suradniceTahy : this.getTahyPocasSachu()) {
            for (int[] suradniceMozT : this.getMozneTahyCierna()) {
                if (suradniceTahy[0] == suradniceMozT[0] && suradniceTahy[1] == suradniceMozT[1]) {
                    this.mozneTahyCiernychPocasSachu.add(suradniceMozT);
                }
            }
        }
        if (this.mozneTahyCiernychPocasSachu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Šach Mat ! Biely vyhral !");
            this.freezeGame = true; 
        }
    }
/**
* Kontroluje či hra neskončí ako remíza.
*/    
    private void checkRemiza() {
        if (!this.getJeSach()) {
            if (this.getMozneTahyBiela().isEmpty() && this.getBielaNaTahu()) {
                JOptionPane.showMessageDialog(null, "Remíza ! Biely sa nemá kam pohnúť !");
                this.freezeGame = true;
            }
            if (this.getMozneTahyCierna().isEmpty() && !this.getBielaNaTahu()) {
                JOptionPane.showMessageDialog(null, "Remíza ! Cierny sa nemá kam pohnúť !");
                this.freezeGame = true;
            }  
            boolean maSachMatFigurku = false;
            int sachMatFigurky = 0;
            for (Policko[] riadok : this.sachovnica) {        
                for (Policko policko : riadok) {
                    if (policko.getJeObsadene() && 
                        policko.getFigurkaNaPolicku().getHodnotaFigurky() == 3) {
                        sachMatFigurky++;
                        if (sachMatFigurky > 1) {
                            maSachMatFigurku = true;
                            break;
                        }
                    }
                    if (policko.getJeObsadene() && 
                        (policko.getFigurkaNaPolicku().getHodnotaFigurky() != 3 &&
                        policko.getFigurkaNaPolicku().getHodnotaFigurky() != 0)) {
                        maSachMatFigurku = true;
                        break;
                    }
                }        
            }
            if (!maSachMatFigurku) {
                JOptionPane.showMessageDialog(null, "Remíza pre nedostatok materiálu !");
                this.freezeGame = true;
            }
        }
    }
/**
* Vyhľadá kráľa na šachovnici, odkiaľ potom zapinuje biele figurky. Figúrka je zapinovaná, pokiaľ bráni kráľa pred priamym ohrozením od nepriateľskej figúrky.
*/   
    
    private void pinujFigurkyBiele() {
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {
                    if (policko.getFigurkaNaPolicku().getJeBiela() && policko.getFigurkaNaPolicku().getHodnotaFigurky() == 0) {
                        policko.getFigurkaNaPolicku().pinujFigurky();
                    }                  
                }
            }
        }
    }
/**
* Vyhľadá kráľa na šachovnici, odkiaľ potom zapinuje čierne figurky. Figúrka je zapinovaná, pokiaľ bráni kráľa pred priamym ohrozením od nepriateľskej figúrky.
*/   
    private void pinujFigurkyCierne() {
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {
                    if (!policko.getFigurkaNaPolicku().getJeBiela() && policko.getFigurkaNaPolicku().getHodnotaFigurky() == 0) {
                        policko.getFigurkaNaPolicku().pinujFigurky();
                    }                  
                }
            }
        }
    }
/**
* Zruší piny všetkých figúriek na šachovnici.
*/   
    private void odpinujFigurky() {
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {               
                    policko.getFigurkaNaPolicku().setPinKolmo(false);
                    policko.getFigurkaNaPolicku().setPinVodorovne(false);
                    policko.getFigurkaNaPolicku().setPinHoreVlavo(false);
                    policko.getFigurkaNaPolicku().setPinDoleVlavo(false);
                }
            }
        }
    }
/**
* Skontroluje či je nejaký pešiak na poslednom riadku. Ak áno, premení ho na kráľovnú.
*/    
    private void checkPesiakPromotion() {
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene() && 
                    policko.getFigurkaNaPolicku().getHodnotaFigurky() == 1) {               
                    if (policko.getSuradniceXY()[1] == 8 && policko.getFigurkaNaPolicku().getJeBiela()) {
                        policko.getFigurkaNaPolicku().vyhodFigurku();
                        this.sachovnica[policko.getSuradniceXY()[0] - 1][8 - 1].priradFigurku(new Kralovna(this.sachovnica[policko.getSuradniceXY()[0] - 1][8 - 1], this, true));
                    }
                    if (policko.getSuradniceXY()[1] == 1 && !policko.getFigurkaNaPolicku().getJeBiela()) {
                        policko.getFigurkaNaPolicku().vyhodFigurku();
                        this.sachovnica[policko.getSuradniceXY()[0] - 1][1 - 1].priradFigurku(new Kralovna(this.sachovnica[policko.getSuradniceXY()[0] - 1][1 - 1], this, false));
                    }
                }
            }
        }
    }
/**
* Po uskutočnení en passantu vyhodí figurku, ktorú preskočila figúrka en passantom
*/    
    private void vyhodFigurkuPoEnPassant(Policko polickoPoEnPassante) {
        polickoPoEnPassante.getFigurkaNaPolicku().vyhodFigurku();
        polickoPoEnPassante.odidZPolicka();
    }
/**
* Zruší sa možnosť preskočenia bielych pešiakov en passantom. Vykonáva sa vždy po tom ako sa čierna pohne, aby v prípade, že en passant hneď nevyužive,
* zruší možnosť na jeho využitie.
*/    
    private void setBieleEnPassantsFalse() {
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene() && 
                    policko.getFigurkaNaPolicku().getHodnotaFigurky() == 1 &&
                    policko.getFigurkaNaPolicku().getJeBiela()) {      
                    policko.getFigurkaNaPolicku().setEnPassantFalse();
                }                   
            }
        }
    }
/**
* Zruší sa možnosť preskočenia čiernych pešiakov en passantom. Vykonáva sa vždy po tom ako sa čierna pohne, aby v prípade, že en passant hneď nevyužive,
* zruší možnosť na jeho využitie.
*/    
    private void setCierneEnPassantsFalse() {
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene() && 
                    policko.getFigurkaNaPolicku().getHodnotaFigurky() == 1 &&
                    !policko.getFigurkaNaPolicku().getJeBiela()) {      
                    policko.getFigurkaNaPolicku().setEnPassantFalse();
                }                   
            }
        }
    }
/**
* Vymaže z plátna grafiku šachovnice.
*/    
    private void skrySachovnicu() {
        for (Policko[] riadok : this.sachovnica) {
            for (Policko policko : riadok) {
                policko.skryPolicko();
                if (policko.getJeObsadene()) {
                    policko.getFigurkaNaPolicku().vyhodFigurku();
                }                   
            }
        }
    }   
}
