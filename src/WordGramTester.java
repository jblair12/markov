import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class WordGramTester {

	private WordGram[] myGrams;

	@Before
	public void setUp(){
		String str = "aa bb cc aa bb cc aa bb cc aa bb dd ee ff gg hh ii jj";
		String[] array = str.split("\\s+");
		myGrams= new WordGram[array.length-2];
		for(int k=0; k < array.length-2; k++){
			myGrams[k] = new WordGram(array,k,3);
		}
	}

	@Test
	public void testHashEquals(){
		assertEquals("hash fail on equals 0,3",myGrams[0].hashCode(),myGrams[3].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[0].hashCode(),myGrams[6].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[1].hashCode(),myGrams[4].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[2].hashCode(),myGrams[8].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[2].hashCode(),myGrams[5].hashCode());
	}

	@Test
	public void testEquals(){

		assertEquals("eq fail on 0,3",myGrams[0].equals(myGrams[3]),true);
		assertEquals("eq fail on 0,6",myGrams[0].equals(myGrams[6]),true);
		assertEquals("eq fail on 1,4",myGrams[1].equals(myGrams[4]),true);
		assertEquals("eq fail on 2,5",myGrams[2].equals(myGrams[5]),true);
		assertEquals("eq fail on 2,8",myGrams[2].equals(myGrams[8]),true);
		assertEquals("eq fail on 0,2",myGrams[0].equals(myGrams[2]),false);
		assertEquals("eq fail on 0,4",myGrams[0].equals(myGrams[2]),false);
		assertEquals("eq fail on 2,3",myGrams[2].equals(myGrams[3]),false);
		assertEquals("eq fail no 2,6",myGrams[2].equals(myGrams[6]),false);
		assertEquals("eq fail no 7,8",myGrams[7].equals(myGrams[8]),false);
	}

	@Test
	public void testHash(){
		Set<Integer> set = new HashSet<Integer>();
		for(WordGram w : myGrams) {
			set.add(w.hashCode());
		}

		assertTrue("hash code test", set.size() > 9);
	}
	
	@Test
	public void testCompare(){
		String[] words = {"apple", "zebra", "mongoose", "hat"};
		WordGram a = new WordGram(words,0,4);
		WordGram b = new WordGram(words,0,4);
		WordGram a2 = new WordGram(words,0,3);
		WordGram b2 = new WordGram(words,2,0);
		
		assertEquals("comp fail self",a.compareTo(a) == 0, true);
		assertEquals("comp fail copy",a.compareTo(b) == 0, true);
		assertEquals("fail sub", a2.compareTo(a) < 0, true);
		assertEquals("fail super",a.compareTo(a2) > 0, true);
		assertEquals("fail empty",b2.compareTo(a2) < 0, true);
	}
	@Test
	public void testShiftAdd(){
		String[] words = {"apple", "zebra", "mongoose", "hat", "lemon"};
		WordGram a = new WordGram(words,0,4);
		WordGram b = new WordGram(words,1,4);
		WordGram a2 = new WordGram(words,0,3);
		WordGram a3 = new WordGram(words,1,3);
		WordGram b2 = new WordGram(words,2,0);
		//System.out.println(a);
		//System.out.println(b);
		//System.out.println(a.shiftAdd("lemon"));
		assertEquals("shift size 4",(a.shiftAdd("lemon")).equals(b), true);
		assertEquals("shift size 3",(a2.shiftAdd("hat")).equals(a3), true);
		assertEquals("if it fails to shift",(a2.shiftAdd("lemon")).equals(a2), false);
		assertEquals("if it adds on",(a2.shiftAdd("lemon")).equals(a), false);
		
	}
	@Test
	public void testToString(){
		String[] words = {"apple", "zebra", "mongoose", "hat", "lemon"};
		WordGram a = new WordGram(words,0,4);
		WordGram b = new WordGram(words,0,4);
		WordGram a2 = new WordGram(words,0,3);
		WordGram a3 = new WordGram(words,1,3);
		WordGram b2 = new WordGram(words,1,3);
		System.out.print(a);
		System.out.print(a.toString());
		assertEquals("regualr string size 4",(a.toString()).equals(b.toString()), true);
		assertEquals("to string after shift",((a2.shiftAdd("hat")).toString()).equals(a3.toString()), true);
		assertEquals("if it fails to shift",(a2.toString()).equals(a3.toString()), false);
		assertEquals("regualr string size 3",(a2.toString()).equals(a2.toString()), true);
		
	}
}
