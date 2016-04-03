package com.learn.fpConcepts.pathDependent

import com.learn.fpConcepts.pathDependent.AwesomeDB.Key

import scala.collection.mutable

/**
 * Created by gabbi on 24/11/14.
 */
class FanFiction {

}

class Franchise(name:String){
  case class Character(name:String)
  def createFanFictionWith(lovestruck:Character, objectOfDesire:Character) = (lovestruck, objectOfDesire)
}


object AwesomeDB{
  abstract class Key(name:String){
    type Value
  }
}

class AwesomeDB{
  val data = mutable.Map.empty[Key,Any]

  def get(key:Key):Option[key.Value] = data.get(key).asInstanceOf[Option[key.Value]]

  def set(key:Key)(value:key.Value) = data.update(key, value)
}

trait IntValued extends Key{
  type Value = Int
}

trait StringValued extends Key {
  type Value = String
}

object Keys{
  val foo = new Key("foo") with IntValued
  val bar = new Key("bar") with StringValued
}