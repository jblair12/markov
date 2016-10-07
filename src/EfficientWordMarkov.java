import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EfficientWordMarkov implements MarkovInterface<WordGram>{
	private String myText;
	private Random myRandom;
	private int myOrder;
	private String[] myTextList;
	
	private static String PSEUDO_EOS = "";
	private static int RANDOM_SEED = 1234;
	
	public EfficientWordMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	public static Map<WordGram, ArrayList<String>> wordMap = new HashMap<WordGram, ArrayList<String>>();

	public void setTraining(String text){
		myText = text;
		myTextList = text.split("\\s+");
		for (int i=0; i<(myTextList.length - myOrder); i++){ 
			
			WordGram phrase = new WordGram(myTextList, i, myOrder);//create a String array of the word
			/*for (int p =0; p<myOrder; p++){
				phrase[p] = myTextList[i + p];//adds the myOrder num of elements to phrase
			}*/

			String followWord = new String();
			if(i+myOrder == (myTextList.length)){ //last word
				if (wordMap.containsKey(phrase)){//already in the HashMap
					
					wordMap.get(phrase).add(PSEUDO_EOS);		//add PSEUDO_EOS to array
				}
				else{
					ArrayList<String> wordList = new ArrayList<String>();
					wordList.add(PSEUDO_EOS);
					wordMap.put(phrase, wordList); //create array with PSEUDO_EOS
				}
			}
			else{

				followWord = myTextList[i+myOrder];//creates string of following word
				//System.out.println(followWord);
				if (wordMap.containsKey(phrase)){

					wordMap.get(phrase).add(followWord);	
					
				}
				else{
					//System.out.println("GOT INTO SETTRAINING");

					ArrayList<String> wordList = new ArrayList<String>();
					wordList.add(followWord);
					wordMap.put(phrase, wordList);
				}
			}
		}
	}
	
	public ArrayList<String> getFollows(WordGram key){
		ArrayList<String> follows = new ArrayList<String>();
		//need to call the HashMap from setTraining
		//Map<String, ArrayList<String>> letMap = new HashMap<String, ArrayList<String>>();
		
		if (wordMap.containsKey(key)){
			follows = wordMap.get(key);
		}
		return follows;
	}

	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myTextList.length - myOrder);

		WordGram current = new WordGram(myTextList, index ,myOrder  );
		sb.append(current);
		//System.out.printf("first random %d for '%s'\n",index,current);
		for(int k=0; k < numWords-myOrder; k++){
			//System.out.println("here");
			//System.out.println(sb.toString());
			ArrayList<String> follows = getFollows(current);
			if (follows.size() == 0){
				//System.out.println("here2");
				break;
			}
			index = myRandom.nextInt(follows.size());
			
			String nextItem = follows.get(index);
			if (nextItem.equals(PSEUDO_EOS)) {
				//System.out.println("PSEUDO");
				break;
				
			}
			sb.append(nextItem);
			sb.append(" ");
			current = current.shiftAdd(nextItem);
		}
		return sb.toString();
	}

	
	//@Override
	/*public String getRandomText(int length) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public ArrayList<String> getFollows(WordGram key) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
