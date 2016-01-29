/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scaledaction.core.kafka

import com.scaledaction.core.config.{ AppConfig, HasAppConfig }
import com.typesafe.config.Config
import java.util.Properties
import org.apache.kafka.clients.producer.ProducerConfig
import scala.util.Try

class KafkaConfig(
  val brokers: String, // brokers is a comma-separated list
  val topic: String,
  val keySerializer: String,
  val valueSerializer: String,
  rootConfig: Config) extends AppConfig(rootConfig: Config) {

  def toProducerProperties: Properties = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer)
    props
  }

  val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)

  val topics = topic.split(",").toSet

  override def toString(): String = s"brokers: ${brokers}, topic: ${topic}, keySerializer: ${keySerializer}, valueSerializer: ${valueSerializer}"
}

trait HasKafkaConfig extends HasAppConfig {

  private val CONFIG_NAME = "kafka"

  def getKafkaConfig: KafkaConfig = getKafkaConfig(rootConfig.getConfig("CONFIG_NAME"))

  def getKafkaConfig(rootName: String): KafkaConfig = getKafkaConfig(rootConfig.getConfig(rootName))

  private def getKafkaConfig(kafka: Config): KafkaConfig = {

    val brokers = getRequiredValue(kafka, "brokers")

    val topic = getRequiredValue(kafka, "topic")

    val keySerializer = getRequiredValue(kafka, "key_serializer")

    val valueSerializer = getRequiredValue(kafka, "value_serializer")

    new KafkaConfig(brokers, topic, keySerializer, valueSerializer, kafka)
  }

  def listKafkaConfig = listConfig(CONFIG_NAME, rootConfig.getConfig(CONFIG_NAME))
}
