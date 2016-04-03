package com.algos.sorting

/**
 * Created by gabbi on 13/09/14.
 */
object Bubble {
  def sort[A <% Ordered[A]](arr:Array[A]):Array[A] = {
    var swapped = false
    do{
      swapped = false
      for(i <- 0 until arr.length-1){
        if (arr(i) > arr(i+1)){
          val tmp = arr(i)
          arr(i) = arr(i+1)
          arr(i+1) = tmp
          swapped = true
        }
      }
    }while(swapped)
    arr
  }
}
