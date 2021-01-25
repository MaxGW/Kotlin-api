package max.kotlin.springboot.demo.model

import java.lang.NumberFormatException
import javax.security.auth.login.AccountException


//DTO - since this object is serialised into the response over the network
//default class/field props are public
class Bank(
    val accNumber: String,
    val trust: Double,
    val transactionFee: Int
)