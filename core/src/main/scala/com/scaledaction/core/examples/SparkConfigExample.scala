package com.scaledaction.core.examples

import com.scaledaction.core.spark.HasSparkConfig

object SparkConfigExample extends HasSparkConfig {

  def main(args: Array[String]) {

    val sparkConfig = getSparkConfig

    println(s"master: ${sparkConfig.master}")

    println
    println(listSparkConfig)
  }
}
