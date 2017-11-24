/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function clearContentAdmin() {
    $('#blogRows').empty();
}

function loadAdmin() {
    clearContentAdmin();
    var rows = $('#blogRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/VirtualRealityGladiators/blogs',
        success: function (data, status) {
            $.each(data, function (index, blog) {
                var title = blog.title;
                var year = blog.date.year;
                var month = blog.date.monthValue;
                var day = blog.date.dayOfMonth;
                var username = blog.user.userName;
                var summary = blog.summary;
                var approved = blog.approved;
                var blogId = blog.blogId;

                var row = '<tr>';
                row += '<td>' + title + '</td>';
                row += '<td>' + year + '-' + month + '-' + day + '</td>';
                row += '<td>' + username + '</td>';
                row += '<td>' + summary + '</td>';
                row += '<td>' + approved + '</td>';
                row += '<td> <a href="${pageContext.request.contextPath}/displayEditBlog/' + blogId + '">Edit</a></td>';
                row += '<td> <button class="btn btn-danger" onclick="deleteBlog(' + blogId + ')">Delete Post</button></td>';
                row += '</tr>';
                rows.append(row);
            });
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service.  Please try again later.'));
        }
    });
}

function deleteBlog(blogId) {
    if (confirm("Are you sure you want to delete this post?") === true) {
        $.ajax({
            type: 'DELETE',
            url: "http://localhost:8080/VirtualRealityGladiators/deleteBlog/" + blogId,
            success: function (status) {
                loadAdmin();
            }
        });
    } else {

    }
}

function clearContentEditor() {
    $('#pendingRows').empty();
    $('#rejectedRows').empty();
}

function loadEditor() {
    clearContentEditor();
    var pending = $('#pendingRows');
    var rejected = $('rejectedRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/VirtualRealityGladiators/rejectedBlogs',
        success: function (data, status) {
            $.each(data, function (index, blog) {
                var title = blog.title;
                console.log(title);
                var year = blog.date.year;
                var month = blog.date.monthValue;
                var day = blog.date.dayOfMonth;
                console.log(year + ' ' + month + ' ' + day);
                var summary = blog.summary;
                console.log(summary);
                var blogId = blog.blogId;
                console.log(blogId);

                var row = '<tr>';
                row += '<td>' + title + '</td>';
                row += '<td>' + summary + '</td>';
                row += '<td>' + year + '-' + month + '-' + day + '</td>';
                row += '<td><a href="${pageContext.request.contextPath}/displayEditBlog/' + blogId + '">Edit</a></td>';
                row += '<td><button class="btn btn-danger" onclick="deleteBlogEditor(' + blogId + ')">Delete Post</button></td>';
                row += '</tr>';
                console.log(row);
                $('#rejectedRows').append(row);
            });
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service.  Please try again later.'));
        }
    });
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/VirtualRealityGladiators/pendingBlogs',
        success: function (data, status) {
            $.each(data, function (index, blog) {
                var title = blog.title;
                var summary = blog.summary;
                var year = blog.date.year;
                var month = blog.date.monthValue;
                var day = blog.date.dayOfMonth;
                var blogId = blog.blogId;

                var row = '<tr>';
                row += '<td>' + title + '</td>';
                row += '<td>' + summary + '</td>';
                row += '<td>' + year + '-' + month + '-' + day + '</td>';
                row += '</tr>';
                pending.append(row);
            });
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service.  Please try again later.'));
        }
    });
}

function deleteBlogEditor(blogId) {
    if (confirm("Are you sure you want to delete this post?") === true) {
        $.ajax({
            type: 'DELETE',
            url: "http://localhost:8080/VirtualRealityGladiators/deleteBlog/" + blogId,
            success: function (status) {
                loadEditor();
            }
        });
    } else {

    }
}

