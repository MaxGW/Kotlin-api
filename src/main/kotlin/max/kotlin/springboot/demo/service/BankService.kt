package max.kotlin.springboot.demo.service

import max.kotlin.springboot.demo.data.BankDataSource
import max.kotlin.springboot.demo.model.Bank
import org.springframework.stereotype.Service

@Service //makes max.kotlin.springboot.demo.service available to application context at runtime, ie. injectable
class BankService(private val dataSource: BankDataSource ) {
    fun getBanks() : Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(accNumber: String) : Bank = dataSource.retrieveBank(accNumber)
    fun createBank(bank: Bank) : Bank = dataSource.createBank(bank)
}