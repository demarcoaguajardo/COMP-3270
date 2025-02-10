import java.util.concurrent.ThreadLocalRandom;

class ProgrammingAssignment1 {

	//Function to swap two integers without pass by reference since Java is pass-by-value only 
	static int swapHelper(int a, int b){
	return a;
	}

	//Function to compute the median of three numbers. Used to benchmark runtimes fo sorting algorithms. 
	static int medianof3(long a, long b, long c){
		if (a < c)
		{
			if (b < a)
				return 1;
			else if (c < b)
				return 3;
			else
				return 2;
		}
		else if (b < c)
			return 3;
		else if (a < b)
			return 1;
		else
			return 2;
	}

	//Check if the array is sorted. Used to check the correctness of the sorting algorithm implementations. 
	static boolean isSorted(int[] arr, int n)
	{
		for (int i = 0; i < n-1; i++)
			if (arr[i+1] < arr[i])
				return false;
		return true;
	}

	//Function to generate a sorted array of size n. 
	static int[] sortedArray(int n){
		int[] data = new int[n];
		for (int i = 0; i < n; i++)
			data[i] = i+1;
		return data;
	}

	// Function to generate an array of size n filled with constants (zero in this case) 
	static int[] constArray(int n){
		int[] data = new int[n];
		return data;
	}

	// Function to generate a random array of size n. 
	static int[] randomArray(int n){
		int[] data = sortedArray(n);
		for (int i = n; i > 1; i--)
		{
			int swap = ThreadLocalRandom.current().nextInt(0,1000) % i;
			data[swap] = swapHelper(data[i-1], data[i-1]=data[swap]);
		}
		return data;
	}
      
   // Function to implement Selection Sort.
   // Input Arguments - data: array to sort, n: number of elements in array 
   // Output: Sorted array (Ascending Order). 
   static int[] selectionSort(int[] data, int n){
      for(int i=0; i < n; i++){
         int curr_max = i;
         for(int k=i+1; k<n; k++){
            if(data[curr_max] > data[k])
               curr_max = k;
         }
         
         data[curr_max] = swapHelper(data[i], data[i]=data[curr_max]);
      }
      return data;
   }
   
