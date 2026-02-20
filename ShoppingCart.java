/*
    Design Shopping Card --
    Functional Requirements

User can:
Add item to cart
Update quantity
Remove item
View cart
Apply coupon
Checkout

Cart should:
Persist between sessions
Expire after inactivity (e.g., 30 days)
Support both logged-in and guest users

Pricing:
Support discounts (percentage, flat)
Support product-level and cart-level discounts
Support multiple coupons but with rules

Inventory:
Validate stock during checkout
Handle race conditions when multiple users try to buy same product

Entities --
ShoppingSite (O)
Cart
Item

Discount (I)
    FlatDiscount(C)
    Persent500Discount(C)

Class --
ShoppingSite
- List<Item>
- Cart
* addItem() / CRUD on cart
* checkoutCart()
* buySingleItem(itemid)

Cart 
- Map<Integer, Quantity>
- totalPrice
* updateTotalPrice()

Item
- id
- name
- description
- price

*/

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

class ShoppingSite {
    Map<Integer, Integer> items; // Inventory
    Cart cart;

    public void addItemToCart(int itemId, int quantity) {
        cart.add(itemId, quantity);
    }

    public void viewCart() {
        cart.viewCart();
    }

    public void checkoutCart() {
        cart.checkoutCart();
    }

    public void removeItem(int itemId) {
        cart.removeItem(itemId);
    }

    public void removeItemQuantity(int itemId, int quantity) {
        cart.removeItemQuantity(itemId, quantity);
    }
}

class Cart {
    Map<Integer, CartItem> cartItems;
    int totalPrice;

    public void updateTotalPrice() {
        totalPrice = cartItems.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void addItemToCart(int itemId, int quantity) {
        if(items.get(itemId).getQuantity() != null && items.get(itemId).getQuantity() != 0){
            this.getCartItems(itemId).put(itemId, quantity);
            this.updateTotalPrice();
        }
    }

    public void viewCart() {
        // Checking if the items are more than 30 days or the inventory has 0 products
        for(Map.Entry<Integer, CartItem> cartItem : this.getCartItems()) {
            Duration duration = Duration.between(cartItem.getValue().getAddedTime(), LocalDateTime.now());
            if(duration.toDays() > 30) 
                this.getCartItems().remove(cartItem.getKey());
            else if(items.get(cartItem.getKey()) == 0)
                this.getCartItems().remove(cartItem.getKey());
            else if(items.get(cartItem.getKey()) < cartItem.getValue().getQuantity()) {
                System.out.println("Cart Items Updated");
                cartItem.getValue().setQuantity(items.get(cartItem.getKey()));
            }
        }

        this.getCartItems().stream().forEach(System.out::println);
    }

    public void checkoutCart() {
        viewCart();
        return this.getTotalPrice();
    }

    public void removeItem(int itemId) {
        this.getCartItems().remove(itemId);
    }

    public void removeItemQuantity(int itemId, int quantity) {
        if(quantity == 0)
            this.getCartItems().remove(itemId);
        else 
            this.getCartItems().getQuantity(quantity);
    }
}

class CartItem {
    int itemId;
    int quantity;
    LocalDateTime addedTime;
    int totalPrice;
}

/**
 * Discount classes
 */

interface Discount {
    int apply(Cart cart);
}

class FlatDiscount implements Discount {
    int flatAmt;
    int minCheckoutAmount;
    public FlatDiscount(int flatAmt, int minCheckoutAmount) {
        this.flatAmt = flatAmt;
        this.minCheckoutAmount = minCheckoutAmount;
    }
    public int apply(Cart cart) {
        return amt - flatAmt;
    }
}

class ProductPercentDiscount implements Discount {
    ProductCategory productCategory;
    int percent;
    public ProductPercentDiscount(ProductCategory productCategory, int percent) {
        this.productCategory = productCategory;
        this.percent = percent;
    }
    public int apply(Cart cart) {
        for(CartItem cartItem : cart.getCartItems())
            if(cartItem.getProductCategory() == cartItem.getProductCategory())
                 cartItem.setTotalPrice(cartItem.getTotalPrice() - (cartItem.getTotalPrice() % this.percent));
    }
}

// Changing ...
class ShoppingSite {
    Map<Integer, Integer> items; // Inventory
    Cart cart;
    Discount discount;

    //......................

    public void checkoutCart() {
        // 1. FlatDiscount()
        // 2. ProductPercentDiscount(ProductCategory.ELECTRONICS)
        cart.checkoutCart();
    }

    // ......................
}

// Checkout Method in Cart class
public void checkoutCart(Discount discount) {
    viewCart();
    discount.apply(this, this.getTotalPrice());
    return this.getTotalPrice();
}

/**
 * Concurrency 
 * ------------------------
 * For item updates in cart - Shared Lock
 * private final Map<Long, ReentrantLock> cartLocks = new ConcurrentHashMap<>();
    ReentrantLock lock = cartLocks
            .computeIfAbsent(cartId, id -> new ReentrantLock());

    this above approch will work in single JVM but fail in distrubutes system
    so, we use VERSION when the data is updated

 * for inventory we need exclusive lock , SELECT FOR UPDATE

    Scalability Discussion
------------------------------------
If system handles:
10 million daily users
100K concurrent carts

How would you scale:
Storage (Redis vs DB)
Caching strategy
Cart sharding
Event-driven architecture?
 */
