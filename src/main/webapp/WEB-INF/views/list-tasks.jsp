<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <table class="table table-striped">
        <caption>Your Tasks are</caption>
        <thead>
        <tr>
            <th>Description</th>
            <th>Date</th>
            <th>Completed</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tasks}" var="task">
            <tr>
                <td>${task.desc}</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy"
                                    value="${task.targetDate}"/></td>
                <td>${task.done}</td>
                <td><a type="button" class="btn btn-primary"
                       href="/update-task?id=${task.id}">Edit</a> <a type="button"
                                                                     class="btn btn-warning"
                                                                     href="/delete-task?id=${task.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a type="button" class="btn btn-success" href="/add-task">Add</a>
    </div>
</div>
<%@ include file="common/footer.jspf" %>