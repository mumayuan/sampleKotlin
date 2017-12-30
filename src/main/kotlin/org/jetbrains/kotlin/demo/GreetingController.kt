package org.jetbrains.kotlin.demo

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong
import java.util.ArrayList

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
    @GetMapping("/api/myheroes")
    fun greetingList(@RequestParam(value = "name", defaultValue = "World") name: String) : ArrayList<Hero>{
        val id: Int = 3
        logger.error("---> Greeting id="+id+ " name="+name)

        return (heros)
    }
    @CrossOrigin
    @GetMapping("/api/myheroes/{id}")
    fun greetingGet(@PathVariable id: Int) : Hero{
        logger.error("---> Greeting id=$id")
        val hero: Hero = (Hero(90, "SuperMan"))

        return (hero)
    }

}