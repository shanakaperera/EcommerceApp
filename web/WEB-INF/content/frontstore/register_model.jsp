<%-- 
    Document   : register_model
    Created on : Dec 25, 2015, 8:29:17 AM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="register" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4>Register</h4>
            </div>
            <div class="modal-body">
                <div class="form">
                    <form class="form-horizontal" id="registerFrom" action="registerAction" method="POST" data-toggle="validator" role="fprm">
                        <div class="form-group">
                            <label class="control-label col-md-3" for="name">Name</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" id="r_name" data-error="Provide your first and last name." required="required">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>   
                        <div class="form-group">
                            <label class="control-label col-md-3" for="email">Email</label>
                            <div class="col-md-7">
                                <input type="email" class="form-control" id="email" data-error="Please provide valid email address." required>
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3" for="username1">Username</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" id="username1" data-error="You should provide a valid user name." required="required">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3"  for="password1">Password</label>
                            <div class="col-md-7">
                                <input type="password" class="form-control" data-minlength="6" id="password1" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="password2">Password again</label>
                            <div class="col-md-7">
                                <input type="password" class="form-control" data-match="#password1" 
                                       data-match-error="Whoops, passwords don't match" id="password2" required="required">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-7 col-md-offset-3">
                                <div class="checkbox inline">
                                    <label>
                                        <input type="checkbox" id="inlineCheckbox2" value="agree" required="required"> 
                                        Agree with  <a href="terms_conditions.jsp">Terms and Conditions</a>
                                    </label>
                                </div>
                            </div>
                        </div> 

                        <div class="form-group">
                            <div class="col-md-9 col-md-offset-3">
                                <button type="submit" class="btn btn-default">Register</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </div>
                        </div>
                        <span id="registerSection"></span>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <p>Already have account? <a href="login.jsp">Login</a> here.</p>
            </div>
        </div>
    </div>
</div>
