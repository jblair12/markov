import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class EfficientMarkov implements MarkovInterface<String> {
	private String myText;
	private Random myRandom;
	private int myOrder;
	
	private static String PSEUDO_EOS = "";
	private static int RANDOM_SEED = 1234;
	
	public EfficientMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	
	public EfficientMarkov() {
		this(3);
	}
	public static Map<String, ArrayList<String>> letterMap = new HashMap<String, ArrayList<String>>();
	public void setTraining(String text) { //fix so that it works for what the number is not just 3
		myText = text;		
		//Map<String, ArrayList<String>> letterMap = new HashMap<String, ArrayList<String>>();
		for (int i=0; i<(myText.length() - myOrder); i++){ //goes through each letter until the phrase out be at the end
			ArrayList<String> letList = new ArrayList<String>();
			String phrase = new String();
			phrase = myText.substring(i, i+myOrder); //creates the letter combo
			String let = new String();
			if(i+myOrder == (myText.length() -1)){ //last 3 letters
				if (letterMap.containsKey(phrase)){//already in the HashMap
					//letterMap.put(phrase, letList);
					letterMap.get(phrase).add(PSEUDO_EOS);		//add PSEUDO_EOS to array
				}
				else{
					letList.add(PSEUDO_EOS);
					letterMap.put(phrase, letList); //create array with PSEUDO_EOS
				}
			}
			else{
				let = myText.substring(i+myOrder,i+(myOrder+1));//creates string of following letter
				if (letterMap.containsKey(phrase)){
					letterMap.get(phrase).add(let);		
				}
				else{
					letList.add(let);
					letterMap.put(phrase, letList);
				}
			}
		}
	}
	public int size() {
		return myText.length();
	}
	
	public String getRandomText(int length) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - myOrder);

		String current = myText.substring(index, index + myOrder);
		//System.out.printf("first random %d for '%s'\n",index,current);
		sb.append(current);
		for(int k=0; k < length-myOrder; k++){
			ArrayList<String> follows = getFollows(current);
			if (follows.size() == 0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			
			String nextItem = follows.get(index);
			if (nextItem.equals(PSEUDO_EOS)) {
				//System.out.println("PSEUDO");
				break;
			}
			sb.append(nextItem);
			current = current.substring(1)+ nextItem;
		}
		return sb.toString();
	}
	
	public ArrayList<String> getFollows(String key){
		/*ArrayList<String> follows = new ArrayList<String>();
		//need to call the HashMap from setTraining
		//Map<String, ArrayList<String>> letMap = new HashMap<String, ArrayList<String>>();
		
		if (letterMap.containsKey(key)){
			follows = letterMap.get(key);
		}*/
		 
		return letterMap.get(key);
	}

	@Override
	public int getOrder() {
		return myOrder;
	}
}
