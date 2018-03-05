package org.jetbrains.kotlin.demo

import java.io.File
import java.util.ArrayList
import org.slf4j.LoggerFactory

import kotlinx.serialization.*
import kotlinx.serialization.json.JSON

class Convert {
    val logger = LoggerFactory.getLogger("Convert")

    companion object {
        fun convert(): String {
            val sb = StringBuilder()



            val file = File("responses"+ File.separator+"data")
            var content:String = file.readText()


            val parsed: ESResp = JSON.parse<ESResp>(content)
            //println("--After parsing.... : -"+ parsed)


            //println("--After parsing.... : -"+ parsed.aggregations.secondLevel.buckets)
            val iterator = parsed.aggregations.secondLevel.buckets.iterator()
            iterator.forEach {
                println("-- parsing.... : -"+ it.keyAsString)
                val xn: String = it.keyAsString

                it.thirdLevel.buckets.iterator().forEach { loop ->
                    val pv : Double = loop.firstLevel.value
                    val buket: String = loop.key
                    val row: DataRow = DataRow(pv, buket, xn)

                    val jsonTxt:String = JSON.stringify(row)

                    sb.append(jsonTxt).append(" ,")
                }




            }

            var ret: String = sb.toString()

            ret = ret.substring(0, ret.length -1)
            ret = "[$ret]"
            println("--Get the data in converter: -"+ ret)
            return ret
        }
    }

}

/*
fun main(args : Array<String>) {
    println("Hello, world!")//+Convert.convert())
}
*/