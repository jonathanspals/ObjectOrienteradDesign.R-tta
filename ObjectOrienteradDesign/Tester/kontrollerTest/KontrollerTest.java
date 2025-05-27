package Tester.kontrollerTest;
import java.lang.reflect.Method;
import java.util.List;

public class KontrollerTest {
    private Kontroller kontroller;

    @BeforeEach
    public void setUp() {
        kontroller = new Kontroller();
    }

    @Test
    public void testFlaggaRabattReturnerarReduceratPris() throws Exception {
        // För att kalla en icke-public metod använder vi reflexion
        Method metod = Kontroller.class.getDeclaredMethod("flaggaRabatt", int.class);
        metod.setAccessible(true);

        // Simulera artiklar i kassan via reflektionsanrop av läggTillArtiklar
        ArtikelDTO artikel = new ArtikelDTO("abc123", 2); 
        Method läggTill = Kontroller.class.getDeclaredMethod("läggTillArtiklar", List.class);
        läggTill.setAccessible(true);
        läggTill.invoke(kontroller, List.of(artikel));

        // Testa med kund som ska få rabatt (t.ex. 12345)
        float nyttPris = (float) metod.invoke(kontroller, 123);

        // Förväntat värde är mindre än totalpris eftersom rabatt ska ges
        assertTrue(nyttPris > 0 && nyttPris < 200, "Rabatt borde tillämpas");
    }

    @Test
    public void testSkapaKvittoReturnerarGiltigtObjekt() throws Exception {
        Method metod = Kontroller.class.getDeclaredMethod("skapaKvitto", float.class, float.class, float.class);
        metod.setAccessible(true);

        Object kvitto = metod.invoke(kontroller, 300.0f, 250.0f, 50.0f);
        assertNotNull(kvitto);
        assertEquals("model.DTO.Kvitto", kvitto.getClass().getName());
    }

    @Test
    public void testProcessSaleBeräknarRättVäxel() throws Exception {
        Method metod = Kontroller.class.getDeclaredMethod("processSale", float.class, float.class);
        metod.setAccessible(true);

        float växel = (float) metod.invoke(kontroller, 150.0f, 200.0f);
        assertEquals(50.0f, växel, 0.001f);
    }
}