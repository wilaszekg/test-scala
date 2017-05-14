package com.wilaszekg.checkout

import java.util.Currency

import org.scalatest.Matchers._
import org.scalatest.WordSpec

class ItemPricingTest extends WordSpec {
  "Price" should {
    "be set for Apple" in {
      ItemPricing.unitPrice(Items.Apple) shouldBe Price(0.60)
    }

    "be set for Orange" in {
      ItemPricing.unitPrice(Items.Orange) shouldBe Price(0.25)
    }

    "be defined in GBP" in {
      val allItemsPrices = Items.values.map(ItemPricing.unitPrice)
      all(allItemsPrices.map(_.currency)) shouldBe Currency.getInstance("GBP")
    }
  }
}
