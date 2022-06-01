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

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h4 class="m-0 font-weight-bold text-primary">Tracklist for ${tracks.get(0).strAlbum} by ${tracks.get(0).strArtist}:</h4>
    </div>
    <div class="card-body">
        <div class="table-responsive">

            <table class="table" id="dataTable" width="100% cellspacing="0">
                <thead class="font-weight-bold text-primary">
                <tr>
                    <th>#</th>
                    <th>Title</th>
                    <th align="center">Add to favorites</th>
                </tr>
                </thead>
            <tbody style="vertical-align: middle; font-size: x-large">
                <c:forEach items="${tracks}" var="track">
                    <tr>
                        <td>${track.intTrackNumber}</td>
                        <td>${track.strTrack}</td>
                        <td align="center">
                            <form method="get" action="/user/add/track-to-favorites">
                                <input type="hidden" name="idTrack" value="${track.idTrack}">
                                <input type="hidden" name="idAlbum" value="${track.idAlbum}">
                                <button type="submit" class="btn btn-google btn-block">
                                    Add
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="../../footer.jsp" %>
