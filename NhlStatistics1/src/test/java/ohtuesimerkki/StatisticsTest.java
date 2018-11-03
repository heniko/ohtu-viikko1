package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchTest1() {
        // Testataan, että listassa olevalla nimellä haettaessa löytyy haettu henkilö
        Player haettu = new Player("Yzerman", "DET", 42, 56);
        
        assertTrue(haettu.toString().equals(stats.search(haettu.getName()).toString()));
    }
    
    @Test
    public void searchTest2() {
        // Testataan, että palautus on null, jos nimellä ei löydy ketään
        assertTrue(stats.search("Not in list") == null);
    }
    
    @Test
    public void topScorers() {
        // topScorers() palauttaa oikean listan
        Player oikea = new Player("Gretzky", "EDM", 35, 89);
        assertTrue(oikea.getName().equals(stats.topScorers(1).get(0).getName()));
    }
    
    @Test
    public void teamTest() {
        //Palauttaa tiimin oikein
        Player oikea = new Player("Yzerman", "DET", 42, 56);
        assertTrue(oikea.getName().equals(stats.team(oikea.getTeam()).get(0).getName()));
    }
    
}