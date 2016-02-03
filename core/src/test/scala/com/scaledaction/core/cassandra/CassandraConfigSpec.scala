package com.scaledaction.core.cassandra

import org.scalatest.FlatSpec
import com.scaledaction.core.config.MissingRequiredConfigurationValueException

class CassandraConfigSpec extends FlatSpec with HasCassandraConfig {

  "getCassandraConfig" should "throw exception when any required value is blank" in {
    intercept[MissingRequiredConfigurationValueException] {
      getCassandraConfig
    }
  }

  //  def getCC = {
  //    System.setProperty("CASSANDRA_KEYSPACE", "tweet")
  //    //    println(System.getProperty("CASSANDRA_KEYSPACE"))
  //    getCassandraConfig
  //  }
  //  "cassandraConfig.seednodes" should "have value 127.0.0.1" in {
  //    val cassandraConfig = getCC
  //
  //    assert(cassandraConfig.seednodes == "127.0.0.1")
  //    //     assert(cassandraConfig.seednodes == "127.0.0.1")
  //  }
}
