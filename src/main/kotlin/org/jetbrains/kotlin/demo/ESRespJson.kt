package org.jetbrains.kotlin.demo
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON

@Serializable
data class Hits(val total: Int = 0,
                @SerialName("max_score")   val maxScore: Int = 0,
                @SerialName("hits") val hitss: List<String>?
)

@Serializable
data class BucketsSecondItem(@SerialName("key_as_string") val keyAsString: String = "",
                             @SerialName("doc_count") val docCount: Int = 0,
                             @SerialName("3") val thirdLevel: ThirdLevel,
                             val key: Long = 0)
@Serializable
data class BucketsItem(
        @SerialName("doc_count") val docCount: Int = 0,
        @SerialName("1") val firstLevel: FirstLevel,
        val key: String = "")


@Serializable
data class ThirdLevel(
        @SerialName("doc_count_error_upper_bound") val docCountErrorUpperBound: Int = 0,
        @SerialName("sum_other_doc_count")              val sumOtherDocCount: Int = 0,
                      val buckets: List<BucketsItem>)


@Serializable
data class Aggregations(@SerialName("2") val secondLevel: SecondLevel)


@Serializable
data class ESResp(
        @SerialName("_shards")   val Shards: Shards,
                  val hits: Hits,
                  val took: Int = 0,
                  @SerialName("timed_out")  val timedOut: Boolean = false,
                  val aggregations: Aggregations,
                  val status: Int = 0)


@Serializable
data class SecondLevel(val buckets: List<BucketsSecondItem>)


@Serializable
data class Shards(val total: Int = 0,
                  val failed: Int = 0,
                  val successful: Int = 0,
                  val skipped: Int = 0)


@Serializable
data class FirstLevel(val value: Double = 0.0)


