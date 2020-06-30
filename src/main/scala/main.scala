package org.ajb0211.EMRtest

import scala.io.StdIn.readLine

import org.apache.spark._
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.Pipeline

import com.johnsnowlabs.nlp.annotator._
import com.johnsnowlabs.nlp.annotators.ner.NerConverter
import com.johnsnowlabs.nlp.base._
import com.johnsnowlabs.nlp.pretrained.PretrainedPipeline


object Main {


    // def steam_input()(implict pipeline:  )

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

        implicit val pipeline = PretrainedPipeline("analyze_sentiment","en")


        // loop to take continuous input while spark server is running
        println
        println("*"*50)
        Iterator.continually{
            print("Input or :q to exit\n> ");
            readLine
        }
            .takeWhile((_: String) != ":q")
            .foreach{ (line: String) => {
                val data = Seq(line)
                        .toDS.toDF("text");
                pipeline.transform(data)
                    .select("sentiment.result")
                    .show(true)
            }}

        // stop the spark server
        spark.stop()
  }
}
