package com.lightbend.training.coffeehouse

import akka.actor.{Actor, ActorLogging, Props}

class CoffeeHouse extends Actor with ActorLogging {
  log.debug("CoffeeHouse open")
  override def receive: Receive = {
    case _ => sender() ! "Coffee Brewing"
  }
}

object CoffeeHouse {
  def props: Props = Props(new CoffeeHouse)
}