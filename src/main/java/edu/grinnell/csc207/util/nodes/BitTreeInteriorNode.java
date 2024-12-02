package edu.grinnell.csc207.util.nodes;

/**
 * The interior nodes of the BitTree.
 *
 * @author Sara Jaljaa
 */
public class BitTreeInteriorNode implements BitTreeNode<BitTreeInteriorNode, BitTreeLeaf> {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The terminating leaf.
   */
  private BitTreeLeaf value;

  /**
   * The left subtree of a BitTree node.
   */
  private BitTreeInteriorNode left;

  /**
   * The right subtree of a BitTree node.
   */
  private BitTreeInteriorNode right;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create an empty interior node.
   */
  public BitTreeInteriorNode() {
    this.left = null;
    this.right = null;
    this.value = null;
  } // BitTreeInteriorNode()

  /**
   * Create a tree of interior nodes.
   *
   * @param n
   *    The levels to add.
   */
  public BitTreeInteriorNode(int n) {
    if (n < 1) {
      this.left = null;
      this.right = null;
    } else {
      this.left = new BitTreeInteriorNode(n - 1);
      this.right = new BitTreeInteriorNode(n - 1);
    } // elif
  } // BitTreeInteriorNode(int)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the left node.
   *
   * @return
   *    The left node.
   */
  public BitTreeInteriorNode left() {
    return this.left;
  } // left()

  /**
   * Get the right node.
   *
   * @return
   *    The right node.
   */
  public BitTreeInteriorNode right() {
    return this.right;
  } // right()

  /**
   * Add a leaf.
   *
   * @param leaf
   *    The leaf to add.
   */
  public void addLeaf(BitTreeLeaf leaf) {
    this.value = leaf;
  } // addLeaf(Leaf)

  /**
   * Get the leaf at a node.
   *
   * @return
   *    The leaf (or null, if there is no leaf).
   */
  public BitTreeLeaf get() {
    return this.value;
  } // get()
} // class InteriorNode
