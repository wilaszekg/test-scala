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

    val checkoutWithOffers = Checkout.withOffers
    "apply Apples offer" when {
      "2 apples" in {
        val basket = Basket(List.fill(2)(Apple))

        checkoutWithOffers.totalCost(basket) shouldBe unitPrice(Apple)
      }

      "3 apples" in {
        val basket = Basket(List.fill(3)(Apple))

        checkoutWithOffers.totalCost(basket) shouldBe unitPrice(Apple) * 2
      }
    }

    "apply Oranges offer" when {
      "3 oranges" in {
        val basket = Basket(List.fill(3)(Orange))

        checkoutWithOffers.totalCost(basket) shouldBe unitPrice(Orange) * 2
      }

      "5 oranges" in {
        val basket = Basket(List.fill(5)(Orange))

        checkoutWithOffers.totalCost(basket) shouldBe unitPrice(Orange) * 4
      }
    }

    "apply multiple offers" when {
      "multiple Apples and Oranges" in {
        val basket = Basket(List.fill(5)(Orange) ++ List.fill(5)(Apple))

        checkoutWithOffers.totalCost(basket) shouldBe (unitPrice(Orange) * 4) + (unitPrice(Apple) * 3)
      }
    }
  }
}
