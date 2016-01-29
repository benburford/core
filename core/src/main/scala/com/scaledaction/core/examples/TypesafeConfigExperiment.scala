package com.scaledaction.core.examples

import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigValue

// sbt assembly

//1. Load configuration from default configuration files
// java -cp core/target/scala-2.10/scaledaction-core-assembly-1.0.jar com.scaledaction.core.examples.TypesafeConfigExperiment

//2. Override the default value with an environment variable
// export CASSANDRA_SEED_NODES=1.2.3.4
// java -cp core/target/scala-2.10/scaledaction-core-assembly-1.0.jar com.scaledaction.core.examples.TypesafeConfigExperiment

//3. Override the default value with a Java property
//unset CASSANDRA_SEED_NODES
// java -cp core/target/scala-2.10/scaledaction-core-assembly-1.0.jar -DCASSANDRA_SEED_NODES=5.6.7.8 com.scaledaction.core.examples.TypesafeConfigExperiment 

object TypesafeConfigExperiment {

  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load()
    val cassandraConfig = conf.getConfig("cassandra")
    val seednodes = cassandraConfig.getString("seednodes")
    println(s"seednodes: ${seednodes}")

    println(s"myConfig:  ${conf.root().render()}")
    
    
  }
}