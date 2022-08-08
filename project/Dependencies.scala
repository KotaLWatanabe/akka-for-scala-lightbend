import com.lightbend.cinnamon.sbt.Cinnamon
import sbt._

object Version {
  val akkaVer = "2.6.19"
  val akkaDiagVer = "1.1.16"
  val logbackVer = "1.2.11"
  val scalaVer = "2.13.4"
  val scalaParsersVer = "2.1.1"
  val scalaTestVer = "3.2.11"
}

object Dependencies {
  val dependencies = Seq(
    "com.typesafe.akka" %% "akka-actor" % Version.akkaVer,
    "com.typesafe.akka" %% "akka-slf4j" % Version.akkaVer,
    "ch.qos.logback" % "logback-classic" % Version.logbackVer,
    "org.scala-lang.modules" %% "scala-parser-combinators" % Version.scalaParsersVer,
    "com.lightbend.akka" %% "akka-diagnostics" % Version.akkaDiagVer,
    Cinnamon.library.cinnamonAkka,
    Cinnamon.library.cinnamonJvmMetricsProducer,
    Cinnamon.library.cinnamonPrometheus,
    Cinnamon.library.cinnamonPrometheusHttpServer,
    "com.typesafe.akka" %% "akka-testkit" % Version.akkaVer % "test",
    "org.scalatest" %% "scalatest" % Version.scalaTestVer % "test"
  )
}
