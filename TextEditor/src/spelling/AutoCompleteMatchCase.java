package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author TamalikaM
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    private List<String> wordList;
    private int numCompletionsList;    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
		wordList = new LinkedList<String>();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		boolean insertFlag = false;

		if(word == null)
			throw new NullPointerException();

		String wordLowerCase = word.trim().toLowerCase();			
		TrieNode curr = root;
		char[] wordArray= wordLowerCase.toCharArray();		
		if(wordArray.length == 0)
			return false;
		for(char c: wordArray) {
			if(curr.getChild(c) == null) {
				curr = curr.insert(c);
				insertFlag = true;
			}
			else {
				curr = curr.getChild(c);
			}
		}
		if(insertFlag || curr.getText().equals(wordLowerCase))
			curr.setEndsWord(true);
		return insertFlag;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
		size = 0;
		preOrderTraversal(root);
		return size;
	}
	
	private void preOrderTraversal(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		if(curr.endsWord()) {
 			size ++;
 		}
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			preOrderTraversal(next);
 		}
 	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	
	private boolean isWordMatchCase(String s) 
	{
		String wordLowerCase = s.trim().toLowerCase();
		TrieNode curr = root;
		char[] wordArray= wordLowerCase.toCharArray();
		for(char c: wordArray) {
			if(curr.getChild(c) == null) {
				return false;
			}
			else {
				curr = curr.getChild(c);
				}
			}
		if(curr.endsWord())
			return true;
		return false;
	}
	
	@Override
	public boolean isWord(String word) {
				
		char[] wordArray = word.toCharArray();
		if(wordArray.length == 0)
			return false;
		if(Character.isUpperCase(wordArray[0])) {
			// First character is upper case, the following characters have to either be all upper case
			// or all lower case
			if(wordArray.length > 1 && Character.isUpperCase(wordArray[1])) {
				//second character is upper case
				//all characters must be upper case
				for(int i = 2; i < wordArray.length; i++) {
					if(Character.isLowerCase(wordArray[i])) {
						return false;
					}
				}
			}
			else if(wordArray.length > 1 && Character.isLowerCase(wordArray[1])) {
				//second character is lower case 
				//all characters must be lower case
				for(int i = 2; i < wordArray.length; i++) {
					if(Character.isUpperCase(wordArray[i])) {
						return false;
					}
				}
			}
			//all OK
			return isWordMatchCase(word);
		}
		else if(Character.isLowerCase(wordArray[0])) {
			// First character is lower case, all the following characters must be lower case
			for(int i = 1; i < wordArray.length; i++) {
				if(Character.isUpperCase(wordArray[i])) {
					return false;
				}
			}
			// if all characters are lower case call isWord
			return isWordMatchCase(word);
		}
		// First character is not a letter, so its not a valid word
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 TrieNode curr = root;
    	 String wordLowerCase = prefix.trim().toLowerCase();			
 		 char[] wordArray= wordLowerCase.toCharArray();
 		 wordList.clear();
 		 if(numCompletions < 1)
 			 return wordList;
 		 numCompletionsList = numCompletions;
 		 if(wordArray.length == 0) {
 			 levelOrderTraversal(root);
 			 return wordList; 
 		 }
 		 for(char c: wordArray) {
 			if(curr.getChild(c) == null) {
 				return wordList;
 			}
 			else {
 				curr = curr.getChild(c);
 			}
 		 }
 	
 		 if(curr.getText().equalsIgnoreCase(prefix)) {
 			levelOrderTraversal(curr);	
 		}
 		return wordList; 		 
    }
    
    private void levelOrderTraversal(TrieNode node) {
    	TrieNode curr = node;
    	if(curr == null)
    		return;
    	List<TrieNode> myQueue = new LinkedList<TrieNode>();
    	myQueue.add(curr);
    	while(!myQueue.isEmpty() && numCompletionsList > 0) {
    		curr = myQueue.get(0);
    		if(curr.endsWord())
    			visited(curr.getText());
    		myQueue.remove(0);
    		for(char c : curr.getValidNextCharacters())
    			myQueue.add(curr.getChild(c));
    	}
    }
    
    private void visited(String word){
    	wordList.add(word);
    	numCompletionsList--;
    }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
}