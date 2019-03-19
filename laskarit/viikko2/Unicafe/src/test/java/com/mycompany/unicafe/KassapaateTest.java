package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate kp;
    Maksukortti mk;
    
    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        kp = new Kassapaate();
        mk = new Maksukortti(1000);
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
    
    @Test
    public void syoEdullisestiVaihtoraha() {
        assertEquals(kp.syoEdullisesti(1000), 1000-240);
        assertEquals(kp.kassassaRahaa(), 100000+240);
    }
    
    @Test
    public void syoMaukkaastiVaihtoraha() {
        assertEquals(kp.syoMaukkaasti(1000), 1000-400);
        assertEquals(kp.kassassaRahaa(), 100000+400);
    }
    
    @Test
    public void syoEdullisestiRiittamaton() {
        assertEquals(kp.syoEdullisesti(100), 100);
        assertEquals(kp.kassassaRahaa(), 100000);
    }
    
    @Test
    public void syoMaukkaastiRiittamaton() {
        assertEquals(kp.syoMaukkaasti(300), 300);
        assertEquals(kp.kassassaRahaa(), 100000);
    }
    @Test
    public void syoEdullisestiTasarahaKortti() {
        mk.otaRahaa(1000-240);
        assertEquals(kp.syoEdullisesti(mk), true);
        assertEquals(mk.saldo(), 0);
    }
    
    @Test
    public void syoMaukkaastiTasarahaKortti() {
        mk.otaRahaa(1000-400);
        assertEquals(kp.syoMaukkaasti(mk), true);
        assertEquals(mk.saldo(), 0);
    }
    
    @Test
    public void syoEdullisestiKortti() {
        assertEquals(kp.syoEdullisesti(mk), true);
        assertEquals(mk.saldo(), 1000-240);
    }
    
    @Test
    public void syoMaukkaastiKortti() {
        assertEquals(kp.syoMaukkaasti(mk), true);
        assertEquals(mk.saldo(), 1000-400);
    }
    
    @Test
    public void syoEdullisestiRiittamatonKortti() {
        mk.otaRahaa(900);
        assertEquals(kp.syoEdullisesti(mk), false);
        assertEquals(mk.saldo(), 100);
    }
    
    @Test
    public void syoMaukkaastiRiittamatonKortti() {
        mk.otaRahaa(700);
        assertEquals(kp.syoMaukkaasti(mk), false);
        assertEquals(mk.saldo(), 300);
    }
    
    @Test
    public void syoEdullisestiKorttiEiLaitaKassaan() {
        kp.syoEdullisesti(mk);
        assertEquals(kp.kassassaRahaa(), 100000);
    }
    
    @Test
    public void syoMaukkaastiKorttiEiLaitaKassaan() {
        kp.syoMaukkaasti(mk);
        assertEquals(kp.kassassaRahaa(), 100000);
    }
    
    @Test
    public void kortilleLataus() {
        kp.lataaRahaaKortille(mk, 2000);
        assertEquals(kp.kassassaRahaa(), 100000+2000);
        assertEquals(mk.saldo(), 1000+2000);
    }
    
    @Test
    public void negatiivinenKortilleLataus() {
        kp.lataaRahaaKortille(mk, -10);
        assertEquals(kp.kassassaRahaa(), 100000);
        assertEquals(mk.saldo(), 1000);
    }
}
