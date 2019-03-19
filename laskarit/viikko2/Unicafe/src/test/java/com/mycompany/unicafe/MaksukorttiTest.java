package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOikein() {
        assertEquals(kortti.saldo(), 10);
    }
    
    @Test
    public void lataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(5);
        assertEquals(kortti.saldo(), 15);
    }
    
    @Test
    public void rahanOttaminenToimii() {
        assertTrue(kortti.otaRahaa(10));
        assertEquals(kortti.saldo(), 0);
    }
    
    @Test
    public void kortiltaEiVoiOttaaLiikaaRahaa() {
        assertFalse(kortti.otaRahaa(11));
        assertEquals(kortti.saldo(), 10);
    }
    
    @Test
    public void toStringToimii() {
        assertEquals(kortti.toString(), "saldo: 0.10");
        kortti.lataaRahaa(5);
        assertEquals(kortti.toString(), "saldo: 0.15");
    }
    
    @Test
    public void toStringNayttaaEurotJaSentit() {
        kortti.lataaRahaa(120);
        assertEquals(kortti.toString(), "saldo: 1.30");

    }
}
