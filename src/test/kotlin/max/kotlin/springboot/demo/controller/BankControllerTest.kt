package max.kotlin.springboot.demo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import max.kotlin.springboot.demo.model.Bank
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

//int tests, which target the endpoints (not necessarily the controller)
//SpringBootTest to trigger entire application context, instantiating full app
@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
){
    //the above ctor handles the autowiring mockMvc and ObjectMapper
    //allows for mocked http request to application, without creating an actual http req
    //Spring can resolve, allows for injected with @Autowired alongside @AutoConfigureMockMvc
    //lateinit indicates the object will be instantiated at a later point, think ctor inject is preferred!

    @Nested
    @DisplayName("GET api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `Should return all banks`() {
            //given
            mockMvc.get("/api/banks")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accNumber") { value("1") } //$ denotes the root response object, a collection of banks
                }

            //when
            //then
        }
    }

    @Nested
    @DisplayName("GET api/banks/accNumber")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return single bank with given accNumber`(){
            //given
            val accNumber = 1

            //when
            //then
            mockMvc.get("/api/banks/$accNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value("1.0") }
                }
        }

        @Test
        fun `should returns not found if no bank`(){
            //given
            val accNumber = "non-existing account number"
            //when
            //then
            mockMvc.get("/api/banks/$accNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank {

        @Test
        fun `should create a new bank`(){
            //given
            val newAccNumber = "123"
            val newBank = Bank(newAccNumber , 1.1, 2)

            //when
            val response = mockMvc.post("/api/banks/"){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            //then
            response
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accNumber") { value(newAccNumber)}
                }
        }

        @Test
        fun `should return BAD REQUEST on conflicting accNumber`(){
            //given
            val existingAccNumber = "1"
            val invalidBank = Bank(existingAccNumber, 0.0, 0)
            //when
            val response = mockMvc.post("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            //then
            response
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
         }
    }

}