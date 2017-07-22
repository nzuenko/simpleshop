<h1>Добавить новый товар</h1>
<div class="col-xs-4">
    <form id="addForm" method="post" action="/admin/products/add">
        <div class="form-group">
            <label for="nameField">Название</label>
            <input type="text" class="form-control" id="nameField" name="name" placeholder="Название">
        </div>
        <div class="form-group">
            <label for="descriptionField">Описание</label>
            <input type="text" class="form-control" id="descriptionField" name="description" placeholder="Описание">
        </div>
        <div class="form-group">
            <label for="priceField">Цена</label>
            <input type="text" class="form-control" id="priceField" name="price" placeholder="Цена">
        </div>
        <div class="form-group">
            <label for="stockField">Кол-во</label>
            <input type="text" class="form-control" id="stockField" name="stock" placeholder="Кол-во">
        </div>
        <div id="formError" hidden="hidden" class="alert alert-danger" role="alert"></div>

        <button type="submit" class="btn btn-success">Добавить</button>
        <a class="btn btn-default" href="/admin/products">Назад</a>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    </form>
</div>
<script>
    $("#addForm").ajaxForm({dataType: "json", success: function (data) {
        if (data.success) {
            window.location.href = "/admin/products";
        } else {
            $("#formError").html(data.message).show();
        }
    }, error: function(){
        $("#formError").html("Произошла ошибка на сервере!").show();
    }});
</script>