package com.scaledaction.core.examples

import com.scaledaction.core.cassandra.HasCassandraConfig

object CassandraConfigExample extends HasCassandraConfig {

  def main(args: Array[String]) {

    val cassandraConfig = getCassandraConfig

    println(s"cassandraConfig: ${cassandraConfig.keyspace}")
  }
}
