package edu.grinnell.csc207.util.nodes;

/**
 * An interface for nodes in a BitTree.
 *
 * @author Sara Jaljaa
 *
 * @param <N>
 *    The type of node.
 * @param <V>
 *    The type of the value at the node.
 */
public interface BitTreeNode<N, V> {

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the left subtree.
   *
   * @return
   *    The node of the left subtree.
   */
  public N left();

  /**
   * Get the right subtree.
   *
   * @return
   *    The node of the right subtree.
   */
  public N right();

  /**
   * Get the value at a node.
   *
   * @return
   *    The value at the node.
   */
  public V get();
} // interface TreeNode
