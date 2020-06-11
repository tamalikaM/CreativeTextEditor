package spelling;

import java.util.TreeSet;

/**
 * @author TamalikaM
 *
 */
public class DictionaryBSTMatchCase implements Dictionary 
{
   private TreeSet<String> dict;
	
    // Implement the dictionary interface using a TreeSet.
 	// You'll need a constructor here
   
   public DictionaryBSTMatchCase() {
	   dict = new TreeSet<String>();
   }
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	String wordLowerCase = word;
    	if(!dict.contains(wordLowerCase)) {
    		dict.add(wordLowerCase);
    		return true;
    	}
    	return false;
    }

    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	if(dict.contains(s.toLowerCase()))
    		return true;
        return false;
    }
}
