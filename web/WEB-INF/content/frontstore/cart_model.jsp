
<%@page import="com.certus.controllers.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.certus.controllers.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="cart" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4>Shopping Cart</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped tcart">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%

                            if (request.getSession().getAttribute("cart") != null) {
                                ShoppingCart cart1 = (ShoppingCart) request.getSession().getAttribute("cart");
                                List<Double> total = new ArrayList<Double>();
                                for (CartItem ci : cart1.getShoppingList()) {
                        %>
                        <tr>
                            <td>
                                <a href="<%=cart1.getUrlProductById(ci.getProduct_id())%>"><%=cart1.getNameofProduct(ci.getProduct_id())%></a><br>
                                    <span>Size : <%=ci.getSize()%></span>
                            </td>
                            <td>
                                <%=ci.getQnty()%>
                            </td>
                            <td>
                                Rs. <%=cart1.getPriceofProduct(ci.getProduct_id(),ci.getSize())%>
                            </td>
                        </tr>

                        <%total.add(cart1.getPriceofProduct(ci.getProduct_id(),ci.getSize()) * ci.getQnty());
                            }%>


                        <tr>
                            <th></th>
                            <th>Total</th>
                            <th>Rs<%=cart1.grandTotal(total)%></th>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <a href="index.jsp" class="btn">Continue Shopping</a>
                <a href="cart.jsp" class="btn btn-danger">Checkout</a>

            </div>
        </div>
    </div>
</div>
