package edu.grinnell.csc207.util.nodes;

/**
 * The leaf of a BitTree.
 *
 * @author Sara Jaljaa
 */
public class BitTreeLeaf implements BitTreeNode<BitTreeLeaf, String> {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The value at a leaf.
   */
  private String value;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new leaf node.
   *
   * @param value
   *    The value at the node.
   */
  public BitTreeLeaf(String value) {
    this.value = value;
  } // Leaf(String)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * The value of the left child of a leaf (empty).
   *
   * @return
   *    A null leaf.
   */
  public BitTreeLeaf left() {
    return null;
  } // left()

    /**
   * The value of the right child of a leaf (empty).
   *
   * @return
   *    A null leaf.
   */
  public BitTreeLeaf right() {
    return null;
  } // right()

  /**
   * Get the value of a leaf.
   *
   * @return
   *    The value of the leaf.
   */
  public String get() {
    return this.value;
  } // get()
} // class Leaf
