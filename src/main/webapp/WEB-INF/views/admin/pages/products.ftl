<a class="btn btn-default" href="/admin/products/add">
    Добавить товар
</a>
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
        <td>${product.name}</td>
        <td>${product.description}</td>
        <td>${product.price}</td>
        <td>${product.stock}</td>
        <td>
            <a class="btn btn-default" href="/admin/products/edit/${product.id}">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                Редактировать</a>
        </td>
    </tr>
    </#list>
    </tbody>
</table>
<#assign paginationUrl = "/admin/products">
<#include "/frag/nav.ftl">