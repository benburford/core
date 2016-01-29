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
package com.scaledaction.core.spark

import scala.util.Try
import com.typesafe.config.Config
import java.util.Properties
import org.apache.kafka.clients.producer.ProducerConfig //Ben:  kafka or spark???
import com.scaledaction.core.config.{ AppConfig, HasAppConfig }

class SparkConfig(
  val master: String,
  rootConfig: Config) extends AppConfig(rootConfig: Config) {

  override def toString(): String = s"master: ${master}"
}

trait HasSparkConfig extends HasAppConfig {

  private val CONFIG_NAME = "spark"

  def getSparkConfig: SparkConfig = getSparkConfig(rootConfig.getConfig("CONFIG_NAME"))

  def getSparkConfig(rootName: String): SparkConfig = getSparkConfig(rootConfig.getConfig(rootName))

  private def getSparkConfig(spark: Config): SparkConfig = {

    val master = getRequiredValue(spark, "master")

    new SparkConfig(master, spark)
  }

  def listSparkConfig = listConfig(CONFIG_NAME, rootConfig.getConfig(CONFIG_NAME))
}
