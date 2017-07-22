<#ftl encoding='UTF-8'>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <nav class="navbar navbar-inverse navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/admin/">Администратор</a>
                </div>
                <@security.authorize access="isAuthenticated()">
                    <ul class="nav navbar-nav">
                        <li <#if page=="products">class="active"</#if>><a href="/admin/products">Товары</a></li>
                        <li <#if page=="purchases">class="active"</#if>><a href="/admin/purchases">Покупки</a></li>
                    </ul>
                </@security.authorize>
                <ul class="nav navbar-nav navbar-right">
                <@security.authorize access="!isAuthenticated()">
                    <li><a href="/login">Вход</a></li>
                </@security.authorize>
                <@security.authorize access="isAuthenticated()">
                    <li>
                        <form action="/logout" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class="btn btn-link navbar-btn" type="submit">Выход</button>
                        </form>
                    </li>
                </@security.authorize>
                </ul>
            </div>
        </nav>

        <div id="content">
        <#include "/admin/pages/${page}.ftl">
        </div>
    </div>

</body>
</html>