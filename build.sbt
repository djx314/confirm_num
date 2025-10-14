scalaVersion := scalaV.v3

lazy val frontend: sbtcrossproject.CrossProject =
  crossProject(JSPlatform, JVMPlatform) in file(".") / "num_frontend" jvmSettings (scalaJSProjects := Seq(frontend.js))

lazy val backend = project in file(".") / "num_backend"  dependsOn frontend.jvm

frontend.js / scalaVersion  := scalaV.v3
frontend.jvm / scalaVersion := scalaV.v3
backend / scalaVersion      := scalaV.v3

addCommandAlias("r1", "; backend/fgRun;")
addCommandAlias("s1", "; backend/bgStop;")

Global / onChangedBuildSource := ReloadOnSourceChanges
