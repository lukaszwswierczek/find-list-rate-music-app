<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 07.04.2022
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../../header.jsp" %>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h4 class="m-0 font-weight-bold text-primary">${artistName} discography:</h4>
    </div>


    <div class="card-body">
        <table class="table">
            <tr class="font-weight-bold text-primary" align="center">
                <th>Album</th>
                <th>Year</th>
                <th>Genre</th>
                <th>Cover art</th>
                <th></th>
            </tr>
            <tfoot>
            <tr class="font-weight-bold text-primary">
                <th>Album</th>
                <th>Year</th>
                <th>Genre</th>
                <th>Cover art</th>
                <th></th>
            </tr>
            </tfoot>
            <c:forEach items="${albums}" var="album">
                <c:if test="${album.strReleaseFormat == 'Album'}">
                    <tr>
                        <td class="font-weight-bold text-primary">${album.strAlbum}</td>
                        <td>
                            <c:choose>
                                <c:when test="${album.intYearReleased == 0}">
                                    [no data]
                                </c:when>
                                <c:otherwise>
                                    ${album.intYearReleased}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${album.strGenre}</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty album.strAlbumThumb}">
                                    [no cover art data]
                                </c:when>
                                <c:otherwise>
                                    <img src="${album.strAlbumThumb}/preview"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <form method="get" action="/album/add" id="add">
                                <input type="hidden" name="idArtist" value="${album.idArtist}">
                                <input type="hidden" name="idAlbum" value="${album.idAlbum}">
                                <button class="btn btn-google btn-block" type="submit">Add
                                </button>
                            </form>
                            <form action="/tracks" method="get">
                                <input hidden name="idAlbum" value="${album.idAlbum}"/>
                                <button class="btn btn-facebook btn-block" type="submit" name="tracklist">
                                    Tracklist
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
    <%--<script src='<spring:url value="/vendor/jquery/jquery.slim.min.js"/>'></script>--%>
    <%@ include file="../../footer.jsp" %>
