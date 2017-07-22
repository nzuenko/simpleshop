<#if totalPages gt 1>
    <nav>
        <ul class="pagination">
            <#list 1..totalPages as p>
                <#if p == pageIndex>
                    <li class="active"><a href="${paginationUrl}?page=${p}">${p}</a></li>
                <#else>
                    <li><a href="${paginationUrl}?page=${p}">${p}</a></li>
                </#if>
            </#list>
        </ul>
    </nav>
</#if>