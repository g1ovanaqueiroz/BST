package adt.bst;

import adt.bt.BTNode;

public class BSTNode<T extends Comparable<T>> extends BTNode<T> {
	
	public BSTNode() {
		super();
	}

	//The code below is an example of using the Builder pattern to build
	//objects of type BSTNode without using constructor directly.
	//the client code of this pattern, creating the empty node would be:
	// 		BSTNode<Integer> node = (BSTNode<Integer>) new BSTNode.Builder<Integer>()
	//			.data(null)
	//			.left(null)
	//			.right(null)
	//			.parent(null)
	//			.build();
	
	public static class Builder<T>{
		T data;
		BTNode<T> left;
		BTNode<T> right;
		BTNode<T> parent;
	       
	    public BSTNode.Builder<T> data(T data){
	      this.data = data;
	      return this;
	    }
	    
	    public BSTNode.Builder<T> left(BTNode<T> left){
	      this.left = left;
	      return this;
	    }
	    
	    public BSTNode.Builder<T> right(BTNode<T> right){
		      this.right = right;
		      return this;
		}
	    
	    public BSTNode.Builder<T> parent(BTNode<T> parent){
		      this.parent = parent;
		      return this;
		}
	    
	     public BSTNode build(){
	      return new BSTNode(this);
	    }
	 }
	private BSTNode(BSTNode.Builder<T> builder){
	    this.data = builder.data;
	    this.left = builder.left;
	    this.right = builder.right;
	    this.parent = builder.parent;
	    
	}
}
