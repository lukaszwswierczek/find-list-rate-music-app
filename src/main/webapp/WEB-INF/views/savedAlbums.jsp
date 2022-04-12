<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 09.04.2022
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../header.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Collection</h1>
    <p class="mb-4">Here is a list of all your saved albums.
        Once you have listened to them, don't forget to rate them.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h4 class="m-0 font-weight-bold text-primary">My to-listen list:</h4>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table" id="dataTable" width="100%" cellspacing="0">
                    <thead class="font-weight-bold text-primary">
                    <tr>
                        <th>Track</th>
                        <th>Album</th>
                        <th>Year of release</th>
                        <th>Genre</th>
                        <th>Rating</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userAlbums}" var="album">
                        <tr>
                            <td>${album.artist}</td>
                            <td>${album.album}</td>
                            <td>${album.yearOfRelease}</td>
                            <td>
                                <c:choose>
                                <c:when test="${empty album.genre}">
                                    [no data]
                                </c:when>
                                <c:otherwise>
                                    ${album.genre}
                                </c:otherwise>
                            </c:choose>
                            </td>
                            <td>${album.rating}</td>
                            <td>
                                <form method="get" action="/tracks">
                                    <input type="hidden" name="idAlbum" value="${album.idAlbum}">
                                    <button class="btn btn-facebook btn-block" type="submit">Tracklist</button>
                                </form>
                                <form action="/album/delete" method="get">
                                    <input type="hidden" name="idAlbum" value="${album.idAlbum}">
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
<!-- /.container-fluid -->

<%--</div>--%>
<%--<!-- End of Main Content -->--%>


<%@include file="../../footer.jsp" %>




