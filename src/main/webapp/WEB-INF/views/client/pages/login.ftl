<div class="col-xs-4 col-md-offset-4">
    <h1>Вход</h1>
    <form method="post" action="/login">
        <div class="form-group">
            <label for="usernameField">Логин</label>
            <input type="text" class="form-control" id="usernameField" name="username" placeholder="Логин">
        </div>
        <div class="form-group">
            <label for="passwordField">Пароль</label>
            <input type="password" class="form-control" id="passwordField" name="password" placeholder="Пароль">
        </div>

        <#if RequestParameters.error??>
            <div class="alert alert-danger" role="alert">Ошибка авторизации: Неверные логин или пароль.</div>
        </#if>

        <button type="submit" class="btn btn-default">Вход</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>