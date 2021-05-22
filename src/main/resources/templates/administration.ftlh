<#import "containers/common.ftlh" as c>
<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-12 p-0 m-0'>
                <#list competitions as competition>
                    <div class="card mb-3">
                        <div class="card-body p-0" onclick="window.location.replace('http://localhost:8585/administration/competitions/${competition.id}/participants')">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-2 border border-secondary">
                                        <p class="card-text text-center m-0"><strong>Название:</strong></p>
                                        <p class="card-text text-center text-truncate"> ${competition.name}</p>
                                    </div>
                                    <div class="col-4 border border-secondary">
                                        <p class="card-text text-center m-0"><strong>Описание:</strong></p>
                                        <p class="card-text text-center text-truncate">${competition.description}</p>
                                    </div>
                                    <div class="col-1 border border-secondary">
                                        <p class="card-text text-center m-0"><strong>Активна:</strong></p>
                                        <#if competition.isActive>
                                            <p class="card-text text-center"><span class="badge badge-success">Да</span>
                                            </p>
                                        <#else>
                                            <p class="card-text text-center"><span class="badge badge-danger">Нет</span>
                                            </p>
                                        </#if>
                                    </div>
                                    <div class="col-2 border border-secondary">
                                        <p class="card-text text-center m-0"><strong>Начало регистрации:</strong></p>
                                        <p class="card-text text-center text-truncate">${competition.startDate}</p>
                                    </div>
                                    <div class="col-2 border border-secondary">
                                        <p class="card-text text-center m-0"><strong>Конец регистрации:</strong></p>
                                        <p class="card-text text-center text-truncate">${competition.endDate}</p>
                                    </div>
                                    <div class="col-1 text-center border border-secondary bg-primary">
                                        <a class="card-text text-center text-white"
                                           href="administration/competitions/edit/${competition.id}">Изменить</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>

</@c.page>