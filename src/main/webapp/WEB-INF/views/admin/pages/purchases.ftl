<table class="table table-hover">
    <thead>
    <tr>
        <th>Имя</th>
        <th>Дата покупки</th>
        <th>Цена покупки</th>
        <th>Количество</th>
        <th>Пользователь</th>
    </tr>
    </thead>
    <tbody>
    <#list purchases as purchase>
    <tr>
        <td class="purchase-product">
        ${(purchase.product.name)!"Неизвестный товар"}
        </td>
        <td class="purchase-date">${purchase.purchaseDate}</td>
        <td class="purchase-price">${purchase.price}</td>
        <td class="purchase-count">${purchase.count}</td>
        <td class="purchase-client">${(purchase.client.login)!"Неизвестный пользователь"}</td>
    </tr>
    <#else>
    <tr>
        <td>Нет данных</td>
    </tr>
    </#list>
    </tbody>
</table>
<#assign paginationUrl = "/admin/purchases">
<#include "/frag/nav.ftl">
