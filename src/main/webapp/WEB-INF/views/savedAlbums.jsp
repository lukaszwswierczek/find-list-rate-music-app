<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div class="card-header py-3 bg-gradient-danger">
            <h4 class="m-0 font-weight-bold text-gray-900">My to-listen list:</h4>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table style="text-align: center" class="text-gray-900 table text-lg" id="dataTable" width="100%"
                       cellspacing="0">
                    <thead style="background: #f8f9fc" class="font-weight-bold text-gray-900">
                    <tr>
                        <th></th>
                        <th>Album</th>
                        <th>Artist</th>
                        <th>Year of release</th>
                        <th>Genre</th>
                        <th>Rating</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody style="vertical-align: middle; font-size: x-large" class="table tr:nth-child(even)">
                    <c:forEach items="${userAlbums}" var="album">
                        <tr>
                            <td rowspan="2"><img style="border-radius: 8px; height: 7em" class="border-left-danger" src="${album.albumCover}/preview"></td>
                            <td>${album.album}</td>
                            <td><a style="color: #fb3f00; text-decoration: none" href="/artist?artistName=${album.artist}">${album.artist}</a></td>
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
                                <form action="/user/album/delete" method="get">
                                    <input type="hidden" name="idAlbum" value="${album.idAlbum}">
                                    <button class="btn btn-google btn-block" type="submit">Delete</button>
                                </form>

                                <!-- Nav Item - Messages -->

                                <a id="messagesDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <button class="btn btn-facebook btn-block">Edit note</button>
                                </a>

                                <!-- Dropdown - Messages -->
                                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="messagesDropdown">
                                    <h6 class="dropdown-header">
                                        Space for your notes
                                    </h6>
                                    <a class="dropdown-item d-flex align-items-center">
                                        <div class="dropdown-list-image mr-3">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
                                                <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z"/>
                                            </svg>
                                            <div class="status-indicator bg-success"></div>
                                        </div>
                                        <div class="font-weight-bold">
                                            <div class="text-truncate">
                                                <form:form method="post" action="/user/albums/saved/editNote"
                                                           modelAttribute="updateNote">
                                                    <form:hidden path="idAlbum" value="${album.idAlbum}"/>
                                                    <form:textarea path="description"
                                                                   placeholder="${album.note.description}"/>
                                                    <input type="submit">
                                                </form:form>
                                            </div>
                                            <div class="topbar-divider d-none d-sm-block"></div>

                            </td>
                        </tr>
                        <tr>
                            <td style="font-size: medium; height: 3em" colspan="6">
                                <c:out value="${album.note.description}"/>
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




