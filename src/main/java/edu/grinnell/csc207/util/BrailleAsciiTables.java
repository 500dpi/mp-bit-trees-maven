package edu.grinnell.csc207.util;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Converts between braille, ASCII, and Unicode.
 *
 * @author Sara Jaljaa
 * @author Samuel A. Rebelsky
 */
public class BrailleAsciiTables {

  // +---------------+-----------------------------------------------
  // | Static fields |
  // +---------------+

  /**
   * A BitTree that converts from ASCII to braille.
   */
  static BitTree a2bTree = null;

  /**
   * A BitTree that converts from braille to ASCII.
   */
  static BitTree b2aTree = null;

  /**
   * A BitTree that converts from braille to Unicode.
   */
  static BitTree b2uTree = null;

  // +-----------------------+---------------------------------------
  // | Static helper methods |
  // +-----------------------+

  /**
   * Convert a string of values given a loaded BitTree.
   *
   * @param tree
   *    The BitTree (loaded).
   * @param bits
   *    The string to convert.
   * @return
   *    The converted string.
   */
  public static String conversion(BitTree tree, String bits) {
    int levels = tree.levels();
    /* Check that the bit length is appropriate for the given tree */
    if (bits.length() % levels != 0) {
      throw new IllegalArgumentException();
    } // if
    StringBuilder sb = new StringBuilder("");
    /* Get the values stored from each bit in the string */
    for (int i = levels; i <= bits.length(); i += levels) {
      sb.append(tree.get(bits.substring(i - levels, i)));
    } // while
    return sb.toString();
  } // conversion(BitTree, String, int)

  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /**
   * Convert an ASCII character to braille.
   *
   * @param letter
   *    The letter to convert.
   * @return
   *    A string representing the letter in braille.
   */
  public static String toBraille(char letter) {
    /* Check tree has nodes */
    if (null == a2bTree) {
      a2bTree = new BitTree(8);
      try {
        /* Get file to load from */
        FileInputStream fs = new FileInputStream(
            "src/main/java/edu/grinnell/csc207/util/convert/a2b.txt");
        a2bTree.load(fs);
        fs.close();
      /* Add a zero for unsigned representation of char */
        return a2bTree.get("0" + Integer.toBinaryString(letter));
      } catch (IOException e) {
        // Doesn't matter.
      } // try/catch
    } // if
    return a2bTree.get("0" + Integer.toBinaryString(letter));
  } // toBraille(char)

  /**
   * Converts braille into an ASCII character.
   *
   * @param bits
   *    The braille to convert.
   * @return
   *    An ASCII letter representing the braille pattern.
   */
  public static String toAscii(String bits) {
    // Make sure we've loaded the braille-to-ASCII tree.
    if (null == b2aTree) {
      b2aTree = new BitTree(6);
      try {
        FileInputStream fs = new FileInputStream(
            "src/main/java/edu/grinnell/csc207/util/convert/b2a.txt");
        b2aTree.load(fs);
        fs.close();
        return conversion(b2aTree, bits);
      } catch (IOException e) {
        // We don't care if we can't close the stream.
      } // try/catch
    } // if
    return conversion(b2aTree, bits);
  } // toAscii(String)

  /**
   * Convert braille into Unicode.
   *
   * @param bits
   *    The braille to convert.
   * @return
   *    A Unicode character representing the braille pattern.
   */
  public static String toUnicode(String bits) {
    /* Check tree has nodes */
    if (null == b2uTree) {
      b2uTree = new BitTree(6);
      try {
        /* Get file to load from */
        FileInputStream fs = new FileInputStream(
            "src/main/java/edu/grinnell/csc207/util/convert/b2u.txt");
        b2uTree.load(fs);
        fs.close();
        /* Find the codepoint value of the Unicode character (U+HHHH) */
        int codepoint = Integer.valueOf(conversion(b2uTree, bits), 16);
        return String.valueOf((char) codepoint);
      } catch (IOException e) {
        // Doesn't matter.
      } // try/catch
    } // if
    return conversion(b2uTree, bits);
  } // toUnicode(String)
} // class BrailleAsciiTables
