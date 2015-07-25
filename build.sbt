scalaVersion := "2.11.7"

lazy val commonSettings = Seq(
  version := "0.1.0"
)

lazy val compilerPlugin = project.
  settings(commonSettings: _*).
  settings(libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ ))
