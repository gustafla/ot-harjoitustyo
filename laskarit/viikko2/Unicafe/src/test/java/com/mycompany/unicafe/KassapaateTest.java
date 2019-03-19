package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate kp;
    
    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        kp = new Kassapaate();
    }
    
    @Test
    public void konstruktoriLaittaaRahaa100000() {
        assertEquals(kp.kassassaRahaa(), 100000);
    }
    
    @Test
    public void edullisiaLounaitaAluksiMyyty0() {
        assertEquals(kp.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void maukkaitaLounaitaAluksiMyyty0() {
        assertEquals(kp.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void syoEdullisestiTasaraha() {
        assertEquals(kp.syoEdullisesti(240), 0);
        assertEquals(kp.kassassaRahaa(), 100000+240);
    }
    
    @Test
    public void syoMaukkaastiTasaraha() {
        assertEquals(kp.syoMaukkaasti(400), 0);
        assertEquals(kp.kassassaRahaa(), 100000+400);
    }
}
