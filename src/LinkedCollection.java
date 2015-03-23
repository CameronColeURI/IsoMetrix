/*
	ArrayDataCollection.java

		This class defines and implements a collection of data items.

		The implementation is based on an array of Items.
		
	Author:
	
	Cameron Cole
	CSC212
	

*/

import java.awt.*;							//	AWT = "Abstract Window Toolkit"

/**
 * Unlike the other Classes, I kept some of the implementation in this Class due to it already
 * being very applicable. The LinkedCollection Class, taken from my own Program3 implementation,
 * is the Complex Data Structure Class of the Final Program. Although most of this program is similar
 * to Program2, this Class actually is most similar to Program3 in the idea that it keeps use
 * of selected and works with a linked list. The LinkedCollection class will be used in my Final Program
 * to hold all the blocks in the game window. Its primary interaction will be with the GameOrganizer
 * class during the adding of the blocks, and with the Ball class when dealing with collision detection.
 * 
 * @author Cameron Cole
 *
 */
public class LinkedCollection implements DataCollection
{
	//Instance Variables
	private final int SIZE = 15;	//	Default size for the collection

	private Node head;	//declaring the Node head
	
	private Node selected;	//declaring the reference selected
	
	private int x, y;	//	Location of the "first" block
						//		in the collection
	
	
	//begin inner Node class
	private class Node
	{
		private Object cell;	//stores the Node's block
		private Node next;	//stores the reference to the next node
		
		/**
		 * default constructor
		 */
		public Node()
		{
			cell = null;
			next = null;
		}
		
		/**
		 * unused constructor which takes parameter for block and next
		 * @param item
		 * @param next
		 */
		public Node(Cell cell, Node next)
		{
			this.cell = cell;
			this.next = next;
		}
		
		/**
		 * setter for block
		 * @param someBlock
		 */
		public void setCell(Object someCell)
		{
			this.cell = someCell;
		}
		
		/**
		 * getter for block
		 * @return
		 */
		public Object getCell()
		{
			return this.cell;
		}
		
		/**
		 * setter for next
		 * @param next
		 */
		public void setNext(Node next)
		{
			this.next = next;
		}
		
		/**
		 * getter for next
		 * @return
		 */
		public Node getNext()
		{
			return this.next;
		}
		
	}	//end inner Node class
	
	/**
	 * default constructor
	 */
	public LinkedCollection()
	{
		this(199, 199);	//somewhere random
		
		this.head = null;	//head is initially a reference to null
	}
	
	/**
	 * constructor which takes parameters initial location of the first block
	 * @param x
	 * @param y
	 */
	public LinkedCollection(int x, int y)
	{
		this.head = null;	//head is initially a reference to null
		
		this.x = x;	//location of first block
		this.y = y;
	}
	
	/**
	 * method to check if the collection is empty
	 * @return
	 */
	public boolean isEmpty()
	{
		return head == null;
	}
	
	/**
	 * finds length of collection
	 * @return
	 */
	public int length()
	{
		return length(head);
	}
	
	/**
	 * recursive length method
	 * 
	 * @param head
	 * @return
	 */
	public int length(Node head)
	{
		if (head == null)
		{
			return 0;
		}
		else
			return 1 + length(head.getNext());	//recursion
	}
	
	/**
	 * changeSelected is used for literally changing the selected Node.
	 * 
	 * @param newSelected
	 */
	private void changeSelected(Node newSelected)
	{
		if (selected != null) {
			((Cell) selected.getCell()).setHighlight(false);
		}
		if (newSelected != null) {
			((Cell) newSelected.getCell()).setHighlight(true);
		}
		selected = newSelected;
	}
	
	/**
	 * Adds a given Block to the LinkedCollection and makes that Block the currently selected.
	 */
	public void add(Cell cell)
	{
		Node placeHolder = new Node();	//placeHolder node (a.k.a. the node being added)
		
		if (head == null)	//first case scenario where head is null
		{			
			head = new Node();	//head is initialized

			head.setCell(cell);
			changeSelected(head);	//make selected head because head
									//was technically added to the list
		}
		else	//if we have our head...
		{			
			placeHolder.setCell(cell);
			selected.setNext(placeHolder);	//linking the list!
			
			changeSelected(selected.getNext());	//change selected to the newly added Node
		}
	}

	/**
	 * By default the reset method changes selected to the very beginning of the collection.
	 */
	public void reset()
	{
		changeSelected(head);	//head is start of list so we change selected to head
	}

/**
 * 	If the given block is part of the collection,
	that block becomes the selected item.
	If the given block is not part of the collection,
	no block remains selected.
	
 * @param someItem
 */
	public void reset(Cell someCell)
	{
		changeSelected(null);	//in case of there being no match
		
		Node current = head; //for looping through the collection
		
		while ((selected == null) && (current != null)) //while we haven't found someItem in a Node
										//and while we are still in the linked list
		{
			if (current.getCell() == someCell)	//we found the item!
			{
				changeSelected(current);	//selected node is now Current
			}
			current = current.getNext();	//iteration
		}
	}

	/**
	 * 	Determines whether there is a selected block
		(in other words, returns true,
		if an invocation to next would return an block).
	 */
	public boolean hasNext() 
	{
		return	(selected != null);
	}

	
	/**
	 * 	If a block is currently selected,
		returns a reference to that block and sets
		the following block as the selected block (if any).
		If no block is currently selected, returns null.
	 */
	public Cell next()
	{	
		Cell result = null;	//null for if no item is currently selected
		
		if (hasNext()) //if our selected isn't null
		{			
			result = (Cell) selected.getCell();	//result becomes selected's item
			
			if (selected.getNext() != null)	//if selected has a next
			{
				changeSelected(selected.getNext());	//we make selected the next Node in the list
			}
			else
			{
				changeSelected(null);	//if there is no next Node then we say selected is null
			}
		}	

		return result;
	}

	/**
	 * Only graphical method of the LinkedCollection class which is used to paint
	 * the collection of blocks into the window.
	 */
	public void paint(Graphics pane, boolean draw2D, boolean drawIso)
	{
		if (isEmpty() == false)	//if we have a collection to paint
		{
			Node current = head;	//for looping through the list
			
			while (current != null)	//while we are still in the list
			{
				((Cell)current.getCell()).paint(pane, draw2D, drawIso);
				//we paint current's item
				current = current.getNext();	//and increment current
			}
			
		}
	}
	
}	// end LinkedCollection
