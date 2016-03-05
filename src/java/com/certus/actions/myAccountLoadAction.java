/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.UserController;
import com.certus.dbmodel.Messages;
import com.certus.dbmodel.Order;
import com.certus.dbmodel.User;
import com.certus.dbmodel.WishList;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "myAccountLoadAction", urlPatterns = {"/myAccountLoadAction"})
public class myAccountLoadAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("parm") != null) {
            String prm = request.getParameter("parm");
            Matcher m = Pattern.compile("=", Pattern.CASE_INSENSITIVE).matcher(prm);
            User u = (User) request.getSession(false).getAttribute("user");
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            String find = "";
            String html = "";
            if (m.find()) {
                find = prm.substring(m.end()).trim();
            }
            switch (find) {
                case "":
                    UserController uc = new UserController();
                    List<Order> orderList = uc.getOrderInfo(u);

                    html = "<h5 class='title'>My Account</h5>"
                            + "<div class='address'>"
                            + "<address>"
                            + "<strong>" + u.getFName() + " " + u.getLName() + "</strong><br>";
                    if (u.getAddress() != null) {
                        html += u.getAddress()
                                + "<br>"
                                + "<abbr title='Phone'>P:</abbr> " + u.getTelephone() + ".<br />";
                    }
                    html += "<a href='mailto:#'>" + u.getEmail() + "</a>"
                            + "</address>"
                            + "</div>"
                            + "<h5 class='title'>My Recent Purchases</h5>";
                    if (!orderList.isEmpty()) {

                        html += "<table class='table table-striped tcart'>"
                                + "<thead>"
                                + "<tr>"
                                + "<th>Date</th>"
                                + "<th>Order ID</th>"
                                + "<th>Price</th>"
                                + "<th>Status</th>"
                                + "<th>View Details</th>"
                                + "</tr>"
                                + "</thead>"
                                + "<tbody>";

                        for (Order o : orderList) {
                            html += "<tr>"
                                    + "<td>" + o.getDateOrdered() + "</td>"
                                    + "<td>" + o.getInvoNum() + "</td>"
                                    + "<td>Rs. " + o.getGrandTot() + "</td>"
                                    + "<td>" + o.getStatus() + "</td>"
                                    + "<td><button class='btn btn-default' type='button' data-dismiss='modal' data-target='#viewOrderInfo' data-toggle='modal' onclick='getOrderInfo(" + o.getId() + ");'><span class='fa fa-info-circle'></span></button></td>"
                                    + "</tr>";
                        }
                        html += "</tbody></table>";
                    } else {
                        html += "<h4>No Recent Purchases.</h4>";
                    }
                    s.close();
                    response.getWriter().write(html);
                    break;
                case "wishList":

                    html = "<h5 class='title'>Wish List</h5>"
                            + "<table class='table table-striped tcart'>"
                            + "<thead>"
                            + "<tr>"
                            + "<th>#</th>"
                            + "<th>Name</th>"
                            + "<th>Price</th>"
                            + "<th>Date Added</th>"
                            + "<th>Remove From List</th>"
                            + "</tr>"
                            + "</thead>"
                            + "<tbody>";
                    List<WishList> list = s.createCriteria(WishList.class, "wList")
                            .createAlias("wList.user", "user")
                            .add(Restrictions.eq("user.id", u.getId())).list();
                    int i = 1;
                    for (WishList wish : list) {

                        html += "<tr>"
                                + "<td>" + i + "</td>"
                                + "<td><a href='single-item.jsp?cat=" + wish.getProduct().getSubCategory().getCategory().getId() + "&sub=" + wish.getProduct().getSubCategory().getId() + "&pid=" + wish.getProduct().getId() + "'>" + wish.getProduct().getName() + "</a></td>"
                                + "<td>Rs. " + wish.getProduct().getProductHasSizes().stream().filter(p -> p.getProduct().getId() == wish.getProduct().getId()).findFirst().get().getPrice() + "</td>"
                                + "<td>" + wish.getDateAdded() + "</td>"
                                + "<td><button class='btn btn-danger' type='button' onclick='removeFromList(" + wish.getId() + ");'><span class='fa fa-remove'></span></button></td>"
                                + "</tr>";
                        i++;
                    }

                    html += "</tbody>"
                            + "</table>";
                    s.close();
                    response.getWriter().write(html);
                    break;
                case "orderHistory":
                    List<Messages> msgList = s.createCriteria(Messages.class, "msg")
                            .createAlias("msg.order", "order")
                            .createAlias("order.user", "user")
                            .add(Restrictions.eq("user.id", u.getId()))
                            .addOrder(org.hibernate.criterion.Order.asc("msg.id"))
                            .list();
                    List<Order> oList = s.createCriteria(Order.class, "od")
                            .createAlias("od.user", "user")
                            .add(Restrictions.eq("user.id", u.getId()))
                            .list();
                    // System.out.println("OList size : " + oList.size());
                    if (!oList.isEmpty()) {
                        html += "<div id='proSection'><div class='msg-wrap' id='msgSec'>";
                        if (!msgList.isEmpty()) {

                            for (Messages msg : msgList) {
                                String img = msg.isAdminSent() ? "img/admin.png" : "img/guest.png";
                                String adminSent = msg.isAdminSent() ? "Admin" : "Me";
                                String viewed = msg.isUserViewed() ? "" : " notViewed";
                                String btn = msg.isAdminSent() ? "<button class='btn btn-default' onclick='viewMsg(" + msg.getId() + ");' type='button' data-dismiss='modal' data-target='#viewMsg' data-toggle='modal' title='View Message'><span class='fa fa-eye'></span></button>" : "";
                                html += "<div class='media msg " + viewed + "'>"
                                        + "    <a class='pull-left'>"
                                        + "        <img class='media-object img-circle' style='width: 32px; height: 32px;' src='" + img + "'>"
                                        + "    </a>"
                                        + "    <div class='media-body'>"
                                        + "        <small class='pull-right time'><i class='fa fa-clock-o'></i>" + msg.getDateSent() + "</small>"
                                        + "        <h5 class='media-heading'>" + adminSent + "</h5>"
                                        + "        <small class='col-md-10 text-muted'>" + msg.getMessage() + "</small>"
                                        + btn
                                        + "    </div>"
                                        + "</div>";
                            }
                        } else {
                            html += "<h3>No Messages available.</h3>";
                        }

                        html += "</div></div>"
                                + "<div class='send-wrap'>"
                                + "<textarea class='form-control send-message' id='msgWrite' rows='3' placeholder='Write a reply...'></textarea>"
                                + "</div>"
                                + "<div class='btn-panel'>"
                                + "<button class='btn btn-success' onclick='sendMsg(" + u.getId() + ");' type='button'>Send Message</button>"
                                + "</div>";
                        s.close();
                        response.getWriter().write(html);
                    } else {
                        html = "<h3>You should have requested orders to activate message section.</h3>";
                        response.getWriter().write(html);
                    }

                    break;
                case "edit":
                    String address = u.getAddress() != null ? u.getAddress() : "";
                    String tele = u.getTelephone() != null ? u.getTelephone() : "";
                    html = " <h5 class='title'>Edit Profile</h5>"
                            + "<div class='form form-small'>"
                            + "  <form class='form-horizontal' method='POST' action='updateAccountAction'>"
                            + "      <div class='form-group'>"
                            + "        <label class='control-label col-md-2' for='name1'>Name</label>"
                            + "        <div class='col-md-6'>"
                            + "          <input type='text' class='form-control' value='" + u.getFName() + " " + u.getLName() + "' required='required' name='name1' id='name1'>"
                            + "        </div>"
                            + "      </div>"
                            + "      <div class='form-group'>"
                            + "        <label class='control-label col-md-2' for='email1'>Email</label>"
                            + "        <div class=\"col-md-6\">\n"
                            + "          <input type='text' class='form-control' value='" + u.getEmail() + "' required='required' name='email1' id='email1'>"
                            + "        </div>"
                            + "      </div>"
                            + "      <div class='form-group'>"
                            + "        <label class='control-label col-md-2' for='telephone'>Telephone</label>"
                            + "        <div class='col-md-6'>"
                            + "          <input type='text' class='form-control' value='" + tele + "' required='required' name='telephone' id='telephone'>"
                            + "        </div>"
                            + "      </div>"
                            + "      <div class='form-group'>"
                            + "        <label class='control-label col-md-2' for='address'>Address</label>"
                            + "        <div class='col-md-6'>"
                            + "          <textarea class='form-control' rows='5' required='required' name='address' id='address'>" + address + "</textarea>"
                            + "        </div>"
                            + "      </div>"
                            + "      <div class='form-group'>"
                            + "        <label class='control-label col-md-2' for='username2'>Username</label>"
                            + "        <div class='col-md-6'>"
                            + "          <input type='text' class='form-control' value='" + u.getUserName() + "' required='required'  name='username2' id='username2'>"
                            + "        </div>"
                            + "      </div>"
                            + "      <div class='form-group'>"
                            + "        <label class='control-label col-md-2' for='password2'>Password</label>"
                            + "        <div class='col-md-6'>"
                            + "          <input type='password' class='form-control' required='required' name='password2' id='password2'>"
                            + "        </div>"
                            + "      </div>"
                            + "      <div class='form-group'>"
                            + "         <div class='col-md-6 col-md-offset-2'>"
                            + "            <label class='checkbox inline'>"
                            + "               <input type='checkbox' id='agreed' required='required' name='agreed' value='agree'> Agree with Terms and Conditions"
                            + "            </label>"
                            + "         </div>"
                            + "      </div>"
                            + "      <div class='form-group'>"
                            + "	<div class='col-md-6 col-md-offset-2'>"
                            + "	<button type='submit' class='btn btn-default'>Update</button>"
                            + "	<button type='reset' class='btn btn-default'>Reset</button>"
                            + "	</div>"
                            + "      </div>"
                            + "  </form>"
                            + "</div>";
                    s.close();
                    response.getWriter().write(html);
                    break;
            }
        }
    }

}
