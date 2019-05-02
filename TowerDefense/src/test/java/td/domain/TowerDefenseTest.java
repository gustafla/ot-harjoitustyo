package td.domain;

import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TowerDefenseTest {
    Field field;
    TowerDefense td;

    @Before
    public void setUp() {
        field = new Field(64, 64, 10, 0);
        td = new TowerDefense(field);
    }

    @Test
    public void constructedWithMoney() {
        assertTrue(0 < td.getMoney());
    }

    @Test
    public void constructedWithWave0() {
        assertEquals(0, td.getWaveNumber());
    }

    @Test
    public void constructedWithHealth() {
        assertTrue(0 < td.getHealth());
    }

    @Test
    public void constructedWithNoEnemies() {
        assertTrue(td.getEnemies().isEmpty());
    }

    @Test
    public void getFieldGivesCorrectField() {
        assertEquals(field, td.getField());
    }

    @Test
    public void enemiesGetSpawned() {
        td.nextWave();
        for (int i = 0; i < 100; i++) {
            td.update(1./30.);
        }
        assertFalse(td.getEnemies().isEmpty());
    }

    @Test(timeout=1000)
    public void waveEndsWithNoEnemies() {
        td.nextWave();
        while (!td.isWaveOver()) {
            td.update(1./30.);
        }
        assertTrue(td.getEnemies().isEmpty());
    }

    @Test(timeout=1000)
    public void waveEndsWithLessHealthAsEnemiesReachBase() {
        int healthBefore = td.getHealth();
        td.nextWave();
        while (!td.isWaveOver()) {
            td.update(1./30.);
        }
        assertTrue(td.getEnemies().isEmpty());
        assertTrue(td.getHealth() < healthBefore);
    }

    @Test(timeout=1000)
    public void towersShootEnemies() {
        int initialHealth = td.getHealth();
        List<Shot> shots = new ArrayList<>();

        Tower[] towers = {
            new Tower(625, 10, 20, 20, 0.4),
            new Tower(625, 20, 20, 20, 0.4),
            new Tower(625, 30, 20, 20, 0.4),
        };
        for (Tower t: towers) {
            assertTrue(td.addTower(t));
        }

        td.nextWave();
        while(!td.isWaveOver()) {
            shots.addAll(td.update(1./30.));
        }

        assertFalse(shots.isEmpty());
        assertEquals(initialHealth, td.getHealth());
    }
}
