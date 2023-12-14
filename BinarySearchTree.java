package trees;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree<T> implements BSTInterface<T> {

    protected BSTNode<T> root;      // reference to the root of this BST
    protected Comparator<T> comp;   // used for all comparisons
    protected int size;

    protected boolean found;   // used by remove

    public static void main(String[] args) {
        
    	try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter the number of elements to store in the binary tree: ");
			int numElements = scanner.nextInt();

			
			BinarySearchTree<Integer> tree = new BinarySearchTree<>(numElements);
			Random random = new Random();
			for (int i = 0; i < numElements; i++) {
			    int element = random.nextInt(1000); // Assuming elements are integers between 0 - 999
			    tree.add(element);
			}
			tree.displayInOrder();
			

			// Find and display the max depth of the initial tree
			int maxDepth = tree.getMaxTreeDepth();
			System.out.println("Max depth of the initial tree: " + maxDepth);

			// Find and display the optimal depth of the tree (assuming it's balanced)
			int optimalDepth = tree.size() == 0 ? 0 : (int) (Math.log(tree.size()) / Math.log(2)); // same expression as log2(tree.size())
			System.out.println("Optimal depth of the tree (assuming it's balanced): " + optimalDepth);

			// Balance the tree
			tree.balanceTree();

			// Find and display the max depth of the final tree
			maxDepth = tree.getMaxTreeDepth();
			System.out.println("Max depth of the final tree (balanced): " + maxDepth);
		}

    }

    public BinarySearchTree(int size) {
    	this.size = size;
        root = null;
        comp = new Comparator<T>() {
            @SuppressWarnings("unchecked")
			public int compare(T element1, T element2) {
                return ((Comparable<T>)element1).compareTo(element2);
            }
        };
    }

    public BinarySearchTree(Comparator<T> comp) {
        root = null;
        this.comp = comp;
    }

    public void displayInOrder() {
        System.out.print("Inorder Traversal: ");
        inOrderDisplay(root);
        System.out.println();
    }

    private void inOrderDisplay(BSTNode<T> node) {
        if (node != null) {
            inOrderDisplay(node.getLeft());
            System.out.print(node.getInfo() + " ");
            inOrderDisplay(node.getRight());
        }
    }
    public void balanceTree() {
        List<T> elements = new ArrayList<>();
        inOrderForBalancing(root, elements);
        root = balanceTreeFromArray(elements, 0, elements.size() - 1);
    }

    private void inOrderForBalancing(BSTNode<T> node, List<T> elements) {
        if (node != null) {
            inOrderForBalancing(node.getLeft(), elements);
            elements.add(node.getInfo());
            inOrderForBalancing(node.getRight(), elements);
        }
    }

    private BSTNode<T> balanceTreeFromArray(List<T> elements, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        BSTNode<T> newNode = new BSTNode<>(elements.get(mid));
        newNode.setLeft(balanceTreeFromArray(elements, start, mid - 1));
        newNode.setRight(balanceTreeFromArray(elements, mid + 1, end));
        return newNode;
    }

    // Other methods...

    @Override
    public Iterator<T> iterator() {
        return getIterator(BSTInterface.Traversal.Inorder);
    }

    @Override
    public BSTNode<T> getRoot() {
        return (BSTNode<T>) root;
    }

	@Override
	
	public boolean add(T element) {
	    root = addRecursive(root, element);
	    return true;  // Assuming the insertion is always successful
	}

	private BSTNode<T> addRecursive(BSTNode<T> node, T element) {
	    if (node == null) {
	        return new BSTNode<>(element);
	    }

	    if (comp.compare(element, node.getInfo()) < 0) {
	        node.setLeft(addRecursive(node.getLeft(), element));
	    } else if (comp.compare(element, node.getInfo()) > 0) {
	        node.setRight(addRecursive(node.getRight(), element));
	    }

	    return node;
	}


	@Override
	public T get(T target) {
		
		return null;
	}

	@Override
	public boolean contains(T target) {
		
		return false;
	}

	@Override
	public boolean remove(T target) {
		
		return false;
	}

	@Override
	public boolean isFull() {
		
		return false;
	}

	@Override
	public boolean isEmpty() {
		
		return false;
	}

	@Override
	public int size() {
	
		return size;
	}

	@Override
	public T min() {
		
		return null;
	}

	@Override
	public T max() {
		
		return null;
	}

	@Override
	public Iterator<T> getIterator(Traversal orderType) {
		
		return null;
	}

	@Override
	public int getMaxTreeDepth() {
	    return maxTreeDepth(root);
	}

	private int maxTreeDepth(BSTNode<T> node) {
	    if (node == null) {
	        return 0;
	    } else {
	        int leftDepth = maxTreeDepth(node.getLeft());
	        int rightDepth = maxTreeDepth(node.getRight());
	        return 1 + Math.max(leftDepth, rightDepth);
	    }
	}

}