package cosc190Assign1_000481460;

public class SortQuestions 
{
	
	//=========================GENERIC BUBBLE SORT================================================================================================//
	
		public static<T extends Comparable<T>> void genBubbleSort(T[]arr)
		{
			
			int length = arr.length;
			while(length>1)
			{
				for(int i = 0; i< arr.length-1;i++)
				{
					if(arr[i].compareTo(arr[i+1])>=0)
					{
						T temp = arr[i];
						 arr[i] = arr[i+1];
						 arr[i+1]= temp;
					}
				}
				length--;
			}
		}
	//===========================================GENERIC Merge Sort==================================================================================//
	public static <T extends Comparable<T>> void genMergeSort(T[]mainArr)
	{
		divide(mainArr,0,mainArr.length-1);
	}
	public static <T extends Comparable<T>> void divide(T[]mainArr, int start, int end)
	{
		if (end - start < 1)
		{
			return;
		}
		
		int mid = (end + start)/2;
		divide(mainArr, start, mid);
		divide(mainArr, mid+1, end);
		
		Conquer(mainArr, start,mid, end);
		
	}
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> void Conquer(T[]mainArr, int start,int mid, int end)
	{
		Object[]tempObjects = new Object[end - start + 1];
		int i = start;
		int j = mid +1;
		int k = 0;
		while (i <= mid && j <= end) 
		{
			if(mainArr[i].compareTo(mainArr[j])<=0)
			{
				tempObjects[k] = mainArr[i];
				i++;
			}
			else
			{
				tempObjects[k] = mainArr[j];
				j++;
			}
			k++;
		}
		if(i <= mid && j > end)
		{
			while(i <= mid)
			{
				tempObjects[k] = mainArr[i];
				k++;
				i++;

			}
		}else {
			while (j <= end) {
				
				tempObjects[k] = mainArr[j];
				k++;
				j++;
				
			}
			
		}
		for(k = 0; k < tempObjects.length; k++)
		{
			mainArr[k + start] = (T) tempObjects[k];
		}
		
	}


	
	
	//=======================================modified mergeSort with bubbleSort==================================================================
	public static void bubbleSort(int[]arr)
	{
		int check = arr.length;
		while (check>1)
		{
			for (int i = 0; i < arr.length-1; i++)
			{
				if (arr[i] > arr[i+1])
				{
					 int temp = arr[i];
					 arr[i] = arr[i+1];
					 arr[i+1]= temp;
					
				}
			}
			check--;
			
		}
		
	}
	
	public static void mergesort(int[] mainarr)
	{
		int mainLength = mainarr.length;
		
		if (mainLength<50) 
		{
			bubbleSort(mainarr);
			return;
			
		}
		else
		{
			int midIndex = mainLength/2;
			int[] left = new int[midIndex];
			int[] right = new int[mainLength-midIndex];
			
			for (int i = 0; i < midIndex; i++)
			{
				left[i] = mainarr[i];
				
			}
			for (int i = midIndex; i < mainLength; i++) 
			{
				right[i-midIndex] = mainarr[i];
				
			}
			mergesort(left);
			mergesort(right);
			
			merge(mainarr, left, right);
			
		}
		
		
		
	
	}
	
	public static void merge(int[]mainarr,int[]left,int[]right)
	{
		int LeftS = left.length;
		int RightS= right.length;
		
		int i=0, j=0, k=0;
		
		while(i < LeftS && j< RightS)
		{
			if(left[i] <= right[j])
			{
				mainarr[k] = left[i];
				i++;
			}
			else
			{
				mainarr[k] = right[j];
				j++;
			}
			k++;
		}
		
		while(i < LeftS)
		{
			mainarr[k] = left[i];
			i++;
			k++;
		}
		
		while(j < RightS)
		{
			mainarr[k] = right[j];
			j++;
			k++;
		}
		
		
		
		
	}
	
	

	public static void main(String[] args) 
	{
//		double start = System.nanoTime();
//
//		int[] num = new int[1000];
//		for (int i = 0; i < num.length; i++) {
//			
//			num[i] = (int)(Math.random()*1000); 
//			
//		}
////		for (int i = 0; i < num.length; i++) {
////			System.out.print(num[i] + " ");
////		}
////		System.out.println(" ");
////		mergesort(num);
////		for (int i = 0; i < num.length; i++) {
////			System.out.print(num[i] + " ");
////		}
//		
//		double end = System.nanoTime();
//		//System.out.println(" ");
//		System.out.println((int)(end - start));
		
		String[]arr = {"sore","docke","deck","dock_","dock__","dock_","dock","cevm","vefs","wndiem","cmokrf"};
		genMergeSort(arr);
        for (int i = 0; i < arr.length; i++) 
            System.out.println(arr[i].toString());

		
		

	}

}
