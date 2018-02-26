package org.jetbrains.kotlin.demo

import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong
import java.util.ArrayList
import java.util.Random
import java.util.concurrent.*
import java.io.File
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity


@RestController
class GreetingController {

    val counter = AtomicLong()
    val logger = LoggerFactory.getLogger("GreetingController")


    val heros: ArrayList<Hero> = ArrayList<Hero>()

    init
    {

    for(i in 1..100) {
        heros.add(Hero(i, "Tintin $i"))
    }

    }


    @CrossOrigin
    @PostMapping("/api/myheroes")
    fun addHero( @RequestBody hero: Hero) {
        logger.error("--add hero -----------------  -> Greeting $hero")

        //return "${hero.id} updated"
    }

    @CrossOrigin
    @PutMapping("/api/myheroes")
    //fun updateHero(@PathVariable id: Int, @RequestBody hero: Hero) : String{
    fun updateHero( @RequestBody hero: Hero) {
        logger.error("--update hero ----------------- JSON ->  $hero")

       // return ResponseEntity<String>(headers, HttpStatus.OK)
    }

    @CrossOrigin
    @GetMapping("/api/myheroes")
    fun greetingList(@RequestParam(value = "name", defaultValue = "World") name: String) : ArrayList<Hero>{

        logger.error("--Get the list -")

        return (heros)
    }
    @CrossOrigin
    @GetMapping("/api/myheroesspec")
    fun getSpec() : String{
        val file = File("responses"+File.separator+"vega.spec")
        var content:String = file.readText()
        logger.error("--Get the spec -"+content)

        return (content)
    }

    @CrossOrigin
    @GetMapping("/api/myheroesspeclite")
    fun getSpecLite() : String{
        val file = File("responses"+File.separator+"lite.spec")
        var content:String = file.readText()
        logger.error("--Get the spec -"+content)

        return (content)
    }
    @CrossOrigin
    @GetMapping("/api/data")
    fun getData() : String{
        val file = File("responses"+File.separator+"data")
        var content:String = file.readText()
        logger.error("--Get the data -"+content)

        return (content)
    }

    @CrossOrigin
    @GetMapping("/api/myheroes/{id}")
    fun greetingGet(@PathVariable id: Int) : HeroDetail{
        logger.error("--Get one Hero-> Greeting id=$id")

        val data : IntArray = intArrayOf(
                (10..30).random(),
                (0..30).random(),
                (0..10).random(),
                (0..5).random(),
                (5..10).random()
                )

        val hero: Hero = (Hero(90, "SuperMan"))
        val heroDetails : HeroDetail = HeroDetail(
                alias = "Alias.E.T.", data = data, hero = hero
        )
        return (heroDetails)
    }


    val random = Random()

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }
    fun ClosedRange<Int>.random() =
            ThreadLocalRandom.current().nextInt(endInclusive - start) +  start



    @CrossOrigin
    @GetMapping("/api/myitems/{id}")
    fun getMyItem(@PathVariable id: Int) : MyItem{
        logger.error("--Get one Item-> Greeting id=$id")


        val myitem: MyItem = (MyItem(990, "SuperMan"))
        return (myitem)
    }
}