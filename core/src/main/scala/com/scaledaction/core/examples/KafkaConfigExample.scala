package com.scaledaction.core.examples

import com.scaledaction.core.kafka.HasKafkaConfig

object KafkaConfigExample extends HasKafkaConfig {

  def main(args: Array[String]): Unit = {

    val kafkaConfig = getKafkaConfig

    println(s"brokers: ${kafkaConfig.brokers}")
    println(s"topic: ${kafkaConfig.topic}")

    println
    println(listKafkaConfig)
  }
}
