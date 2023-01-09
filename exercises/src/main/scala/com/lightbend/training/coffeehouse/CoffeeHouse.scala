package com.lightbend.training.coffeehouse

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.lightbend.training.coffeehouse.CoffeeHouse.CreateGuest

object CoffeeHouse {
  case object CreateGuest
  def props: Props = Props(new CoffeeHouse)
}
class CoffeeHouse extends Actor with ActorLogging {
  log.debug("CoffeeHouse open")

  protected def createGuest(): ActorRef = context.actorOf(Guest.props)
  override def receive: Receive = {
    case CreateGuest => createGuest()
    case _ => sender() ! "Coffee Brewing"
  }
}
