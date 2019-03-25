name := """sound_collector"""
organization := "Emerge Medical"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.8"

libraryDependencies += guice

libraryDependencies ++= Seq(
	"org.mongodb" % "mongo-java-driver" % "3.10.1",
    "org.mongodb" % "mongodb-driver-sync" % "3.10.1",
    "org.mongodb.morphia" % "morphia" % "0.107",
)
// Visit https://mvnrepository.com/artifact/commons-codec/commons-codec to see the list of versions available
libraryDependencies += "commons-codec" % "commons-codec" % "1.11"

// Visit https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 to see the list of versions available
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.8.1"

EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)

