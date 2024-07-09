import fri.shapesge.Stvorec;

/**
 * Predstavuje konkrétne políčko na šachovnici. Má svoje súradnice Xová 1 - 8, Yová 1 - 8, Podľa štandarného rozpoloženia šachovnice, s tým rozdielom
 * že X nie je od a - h. Podľa súradníc sa zobrazí na plátne. Hlavné funkcie políčka sú, že drží informácie o tom či sa na ňom nachádza figúrka alebo nie,
 * čo je kľúčové pri generovaní ťahov
 * 
 * @author Martin Šimko 
 * @version 1.0.0
 */
public class Policko {

    private boolean jeObsadene = false;
    private int[] suradniceXY;
    private Figurka figurkaNaPolicku = null;
    private String farba;
    private Stvorec polickoGrafika;
    private final int sirka = 80;
    private final int posunutieNaStredX = 180;
    private final int posunutieNaStredY = 560;
    private final String bielaFarba = "#f2e5d9";
    private final String ciernaFarba = "#85582e";
    private int surXpreFigurku;
    private int surYpreFigurku;
/**
 * Inicializuje suradnice políčka podľa výskytu políčka. Políčko potom vykreslí na plátno 
 * @param x suradnica x podľa toho, kde sa políčko na šachovnici nachádza
 * @param y suradnica y podľa toho, kde sa políčko na šachovnici nachádza
 */    
    public Policko(int x, int y) {
        this.suradniceXY = new int[]{x, y};
        this.polickoGrafika = new Stvorec(this.posunutieNaStredX + ((this.suradniceXY[0] - 1) * this.sirka), this.posunutieNaStredY - this.sirka * (this.suradniceXY[1] - 1));
        this.surXpreFigurku = this.posunutieNaStredX + ((this.suradniceXY[0] - 1) * this.sirka) + 10;
        this.surYpreFigurku = this.posunutieNaStredY - this.sirka * (this.suradniceXY[1] - 1) + 10;
        this.polickoGrafika.zmenStranu(this.sirka);
        this.polickoGrafika.zmenFarbu(this.vyberFarbuPolicka());
        this.polickoGrafika.zobraz();
        
        
    }
/**
 * Vráti true pokiaľ je políčko obsadené figúrkou, false ak nie je.
 */       
    public boolean getJeObsadene() {
        return this.jeObsadene;
    }
/**
 * Vráti súradnice políčka
 */     
    public int[] getSuradniceXY() {
        return this.suradniceXY;
    }
/**
 * Vráti farbu políčka podľa toho, kde sa nachádza
 */    
    private String vyberFarbuPolicka() {
        if ((this.suradniceXY[0] + this.suradniceXY[1]) % 2 == 0) {
            this.farba = this.ciernaFarba;
        } else {
            this.farba = this.bielaFarba;
        }
        return this.farba;
    }
/**
 * Priradí figúrku na toto políčko
 * @param figurka Referencia na figúrku ktorá sa má priradiť ku tomuto políčku
 */    
    public void priradFigurku(Figurka figurka) {
        this.figurkaNaPolicku = figurka;
        this.jeObsadene = true;
    }
/**
 * Vráti súradnicu X, kde sa má vykresliť obrázok figúrky, pokiaľ je na tomto políčku
 */    
    public int getSurXpreFigurku() {
        return this.surXpreFigurku;
    }
/**
 * Vráti súradnicu Y, kde sa má vykresliť obrázok figúrky, pokiaľ je na tomto políčku
 */   
    public int getSurYpreFigurku() {
        return this.surYpreFigurku;
    }
/**
 * Vráti figúrku, ktorá sa náchádza na tomto políčku
 */     
    public Figurka getFigurkaNaPolicku() {
        return this.figurkaNaPolicku;
    }
/**
 * Odstráni figúrku z tohto políčka
 */    
    public void odidZPolicka() {
        this.jeObsadene = false;
        this.figurkaNaPolicku = null;
    }
/**
 * Odstráni políčko z plátna
 */    
    public void skryPolicko() {
        this.polickoGrafika.skry();
    }
}
