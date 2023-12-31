//----------------------------------------------------------------------------
// BSTInterface.java            by Dale/Joyce/Weems                  
//
// Interface for a class that implements a binary search tree (BST).
//
// The trees are unbounded and allow duplicate elements, but do not allow null
// elements. As a general precondition, null elements are not passed as 
// arguments to any of the methods.
//----------------------------------------------------------------------------
package trees;

import java.util.Iterator;

public interface BSTInterface<T> extends CollectionInterface<T>, Iterable<T>
{
  // Used to specify traversal order.
  public enum Traversal {Inorder, Preorder, Postorder};

  T min();
  // If this BST is empty, returns null;
  // otherwise returns the smallest element of the tree.

  T max();
  // If this BST is empty, returns null;
  // otherwise returns the largest element of the tree.
  
  public Iterator<T> getIterator(Traversal orderType);
  // Creates and returns an Iterator providing a traversal of a "snapshot" 
  // of the current tree in the order indicated by the argument.

  int getMaxTreeDepth(); // New method to calculate the max depth
  void balanceTree();    // New method to balance the tree

  BSTNode<T> getRoot();
} 