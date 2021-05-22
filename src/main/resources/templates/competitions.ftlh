<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid">
        <div class="d-flex justify-content-center">
            <div class='col-11'>
                <#list competitions as competition>
                    <div class="card mb-3">
                        <h5 class="card-header">
                            Конкурс: ${competition.name}
                        </h5>
                        <div class="card-body">
                            <p class="card-text m-0"><strong>Описание:</strong> ${competition.description}</p>
                            <p class="card-text m-0"><strong>Приз:</strong> ${competition.prize}</p>
                            <p class="card-text m-0"><strong>Дата начала регистрации:</strong> ${competition.startDate}</p>
                            <p class="card-text m-0"><strong>Дата окончания регистрации:</strong> ${competition.endDate}</p>
                            <#if competition.isActive>
                                <p class="card-text m-0"><strong>Регистрация активна:</strong> <span class="badge badge-success">Да</span></p>
                            <#else>
                                <p class="card-text m-0"><strong>Регистрация активна:</strong> <span class="badge badge-danger">Нет</span></p>
                            </#if>
                        </div>
                        <div class="card-footer">
                            <a href="/competitions/${competition.id}/participants" class="btn btn-primary">Принять участие</a>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>

</@c.page>