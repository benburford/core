package com.scaledaction.core.spark

import org.scalatest.FlatSpec

class SparkConfigSpec extends FlatSpec with HasSparkConfig {
  val sparkConfig = getSparkConfig

  "sparkConfig.master" should "have value local[2]" in {
    assert(sparkConfig.master == "local[2]")
  }
}