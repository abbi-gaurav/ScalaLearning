package com.learn.rxScala.games

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.chart.XYChart.Data
import javafx.scene.chart.{Chart, XYChart}
import javafx.scene.input.{KeyCode, KeyEvent}

import rx.lang.scala.{Subscription, Observable}

/**
 * Created by gabbi on 29/12/14.
 */
class Utils {
  implicit def actionToEventHandler[E <: javafx.event.Event](f: E => Unit): EventHandler[E] = {
    new EventHandler[E] {
      def handle(e: E): Unit = f(e)
    }
  }

  def keyPresses(scene: Scene): Observable[KeyEvent] = Observable[KeyEvent] { subscriber =>
    val handler: EventHandler[KeyEvent] = (e: KeyEvent) => subscriber.onNext(e)
    scene.addEventHandler(KeyEvent.KEY_PRESSED, handler)
    //this is for unsubscription event 
    subscriber.add {
      scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler)
    }
  }

  def spaceBars(scene: Scene):Observable[KeyEvent] = keyPresses(scene).filter(_.getCode == KeyCode.SPACE)

  def keyChart(scene: Scene, chart:XYChart[Number,String]):Subscription = {
    val keys = {
      val ks:XYChart.Series[Number,String] = new XYChart.Series[Number,String]
      chart.getData.add(ks)
      ks
    }
    val keyboard:Observable[String] = keyPresses(scene).map(_.getText)
    val withIndex = keyboard.zipWithIndex.map(_.swap)

    withIndex.subscribe(s =>
      keys.getData.add(new Data[Number,String](s._1,s._2))
    )
  }
}
