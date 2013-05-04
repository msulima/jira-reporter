import sbt._
import Keys._

object ApplicationBuild extends Build {

  val appName = "jira-reporter"
  val appVersion = "0.1-SNAPSHOT"

  val appDependencies = Seq()

  lazy val scalatestVersion = "1.9.1"

  val main = play.Project(appName, appVersion, appDependencies).settings(
    libraryDependencies ++= List(
      "org.scalatest" %% "scalatest" % scalatestVersion % "test" withSources(),
      "org.scalesxml" %% "scales-xml" % "0.4.5" withSources(),
      // and additionally use these for String based XPaths
      "org.scalesxml" %% "scales-jaxen" % "0.4.5" intransitive(),
      "jaxen" % "jaxen" % "1.1.3" intransitive()
    )
  )
}