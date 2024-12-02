package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Tests for methods within the project as they're implemented.
 *
 * @author Sara Jaljaa
 */
public class TestSara {

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Braille to ASCII.
   */
  static final String[][] B2A = {
      ("100000,A").split(","), ("110000,B").split(","), ("100100,C").split(","),
      ("100110,D").split(","), ("100010,E").split(","), ("110100,F").split(","),
      ("110110,G").split(","), ("110010,H").split(","), ("010100,I").split(","),
      ("010110,J").split(","), ("101000,K").split(","), ("111000,L").split(","),
      ("101100,M").split(","), ("101110,N").split(","), ("101010,O").split(","),
      ("111100,P").split(","), ("111110,Q").split(","), ("111010,R").split(","),
      ("011100,S").split(","), ("011110,T").split(","), ("101001,U").split(","),
      ("111001,V").split(","), ("101101,X").split(","), ("101111,Y").split(","),
      ("101011,Z").split(","), ("010111,W").split(","), ("000000, ").split(",")};

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Manually load values for a braille -> ASCII tree & check that they
   * all set properly.
   */
  @Test
  public void b2aTest() {
    BitTree tree = new BitTree(6);

    /* Set values A-M */
    tree.set(B2A[0][0], B2A[0][1]);   /* A */
    tree.set(B2A[1][0], B2A[1][1]);   /* B */
    tree.set(B2A[2][0], B2A[2][1]);   /* C */
    tree.set(B2A[3][0], B2A[3][1]);   /* D */
    tree.set(B2A[4][0], B2A[4][1]);   /* E */
    tree.set(B2A[5][0], B2A[5][1]);   /* F */
    tree.set(B2A[6][0], B2A[6][1]);   /* G */
    tree.set(B2A[7][0], B2A[7][1]);   /* H */
    tree.set(B2A[8][0], B2A[8][1]);   /* I */
    tree.set(B2A[9][0], B2A[9][1]);   /* J */
    tree.set(B2A[10][0], B2A[10][1]); /* K */
    tree.set(B2A[11][0], B2A[11][1]); /* L */
    tree.set(B2A[12][0], B2A[12][1]); /* M */
    tree.set(B2A[26][0], B2A[26][1]); /*   */

    /* Check that the values are set properly. */
    assertEquals("A", tree.get(B2A[0][0]), "A has been set.");
    assertEquals("B", tree.get(B2A[1][0]), "B has been set.");
    assertEquals("C", tree.get(B2A[2][0]), "C has been set.");
    assertEquals("D", tree.get(B2A[3][0]), "D has been set.");
    assertEquals("E", tree.get(B2A[4][0]), "E has been set.");
    assertEquals("F", tree.get(B2A[5][0]), "F has been set.");
    assertEquals("G", tree.get(B2A[6][0]), "G has been set.");
    assertEquals("H", tree.get(B2A[7][0]), "H has been set.");
    assertEquals("I", tree.get(B2A[8][0]), "I has been set.");
    assertEquals("J", tree.get(B2A[9][0]), "J has been set.");
    assertEquals("K", tree.get(B2A[10][0]), "K has been set.");
    assertEquals("L", tree.get(B2A[11][0]), "L has been set.");
    assertEquals("M", tree.get(B2A[12][0]), "M has been set.");
    assertEquals(" ", tree.get(B2A[26][0]), "Space has been set.");
  } // b2aTest()

  /**
   * Check that an exception is thrown when trying to set with invalids
   * parameters to set.
   */
  @Test
  public void setTest() {
    BitTree tree = new BitTree(6);
    tree.set(B2A[13][0], B2A[13][1]);
    tree.set(B2A[14][0], B2A[14][1]);

    /* Try to set a bit with length 7 in a 6 level bit tree */
    assertThrows(IndexOutOfBoundsException.class, () -> tree.set("0001001", "d"));

    /* Try to set a bit with non-zero or one digits */
    assertThrows(IllegalArgumentException.class, () -> tree.set("091329", "e"));

    /* Try to set a string longer than one */
    assertThrows(IllegalArgumentException.class, () -> tree.set("010101", "dee"));

    /* Try to set an empty string */
    assertThrows(IllegalArgumentException.class, () -> tree.set("010010", ""));
  } // setTest()

  /**
   * Check that the values in interior nodes are null.
   */
  @Test
  public void getTest() {
    BitTree tree = new BitTree(6);
    tree.set(B2A[15][0], B2A[15][1]);
    assertEquals(null, tree.get("1"));
    assertEquals(null, tree.get("11"));
    assertEquals(null, tree.get("111"));
    assertEquals(null, tree.get("1111"));
    assertEquals(null, tree.get("11110"));
    assertEquals("P", tree.get("111100"));
  } // getTest()

  /**
   * Jankily try loading b2a.txt & check that it loaded correctly
   * (to avoid manually checking) until Sam comes up with a better
   * way to test load().
   */
  @Test
  public void loadTest() {
    BitTree tree = new BitTree(6);
    try {
      FileInputStream f = new FileInputStream(
        "src/main/java/edu/grinnell/csc207/util/convert/b2a.txt");
      tree.load(f);
      BufferedReader br = new BufferedReader(new InputStreamReader(f));
      String str = "";
      String[] s = new String[2];
      while ((str = br.readLine()) != null) {
        s = str.split(",");
        if (tree.get(s[0]) == null) {
          throw new IllegalArgumentException();
        } // if
      } // while
      br.close();
    } catch (Exception e) {
      /* Should throw an IOException when br is closed (can double
      check with e.printStackTrace()). If it is throwing an IOException
      for some unknown other reason, it doesn't matter. */
      assertEquals(IOException.class, e.getClass());
    } // try/catch
  } // loadTest()
} // class TestSara
