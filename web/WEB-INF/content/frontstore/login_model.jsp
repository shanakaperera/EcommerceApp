<%-- 
    Document   : login_model
    Created on : Dec 25, 2015, 8:28:10 AM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="login" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4>Login    </h4><span style="color: #c62d2d;" id="warningMsg"></span>
            </div>
            <div class="modal-body">
                <div class="form">
                    <form class="form-horizontal" id="loginForm" action="loginAction" method="POST">   
                        <div class="form-group">
                            <label class="control-label col-md-3" for="username">Username</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" id="username">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="email">Password</label>
                            <div class="col-md-7">
                                <input type="password" class="form-control" id="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-7 col-md-offset-3">
                                <div class="checkbox inline">
                                    <label>
                                        <input type="checkbox" id="inlineCheckbox1" value="agree"> Remember Password
                                    </label>
                                </div>
                            </div>
                        </div> 

                        <div class="form-group">
                            <div class="col-md-7 col-md-offset-3">
                                <button type="submit" class="btn btn-default">Login</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </div>
                        </div>
                    </form>
                </div> 
            </div>
            <div class="modal-footer">
                <p>Dont have account? <a href="register.jsp">Register</a> here.</p>
            </div>
        </div>
    </div>
</div>
