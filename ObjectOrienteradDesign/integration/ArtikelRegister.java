package ObjectOrienteradDesign.integration;

import ObjectOrienteradDesign.model.DTO.ArtikelDTO;

/**
 * Representerar ett register för att hämta artikelinformation.
 */

public class ArtikelRegister {
/**
     * Hämtar artikelinformation baserat på artikel-ID och antal.
     * bla bla bla
     *  :D
     * @param artikelID ID som identifierar en artikel i systemet.
     * @param antalAvArtikel Antal enheter av artikeln som ska hanteras.
     * @return Ett {ArtikelDTO}-objekt som innehåller artikelinformation.
     */
    public ArtikelDTO hämtaArtikelInformation(String artikelID, int antalAvArtikel) {
        ArtikelDTO artikel = new ArtikelDTO();
    
        switch (artikelID) {
            case "abc123":
                artikel.setArtikelID(artikelID);
                artikel.setArtikelNamn("BigWheel Oatmeal");
                artikel.setPris(29.90);
                artikel.setVAT(6);
                artikel.setBeskrivning("BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free");
                artikel.setAntal(antalAvArtikel);
                break;
            case "def456":
                artikel.setArtikelID(artikelID);
                artikel.setArtikelNamn("YouGoGo Blueberry");
                artikel.setPris(14.90);
                artikel.setVAT(6);
                artikel.setBeskrivning("YouGoGo Blueberry 240 g, low sugar yoghurt, blueberry flavour");
                artikel.setAntal(antalAvArtikel);
                break;
            default:
                throw new IllegalArgumentException("ArtikelID finns inte: " + artikelID);
        }
       
        return artikel;
    }
}
