<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 09.04.2022
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Collection</h1>
    <p class="mb-4">Here is a list of all your saved albums.
        Once you listened to them, don't forget to rate them.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">My to-listen list:</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Artist</th>
                        <th>Album</th>
                        <th>Year of release</th>
                        <th>Genre</th>
                        <th>Rating</th>
                    </tr>
                    </thead>
<%--                    <tfoot>--%>
<%--                    <tr>--%>
<%--                        <th>Name</th>--%>
<%--                        <th>Position</th>--%>
<%--                        <th>Office</th>--%>
<%--                        <th>Age</th>--%>
<%--                        <th>Start date</th>--%>
<%--                        <th>Salary</th>--%>
<%--                    </tr>--%>
<%--                    </tfoot>--%>
                    <tbody>
                    <c:forEach items="${userAlbums}" var="album">
                    <tr>
                        <td>${album.artistName}</td>
                        <td>
                                ${album.albumTitle}
                            <div></div>
                            <form method="get" action="/tracks">
                            <input type="hidden" name="idAlbum" value="${album.idAlbum}">
                            <button type="submit">See tracklist</button>
                            </form>
                        </td>
                        <td>${album.yearOfRelease}</td>
                        <td>${album.genre}</td>
                        <td>${album.rating}</td>
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



<%@include file="../../footer.jsp"%>




