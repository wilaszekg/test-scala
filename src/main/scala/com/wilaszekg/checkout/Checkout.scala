package com.wilaszekg.checkout

class Checkout {

  def totalCost(basket: Basket): Price = {
    basket.items
      .map(ItemPricing.unitPrice)
      .foldLeft(Price(0))(_ + _)
  }
}
