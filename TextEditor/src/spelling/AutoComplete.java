/**
 * 
 */
package spelling;

import java.util.List;

/**
 * @author TamalikaM
 *
 */
public interface AutoComplete {
	public List<String> predictCompletions(String prefix, int numCompletions);

	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	boolean isWord(String s);
}
