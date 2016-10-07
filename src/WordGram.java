
public class WordGram {
	private String[] myWords;
	private int myHash;

	public WordGram(String[] source, int start, int size) {
		this.myWords = new String[size];
		for (int i = 0; i < size; i++) {
			this.myWords[i] = source[start + i];
		}

	}

	public int hashCode() {
		myHash = this.myWords.hashCode();
		myHash = 1;
		for (int j = 0; j < this.myWords.length; j++) {
			String midword = this.myWords[j];
			for (int y = 0; y < midword.length(); y++) {
				myHash = myHash * 17 + midword.charAt(y);
			}
		}
		return myHash;
	}

	public String toString() {
		String wordString = "{";
		for (String s : this.myWords) {
			wordString = wordString + s;
			if (s.equals(this.myWords[this.myWords.length - 1])) {
				wordString = wordString;
			} else {
				wordString = wordString + ",";
			}
		}
		wordString = wordString + "}";
		return wordString;
	}

	public boolean equals(Object other) {
		WordGram wg = (WordGram) other;
		if (!(other instanceof WordGram))
			return false;
		for (int h = 0; h < this.myWords.length; h++) {

			if (!wg.myWords[h].equals(this.myWords[h])) {
				return false;
			}
		}

		return true;

	}

	public int compareTo(WordGram other) {
		String v1[] = this.myWords;
		String v2[] = other.myWords;
		int len1 = this.myWords.length;
		int len2 = other.myWords.length;
		int lim = Math.min(len1, len2);

		int k = 0;
		while (k < lim) {
			String c1 = v1[k];
			String c2 = v2[k];
			if (c1 != c2) {
				if (c1.compareTo(c2) > 0)
					return 1; // c1 or c2 first?
				if (c1.compareTo(c2) < 0)
					return -1;
			}
			k++;
		}
		return len1 - len2;
	}

	public int length() {
		return this.myWords.length;
	}

	public WordGram shiftAdd(String last) {
		String[] newArray = new String[this.myWords.length];
		for (int k = 0; k < (this.myWords.length - 1); k++) {
			newArray[k] = this.myWords[k + 1];
		}
		newArray[(this.myWords.length - 1)] = last;
		WordGram newGram = new WordGram(newArray, 0, newArray.length);

		return newGram;
	}

}
