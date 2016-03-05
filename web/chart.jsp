<%-- 
    Document   : chart
    Created on : Feb 5, 2016, 8:31:43 AM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-View Reports</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <link rel="stylesheet" type="text/css" href="http://www.shieldui.com/shared/components/latest/css/light/all.min.css" />

    </head>
    <%
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("adminAuth.jsp");
        }
    %>
    <body class="bg-info">
        <div class="container">
            <%@include file="WEB-INF/content/admin/header.jsp" %>

            <div class="row">
                <div class="page-header" style="background-color:#F25758"> 
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Reports&nbsp;<small>View Reports for Compare Business&nbsp;</small></h4> 
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-md-6"><h3>Quarterly Sales</h3></div>
                                <div class="col-md-3">
                                    <select class="form-control pull-right" id="saleSel">
                                        <option value="week">Sales for this week</option>
                                        <option value="month">Sales for this month</option>
                                        <option value="year">Sales for this year</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <select class="form-control pull-right" id="chatSel">
                                        <option value="bar">Bar Chart</option>
                                        <option value="area">Area Chart</option>
                                        <option value="line">Line Chart</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div id="salesChart" style="height: 270px;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <hr />

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-md-6"><h3>Quarterly Orders</h3></div>
                                <div class="col-md-3">
                                    <select class="form-control pull-right" id="orderSel">
                                        <option value="week">Orders for this week</option>
                                        <option value="month">Orders for this month</option>
                                        <option value="year">Orders for this year</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <select class="form-control pull-right" id="chatOrderSel">
                                        <option value="bar">Bar Chart</option>
                                        <option value="area">Area Chart</option>
                                        <option value="line">Line Chart</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div id="ordersChart" style="height: 270px;"></div>
                        </div>
                    </div>
                </div>

            </div>
            <hr />
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-md-6"><h3>Quarterly Expenses</h3></div>
                                <div class="col-md-3">
                                    <select class="form-control pull-right" id="expenseSel">
                                        <option value="week">Expenses for this week</option>
                                        <option value="month">Expenses for this month</option>
                                        <option value="year">Expenses for this year</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <select class="form-control pull-right" id="chatExpenseSel">
                                        <option value="bar">Bar Chart</option>
                                        <option value="area">Area Chart</option>
                                        <option value="line">Line Chart</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div id="expensesChart" style="height: 270px;"></div>
                        </div>
                    </div>
                </div>

            </div>  




            <%@include file="WEB-INF/content/admin/scripts.jsp" %>
            <script type="text/javascript" src="http://www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
            <script type="text/javascript">
