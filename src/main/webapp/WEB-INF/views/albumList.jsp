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
    <div class="card-header py-3 bg-gradient-danger">
        <h4 class="m-0 font-weight-bold text-gray-900">${artistName} discography:</h4>
    </div>


    <div class="card-body">
        <table style="text-align: center" class="text-gray-900 table text-lg">
            <tr class="font-weight-bold" align="center">
                <th>Cover art</th>
                <th>Album</th>
                <th>Year</th>
                <th>Genre</th>
                <th></th>
            </tr>
            <tfoot>
            <tr>
                <th>Album</th>
                <th>Year</th>
                <th>Genre</th>
                <th>Cover art</th>
                <th></th>
            </tr>
            </tfoot>
            <tbody style="vertical-align: middle; font-size: x-large">
            <c:forEach items="${albums}" var="album">
                <c:if test="${album.strReleaseFormat == 'Album'}">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${empty album.strAlbumThumb}">
                                    [no cover art data]
                                </c:when>
                                <c:otherwise>
                                    <img style="border-radius: 8px" class="border-left-danger" src="${album.strAlbumThumb}/preview"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${album.strAlbum}</td>
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
                        <td class="text-center">
                            <form method="get" action="/user/album/add" id="add">
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
            </tbody>
        </table>
    </div>
    <%--<script src='<spring:url value="/vendor/jquery/jquery.slim.min.js"/>'></script>--%>
    <%@ include file="../../footer.jsp" %>
