package max.kotlin.springboot.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/hello")
class HelloWorldController {

    @GetMapping
    fun heloWorld(): String = "Hello World!"


    @GetMapping("greet")
    fun helloWorldInline(): String = "RESTful endpoint"

}