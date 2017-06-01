organization := "underscore.io"
name := "ditl-crm"
version := "1.0.0"
scalaVersion := "2.12.1"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-language:higherKinds",
  "-Xlint",
  "-Xfatal-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

libraryDependencies ++= webserver ++ json ++ database ++ logging ++ scalatest ++ merge

resolvers += Resolver.sonatypeRepo("snapshots")
val http4sVersion = "0.17.0-M3"
lazy val webserver = Seq(
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-dsl"          % http4sVersion,
  "org.http4s" %% "http4s-circe"        % http4sVersion
)

val circeVersion =  "0.6.1"
lazy val json = Seq(
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser"  % circeVersion
)

val doobieVersion = "0.4.1"
lazy val database = Seq(
  "org.tpolecat" %% "doobie-core-cats" % doobieVersion,
  "org.tpolecat" %% "doobie-h2-cats"   % doobieVersion
)

lazy val logging = Seq(
  "org.slf4j" % "slf4j-simple" % "1.6.4"
)

lazy val scalatest = Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)

lazy val merge = Seq(
  "com.davegurnell" %% "bulletin" % "0.7.0"
)

lazy val shapeless = Seq(
  "com.chuusai" %% "shapeless" % "2.3.2"
)
