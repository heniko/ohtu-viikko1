package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonAlleNolla() {
        varasto.lisaaVarastoon(-10);

        // varastossa pitäisi olla tilaa 10, sillä yritettiin vain lisätä negatiivinen määrä
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonYliTilavuuden() {
        varasto.lisaaVarastoon(20);

        // Varastossa pitäisi olla tilaa 0, sillä sinne lisätään tavaraa enemmän kuin mahtuu
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaAlleNolla() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-5);

        // Täydestä varastosta yritetään ottaa negatiivinen määrä tavaraa
        assertEquals(5, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaEnemmanKuinVarastoSisaltaa() {
        varasto.lisaaVarastoon(2);

        // Varasto sisältää 2, mutta otetaan 3. Pitäisi palauttaa siis 2
        assertEquals(2, varasto.otaVarastosta(3), vertailuTarkkuus);
    }

    @Test
    public void toStringTest() {
        varasto.lisaaVarastoon(2);
        String testString = "saldo = 2.0, vielä tilaa 8.0";
        
        // toString() tulisi palauttaa "saldo = 2, vielä tilaa 8"
        assertTrue(testString.equals(varasto.toString()));
    }

    @Test
    public void varastoConstructor1() {
        varasto = new Varasto(-1);

        // Varaston tilavuuden tulisi olla 0
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoConstructor2() {
        varasto = new Varasto(-1, 0);

        // Varaston tilavuuden tulisi olla 0
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoConstructor3() {
        varasto = new Varasto(2, 3);

        // Luodaan varasto ja lisätään sinne tavaraa yli kapasiteetin
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoConstructor4() {
        varasto = new Varasto(3, 2);

        // Luodaan varasto tilavuudella 3 ja saldolla 2
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoConstructor5() {
        varasto = new Varasto(2, -2);
        
        // Saldon tulisi olla 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
}
