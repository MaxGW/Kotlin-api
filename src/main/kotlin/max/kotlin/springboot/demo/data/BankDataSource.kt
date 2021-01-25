package max.kotlin.springboot.demo.data

import max.kotlin.springboot.demo.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accNumber: String): Bank
    fun createBank(bank: Bank): Bank
}