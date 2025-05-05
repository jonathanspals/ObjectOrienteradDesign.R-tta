package ObjectOrienteradDesign.model.DTO;

/**
 * Data Transfer Object (DTO) för att representera en artikel.
 * Innehåller information som ID, namn, pris, moms, beskrivning och antal.
 */
public class ArtikelDTO {
    private String artikelID;
    private double artikelPris;
    private int VAT;
    private String artikelBeskrivning;
    private int antalAvArtikel;
    private String artikelNamn;
    public ArtikelDTO(){
        
    }
    public ArtikelDTO( String artikelID,
    double artikelPris,
    int VAT,
    String artikelBeskrivning,
    int antalAvArtikel){

this.artikelID = artikelID;
this.artikelPris = artikelPris;
this.VAT = VAT;
this.artikelBeskrivning = artikelBeskrivning;
this.antalAvArtikel = antalAvArtikel;
}
public String  getartikelID(){
return artikelID;
}
public double getartikelPris(){
return artikelPris;
}
public int getVAT(){
return VAT;
}
public String getartikelBeskrivning(){
return artikelBeskrivning;
}
public int getantalAvArtikel(){
return antalAvArtikel;
}
// Setters "tilldelar" olika saker till den aktuella objectet(varan)
public void setArtikelID(String artikelID) {
this.artikelID = artikelID;
}

public String getArtikelNamn(){
    return artikelNamn;
}

public void setArtikelNamn(String artikelNamn) {
this.artikelNamn = artikelNamn;
}

public void setPris(double artikelPris) {
this.artikelPris = artikelPris;
}

public void setVAT(int VAT) {
this.VAT = VAT;
}

public void setBeskrivning(String artikelBeskrivning) {
this.artikelBeskrivning = artikelBeskrivning;
}

public void setAntal(int antalAvArtikel) {
this.antalAvArtikel = antalAvArtikel;
}

public void ökaAntal(int extraAntal) {
    if (extraAntal > 0) {
        this.antalAvArtikel += extraAntal;
    } else {
        System.out.println("Antalet som ska ökas kan inte vara negativt eller noll.");
    }
}
}