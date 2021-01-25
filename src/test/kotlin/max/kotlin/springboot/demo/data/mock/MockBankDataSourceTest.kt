package max.kotlin.springboot.demo.data.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest{

    //POJO - plain old javascript object (instantiate basic class and test that)
    private val mockBankDataSource = MockBankDataSource()

    @Test
    fun `should return a collection of banks`(){
        //given
        //when
        val banks = mockBankDataSource.retrieveBanks()

        //then
        assertThat(banks.size).isGreaterThan(2)
    }

    @Test
    fun `should provide mock bank data`(){
        //given
        //when
        val banks = mockBankDataSource.retrieveBanks()

        //then
        assertThat(banks).allMatch { it.accNumber.isNotBlank() }
        assertThat(banks).anyMatch { it.transactionFee != 0 }
        assertThat(banks).anyMatch { it.trust != 0.0 }
     }
    
    @Test
    fun `return single bank with given accNumber`(){
        //given
        val expectedAccNumber = mockBankDataSource.retrieveBanks().first().accNumber

        //when
        val singleBank = mockBankDataSource.retrieveBank(expectedAccNumber)

        //then
        assertThat(singleBank.accNumber).isEqualTo(expectedAccNumber)
     }
}