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
package com.scaledaction.core.config

import java.net.InetAddress
import com.typesafe.config.{ Config, ConfigFactory }
import scala.util.{ Try, Success, Failure }

/**
 * 1. Default configuration values come from "reference.conf".
 *
 * 2. The second source of configuration values comes from Environment Vairables.
 * These require entries in the reference.conf file (e.g. seednodes = ${?CASSANDRA_SEED_NODES}
 *
 * 3. The third source of configuration values comes from java run command through the use of
 * the -D tag (e.g. -DCASSANDRA_SEED_NODES=5.6.7.8)
 *
 * The order of precedence is:
 * 1. -D (if java -cp . . . -D. . . is used then this takes priority over
 * reference.conf and Environment Variables).
 *
 * 2. Environment Variables (if Environment Variables are set then they take priority over
 * reference.conf values).
 *
 * 3. reference.conf (reference.conf values are used when Environment Variables are not present
 * and -D was not used.)
 *
 *
 * Standard behavior - the following are loaded with first-listed as higher priority:
 *
 * system properties
 * application.conf (all resources on classpath with this name)
 * application.json (all resources on classpath with this name)
 * application.properties (all resources on classpath with this name)
 * reference.conf (all resources on classpath with this name)
 *
 *
 * [For Debug - Set the Java system property -Dconfig.trace=loads to get output on stderr describing
 * each file that is loaded.]
 *
 */
protected class AppConfig(rootConfig: Config) {
  def getString(key: String): String = rootConfig.getString(key)
}

trait HasAppConfig extends Serializable {

  val rootConfig = ConfigFactory.load

  def getRequiredValue(config: Config, name: String): String = {
    config.getString(name)
  }

  def listConfig = rootConfig.root().render()

  protected def listConfig(configName: String, config: Config) = "\"" + configName + "\": " + config.root().render()

  import akka.util.Timeout
  import scala.concurrent.duration._

  protected def stringToDuration(t: String): Timeout = {
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}
