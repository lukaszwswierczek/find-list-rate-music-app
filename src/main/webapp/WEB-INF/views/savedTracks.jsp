<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 10.04.2022
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../../header.jsp" %>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Collection</h1>
    <p class="mb-4">Here is a list of all your favorite tracks.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3 bg-gradient-danger">
            <h4 class="m-0 font-weight-bold text-gray-900">Favorite tracks:</h4>
            <h6 class="text-gray-900">Total tracks: ${total}.</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table" id="dataTable" width="100%" cellspacing="0">
                    <thead style="background: #f8f9fc" class="font-weight-bold text-gray-900">
                    <tr>
                        <th>Title</th>
                        <th>Album</th>
                        <th>Artist</th>
                        <th>Genre</th>
                        <th>Duration</th>
                        <th>Rating</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody class="text-gray-900 table tr:nth-child(even)">
                    <c:forEach items="${userTracks}" var="track">
                    <tr>
                        <td>${track.title}</td>
                        <td>${track.album}</td>
                        <td>${track.artist}</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty track.genre}">
                                    [no data]
                                </c:when>
                                <c:otherwise>
                                    ${track.genre}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${track.duration}</td>
                        <td>
                                <c:if test="${track.rating.rating == 0}">
                                    <h6 class="alert-danger font-weight-bold text-gray-900">Rate this track</h6>
                                </c:if>
                                <c:if test="${track.rating.rating != 0}">
                                    <h6 class="alert-success font-weight-bold text-gray-900">Your rate: ${track.rating.rating}</h6>
                                </c:if>
                            <%@include file="rating.jsp" %>
                        </td>
                        <td>
                            <form action="/track/delete" method="get">
                                <input type="hidden" name="idTrack" value="${track.idTrack}">
                                <button class="btn btn-google btn-block" type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<%@include file="../../footer.jsp" %>
