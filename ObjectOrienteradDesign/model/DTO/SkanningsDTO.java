package ObjectOrienteradDesign.model.DTO;

/**
 * DTO som representerar information om en skannad artikel.
 * Skapas när en artikel skannas med visst antal.
 */
public class SkanningsDTO {
    private String artikelID;
    private float artikelPris;
    private int VAT;
    private String artikelBeskrivning;
    private int antalAvArtikel;
    private String artikelNamn;

    public SkanningsDTO(String artikelID,
                      int VAT,
                      float artikelPris,
                      String artikelBeskrivning,
                      int antalAvArtikel,
                      String artikelNamn) {

        this.artikelID = artikelID;
        this.VAT = VAT;
        this.artikelPris = artikelPris;
        this.artikelBeskrivning = artikelBeskrivning;
        this.antalAvArtikel = antalAvArtikel;
    }

    public String getArtikelID() {
        return artikelID;
    }

    public float getArtikelPris() {
        return artikelPris;
    }

    public int getVAT() {
        return VAT;
    }

    public String getArtikelBeskrivning() {
        return artikelBeskrivning;
    }

    public int getAntalAvArtikel() {
        return antalAvArtikel;
    }

    public String getArtikelNamn(){
        return artikelNamn;
    }

}
