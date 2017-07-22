<table class="table table-hover">
    <thead>
        <tr>
            <th>Имя</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Наличие</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <#list products as product>
            <tr>
                <td class="product-name">${product.name}</td>
                <td class="product-description">${product.description}</td>
                <td class="product-price">${product.price}</td>
                <td class="product-stock">${product.stock}</td>
                <td>
                    <#if product.stock gt 0>
                        <button data-toggle="modal" data-target="#buyModal" data-id="${product.id}" data-name="${product.name}" data-description="${product.description}" data-price="${product.price}" data-stock="${product.stock}" class="btn btn-primary">Купить</button>
                    <#else>
                        <button class="btn btn-primary" disabled="disabled">Нет в наличии</button>
                    </#if>
                </td>
            </tr>
        <#else>
            <tr>
                <td>Нет данных</td>
            </tr>
        </#list>
    </tbody>
</table>
<#assign paginationUrl = "/products">
<#include "/frag/nav.ftl">

<div class="modal fade" id="buyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="buyForm" action="/products/buy/" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Купить товар</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="buyName">Название товара</label>
                        <input type="text" class="form-control" disabled="disabled" id="buyName"/>
                    </div>
                    <div class="form-group">
                        <label for="buyDescription">Описание</label>
                        <input type="text" class="form-control" disabled="disabled" id="buyDescription"/>
                    </div>
                    <div class="form-group">
                        <label for="buyPrice">Цена</label>
                        <input type="text" class="form-control" disabled="disabled" id="buyPrice"/>
                    </div>
                    <div class="form-group">
                        <label for="buyStock">Кол-во</label>
                        <input type="text" class="form-control" name="buyStock" id="buyStock"/>
                    </div>
                    <input type="hidden" id="buyId" name="productId"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div id="modalError" class="alert alert-danger" role="alert"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                    <button type="submit" class="btn btn-primary">Купить</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $("#buyForm").ajaxForm({dataType: "json", success: function (data) {
        if (data.success) {
            window.location.href = "/purchases";
        } else {
            $("#modalError").html(data.message).show();
        }
    }, error: function(){
        $("#formError").html("Произошла ошибка на сервере!").show();
    }});
    $('#buyModal').on('show.bs.modal', function (event) {
        var modal = $(this);
        var button = $(event.relatedTarget);

        modal.find('#modalError').hide();
        modal.find('#buyForm').attr("action", "/products/buy/" + button.attr("data-id"));
        modal.find('#buyName').val(button.attr("data-name"));
        modal.find('#buyDescription').val(button.attr("data-description"));
        modal.find('#buyPrice').val(button.attr("data-price"));
        modal.find('#buyStock').val(button.attr("data-stock"));
        modal.find('#buyId').val(button.attr("data-id"));
    })
</script>