import java.awt.Graphics;

/**
 * The DataCollection interface contains methods that can be used to manipulate
 * a collection.
 *  
 * Central to the definition of that interface (and the manipulation of the
 *		collection), is the notion of a 'selected item.'
 *
 *	There is at most one item selected in a collection. It is the item that was
 *		last added, or the item that will be next handled.
 *		
 * @author Cameron Cole
 *
 */
public interface DataCollection
{	
	
	/**
	 * Adds the given Block to the collection.
	 * That item becomes the item currently selected.
	 * @param someBlock
	 */
	public void add(Cell cell);


	/**
	 * resets the simulation and collection to default values
	 */
	public void reset();
	
	/**
	 * 	If the given item is part of the collection,
		that item becomes the selected item.
		If the given item is not part of the collection,
		no item remains selected.
		
	 * @param cell
	 */
	public void reset(Cell cell);

	/**
	 * 	Determines whether there is a selected item
	 * (in other words, returns true,
	 * if an invocation to next would return an item).
	 * 
	 * @return
	 */
	public boolean hasNext();
	
	/**
	 * 	If an item is currently selected,
	 * 	returns a reference to that item and sets	
	 * the following item as the selected item (if any).		
	 * If no item is currently selected, returns null.
	 * 
	 * @return
	 */
	public Cell next();
	
	/**
	 * checks to see if the collection is empty
	 * @return
	 */
	public boolean isEmpty();
  	
	/**
	 * Paints the entire collection of items
     * (possibly including a selected item).
     *  
	 * @param pane
	 */
	public void paint(Graphics pane, boolean draw2D, boolean drawIso);

}
