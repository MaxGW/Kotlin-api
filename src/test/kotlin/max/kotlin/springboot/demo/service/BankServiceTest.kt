package max.kotlin.springboot.demo.service

import io.mockk.mockk
import io.mockk.verify
import max.kotlin.springboot.demo.data.BankDataSource
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val dataSource: BankDataSource = mockk(relaxed = true) //mockBehaviour, returns default values for all mocked method calls
    private val bankService = BankService(dataSource)

    @Test
    fun `should invoke its data source`(){
        //given
        //when
        bankService.getBanks()

        //then
        verify(exactly = 1) { dataSource.retrieveBanks() }
     }
}
