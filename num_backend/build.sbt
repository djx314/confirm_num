libraryDependencies ++= libScalax.`http4s-Release-ember-client`.value
libraryDependencies ++= libScalax.`http4s-Release-ember-server`.value
libraryDependencies ++= libScalax.`http4s-Release-dsl`.value
libraryDependencies ++= libScalax.`http4s-Release-circe`.value

Compile / run / fork := true

scalafmtOnCompile := true
