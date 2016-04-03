package com.learn.akka.pi

import akka.actor.{ActorSystem, Props}

/**
 * Created by gabbi on 17/08/14.
 */

object Pi extends App{

  def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) = {
    val system = ActorSystem("PiSystem")

    val listener = system.actorOf(Props[Listener], name="listener")

    val master = system.actorOf(Props(new Master(nrOfWorkers, nrOfMessages, nrOfElements, listener)), "master")

    master ! Calculate
  }

  calculate(nrOfWorkers=16, nrOfElements=10000, nrOfMessages=10000)
}
