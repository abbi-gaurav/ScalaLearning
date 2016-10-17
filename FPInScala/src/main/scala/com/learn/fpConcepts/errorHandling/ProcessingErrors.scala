package com.learn.fpConcepts.errorHandling

/**
  * Created by gabbi on 16/05/16.
  */
case class ProcessingErrors[+E] private(errorList: List[E]) {
  def +[ES >: E](e: ES): ProcessingErrors[ES] = ProcessingErrors(e :: errorList)
}

object ProcessingErrors {
  def apply[E](error: E): ProcessingErrors[E] = ProcessingErrors(List(error))
}
