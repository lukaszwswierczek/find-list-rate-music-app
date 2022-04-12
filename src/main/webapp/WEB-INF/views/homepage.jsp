<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 07.04.2022
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../header.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
        <%--        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i--%>
        <%--                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>--%>
    </div>


    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h4 class="m-0 font-weight-bold text-primary">Search here:</h4>
        </div>
        <div class="card-body">
            <form method="get" action="/artist"
                  class="">
                <div class="input-group">
                    <input type="text" name="artistName" class="form-control bg-light border-secondary small"
                           placeholder="Search for artists..."
                           aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="submit">
                            Search
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="card-body">

        <%--    <!-- Content Row -->--%>
        <%--    <div class="row">--%>

        <%--        <!-- Earnings (Monthly) Card Example -->--%>
        <%--        <div class="col-xl-3 col-md-6 mb-4">--%>
        <%--            <div class="card border-left-primary shadow h-100 py-2">--%>
        <%--                <div class="card-body">--%>
        <%--                    <div class="row no-gutters align-items-center">--%>
        <%--                        <div class="col mr-2">--%>
        <%--                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">--%>
        <%--                                Earnings (Monthly)</div>--%>
        <%--                            <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>--%>
        <%--                        </div>--%>
        <%--                        <div class="col-auto">--%>
        <%--                            <i class="fas fa-calendar fa-2x text-gray-300"></i>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <%--        <!-- Earnings (Monthly) Card Example -->--%>
        <%--        <div class="col-xl-3 col-md-6 mb-4">--%>
        <%--            <div class="card border-left-success shadow h-100 py-2">--%>
        <%--                <div class="card-body">--%>
        <%--                    <div class="row no-gutters align-items-center">--%>
        <%--                        <div class="col mr-2">--%>
        <%--                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">--%>
        <%--                                Earnings (Annual)</div>--%>
        <%--                            <div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>--%>
        <%--                        </div>--%>
        <%--                        <div class="col-auto">--%>
        <%--                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>


        <%@ include file="../../footer.jsp" %>
