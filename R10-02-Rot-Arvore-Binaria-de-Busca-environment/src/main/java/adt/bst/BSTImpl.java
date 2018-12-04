
package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.getRoot());
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty())
			return -1;
		return 1 + Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight()));
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(this.getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty())
			return new BSTNode<T>();
		else if (node.getData().equals(element))
			return node;
		else if (element.compareTo(node.getData()) > 0)
			return this.search((BSTNode<T>) node.getRight(), element);
		return this.search((BSTNode<T>) node.getLeft(), element);
	}

	@Override
	public void insert(T element) {
		insert(this.getRoot(), element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else if (element.compareTo(node.getData()) > 0)
			this.insert((BSTNode<T>) node.getRight(), element);
		else
			this.insert((BSTNode<T>) node.getLeft(), element);

	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;
		else if (node.getRight().isEmpty())
			return node;
		return this.maximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;
		else if (node.getLeft().isEmpty())
			return node;
		return this.minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty())
			return null;
		else if (!node.getRight().isEmpty())
			return this.minimum((BSTNode<T>) node.getRight());

		while (node != null && node.getData().compareTo(element) <= 0) {
			node = (BSTNode<T>) node.getParent();
		}
		return node;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty())
			return null;
		else if (!node.getLeft().isEmpty())
			return this.maximum((BSTNode<T>) node.getLeft());
		
		while (node != null && node.getData().compareTo(element) >= 0) {
			node = (BSTNode<T>) node.getParent();
		}
		return node;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			this.remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.isLeaf()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);
		} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			this.swap(node, (BSTNode<T>) node.getLeft());
			this.remove((BSTNode<T>) node.getLeft());
		} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			this.swap(node, (BSTNode<T>) node.getRight());
			this.remove((BSTNode<T>) node.getRight());
		} else {
			BSTNode<T> pred = this.minimum((BSTNode<T>) node.getRight());
			this.swap(node, pred);
			remove(pred);
		}
	}

	private void swap(BSTNode<T> node, BSTNode<T> predecessor) {
		T aux = node.getData();
		node.setData(predecessor.getData());
		predecessor.setData(aux);
	}

	@Override
	public T[] preOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		this.preOrder(result, 0, this.getRoot());
		return result;
	}

	private int preOrder(T[] result, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			result[i++] = node.getData();
			i = preOrder(result, i, (BSTNode<T>) node.getLeft());
			i = preOrder(result, i, (BSTNode<T>) node.getRight());
		}
		return i;
	}

	@Override
	public T[] order() {
		T[] result = (T[]) new Comparable[this.size()];
		this.order(result, 0, this.getRoot());
		return result;
	}

	private int order(T[] result, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			i = this.order(result, i, (BSTNode<T>) node.getLeft());
			result[i++] = node.getData();
			i = this.order(result, i, (BSTNode<T>) node.getRight());
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		this.postOrder(result, 0, this.getRoot());
		return result;
	}

	private int postOrder(T[] result, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			i = this.postOrder(result, i, (BSTNode<T>) node.getLeft());
			i = this.postOrder(result, i, (BSTNode<T>) node.getRight());
			result[i++] = node.getData();
		}
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
