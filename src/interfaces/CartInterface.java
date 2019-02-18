package interfaces;

import models.Product;

public interface CartInterface {
    void productAddedToCart(Product product);
    void productRemovedFromCart(Product product);
}
