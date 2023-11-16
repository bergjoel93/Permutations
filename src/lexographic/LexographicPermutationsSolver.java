package lexographic;

public class LexographicPermutationsSolver {
	/**
	 * 
	 * @param n - size of the permutation set. n = 3 is {1,2,3}
	 * @return
	 */
	public static Integer[][] generateLexPermutations(int n) {
		int numberOfPermutations = factorial(n);
		Integer[][] permutations = new Integer[numberOfPermutations][n];
		
		// Initialize the first permutation
		Integer[] currentPermutation = new Integer[n];
		
		// generate permutation set. 
		for(int i = 0; i < n; i++) {
			currentPermutation[i] = i + 1;

		}
		//establish first set in permutations
		permutations[0] = currentPermutation.clone();
		
		// generate subsequent permutations
		for(int i = 1; i < numberOfPermutations; i++) {
			currentPermutation = findNextPerm(currentPermutation);
			permutations[i] = currentPermutation.clone();
		}
		
		return permutations;
		
	}
	
	public static int factorial(int n) {
		if(n <= 1) {
			return 1;
		} else {
			return n * factorial(n-1);
		}
	}
	
	public static Integer[] findNextPerm(Integer[] original) {
		Integer[] nextPerm = original.clone(); // cloning the original array. 
		
		int i = original.length - 2; // starting from the second last element.
		
		// Find the largest index 'i' such that such that array[i] < array[i+1]
		while(i > 0 && nextPerm[i] >= nextPerm[i + 1]) {
			i--;
		}
		
		// 3. Find the largest index 'j' greater than 'i' such that  array[i] < array[j]
		if( i >=0) { // Check if such an 'i' exists
			int j = original.length - 1; // starting at the last element. 
			
			while(nextPerm[j] <= nextPerm[i]) {
			j--;
			}
			
			// Once we find i and j, then we swap them. 
			swap(nextPerm, i, j);
			
			// reorder sequence so that all elements after i (i+1) are in increasing order. 
			// To reverse the subsection use two pointers. 
			int start = i+1; 
			int end = original.length-1;
			
			//loop to reverse: 
			while(start < end) {
				// swap the elements start and end. 
				swap(nextPerm, start, end);
				// increment start
				start++;
				end--;
			}
			
		}
		

		return nextPerm;
	}
	
	public static void swap(Integer[] array, int i, int j) {
		Integer temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
		
	public static void printArray(Integer[] array) {
		System.out.print("Array: ");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
	
	public static void printPermutations(Integer[][] permutations) {
	    for (int i = 0; i < permutations.length; i++) {
	        // Print the label for the permutation
	        System.out.print((i + 1) + ". {");

	        // Print each element in the permutation
	        for (int j = 0; j < permutations[i].length; j++) {
	            System.out.print(permutations[i][j]);
	            if (j < permutations[i].length - 1) {
	                System.out.print(", "); // Add comma between numbers, but not after the last number
	            }
	        }

	        System.out.println("}"); // End of the current permutation
	    }
	}
	

	    public static void generateLexSubsets(int r, int n) {
	        // Check for valid input
	        if (r > n) {
	            throw new IllegalArgumentException("r cannot be greater than n");
	        }

	        // Initialize the first subset
	        int[] subset = new int[r];
	        for (int i = 0; i < r; i++) {
	            subset[i] = i + 1;
	        }

	        // Generate and output all subsets
	        while (true) {
	            printSubset(subset);  // Output the current subset

	            // Find the next subset
	            int i;
	            for (i = r - 1; i >= 0 && subset[i] == n - r + i + 1; i--);
	            if (i < 0) break;  // All subsets have been generated

	            subset[i]++;
	            for (int j = i + 1; j < r; j++) {
	                subset[j] = subset[j - 1] + 1;
	            }
	        }
	    }

	    private static void printSubset(int[] subset) {
	        System.out.print("{");
	        for (int i = 0; i < subset.length; i++) {
	            System.out.print(subset[i]);
	            if (i < subset.length - 1) {
	                System.out.print(", ");
	            }
	        }
	        System.out.println("}");
	    }


	public static void main(String[] args) {
		//printPermutations(generateLexPermutations(5));
		Integer[] array = {5,3,4,2,1};
		Integer[] nextPerm = findNextPerm(array);
		
		printArray(nextPerm);
		//generateLexSubsets(4,9);
		

	}

}
