/*
 * $Id: CaesarCipherTest.java,v 1.4 2008/01/29 22:23:37 bragheb Exp $
 *
 * Copyright 2000-2008, TransactTools, Inc.
 * 22 Cortlandt Street, New York NY 10007, U.S.A.
 * All Rights Reserved.
 * Patent Pending.
 *
 * This software is the confidential and proprietary information
 * of TransactTools, Inc. ("Confidential Information").  You
 * may not disclose such Confidential Information and may use
 * it only in accordance with the terms of the license agreement
 * you entered into with TransactTools.
 *
 * www.nysetransacttools.com
 *
 */

import junit.framework.TestCase;

public class CaesarCipherTest extends TestCase {

    public void testZero() {
        CaesarCipher encoder = new CaesarCipher(0);
        assertEquals("Veni, vidi, vici.", encoder.encode("Veni, vidi, vici."));
    }

    public void testShiftBy5() {
        // ABCDEFGHIJKLMNOPQRSTUVWXYZ
        // FGHIJKLMNOPQRSTUVWXYZABCDE
        CaesarCipher encoder = new CaesarCipher(5);
        assertEquals("FEAZ", encoder.encode("AZVU"));
        assertEquals("feaz", encoder.encode("azvu"));
    }

    public void testShiftBy10() {
        // ABCDEFGHIJKLMNOPQRSTUVWXYZ
        // IJKLMNOPQRSTUVWXYZABCDEFGH
        CaesarCipher encoder = new CaesarCipher(10);
        assertEquals("klbkmknklbk", encoder.encode("abracadabra"));
    }

    public void testShiftBy23() {
        // ABCDEFGHIJKLMNOPQRSTUVWXYZ
        // XYZABCDEFGHIJKLMNOPQRSTUVW
        CaesarCipher encoder = new CaesarCipher(23);
        assertEquals("XW, ZA", encoder.encode("AZ, CD"));
        assertEquals("xwza!?", encoder.encode("azcd!?"));
    }

    public void testROT13() {
        String plainText = "Fere libenter homines id quod volunt credunt.";
        CaesarCipher encoder = new CaesarCipher(13);
        String cipherText = encoder.encode(plainText);
        assertEquals(plainText, encoder.encode(cipherText));
    }

    public void testLegalShiftAmounts() {
        new CaesarCipher(0);
        new CaesarCipher(25);
    }

    public void testBadShiftAmounts() {
        try {
            new CaesarCipher(42);
            fail("shift too large");
        } catch (IllegalArgumentException e) {
            // ok!
        }
        try {
            new CaesarCipher(26);
            fail("shift too large");
        } catch (IllegalArgumentException e) {
            // ok!
        }
        try {
            new CaesarCipher(-1);
            fail("negative shift");
        } catch (IllegalArgumentException e) {
            // ok!
        }
        try {
            new CaesarCipher(-3);
            fail("negative shift");
        } catch (IllegalArgumentException e) {
            // ok!
        }
    }

    public void testNull() {
        CaesarCipher encoder = new CaesarCipher(13);
        assertNull(encoder.encode(null));
    }

}
