<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 08.04.2022
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../header.jsp" %>
<table>
    <tr>
        <th></th>
        <th></th>
        <th>Add to favorites</th>
    </tr>
    <c:forEach items="${tracks}" var="track">
        <tr>
            <td>${track.intTrackNumber}</td>
            <td>${track.strTrack}</td>
            <td>
                <form method="get" action="/add/track-to-favorites">
                    <input type="hidden" name="idTrack" value="${track.idTrack}">
                    <input type="hidden" name="idAlbum" value="${track.idAlbum}">
                    <button type="submit" class="btn-success border-0 btn-circle">

                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="../../footer.jsp" %>
