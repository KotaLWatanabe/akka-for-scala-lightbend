package com.lightbend.training.coffeehouse

import akka.actor.{Actor, ActorLogging, ActorRef, Props, Timers}
import com.lightbend.training.coffeehouse.Guest.{CaffeineException, CoffeeFinished}

import scala.concurrent.duration.FiniteDuration

object Guest {
  case object CoffeeFinished
  case object CaffeineException extends IllegalStateException
  def props(waiter: ActorRef,
            favoriteCoffee: Coffee,
            finishCoffeeDuration: FiniteDuration,
            caffeineLimit: Int): Props =
    Props(new Guest(waiter, favoriteCoffee, finishCoffeeDuration, caffeineLimit))
}
class Guest(waiter: ActorRef,
            favoriteCoffee: Coffee,
            finishCoffeeDuration: FiniteDuration,
            caffeineLimit: Int) extends Actor with ActorLogging with Timers {
  log.info(s"Guest started. $favoriteCoffee, $finishCoffeeDuration")
  private var coffeeCount: Int = 0

  orderCoffee()

  override def postStop(): Unit = {
    log.info(s"good bye.")
    super.postStop()
  }

  override def receive: Receive = {
    case Waiter.CoffeeServed(coffee) =>
      coffeeCount += 1
      log.info(s"Enjoying my $coffeeCount yummy $coffee")
      timers.startSingleTimer("coffee-finished", CoffeeFinished, finishCoffeeDuration)
    case CoffeeFinished if coffeeCount >= caffeineLimit =>
      throw CaffeineException
    case CoffeeFinished =>
      orderCoffee()
  }

  private def orderCoffee(): Unit = {
    waiter ! Waiter.ServeCoffee(favoriteCoffee)
  }
}
