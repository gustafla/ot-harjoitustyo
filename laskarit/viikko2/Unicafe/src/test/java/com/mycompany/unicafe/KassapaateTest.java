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
}
