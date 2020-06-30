package org.ajb0211.EMRtest

import scala.math.random

import org.apache.spark._
import org.apache.spark.sql.SparkSession

import com.johnsnowlabs.nlp.annotator._
import com.johnsnowlabs.nlp.annotators.ner.NerConverter
import com.johnsnowlabs.nlp.base._
import org.apache.spark.ml.Pipeline

import com.johnsnowlabs.nlp.pretrained.PretrainedPipeline



object Main {
    def main(args: Array[String]) {

        // Initialize Spark Objects
        val spark: SparkSession = SparkSession.builder
                                .master("local[*]")
                                .appName("TestSparknlp")
                                // Configs for sparknlp
                                // Set version in project and import into here and build.sbt
                                .config("spark.jars.packages", "com.johnsnowlabs.nlp:spark-nlp_2.11:2.5.2")
                                .config("spark.kryoserializer.buffer.max", "1000M")
                                .getOrCreate
        import spark.implicits._

        val sc = spark.sparkContext
        sc.setLogLevel("ERROR")

        val pipeline = PretrainedPipeline("analyze_sentiment","en")

        val data = Seq(args.foldRight("")(_ + " " + _))
                    .toDS.toDF("text")

        // val data = Seq(
        //             """
        //             Barclays misled shareholders and the public about one of the biggest investments in the bank's history, a BBC Panorama investigation has found.
        //             The bank announced in 2008 that Manchester City owner Sheikh Mansour had agreed to invest more than £3bn.
        //             But the BBC found that the money, which helped Barclays avoid a bailout by British taxpayers, actually came from the Abu Dhabi government.
        //             Barclays said the mistake in its accounts was "a drafting error".
        //             Unlike RBS and Lloyds TSB, Barclays narrowly avoided having to request a government bailout late in 2008 after it was rescued by £7bn worth of new investment, most of which came from the Gulf states of Qatar and Abu Dhabi.
        //             The S&P 500's price to earnings multiple is 71% higher than Apple's, and if Apple were simply valued at the same multiple, its share price would be $840, which is 52% higher than its current price.
        //             """
        //             ).toDS.toDF("text")

        pipeline.transform(data)
            .select("sentiment.result")
            .show(true)


        spark.stop()
  }
}
