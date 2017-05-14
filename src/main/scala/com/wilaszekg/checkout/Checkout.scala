package com.wilaszekg.checkout

import com.wilaszekg.checkout.Checkout.BuyNGetOneFree

class Checkout(offers: List[BuyNGetOneFree] = Nil) {

  def totalCost(basket: Basket): Price = {
    applyOffers(basket)
      .map(ItemPricing.unitPrice)
      .foldLeft(Price(0))(_ + _)
  }

  private def applyOffer(items: List[Items.Value], offer: BuyNGetOneFree): List[Items.Value] = {
    items.zipWithIndex.collect {
      case (item, index) if (index + 1) % offer.freeItemOrdinal != 0 => item
    }
  }

  private def applyOffers(basket: Basket): List[Items.Value] = {
    val (offered, otherItems) = offers.foldLeft((List.empty[Items.Value], basket.items)) {
      case ((offeredItems, itemsLeft), offer) =>
        val (offerItems, others) = itemsLeft.partition(_ == offer.item)
        (offeredItems ++ applyOffer(offerItems, offer), others)
    }

    offered ++ otherItems
  }
}

object Checkout {

  final case class BuyNGetOneFree(item: Items.Value, freeItemOrdinal: Int)

  def withOffers: Checkout = new Checkout(List(
    BuyNGetOneFree(Items.Apple, 2),
    BuyNGetOneFree(Items.Orange, 3)
  ))

}