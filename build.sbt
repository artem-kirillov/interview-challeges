lazy val root = (project in file("."))
  .settings(
    name := "interview-challenges",
    scalaVersion := "2.12.2",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
  )
