/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
		if(list.size() <2)
		{
			return list;
		}
		
		return mergeSortHelper(list, comparator);
	}
	
	private List<T> mergeSortHelper(List<T> list, Comparator<T> comparator) {
		int mid = list.size() / 2;
		
		if(list.size() <2)
		{
			return list;
		}
		
		List<T> l1 = mergeSortHelper(list.subList(0, mid), comparator);
		List<T> l2 = mergeSortHelper(list.subList(mid, list.size()), comparator);
		
		return merge(l1, l2, comparator);
	}
	
	private List<T> merge(List<T> l1, List<T> l2, Comparator<T> comparator)
	{
		List<T> sorted = new ArrayList<T>(l1);
		sorted.addAll(l2);
		insertionSort(sorted, comparator);
		return sorted;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
		if(!list.isEmpty())
		{
			PriorityQueue<T> pq = new PriorityQueue<T>(list.size(), comparator);
			for(T t : list)
			{
				pq.offer(t);
			}
			for(int i = 0; i<list.size(); i++)
			{
				list.set(i, pq.poll());
			}
		}
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
		if(k < 1)
		{
			return null;
		}
		PriorityQueue<T> pq = new PriorityQueue<T>(k, comparator);
		for(T t : list)
		{
			if(pq.size() < k)
			{
			 pq.offer(t);
			}
			else
			{
				if(comparator.compare(pq.peek(), t) < 0)
				{
					pq.poll();
					pq.offer(t);
				}
			}
		}
		List<T> l = new ArrayList<T>();
		while(pq.peek() != null)
		{
			l.add(pq.poll());
		}
		
		return l;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
