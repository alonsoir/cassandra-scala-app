name := "My Project"

val commonSettings = Seq(
organization := "net.example",
version := "0.1",
scalaVersion := "2.11.4",
scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
)

lazy val testDependencies = Seq (
"org.scalatest" %% "scalatest" % "2.2.0" % "test"
)

lazy val cassandraDependencies = Seq (
"com.datastax.cassandra" % "cassandra-driver-core" % "2.1.2",
"com.chrisomeara" % "pillar_2.11" % "2.0.1"
)

lazy val common = project.in(file("common"))
.settings(commonSettings:_*)
.settings(libraryDependencies ++= (testDependencies ++ cassandraDependencies))

val projectMainClass = "common.utils.cassandra.CassandraMain"

lazy val main = project.in(file("main"))
  .dependsOn(common) 
  .settings(commonSettings:_*)
  .settings(
    mainClass := Some(projectMainClass)
  )	

mainClass in (Compile, run) := Some(projectMainClass)
