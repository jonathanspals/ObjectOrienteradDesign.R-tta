package integration;

import model.DTO.ArtikelDTO;

/**
 * ArtikelRegister ansvarar för att hämta artikelinformation baserat på artikel-ID.
 * 
 * Denna klass simulerar en koppling till ett externt artikelregister (databas),
 * där information som namn, pris, moms och beskrivning om artiklar finns sparat.
 */
public class ArtikelRegister {
    /**
     * Hämtar artikelinformation baserat på artikel-ID och antal.
     * 
     * @param artikelID ID som identifierar en artikel i systemet.
     * @param antalAvArtikel Antal enheter av artikeln som ska hanteras.
     * @return Ett ArtikelDTO-objekt som innehåller artikelinformation.
     * @throws ArtikelFinnsInteException Om artikel-ID inte hittas
     * @throws DatabasNedException Om det är simulerat databasfel
     */
    public ArtikelDTO hämtaArtikelInformation(String artikelID, int antalAvArtikel)
            throws ArtikelFinnsInteException {
                
        if (artikelID.equals("DBERROR")) {
            throw new DatabasNedException(artikelID);
        }

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
                throw new ArtikelFinnsInteException(artikelID);
        }

        return artikel;
    }
}
