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
}
