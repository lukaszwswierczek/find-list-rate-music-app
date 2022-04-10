<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 08.04.2022
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../header.jsp" %>

<c:forEach items="${artistList}" var="artist">
    <table>
        <tr>
            <td><img src="${artist.strArtistThumb}/preview"/></td>
            <td><h1>${artist.strArtist}</h1></td>
        </tr>
    </table>

    <table>
        <tr>
            <th>Artist</th>
            <th>Genre</th>
            <th>Biography</th>

        </tr>

        <tr>
            <td>${artist.strArtist}</td>
            <td>${artist.strGenre}</td>
            <td>${artist.strBiographyEN}</td>

        </tr>

    </table>

<%--    przekierowanie do listy albumow--%>
        <form method="get" action="/albums">
            <input type="hidden" name="idArtist" value="${artist.idArtist}"/>
            <button class="btn btn-primary btn-icon-split btn-lg" type="submit">
            <span class="icon text-white-50">
                <i class="fas fa-flag"></i>
            </span>
                <span class="text">Find ${artist.strArtist}'s albums</span>
            </button>
        </form>

</c:forEach>

<%@ include file="../../footer.jsp" %>