   // Helper function for merge sort 
   static int[] mergeFn(int[] data, int left, int mid, int right){
      int n1 = mid - left + 1;
      int n2 = right - mid;
      
      int leftArr[] = new int[n1];
      int rightArr[] = new int[n2];
      
      for (int i = 0; i < n1; ++i)
         leftArr[i] = data[left + i];
      for (int j = 0; j < n2; ++j)
         rightArr[j] = data[mid + 1 + j];
      
      int i = 0, j = 0;
      
      int k = left;
      while (i < n1 && j < n2) {
         if (leftArr[i] <= rightArr[j]) {
             data[k] = leftArr[i];
             i++;
         }
         else {
             data[k] = rightArr[j];
             j++;
         }
         k++;
      }
      
      while (i < n1) {
            data[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            data[k] = rightArr[j];
            j++;
            k++;
        }
        return data;
   }
   // Function to implement Merge Sort.
   // Input Arguments - data: array to sort, n: number of elements in array 
   // Output: Sorted array (Ascending Order). 
   static int[] mergeSort(int[] data, int left, int right){
      if(left < right){
         int mid = (left + right)/2;
         
         data = mergeSort(data, left, mid);
         data = mergeSort(data, mid + 1, right);
         
         data = mergeFn(data, left, mid, right);
      }
      return data;
   }    
   
   // Function to implement Quick Sort.
   // Input Arguments - data: array to sort, n: number of elements in array 
   // Output: Sorted array (Ascending Order). 
   static int[] quickSort(int[] data, int n){
      //TODO: Your implementation of Quick Sort comes here. 

      quickSortHelper(data, 0, n - 1);
      return data;
   }

   // Function that partitions the array using a pivot index. 
   // Input Arguments - data: array to sort, firstIndex: first index of the array,
   // lastIndex: last index of the array
   static void quickSortHelper(int[] data, int firstIndex, int lastIndex) {
      while (firstIndex < lastIndex) {
         int pivotIndex = partition(data, firstIndex, lastIndex);
         
         // Recurse on the smaller partition and iterate on the larger partition
         if (pivotIndex - firstIndex < lastIndex - pivotIndex) {
            quickSortHelper(data, firstIndex, pivotIndex - 1);
            firstIndex = pivotIndex + 1;
         } else {
            quickSortHelper(data, pivotIndex + 1, lastIndex);
            lastIndex = pivotIndex - 1;
         }
      }
   }

   // Function that partitions subarray with last element as pivot.
   // Input Arguments - data: array to sort, low: starting index of the array,
   // high: ending index of the array
   // Output: partitioning index.
   static int partition(int[] data, int low, int high) {
      //Choose pivot with the median of three method.
      int medianIndex = medOfThreeMethod(data, low, high);
      int pivot = data[medianIndex];
      //Swap data[medianIndex] and data[high] to move pivot to end of array.
      int temp = data[medianIndex];
      data[medianIndex] = data[high];
      data[high] = temp;
      
      //Index of smaller element and indicates the right position of pivot found so far.
      int i = (low - 1);

      for (int j = low; j < high; j++) {
         //If current element is smaller than the pivot.
         if (data[j] < pivot) {
            //Increment index of smaller element. 
            i++;
            //Swap data[i] and data[j]
            temp = data[i];
            data[i] = data[j];
            data[j] = temp;
         }
      }

      //Swap data[i + 1] and data[high]
      temp = data[i + 1];
      data[i + 1] = data[high];
      data[high] = temp;

      //Return the partitioning index.
      return i + 1;
   }

   // Function to find the median of three elements.
   // Input Arguments - data: array to sort, low: starting index of the array,
   // high: ending index of the array
   // Output: index of median element.
   static int medOfThreeMethod(int[] data, int low, int high) {

      //Middle index
      int mid = (low + high) / 2;

      //Return index of median element.
      if (data[low] > data[mid]) {
         if (data[mid] > data[high]) {
            return mid;
         } else if (data[low] > data[high]) {
            return high;
         } else {
            return low;
         }
      }
      else {
         if (data[low] > data[high]) {
            return low;
         } else if (data[mid] > data[high]) {
            return high;
         } else {
            return mid;
         }
      }
   }
   
   // Function to implement Insertion Sort.
   // Input Arguments - data: array to sort, n: number of elements in array 
   // Output: Sorted array (Ascending Order). 
   static int[] insertionSort(int[] data, int n){
      //TODO: Your implementation of Insertion Sort comes here. 

      for (int i = 1; i < n; ++i) {
         // Assign current element 
         int current = data[i];
         int j = i - 1;

         // Move elements from range of 0 to i-1 that are greater than current
         // one spot in front of current's position. 
         while (j >= 0 && data[j] > current) {
            data[j + 1] = data[j];
            j = j - 1;
         }
         // Insert current element at its correct position.
         data [j + 1] = current;
      }
      return data;
   }

   // ---------------------- DO NOT MODIFY THE CODE BELOW ----------------------

	// Main function. Input Arguments: 
	// Type of array to generate - Allowed values: 'r':Random, 's': Sorted, 'c': Constant
	// Size of array to evaluate on - Allowed values: any positive integer > 0
	// Sorting algorithm to evaluate - Allowed values: 'q': Quick Sort, 'i': Insertion Sort, 'm': Merge Sort, 's': Selection Sort
	// Output: time taken to run sorting algorithm
	public static void main(String[] args) {
		String arrayType = "r";
		int n = 10;
		String algo = "q";  

		if (args.length != 3) {
			System.out.println("Invalid command line arguments. Using defaults. Running quick sort on a random array of length 10");
		}
		else
		{
			try{
				arrayType = args[0];
				n = Integer.parseInt(args[1]);
				algo = args[2];
			}
			catch(Exception e){
				System.out.println("Invalid command line arguments. Using defaults. Running quick sort on a random array of length 10");
				arrayType = "r";
				n = 10;
				algo = "q";  
			}
         if(!(arrayType.equals("r") || arrayType.equals("s") || arrayType.equals("c"))){
            System.out.println("Invalid Array Type. Using default random array of length " + n);
				arrayType = "r";
         }
         if(!(algo.equals("q") || algo.equals("i") || algo.equals("s") || algo.equals("m"))){
            System.out.println("Invalid Sorting Algorithm. Using default Quick Sort");
				algo = "q";
         }
         if(n<=0){
            System.out.println("Number of elements in array must be positive and greater than zero. Using default length " + 10);
				n=10;
         }
		}
		System.out.println("Array type: " + arrayType + " Array Length: " + n + " Algo: " + algo);  
      
      int[] data = new int[n]; 
      int[] output = new int[n];
      switch(arrayType)
      {
         case "r": 
                  data = randomArray(n);
                  break;
         case "s":
                  data = sortedArray(n);
                  break;
         case "c":
                  data = constArray(n);
                  break;
      } 

      // //------------------- Print original array. -----------------------
      // System.out.println("Original Array:");
      // for(int i = 0; i < n; i++) {
      //    System.out.print(data[i] + " ");
      // }  
      // System.out.print("\n");
      // // ----------------------------------------------------------------
      
      
      long startTime = 0;
      long endTime = 0;
      long[] totalTime = new long[3];
      boolean success = false;
       switch(algo)
      {
         case "q":
                  for(int i=0; i<3; i++){
                     startTime = System.nanoTime(); 
                     output = quickSort(data, n);
                     endTime = System.nanoTime();
                     totalTime[i] = endTime - startTime;
                     success = isSorted(output, n);
                  }
                  break;
         case "s":
                  for(int i=0; i<3; i++){
                     startTime = System.nanoTime(); 
                     output = selectionSort(data, n);
                     endTime = System.nanoTime();
                     totalTime[i] = endTime - startTime;
                     success = isSorted(output, n);
                  }
                  break;

         case "i":
                  for(int i=0; i<3; i++){
                     startTime = System.nanoTime(); 
                     output = insertionSort(data, n);
                     endTime = System.nanoTime();
                     totalTime[i] = endTime - startTime;
                     success = isSorted(output, n);
                  }
                  break;
         case "m":
                  for(int i=0; i<3; i++){
                     startTime = System.nanoTime(); 
                     output = mergeSort(data, 0, n - 1);
                     endTime = System.nanoTime();
                     totalTime[i] = endTime - startTime;
                     success = isSorted(output, n);
                  }
                  break;

      } 
      
      if(!success){
         System.out.println("Your sorting algorithm failed multiple runs. Aborting...");
         for(int i = 0; i < n; i ++){
            System.out.println(output[i]);
         }
      }
      else{
         System.out.println("Median Execution time: " + totalTime[medianof3(totalTime[0], totalTime[1], totalTime[2]) - 1] + " ns");
      }

      // //----------------- Print sorted array -----------------------
      // System.out.println("Sorted Array:");
      // for(int i = 0; i < n; i++) {
      //    System.out.print(output[i] + " ");
      // }
      // //------------------------------------------------------------
	}
}
