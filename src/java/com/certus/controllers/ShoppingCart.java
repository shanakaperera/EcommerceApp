/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Product;
import com.certus.dbmodel.ProductHasSize;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class ShoppingCart {

    private CopyOnWriteArrayList<CartItem> shoppingList;

    private final Session ses;
    private Criteria criteria;
    private String invoiceNum;
    private String couponNum;
    private Double total;

    public CopyOnWriteArrayList<CartItem> getShoppingList() {
        return shoppingList;
    }

    public ShoppingCart() {
        shoppingList = new CopyOnWriteArrayList<>();
        ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();

    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public boolean addItem(CartItem item) {
        boolean flag = false;
        for (CartItem itm : this.shoppingList) {
            if (itm.isSameCartItem(item)) {
                itm.setQnty(item.getQnty() + itm.getQnty());
                flag = true;
                break;
            }
        }
        if (!flag) {
            this.shoppingList.add(item);
            flag = true;
        }
        return flag;

    }

    public void updateItemCount(int pro_id, int qnty, String size) {
        for (CartItem item : this.shoppingList) {
            if (item.getProduct_id() == pro_id && item.getSize().equals(size)) {
                this.shoppingList.get(this.shoppingList.indexOf(item)).setQnty(qnty);
            }
        }
    }

    public void updateProductSize(int pro_id, String sizePre, String sizeNew) {
        for (CartItem item : this.shoppingList) {
            if (item.getProduct_id() == pro_id && item.getSize().equals(sizePre)) {
                this.shoppingList.get(this.shoppingList.indexOf(item)).setSize(sizeNew);
            }
        }
    }

    public void removeItem(int item_id, String size) {
        for (CartItem item : this.shoppingList) {
            if (item.getProduct_id() == item_id && item.getSize().equals(size)) {
                this.shoppingList.remove(item);
            }
        }
    }

    public List<ProductHasSize> getCompleteProductCart() {
        List<ProductHasSize> cartListOriginal = new ArrayList<>();

        for (CartItem crt : this.getShoppingList()) {
            ProductHasSize proHasSize = (ProductHasSize) ses.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.size", "size")
                    .createAlias("phs.product", "pro")
                    .add(Restrictions.eq("pro.id", crt.getProduct_id()))
                    .add(Restrictions.eq("size.sizeName", crt.getSize()))
                    .uniqueResult();
            cartListOriginal.add(proHasSize);
        }
        return cartListOriginal;
    }

    public int getTotalItemsOfTheCart() {
        return getShoppingList().stream().mapToInt(CartItem::getQnty).sum();
    }

    public String getImageofProduct(int pro_id) {
        return getCompleteProductCart().stream().filter(f -> f.getProduct().getId() == pro_id)
                .findFirst()
                .get().getProduct()
                .getImageMain();
    }

    public String getNameofProduct(int pro_id) {
        return getCompleteProductCart().stream().filter(f -> f.getProduct().getId() == pro_id)
                .findFirst()
                .get().getProduct()
                .getName();
    }
//////////////////////////////////////////////////////

    public Double getPriceofProduct(int pro_id, String size) {
        int discountPrice = getCompleteProductCart().stream()
                .filter(f -> f.getProduct().getId() == pro_id)
                .findFirst()
                .get().getProduct()
                .getProductHasSizes().stream().
                filter(p -> p.getSize().getSizeName().equals(size))
                .findFirst().get().getProduct().getDiscountPrice();
        double price = getCompleteProductCart().stream()
                .filter(f -> f.getProduct().getId() == pro_id)
                .findFirst()
                .get().getProduct()
                .getProductHasSizes().stream().
                filter(p -> p.getSize().getSizeName().equals(size))
                .findFirst().get().getPrice();

        if (discountPrice != 0) {
            return getDiscountPrice(price, discountPrice);

        } else {
            return price;
        }
    }
/////////////////////////////////////////////////////////

    public Double grandTotal(List<Double> total) {
        return total.stream().mapToDouble(Double::doubleValue).sum();
    }

    public String getUrlProductById(int pid) {

        Product p = (Product) ses.createCriteria(Product.class).add(Restrictions.eq("id", pid)).uniqueResult();
        return "single-item.jsp?cat=" + p.getSubCategory().getCategory().getId()
                + "&sub=" + p.getSubCategory().getId() + "&pid=" + pid;
    }

    public double getDiscountPrice(double price, int percentage) {
        return (int) (price - (price * (percentage / 100.0f)));
    }

   

    public Double getTotalWithCoupon(Double coupon) {
        double discount = (int) (getTotal() * (coupon / 100.0f));
        return getTotal() - discount;
    }
//    public static void main(String[] args) {
//
//        ShoppingCart cart = new ShoppingCart();
//        cart.addItem(new CartItem(1, 3, "S"));
//        cart.addItem(new CartItem(2, 5, "S"));
//        cart.addItem(new CartItem(1, 2, "S"));
//        cart.addItem(new CartItem(2, 6, "M"));
//        cart.updateProductSize(1, "S", "L");
//         cart.addItem(new CartItem(4, 1, "M"));
//          cart.updateItemCount(4, 2, "X");
//        for (CartItem ci : cart.getShoppingList()) {
//            System.out.println(ci.getProduct_id() + " - " + ci.getQnty() + " - " + ci.getSize());
//        }
//        int addedQnty = cart.getShoppingList().stream()
//                            .filter(p -> p.getProduct_id() == 1
//                                    && p.getSize().equals("L")).findAny().get().getQnty();
//        System.out.println("Added - "+addedQnty);
//

//        for (ProductHasSize ph : cart.getCompleteProductCart()) {
//
//            String pob = ph.getProduct().getName() + " - " + ph.getSize().getSizeName()
//                    + " - " + ph.getProduct().getBrand().getBrandName() + " - " + ph.getPrice();
//            System.out.println(pob);
//
//        }
//        System.out.println("Price - - " + cart.getPriceofProduct(2, "M"));
//        System.out.println("Price - - " + cart.getNameofProduct(1));
   // }
}
