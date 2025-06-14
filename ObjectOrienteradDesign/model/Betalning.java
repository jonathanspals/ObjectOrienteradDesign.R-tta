package model;

/**
 * Representerar en betalning gjord av kund i samband med ett köp.
 */
public class Betalning {
    private float belopp;

    /**
     * Skapar en instans av Betalning med angivet belopp.
     *
     * @param belopp Det belopp som kunden har betalat.
     */
    public Betalning(float belopp) {
        this.belopp = belopp;
    }

    /**
     * Hämtar det belopp som betalats.
     *
     * @return Betalningsbeloppet.
     */
    public float getBelopp() {
        return belopp;
    }
}