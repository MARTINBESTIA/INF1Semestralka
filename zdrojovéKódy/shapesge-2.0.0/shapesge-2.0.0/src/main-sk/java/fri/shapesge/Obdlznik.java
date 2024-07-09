package fri.shapesge;

import fri.shapesge.drawables.RectangularDrawable;

import java.awt.Color;

/**
 * Obdĺžnik, s ktorým možno pohybovať a nakreslí sa na plátno.
 *
 * @author originál: Michael Kölling and David J. Barnes
 * @author engine: Ján Janech
 * @version 1.0  (9.11.2022)
 */
@SuppressWarnings("unused")
public class Obdlznik {
    private final RectangularDrawable drawable;

    /**
     * Vytvor nový obdĺžnik preddefinovanej farby na preddefinovanej pozícii.
     */
    @SuppressWarnings("unused")
    public Obdlznik() {
        this(60, 50);
    }

    /**
     * Vytvor nový obdĺžnik preddefinovanej farby na danej pozícii.
     * @param x x-ová súradnica obdĺžnika
     *          (vzdialenosť od ľavého okraja plátna)
     * @param y y-ová súradnica obdĺžnika
     *          (vzdialenosť od horného okraja plátna)
     */
    @SuppressWarnings("unused")
    public Obdlznik(int x, int y) {
        this.drawable = new RectangularDrawable(x, y, 30, 60, Color.red);
    }

    /**
     * Zobraz sa.
     */
    @SuppressWarnings("unused")
    public void zobraz() {
        this.drawable.makeVisible();
    }

    /**
     * Skry sa.
     */
    @SuppressWarnings("unused")
    public void skry() {
        this.drawable.makeInvisible();
    }

    /**
     * Posuň sa vpravo o pevnú dĺžku.
     */
    @SuppressWarnings("unused")
    public void posunVpravo() {
        this.drawable.moveBy(20, 0);
    }

    /**
     * Posuň sa hore o pevnú dĺžku.
     */
    @SuppressWarnings("unused")
    public void posunVlavo() {
        this.drawable.moveBy(-20, 0);
    }

    /**
     * Posuň sa dole o pevnú dĺžku.
     */
    @SuppressWarnings("unused")
    public void posunHore() {
        this.drawable.moveBy(0, -20);
    }

    /**
     * Posuň sa dole o pevnú dĺžku.
     */
    @SuppressWarnings("unused")
    public void posunDole() {
        this.drawable.moveBy(0, 20);
    }

    /**
     * Posuň sa vodorovne o dĺžku danú parametrom.
     * @param vzdialenost vzdialenosť v pixeloch
     */
    @SuppressWarnings("unused")
    public void posunVodorovne(int vzdialenost) {
        this.drawable.moveBy(vzdialenost, 0);
    }

    /**
     * Posuň sa zvisle o dĺžku danú parametrom.
     * @param vzdialenost vzdialenosť v pixeloch
     */
    @SuppressWarnings("unused")
    public void posunZvisle(int vzdialenost) {
        this.drawable.moveBy(0, vzdialenost);
    }

    /**
     * Zmeň dĺžky strán na hodnoty dané parametrami.
     * Dĺžka strany musí byť nezáporné celé číslo.
     * @param stranaA dĺžka prvej strany
     * @param stranaB dĺžka druhej strany
     */
    @SuppressWarnings("unused")
    public void zmenStrany(int stranaA, int stranaB) {
        this.drawable.changeSize(stranaA, stranaB);
    }

    /**
     * Zmeň farbu na hodnotu danú parametrom.
     * @param farba nová farba z palety alebo v tvare #rrggbb
     */
    @SuppressWarnings("unused")
    public void zmenFarbu(String farba) {
        this.drawable.changeColor(farba);
    }

    /**
     * Zmeň polohu obdĺžnika na hodnoty dané parametrami.
     * @param x x-ová súradnica obdĺžnika
     *          (vzdialenosť od ľavého okraja plátna)
     * @param y y-ová súradnica obdĺžnika
     *          (vzdialenosť od horného okraja plátna)
     */
    @SuppressWarnings("unused")
    public void zmenPolohu(int x, int y) {
        this.drawable.moveTo(x, y);
    }
}
