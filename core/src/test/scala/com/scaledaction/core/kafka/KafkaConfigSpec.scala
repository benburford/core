package com.scaledaction.core.kafka

import org.scalatest.FlatSpec
import com.scaledaction.core.config.MissingRequiredConfigurationValueException

class KafkaConfigSpec extends FlatSpec with HasKafkaConfig {
  "getKafkaConfig" should "throw exception when any required value is blank" in {
    intercept[MissingRequiredConfigurationValueException] {
      getKafkaConfig
    }
  }

  //  val kafkaConfig = getKafkaConfig
  //
  //  "kafkaConfig.seednodes" should "127.0.0.1:9092" in {
  //    assert(kafkaConfig.brokers == "127.0.0.1:9092")
  //  }
}
