import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class Solution {

  private static final String SPACE = " ";
	private static final String NO_SUBSEGMENT_FOUND = "NO SUBSEGMENT FOUND";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			List<String> inputList = new ArrayList<String>();
			String paragraph = br.readLine();
			int num = Integer.parseInt(br.readLine());
			for (int i = 0; i < num; i++) {
				inputList.add(br.readLine().toLowerCase());
			}
			System.out.println(getShortestSubSegment(paragraph, inputList));
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static String getShortestSubSegment(String paragraph,
			List<String> inputList) {
		String result = paragraph.replaceAll("[^\\p{L}\\p{Z}]", "");
		String lowerResult = result.toLowerCase();
		List<String> orderedWordList = convertToWordList(lowerResult);
		List<String> subsetList = new ArrayList<String>();
		
		if (orderedWordList.containsAll(inputList)) {
			
			int minFound =orderedWordList.size();
			for (int j = inputList.size(); j < orderedWordList.size() && minFound > j ; j++) {
				for (int i = 0; i < orderedWordList.size() - (j - 1); i++) {
					List<String> subList = orderedWordList.subList(i, i + j);
					
					if (subList.containsAll(inputList)) {
						minFound=j;
						subsetList.add(generateString(convertToWordList(result).subList(i, i+j)));
					}
				}
			}
			Collections.sort(subsetList, new Comparator<String>() {

				@Override
				public int compare(String arg0, String arg1) {
					if (arg0.length() < arg1.length())
						return -1;
					else if (arg0.length() > arg1.length())
						return 1;
					else
						return 0;
				}

			});
			return subsetList.get(0);
		} else {
			return NO_SUBSEGMENT_FOUND;
		}

	}

	private static String generateString(List<String> subList) {
		Iterator<String> iterator = subList.iterator();
		StringBuffer sb = new StringBuffer();
		while (iterator.hasNext()) {
			sb.append(iterator.next());
			if (iterator.hasNext())
				sb.append(SPACE);

		}
		return sb.toString();
	}

	private static List<String> convertToWordList(String result) {
		return Arrays.asList(result.split(SPACE));
	}

}

