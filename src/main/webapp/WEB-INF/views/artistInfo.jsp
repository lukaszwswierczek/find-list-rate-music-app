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

    <!-- Illustrations -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Artist info:</h6>
        </div>
        <div class="card-body">
            <div>
                <table>
                    <tr>
                        <td><img src="${artist.strArtistThumb}/preview" class="img-fluid px-3 px-sm-4 mt-3 mb-4"/></td>
                        <td>
                            <h1>${artist.strArtist}</h1>
                            <h2>${artist.strGenre}</h2>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

    </div>
    <div class="justify-content-center">

    <div class="card-body">
            ${artist.strBiographyEN}
    </div>

    <div class="dropdown-divider"></div>
<%--    przekierowanie do listy albumow--%>

    <div class="card-body">
        <form method="get" action="/albums">
            <input type="hidden" name="idArtist" value="${artist.idArtist}"/>
            <button class="btn btn-primary btn-icon-split btn-lg" type="submit">
            <span class="icon text-white-50">
                <i class="fas fa-flag"></i>

            </span>
                <span class="text">Find ${artist.strArtist}'s albums</span>
            </button>
        </form>
    </div>

</c:forEach>

<%@ include file="../../footer.jsp" %>

