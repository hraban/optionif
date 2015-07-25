
lazy val commonSettings = Seq(
  version := "0.1.0",
  scalaVersion := "2.11.7"
)

lazy val compilerPlugin = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
  )

lazy val testproject = project.
  settings(commonSettings: _*).
  settings(
    scalacOptions += "-Xplugin:" + (packageBin in Compile in compilerPlugin).value
  )