# core

Core Functions - useful notes (temporary placeholder):

**********************************************************************************************************
### Order of precedence for setting reference values:

env
export CASSANDRA_SEED_NODES=2.4.6.8


reference.conf only -> reference.conf
java -cp core/target/scala-2.10/scaledaction-core-assembly-1.0.jar com.scaledaction.core.examples.CassandraConfigExample


reference.conf + -D or env  ->  -D/env

java -cp core/target/scala-2.10/scaledaction-core-assembly-1.0.jar -DCASSANDRA_SEED_NODES=1.3.5.7 com.scaledaction.core.examples.CassandraConfigExample

or

export CASSANDRA_SEED_NODES=2.4.6.8


-D or env  ->  -D

export CASSANDRA_SEED_NODES=2.4.6.8

java -cp core/target/scala-2.10/scaledaction-core-assembly-1.0.jar -DCASSANDRA_SEED_NODES=1.3.5.7 com.scaledaction.core.examples.CassandraConfigExample

**********************************************************************************************************
### Two methods to display reference value information:

1. Use myConfig.root().render() to get a Config printed out as a string with comments showing where each value came from.

    val conf = ConfigFactory.load()
    val cassandraConfig = conf.getConfig("cassandra")
    val seednodes = cassandraConfig.getString("seednodes")
    println(s"seednodes: ${seednodes}")

    println(s"myConfig:  ${conf.root().render()}")


2. Set the Java system property -Dconfig.trace=loads to get output on stderr describing each file that is loaded.

   java -cp core/target/scala-2.10/scaledaction-core-assembly-1.0.jar -Dconfig.trace=loads com.scaledaction.core.examples.CassandraConfigExample

**********************************************************************************************************
### Check for Mac OS X to set environment variables

if [[ "$OSTYPE" == "darwin"* ]]; then

export CASSANDRA_SEED_NODES=192.168.99.100

export KAFKA_BROKERS=192.168.99.100:9092

fi
