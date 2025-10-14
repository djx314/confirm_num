scalaVersion := scalaV.v3

lazy val frontend = project in file(".") / "num_frontend"
lazy val backend  = project in file(".") / "num_backend" settings (scalaJSProjects := Seq(frontend))

frontend / scalaVersion := scalaV.v3
backend / scalaVersion  := scalaV.v3

Global / onChangedBuildSource := ReloadOnSourceChanges
