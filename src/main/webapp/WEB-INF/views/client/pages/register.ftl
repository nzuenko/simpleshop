<div class="col-xs-4 col-md-offset-4">
    <h1>Регистрация</h1>
    <form id="registerForm" method="post" action="/register">
        <div class="form-group">
            <label for="usernameField">Логин</label>
            <input type="text" class="form-control" id="usernameField" name="login" placeholder="Логин">
        </div>
        <div class="form-group">
            <label for="passwordField">Пароль</label>
            <input type="password" class="form-control" id="passwordField" name="password" placeholder="Пароль">
        </div>

        <div class="form-group">
            <label for="passwordFieldRepeat">Пароль</label>
            <input type="password" class="form-control" id="passwordFieldRepeat" name="passwordRepeat" placeholder="Повтор пароля">
        </div>

        <div id="formError" hidden="hidden"  class="alert alert-danger" role="alert">Ошибка авторизации: Неверные логин или пароль.</div>

        <button type="submit" class="btn btn-default">Регистрация</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
<script>
    $("#registerForm").ajaxForm({dataType: "json", success: function (data) {
        if (data.success) {
            window.location.href = "/login";
        } else {
            $("#formError").html(data.message).show();
        }
    }});
</script>