package com.wilaszekg.checkout

import java.util.Currency

final case class Price(amount: BigDecimal) {
  val currency: Currency = Currency.getInstance("GBP")

  def +(other: Price) = Price(amount + other.amount)

  def *(multiplier: Int) = Price(amount * multiplier)
}
