package com.lightbend.training.coffeehouse

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._

object CoffeeHouse {
  case class CreateGuest(favoriteCoffee: Coffee)
  def props: Props = Props(new CoffeeHouse)
}
class CoffeeHouse extends Actor with ActorLogging {
  import CoffeeHouse._
  log.debug("CoffeeHouse Open")

  private val finishedCoffeeDuration: FiniteDuration =
    context.system.settings.config.getDuration("coffee-house.guest.finish-coffee-duration", TimeUnit.MILLISECONDS).millis

  println(finishedCoffeeDuration)

  private val prepareCoffeeDuration: FiniteDuration =
    context.system.settings.config.getDuration("coffee-house.barista.prepare-coffee-duration", TimeUnit.MILLISECONDS).millis
  private val barista: ActorRef = createBarista()
  private val waiter: ActorRef = createWaiter()


  protected def createBarista(): ActorRef = context.actorOf(Barista.props(prepareCoffeeDuration), "barista")
  protected def createGuest(favoriteCoffee: Coffee): ActorRef = context.actorOf(Guest.props(waiter, favoriteCoffee, finishedCoffeeDuration))
  private def createWaiter(): ActorRef = context.actorOf(Waiter.props(barista), "waiter")

  override def receive: Receive = {
    case CreateGuest(coffee: Coffee) => createGuest(coffee)
    case _ => sender() ! "Coffee Brewing"
  }
}
