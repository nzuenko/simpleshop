<h1>Редактировать товар</h1>
<div class="col-xs-4">
    <form id="editForm" method="post" action="/admin/products/edit/${product.id}">
        <div class="form-group">
            <label for="nameField">Название</label>
            <input type="text" class="form-control" id="nameField" name="name" value="${product.name}" placeholder="Название">
        </div>
        <div class="form-group">
            <label for="descriptionField">Описание</label>
            <input type="text" class="form-control" id="descriptionField" name="description" value="${product.description}" placeholder="Описание">
        </div>
        <div class="form-group">
            <label for="priceField">Цена</label>
            <input type="text" class="form-control" id="priceField" name="price" value="${product.price?c}" placeholder="Цена">
        </div>
        <div class="form-group">
            <label for="stockField">Кол-во</label>
            <input type="text" class="form-control" id="stockField" name="stock" value="${product.stock?c}" placeholder="Кол-во">
        </div>
        <div id="formError" hidden="hidden" class="alert alert-danger" role="alert"></div>

        <button type="submit" name="action" value="save" class="btn btn-default">Сохранить</button>
        <button type="submit" name="action" value="delete" class="btn btn-default">Удалить</button>
        <a class="btn btn-default" href="/admin/products">Назад</a>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="id" value="${product.id}"/>
    </form>
</div>
<script>
    $("#editForm").ajaxForm({dataType: "json", success: function (data) {
        if (data.success) {
            window.location.href = "/admin/products";
        } else {
            $("#formError").html(data.message).show();
        }
    }, error: function(){
        $("#formError").html("Произошла ошибка на сервере!").show();
    }});
</script>