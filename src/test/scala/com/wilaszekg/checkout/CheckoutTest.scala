package com.wilaszekg.checkout

import com.wilaszekg.checkout.ItemPricing.unitPrice
import com.wilaszekg.checkout.Items.{Apple, Orange}
import org.scalatest.Matchers._
import org.scalatest.WordSpec

class CheckoutTest extends WordSpec {

  "Checkout" should {
    "evaluate basket" when {
      "is empty" in {
        val checkout = new Checkout

        checkout.totalCost(Basket(Nil)) shouldBe Price(0.0)
      }

      "has one items" in {
        val checkout = new Checkout
        val basket = Basket(List(Apple))

        checkout.totalCost(basket) shouldBe unitPrice(Apple)
      }

      "has multiple items" in {
        val checkout = new Checkout
        val basket = Basket(List(Apple, Orange, Apple))

        checkout.totalCost(basket) shouldBe unitPrice(Apple) + unitPrice(Orange) + unitPrice(Apple)
      }
    }

  }
}
