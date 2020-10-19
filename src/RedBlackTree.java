// --== CS400 File Header Information ==--
// Name: Ryan McBrayer
// Email: rmcbrayer@wisc.edu
// Team: ED
// TA: Keren
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing
 * the nodes within a binary search tree.  You can use this class' insert
 * method to build a binary search tree, and its toString method to display
 * the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always be maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild; 
        public Node<T> rightChild; 
        public boolean isBlack;
        public Node(T data) { this.data = data; }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
        /**
         * This method performs a level order traversal of the tree rooted
         * at the current node.  The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         * @return string containing the values of this tree in level order
         */
        @Override
        public String toString() { // display subtree in order traversal
            String output = "[";
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree.  After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the tree already contains data
     */
    public void insert(T data) throws NullPointerException,
              IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        newNode.isBlack = false;
          // add first node to an empty tree
        if(root == null) {
          root = newNode; 
          root.isBlack = true;
          }
        else { insertHelper(newNode,root); // recursively insert into subtree
        enforceRBTreePropertiesAfterInsert(newNode);
        }
        
    }

    /** 
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references (as defined by Comparable.compareTo())
     */
    private void insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) throw new IllegalArgumentException(
            "This RedBlackTree already contains that value.");

        // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else { 
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.rightChild);
        }
    }

    /**
     * This method performs a level order traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { return root.toString(); }

    /**
     * Performs the rotation operation on the provided nodes within this BST.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation (sometimes called a left-right 
     * rotation).  When the provided child is a rightChild of the provided 
     * parent, this method will perform a left rotation (sometimes called a 
     * right-left rotation).  When the provided nodes are not related in one 
     * of these ways, this method will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    void rotate(Node<T> child, Node<T> parent)
  throws IllegalArgumentException {
      if(!child.isLeftChild() && !parent.rightChild.equals(child)) {
        throw new IllegalArgumentException("Child and parent are not related");
      }
      //if child is left child perform right rotation
      if(child.isLeftChild()) {
        Node<T> leftRight = parent.leftChild.rightChild;
        if(parent.equals(root)) {
          parent.parent = child;
          root = child;
          root.rightChild = parent;
          parent.leftChild = leftRight;
          return;
        }
        else {
         
          child.parent = parent.parent;
          parent.parent.leftChild = child;
          parent.leftChild = child.rightChild;
          child.rightChild = parent;
          parent.parent = child;

          
        }

        
       
          return;
      }
      //else assume left rotation
      Node<T> rightLeft = parent.rightChild.leftChild;
     if(parent.equals(root)) {
       parent.parent = child;
       root = child;
       root.leftChild = parent;
       parent.rightChild = rightLeft;
       return;
     }
     else {
       child.parent = parent.parent;
       parent.parent.rightChild = child;
       parent.rightChild = child.leftChild;
       child.rightChild = parent;
       parent.parent = child;
     }


    }
    /*Called after inserting a new node into the RB Tree
     * in order to maintain the properties of a RB tree
     * 
     * @param newNode - a reference to the new node to be inserted
     * into to RB tree]
     * 
     */
   private void enforceRBTreePropertiesAfterInsert(Node newNode) {
     //if new node is root then color it black and return
     if(newNode.equals(root) || newNode.parent == null) {
      newNode.isBlack = true;
       return;
     }
     //if the parent of the node is black then no balance needed
     else if(newNode.parent.isBlack) {
       return;
     }
     //find relatives to node
     Node<T> parent = newNode.parent;
     Node<T> grandparent = newNode.parent.parent;
     Node<T> uncle;
     if(grandparent.leftChild.equals(parent)) {
       uncle = grandparent.rightChild;
     }
     else {
       uncle = grandparent.leftChild;
     }
     //if uncle exists and is red, recolor nodes and call again
     if(uncle != null && uncle.isBlack == false) {
       grandparent.isBlack = false;
       parent.isBlack = true;
       uncle.isBlack = true;
       //then recursively call again on grandparent
       enforceRBTreePropertiesAfterInsert(grandparent);
       return;
     }
     //if new node is a right child of a left child, then rotate right
     if (!newNode.isLeftChild() && newNode.parent.isLeftChild()) {
       rotate(parent, grandparent);
       newNode = parent;
       parent = newNode.parent;
     }
     //change colors of parent and grandparent
     parent.isBlack = true;
     grandparent.isBlack = false;
     //rotate left/right based on if child is left or right
     if(newNode.isLeftChild()) {
       rotate(parent, grandparent);
         
       }
       else {
         rotate(parent, grandparent);
       }
     }

   }

