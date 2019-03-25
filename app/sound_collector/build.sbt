name := """sound_collector"""
organization := "Emerge Medical"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.8"

libraryDependencies += guice

libraryDependencies ++= Seq(
	"org.mongodb" % "mongo-java-driver" % "3.10.1",
    "org.mongodb" % "mongodb-driver-sync" % "3.10.1",
    "org.mongodb.morphia" % "morphia" % "0.107"
)

EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)

