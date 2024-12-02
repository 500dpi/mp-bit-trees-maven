package edu.grinnell.csc207.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import edu.grinnell.csc207.util.nodes.BitTreeInteriorNode;
import edu.grinnell.csc207.util.nodes.BitTreeLeaf;

import java.io.PrintWriter;

/**
 * Trees intended to be used in storing mappings between fixed-length
 * sequences of bits and corresponding values.
 *
 * @author Sara Jaljaa
 * @author Samuel A. Rebelsky
 */
public class BitTree {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The levels of the tree.
   */
  private int levels;

  /**
   * The root of the tree.
   */
  BitTreeInteriorNode root;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new BitTree.
   *
   * @param n
   *    The levels in the tree.
   */
  public BitTree(int n) {
    this.levels = n;
    this.root = new BitTreeInteriorNode(n);
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Traverse the tree to find the node to add a leaf to.
   *
   * @param bit
   *    The bit value to add.
   * @return
   *    The node that matches the bit pattern.
   */
  private BitTreeInteriorNode traverse(String bit) {
    BitTreeInteriorNode node = this.root;
    for (int i = 0; i < bit.length(); i++) {
      if (node != null) {
        if (bit.charAt(i) == '0' && node.left() != null) {
          node = node.left();
        } else if (bit.charAt(i) == '1' && node.right() != null) {
          node = node.right();
        } else {
          throw new IllegalArgumentException(
              "String: [" + bit + "] does not contain appropriate values.");
        } // elif
      } // if
    } // for(bit)
    return node;
  } // traverse(String)

  /**
   * Read a file, given a path of the form:
   * << "src/main/java/edu/grinnell/csc207/util/convert/[FILE].txt" >>
   * and set a tree's leaves according to the file's contents.
   *
   * @param stream
   *    The file path.
   *
   * @throws IOException
   *    When the file does not exist.
   */
  private void read(InputStream stream) throws IOException {
    /* Cannot read from an empty stream. */
    if (stream == null) {
      throw new IOException();
    } // if

    String str = "";
    String[] conversion = new String[2];
    BufferedReader br = new BufferedReader(new InputStreamReader(stream));

    /* Until EOF, read a line & split at the comma; use the resulting
    values to set the tree. */
    while ((str = br.readLine()) != null) {
      conversion = str.split(",");
      this.set(conversion[0], conversion[1]);
    } // while
    br.close();
  } // read(InputStream)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the levels in a tree.
   *
   * @return
   *    The number of levels in a tree.
   */
  public int levels() {
    return this.levels;
  } // levels()

  /**
   * Set a value in a BitTree.
   *
   * @param bits
   *    The braille/bits pattern.
   * @param value
   *    The value to set the tree's bit pattern to.
   * @return
   *    The set string value.
   *
   * @throws IndexOutOfBoundsException
   *    When the bits are greater than the tree's levels.
   */
  public String set(String bits, String value) throws IndexOutOfBoundsException {
    if (bits.length() != this.levels) {
      throw new IndexOutOfBoundsException(
          "Bit length " + bits.length() + " is greater than tree levels: " + this.levels);
    } // if
    if (value.length() != 1) {
      throw new IllegalArgumentException("String [" + value + "] must have length = 1.");
    } // if
    BitTreeLeaf leaf = new BitTreeLeaf(value);
    BitTreeInteriorNode node = traverse(bits);
    node.addLeaf(leaf);
    return leaf.get();
  } // set(String, String)

  /**
   * Get a value in a BitTree.
   *
   * @param bits
   *    The braille/bits pattern.
   * @return
   *    The value stored at the bit's pattern.
   */
  public String get(String bits) {
    BitTreeLeaf leaf = this.traverse(bits).get();
    if (leaf == null) {
      return null;
    } else {
      return leaf.get();
    } // elif
  } // get(String)

  /**
   * Print all the contents of a BitTree.
   *
   * @param pen
   *    The pen to print with.
   */
  public void dump(PrintWriter pen) {
    // STUB
  } // dump(PrintWriter)

  /**
   * Loads a tree with the values to convert to.
   *
   * @param source
   *    The files containing the tree paths & their corresponding
   *    leaf values.
   */
  public void load(InputStream source) {
    try {
      this.read(source);
    } catch (IOException e) {
      return;
    } // try/catch
  } // load(InputStream)
} // class BitTree
