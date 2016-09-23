import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class WordGramTester {
	;
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

		assertEquals("fail on 0,3",myGrams[0].equals(myGrams[3]),true);
		assertEquals("fail on 0,6",myGrams[0].equals(myGrams[6]),true);
		assertEquals("fail on 1,4",myGrams[1].equals(myGrams[4]),true);
		assertEquals("fail on 2,5",myGrams[2].equals(myGrams[5]),true);
		assertEquals("fail on 2,8",myGrams[2].equals(myGrams[8]),true);
		assertEquals("fail on 0,2",myGrams[0].equals(myGrams[2]),false);
		assertEquals("fail on 0,4",myGrams[0].equals(myGrams[2]),false);
		assertEquals("fail on 2,3",myGrams[2].equals(myGrams[3]),false);
		assertEquals("fail no 2,6",myGrams[2].equals(myGrams[6]),false);
		assertEquals("fail no 7,8",myGrams[7].equals(myGrams[8]),false);
	}

	@Test
	public void testHash(){
		Set<Integer> set = new HashSet<Integer>();
		for(WordGram w : myGrams) {
			set.add(w.hashCode());
		}

		assertTrue("hash code test", set.size() > 9);
	}

}
