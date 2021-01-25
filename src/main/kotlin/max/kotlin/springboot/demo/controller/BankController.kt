package max.kotlin.springboot.demo.controller

import max.kotlin.springboot.demo.model.Bank
import max.kotlin.springboot.demo.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("api/banks")
class BankController(private val dataSource: BankService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun notFoundExceptionHandler(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun badRequestHandler(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getBanks(): Collection<Bank> = dataSource.getBanks()

    @GetMapping("/{accNumber}")
    fun getBank(@PathVariable accNumber: String) = dataSource.getBank(accNumber)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank = dataSource.createBank(bank)
}
