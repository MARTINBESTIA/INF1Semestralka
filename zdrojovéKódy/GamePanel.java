import fri.shapesge.BlokTextu;
import fri.shapesge.Obdlznik;
import fri.shapesge.StylFontu;
/**
 * Trieda sa stará o zobrazovanie celkovej materiálovej výhody, zobrazovanie ktorá strana je na ťahu a zobrazuje tlačidlo hrať znovu
 * 
 * @author Martin Šimko
 * @version 1.0.0
 */
public class GamePanel {

    private GameController gameController;
    private int materialDiff = 0;
    private BlokTextu materialDiffText;
    private String materialDiffTextString;
    private BlokTextu naTahuText;
    private BlokTextu hratZnovaText;
    private String naTahuTextString;
    private final int posYDiffText = 700;
    private final int posXDiffText = 200;
    private final int posDiffTextVelkost = 35;
    private final int posYNaTahuText = 700;
    private final int posXNaTahuText = 550;
    private final int posNaTahuTextVelkost = 35;
    private Obdlznik tlacidloHratZnovu;
/**
* Inicializuje a zobrazí všetky texty a grafiku.
* @param gameController referencia na GameController
*/   
    public GamePanel(GameController gameController) {
        this.tlacidloHratZnovu = new Obdlznik(10, 720);
        this.tlacidloHratZnovu.zmenStrany(100, 40);
        this.tlacidloHratZnovu.zmenFarbu("green");
        this.tlacidloHratZnovu.zobraz();
        this.hratZnovaText = new BlokTextu("hrať znova", 12, 740);
        this.hratZnovaText.zmenFont("Arial", StylFontu.BOLD, 17);
        this.hratZnovaText.zobraz();
        this.gameController = gameController;
        this.zobrazMaterialDiff();
        this.materialDiffText.zobraz();
        this.zobrazStranuNaTahu();
    }
/**
* Na základe toho, ktorá strana je na ťahu zmení a vykreslí text ktorá strana je na ťahu
*/    
    public void zobrazStranuNaTahu() {
        if (this.naTahuText != null) {
            this.naTahuText.skry();
        }
        String farba = "";
        if (this.gameController.getBielaNaTahu()) {
            farba = "biely";
        
        } else {
            farba = "čierny";
        }
        this.naTahuText = new BlokTextu(String.format("Na ťahu: %s", farba),  this.posXNaTahuText, this.posYNaTahuText);
        this.naTahuText.zmenFont("Arial", StylFontu.BOLD, this.posDiffTextVelkost);
        this.naTahuText.zobraz();
    }
/**
* Na základe toho, aký je celková hodnota metriálu strán, sa vypočíta a zobrazí ktorá strana má materiálovú výhodu
*/    
    public void zobrazMaterialDiff() {
        if (this.materialDiffText != null) {
            this.materialDiffText.skry();
        }
        int difference = 0;
        String text = "";
        for (Policko[] riadok : this.gameController.getSachovnica()) {
            for (Policko policko : riadok) {
                if (policko.getJeObsadene()) {
                    if (policko.getFigurkaNaPolicku().getJeBiela()) {
                        difference += policko.getFigurkaNaPolicku().getHodnotaFigurky();
                    }
                    if (!policko.getFigurkaNaPolicku().getJeBiela()) {
                        difference -= policko.getFigurkaNaPolicku().getHodnotaFigurky();
                    }                    
                }
            }
        }
        if (difference == 0) {
            text = "remíza";
        }
        if (difference > 0) {
            text = "biely";
        }
        if (difference < 0) {
            text = "čierny";
        }
        this.materialDiffText = new BlokTextu(String.format("Výhoda: +%d %s", Math.abs(difference), text), this.posXDiffText, this.posYDiffText);
        this.materialDiffText.zmenFont("Arial", StylFontu.BOLD, this.posDiffTextVelkost);
        this.materialDiffText.zobraz();
    }
    
}
