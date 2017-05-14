package com.wilaszekg.checkout

object ItemPricing {
  def unitPrice(item: Items.Value): Price = item match {
    case Items.Apple => Price(0.60)
    case Items.Orange => Price(0.25)
  }
}