//orders,sales,reviews,registered customers
                function chartLoad(url, sel1, sel2, chart, name, parameter) {

                    $.ajax({
                        url: url,
                        dataType: 'json',
                        data: parameter,
                        cache: false,
                        success: function (data) {

                            chart.shieldChart({
                                theme: "bootstrap",
                                axisX: {
                                    categoricalValues: $.map(data, function (item) {
                                        return item.day;
                                    })
                                },
                                zoomMode: "xy",
                                axisY: {
                                    title: {
                                        text: "Total (Rs.)"
                                    }
                                },
                                seriesSettings: {
                                    bar: {
                                        dataPointText: {
                                            enabled: true
                                        }
                                    }
                                },
                                primaryHeader: {
                                    text: "Chart"
                                },
                                dataSeries: [{
                                        seriesType: sel2,
                                        collectionAlias: name + " per " + sel1,
                                        data: $.map(data, function (item) {
                                            return item.amount;
                                        })
                                    }]

                            });

                            $("tspan:contains('Demo Version')").hide();

                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }

                function expenseLoad(sel, parameter) {

                    $.ajax({
                        url: 'getChartExpencesAction',
                        dataType: 'json',
                        data: parameter,
                        cache: false,
                        success: function (data) {

                            $("#expensesChart").shieldChart({
                                theme: "bootstrap",
                                axisX: {
                                    categoricalValues: $.map(data, function (item) {
                                        return item.day;
                                    })
                                },
                                zoomMode: "xy",
                                axisY: {
                                    title: {
                                        text: "Total (Rs.)"
                                    }
                                },
                                seriesSettings: {
                                    bar: {
                                        dataPointText: {
                                            enabled: true
                                        }
                                    }
                                },
                                primaryHeader: {
                                    text: "Chart"
                                },
                                dataSeries: [
                                    {
                                        seriesType: sel,
                                        collectionAlias: "Sales",
                                        data: $.map(data, function (item) {
                                            return item.sale;
                                        })
                                    },
                                    {
                                        seriesType: sel,
                                        collectionAlias: "Expenses",
                                        data: $.map(data, function (item) {
                                            return item.expense;
                                        })
                                    }

                                ]

                            });

                            $("tspan:contains('Demo Version')").hide();

                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
                $(document).ready(function () {
                    $.ajax({
                        url: 'getChartSalesAction',
                        dataType: 'json',
                        data: {sales: "week"},
                        cache: false,
                        success: function (data) {

                            $("#salesChart").shieldChart({
                                theme: "bootstrap",
                                axisX: {
                                    categoricalValues: $.map(data, function (item) {
                                        return item.day;
                                    })
                                },
                                zoomMode: "xy",
                                axisY: {
                                    title: {
                                        text: "Total (Rs.)"
                                    }
                                },
                                seriesSettings: {
                                    bar: {
                                        dataPointText: {
                                            enabled: true
                                        }
                                    }
                                },
                                primaryHeader: {
                                    text: "Chart"
                                },
                                dataSeries: [{
                                        seriesType: "bar",
                                        collectionAlias: "Sales per Day",
                                        data: $.map(data, function (item) {
                                            return item.amount;
                                        })
                                    }]

                            });

                            $("tspan:contains('Demo Version')").hide();

                        },
                        error: function () {
                            alert('error');
                        }
                    });

                    $.ajax({
                        url: 'getChartOrdersAction',
                        dataType: 'json',
                        data: {orders: "week"},
                        cache: false,
                        success: function (data) {

                            $("#ordersChart").shieldChart({
                                theme: "bootstrap",
                                axisX: {
                                    categoricalValues: $.map(data, function (item) {
                                        return item.day;
                                    })
                                },
                                zoomMode: "xy",
                                axisY: {
                                    title: {
                                        text: "Total (Rs.)"
                                    }
                                },
                                seriesSettings: {
                                    bar: {
                                        dataPointText: {
                                            enabled: true
                                        }
                                    }
                                },
                                primaryHeader: {
                                    text: "Chart"
                                },
                                dataSeries: [{
                                        seriesType: "bar",
                                        collectionAlias: "Orders per Day",
                                        data: $.map(data, function (item) {
                                            return item.amount;
                                        })
                                    }]

                            });

                            $("tspan:contains('Demo Version')").hide();

                        },
                        error: function () {
                            alert('error');
                        }
                    });
//======================================================================
                    $.ajax({
                        url: 'getChartExpencesAction',
                        dataType: 'json',
                        data: {expenses: "week"},
                        cache: false,
                        success: function (data) {

                            $("#expensesChart").shieldChart({
                                theme: "bootstrap",
                                axisX: {
                                    categoricalValues: $.map(data, function (item) {
                                        return item.day;
                                    })
                                },
                                zoomMode: "xy",
                                axisY: {
                                    title: {
                                        text: "Total (Rs.)"
                                    }
                                },
                                seriesSettings: {
                                    bar: {
                                        dataPointText: {
                                            enabled: true
                                        }
                                    }
                                },
                                primaryHeader: {
                                    text: "Chart"
                                },
                                dataSeries: [
                                    {
                                        seriesType: "bar",
                                        collectionAlias: "Sales",
                                        data: $.map(data, function (item) {
                                            return item.sale;
                                        })
                                    },
                                    {
                                        seriesType: "bar",
                                        collectionAlias: "Expenses",
                                        data: $.map(data, function (item) {
                                            return item.expense;
                                        })
                                    }

                                ]

                            });

                            $("tspan:contains('Demo Version')").hide();

                        },
                        error: function () {
                            alert('error');
                        }
                    });


                });
                $(document).on("change", "#saleSel", function () {
                    var parameter = {sales: $('#saleSel').val()};
                    chartLoad('getChartSalesAction', $('#saleSel').val(), $('#chatSel').val(), $('#salesChart'), 'Sales', parameter);
                });
                $(document).on("change", "#chatSel", function () {
                    var parameter = {sales: $('#saleSel').val()};
                    chartLoad('getChartSalesAction', $('#saleSel').val(), $('#chatSel').val(), $('#salesChart'), 'Sales', parameter);
                });

                $(document).on("change", "#orderSel", function () {
                    var parameter = {orders: $('#orderSel').val()};

                    chartLoad('getChartOrdersAction', $('#orderSel').val(), $('#chatOrderSel').val(), $('#ordersChart'), 'Orders', parameter);
                });
                $(document).on("change", "#chatOrderSel", function () {
                    var parameter = {orders: $('#orderSel').val()};
                    chartLoad('getChartOrdersAction', $('#orderSel').val(), $('#chatOrderSel').val(), $('#ordersChart'), 'Orders', parameter);
                });

                $(document).on("change", "#expenseSel", function () {
                    var parameter = {expenses: $('#expenseSel').val()};
                    expenseLoad($('#chatExpenseSel').val(), parameter);
                });
                $(document).on("change", "#chatExpenseSel", function () {
                    var parameter = {expenses: $('#expenseSel').val()};
                    expenseLoad($('#chatExpenseSel').val(), parameter);
                });
            </script>
<!--             <script type="text/javascript" src="js/notification-bar-new.js"></script>-->
    </body>
</html>
