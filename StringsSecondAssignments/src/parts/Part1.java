/**
 * 
 */
package parts;

/**
 * @author JOADUART
 *
 */
public class Part1 {
	
	public static void main(String[] args) {
		
		testFindStopCodon();
		
	}
	
	/**
	 * This function tries to find a valid stop Codon in the received DNA sequence. The amount of letters between Codons
	 * must be a multiple of 3
	 * 
	 * @param dna        Sequence where we try to find a gene
	 * @param startIndex Starting search position in the Sequence
	 * @param stopCodon  Codon that will mark the end of the gene
	 * @return
	 */
	public static int findStopCodon(String dna, int startIndex, String stopCodon) {
		
		// Getting the index of the closest stop Codon we find in dna, after the indicated startIndex
		int stopIndex = dna.indexOf(stopCodon, startIndex);
		
		// While we don't reach the end of the sequence
		while (stopIndex != -1) {
			
			// CODON has been found
			// Consider LENGTH between start and stop index
			// Return GENE if LENGTH is multiple of 3
			if ((stopIndex - startIndex) % 3 == 0) {
				
				return stopIndex;
				
			}
			
			// Otherwise, we find the next stop Codon, starting from the Index immediately after the last stop Codon's
			// index and repeat the cycle, until we either find a valid one or we reach the end of the sequence
			stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
			
		}
		
		// If we don't find a valid index we return the length of the sequence
		return dna.length();
		
	}
	
	/**
	 * Finds a valid stop Codon's index for each of the defined sequences. Calls findStopCodon with a starting index of
	 * 3 and a stop Codon "TAA"
	 */
	public static void testFindStopCodon() {
		
		// Test strings
		String sequence1 = "asdATGasdzxcasdTAAasd";
		String sequence2 = "aswATGasdasTAAaasdTAAasdasd";
		String sequence3 = "qweATGqweqweqweqwTAAwqweqweqwTAAadf";
		String sequence4 = "qweATGaTAAaTAAsdTAAadsasd";
		
		// Strings are gathered in a String array
		String[] sequences = {sequence1, sequence2, sequence3, sequence4};
		
		int stopIndex;
		
		// We loop through all the sequences
		for (String sequence : sequences) {
			
			// Try to find the stop codon's index in the sequence
			stopIndex = findStopCodon(sequence, 3, "TAA");
			
			// When stopIndex is the sequence it means that we haven't found a valid gene
			if (stopIndex == sequence.length()) {
				
				System.out.println("No gene!");
				
				// Sequence was found
			} else {
				
				System.out.println("Sequence found! " + sequence);
				
			}
			
		}
		
	}
	
	/**
	 * Returns a valid Codon, which has to start with "ATG" and can end with either:
	 * 		- TAA
	 * 		- TAG
	 * 		- TGA
	 * 
	 * @param dna Sequence that might contain a valid Codon
	 * @return
	 */
	public static String findGene(String dna) {
		
		// Finds the first occurrence of "ATG" in the given dna sequence
		int startIndex = dna.indexOf("ATG");
		int stopIndexTAA;
		int stopIndexTAG;
		int stopIndexTGA;            
		int stopIndex;  
		
		// If there is no "ATG" Codon, return an empty string
		if (startIndex == -1) {
			
			return "";
			
		}
		
		// Trying to find the stopIndex for different stop Codons
		stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
		stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
		stopIndexTGA = findStopCodon(dna, startIndex, "TGA");
		
		// from the 3 indexes found, we will selected the smallest one
		stopIndex = Math.min(stopIndexTAA, stopIndexTAG);
		stopIndex = Math.min(stopIndex, stopIndexTGA);
		
		// If the smallest stopIndex found is equal to the sequence's length, then it was because no valid gene is
		// within the sequence, therefore we return an empty string
		if (stopIndex == dna.length()) {		
		
			return "";
		
		}
		// Otherwise, we return the gene, which is between the start and the stop index plus the stopCodon's length,
		// which is 3
		else {
			
			return dna.substring(startIndex, stopIndex + 3);
			
		}
		
	}
	
}
