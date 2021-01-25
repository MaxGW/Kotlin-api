package max.kotlin.springboot.demo.data.mock

import max.kotlin.springboot.demo.data.BankDataSource
import max.kotlin.springboot.demo.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockBankDataSource : BankDataSource {



    val banks = mutableListOf(
        Bank(accNumber = "1", trust = 1.0, transactionFee = 1),
        Bank(accNumber = "11", trust = 1.0, transactionFee = 1),
        Bank(accNumber = "111", trust = 0.0, transactionFee = 1)
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accNumber: String): Bank {
        return banks.firstOrNull() { it.accNumber == accNumber }
            ?: throw NoSuchElementException("No matching bank for account number: $accNumber")
    }

    override fun createBank(bank: Bank): Bank {
        if(banks.any() { it.accNumber == bank.accNumber }) {
            throw IllegalArgumentException("Bank with account number ${bank.accNumber} already exists.")
        }
        banks.add(bank)
        return bank
    }
}