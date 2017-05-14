package com.wilaszekg.checkout

class Checkout(enableApplesOffer: Boolean = false) {

  def totalCost(basket: Basket): Price = {
    itemsWithoutOfferedFreeUnits(basket)
      .map(ItemPricing.unitPrice)
      .foldLeft(Price(0))(_ + _)
  }

  private def itemsWithoutOfferedFreeUnits(basket: Basket): List[Items.Value] = {
    if (enableApplesOffer) {
      val (apples, others) = basket.items.partition(_ == Items.Apple)
      apples.take((apples.size + 1) / 2) ++ others
    } else basket.items
  }
}
