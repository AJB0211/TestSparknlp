ThisBuild / scalaVersion     := "2.11.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.ajb0211"
ThisBuild / organizationName := "ajb0211"

// not sure this needs to be here
scalacOptions += "-target:jvm-1.8"

val sparkVersion = "2.4.2"

libraryDependencies ++=Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql"  % sparkVersion,
    "org.apache.spark" %% "spark-mllib"   % sparkVersion,
    "com.johnsnowlabs.nlp" %% "spark-nlp" % "2.5.2"
)

// libraryDependencies += "com.johnsnowlabs.nlp" %% "spark-nlp-gpu" % "2.5.2"

lazy val root = (project in file("."))
  .settings(
    name := "Main"
  )
