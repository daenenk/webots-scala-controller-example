Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / organization := "daenenk"

lazy val scala3 = "3.2.1"

ThisBuild / version := "0.0.1"

/* scalacOptions: see doc https://docs.scala-lang.org/overviews/compiler-options/index.html
 */
ThisBuild / scalacOptions ++= Seq(
  "-feature",
  "-deprecation"
)

/* About versionScheme:
 * https://www.scala-lang.org/blog/2021/02/16/preventing-version-conflicts-with-versionscheme.html
 * https://docs.scala-lang.org/overviews/core/binary-compatibility-for-library-authors.html#versioning-scheme---communicating-compatibility-breakages
 */
ThisBuild / versionScheme := Some("early-semver")

lazy val controller = project
  .in(file("."))
  .settings(
    name := "scala-controller",
    scalaVersion := scala3
  )
