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
<h2>${artistName} discography:</h2>
<table>
    <tr>
        <th>Album</th>
        <th>Year</th>
        <th>Genre</th>
        <th>Art Cover</th>
    </tr>
    <c:forEach items="${albums}" var="album">
        <c:if test="${album.strReleaseFormat == 'Album'}">
            <tr>
                <td>${album.strAlbum}</td>
                <td>${album.intYearReleased}</td>
                <td>${album.strGenre}</td>
                <td><img src="${album.strAlbumThumb}/preview"/></td>
                <td>
                    <form method="get" action="/album/add" id="add">
                        <input type="hidden" name="idArtist" value="${album.idArtist}">
                        <input type="hidden" name="idAlbum" value="${album.idAlbum}">
                        <input type="submit" value="Add">
                    </form>

                    <button type="button" name="tracklist">
                        <a href="/tracks?idAlbum=${album.idAlbum}">See tracklist</a>
                    </button>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
<%--<script src='<spring:url value="/vendor/jquery/jquery.slim.min.js"/>'></script>--%>
<%@ include file="../../footer.jsp" %>
