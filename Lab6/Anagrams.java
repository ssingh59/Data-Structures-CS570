package HW6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author shweta singh
 *
 */
public class Anagrams {

	/**
	 * Data Fields
	 */
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
								67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	/**
	 * constructors
	 */
	public Anagrams () {
		
		letterTable = new HashMap<Character,Integer>();
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	/**
	 * build a letter table for hashing
	 */
	private void buildLetterTable() {
		
		Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int i=0;
		for (i=0; i <26; i++) {
			letterTable.put(letters[i], primes[i]);
		}
		
	}
	/**
	 * a function to store anagrams	
	 * @param s
	 */
	private void addWord(String s) {
		
		ArrayList<String> temp;
		
		if (!anagramTable.containsKey(myHashCode(s))){
			temp = new ArrayList<String>();
			temp.add(s);
			anagramTable.put(myHashCode(s), temp);
		} else {
			temp = anagramTable.get(myHashCode(s));
			temp.add(s);
			anagramTable.replace(myHashCode(s), temp);
			
		}
	}
	/**
	 * fuction to generate unique keys for anagrams
	 * @param s
	 * 
	 */
	private Long myHashCode(String s) {
		long key = 1;
		int i=0;
		int value=0;
        for (i = 0; i < s.length(); i++) {
        	//get prime value
        	value = letterTable.get(s.charAt(i));
        	key = key * value;
        }
        return key;
	}
	/**
	 * this function opens the text file to check the anagrams
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException {
		
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
		String strLine;
		while ((strLine = br.readLine()) != null) { 
			this.addWord(strLine);
		} 
		br.close();
	}
	
	/**
	 * this functions gets the entry that has largest number of anagram
	 * @return
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
		
		ArrayList<Map.Entry<Long,ArrayList<String>>> temp = new ArrayList<Map.Entry<Long,ArrayList<String>>>(); 
		int max = 0;
		Iterator<Entry<Long, ArrayList<String>>> iterator = anagramTable.entrySet().iterator();
		
		while(iterator.hasNext()){
			Entry<Long, ArrayList<String>> temp2 = iterator.next();
			int size = 0;
			size = temp2.getValue().size();
			if(size > max){
				temp.clear();
				temp.add(temp2);
				max = size;
			}
			else if(size == max){
				temp.add(temp2);
			}
		}
		return temp;
	}
	
	public static void main(String[] args) {
		
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime(); 
		try {
			//a.processFile("C:/Users/Shweta Singh/eclipse-workspace/DS 570/src/HW6/words_alpha.txt");
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		int length=0;
		long key =0;
		key = maxEntries.get(0).getKey();
		length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("Key of max anagrams: " + key);
		System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue()); 
		//System.out.println("List of max anagrams: "+ maxEntries); 
		System.out.println("Length of list of max anagrams: "+ length);
	}
}