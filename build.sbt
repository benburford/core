val globalSettings = Seq(
  version := "1.0",
  scalaVersion := "2.10.5"
)

val modulePrefix = "scaledaction"

lazy val core = (project in file("core"))
  .settings(name := s"${modulePrefix}-core")
  .settings(globalSettings:_*)
  .settings(libraryDependencies ++= coreDeps)

val akkaVersion = "2.3.11"
val sparkVersion = "1.4.1"
val sparkCassandraConnectorVersion = "1.4.0-M3"
val kafkaVersion = "0.8.2.1"
val scalaTestVersion = "2.2.4"
val sprayVersion = "1.3.3"

lazy val coreDeps = Seq(
  "com.typesafe.akka"      %% "akka-actor"            % akkaVersion,
  "com.typesafe.akka"      %% "akka-slf4j"            % akkaVersion,
  "io.spray"               %% "spray-can"             % sprayVersion,
  "io.spray"               %% "spray-client"          % sprayVersion,
  "io.spray"               %% "spray-routing"         % sprayVersion,
  "io.spray"               %% "spray-json"            % "1.3.2",
  "com.datastax.spark"     % "spark-cassandra-connector_2.10" % sparkCassandraConnectorVersion,
  "org.apache.spark"       %% "spark-sql"             % sparkVersion % "provided",
  "org.apache.spark"       %% "spark-streaming"       % sparkVersion % "provided",
  "org.specs2"             %% "specs2"                % "2.2.2"        % "test",
  "io.spray"               %% "spray-testkit"         % sprayVersion   % "test",
  "com.typesafe.akka"      %% "akka-testkit"          % akkaVersion    % "test",
  "org.apache.kafka"       % "kafka_2.10"             % kafkaVersion
  exclude("javax.jms", "jms")
  exclude("com.sun.jdmk", "jmxtools")
  exclude("com.sun.jmx", "jmxri")
)

