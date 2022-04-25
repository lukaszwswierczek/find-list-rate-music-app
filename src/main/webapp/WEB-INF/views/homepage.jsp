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
        <h1 class="h3 mb-0 text-gray-800">Your homepage</h1>
    </div>

    <div class="card shadow mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-s font-weight-bold text-primary text-uppercase mb-1 align-content-center">
                            <sec:authorize access="isAuthenticated()">
                                <p class="text-danger">You are logged in as: <sec:authentication
                                        property="principal.username"/>.</>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <a class="text-danger" href="/login">Log in to create your lists.</a>
                            </sec:authorize>
                        </div>
                    </div>
                    <div class="col-auto">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-people-fill" viewBox="0 0 16 16">
                            <path d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H7zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                            <path fill-rule="evenodd"
                                  d="M5.216 14A2.238 2.238 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.325 6.325 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1h4.216z"/>
                            <path d="M4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/>
                        </svg>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card shadow mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-s font-weight-bold text-primary text-uppercase mb-1 align-content-center">
                            <sec:authorize access="isAuthenticated()">
                                <c:if test="${not empty userAlbums}">
                                    Last added albums:
                                    <hr>
                                    <c:forEach items="${userAlbums}" var="album" end="7">
                                        <img src="${album.albumCover}/preview" alt="albumCover" class="border-left-danger" style="border-radius: 8px; height: 7em"/>
                                    </c:forEach>
                                </c:if>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                Your favorite albums will be here.
                            </sec:authorize>
                        </div>
                    </div>
                    <div class="col-auto">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-easel" viewBox="0 0 16 16">
                            <path d="M8 0a.5.5 0 0 1 .473.337L9.046 2H14a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1h-1.85l1.323 3.837a.5.5 0 1 1-.946.326L11.092 11H8.5v3a.5.5 0 0 1-1 0v-3H4.908l-1.435 4.163a.5.5 0 1 1-.946-.326L3.85 11H2a1 1 0 0 1-1-1V3a1 1 0 0 1 1-1h4.954L7.527.337A.5.5 0 0 1 8 0zM2 3v7h12V3H2z"/>
                        </svg>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card shadow mb-4 ">
        <div class="card-header py-3 bg-gradient-danger">
            <h4 class="m-0 font-weight-bold text-black-50">Search here:</h4>
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

        <%@ include file="../../footer.jsp" %>
