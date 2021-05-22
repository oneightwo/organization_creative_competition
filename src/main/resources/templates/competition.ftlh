<#import "containers/common.ftlh" as c>
<@c.page>
    <#include "component/navbar.ftlh">

    <div class="container-fluid">
        <div class="d-flex justify-content-center">
            <div class='col-12'>
                <#if competition??>
                    <div class="card mb-3">
                        <h5 class="card-header">
                            Конкурс: ${competition.name}
                        </h5>
                        <div class="card-body">
                            <p class="card-text m-0">
                                <strong>Описание:</strong> ${competition.description}
                            </p>
                            <p class="card-text m-0"><strong>Приз:</strong> ${competition.prize}</p>
                            <p class="card-text m-0"><strong>Дата начала
                                    регистрации:</strong> ${competition.startDate}</p>
                            <p class="card-text m-0"><strong>Дата окончания
                                    регистрации:</strong> ${competition.endDate}</p>
                            <#if competition.isActive>
                                <p><strong>Регистрация активна:</strong> <span class="badge badge-success">Да</span></p>
                            <#else>
                                <p><strong>Регистрация активна:</strong> <span class="badge badge-danger">Нет</span></p>
                            </#if>
                        </div>
                        <div class="card-footer">
                            <a class="btn btn-info" href="/administration/reports/${competition.id}">Получить отчет</a>
                        </div>
                    </div>
                </#if>
                <div class="card">
                    <h5 class="card-header">
                        Участники
                    </h5>
                </div>
                <#if participants??>
                    <#list participants as participant>
                        <div class="card">
                            <div class="card-body p-0">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-4 border border-secondary">
                                            <#if participant.isWinner??>
                                                <#if participant.isWinner>
                                                    <div class="badge badge-success">Победитель</div>
                                                <#else>
                                                    <div class="badge badge-danger">Проигравший</div>
                                                </#if>
                                            </#if>
                                            <p class="card-text m-0"><strong>Имя
                                                    пользователя:</strong> ${participant.user.username}
                                            </p>
                                            <p class="card-text m-0">
                                                <strong>Фамилия:</strong> ${participant.user.surname}
                                            </p>
                                            <p class="card-text m-0"><strong>Имя:</strong> ${participant.user.name}</p>
                                            <p class="card-text m-0">
                                                <strong>Отчество:</strong> ${participant.user.patronymic}</p>
                                            <p class="card-text m-0"><strong>Дата
                                                    рождения:</strong> ${participant.user.dateOfBirth}
                                            </p>
                                        </div>
                                        <div class="col-7 border border-secondary">
                                            <p class="card-text m-0"><strong>Сообщение:</strong> ${participant.message}
                                            </p>
                                        </div>
                                        <#if participant.competition.isActive>
                                            <div class="col-1 p-0 m-0">
                                                <div class="d-flex flex-column">
                                                    <button class="d-flex align-items-stretch btn btn-outline-success"
                                                            onclick="markAsWinner(${participant.id})">
                                                        Победитель
                                                    </button>
                                                    <button class="d-flex align-items-stretch btn btn-outline-warning"
                                                            onclick="markAsNotWinner(${participant.id})">
                                                        Отменить
                                                    </button>
                                                    <button class="d-flex align-items-stretch btn btn-outline-danger"
                                                            onclick="deleteParticipant(${participant.id})">
                                                        Удалить
                                                    </button>
                                                </div>
                                            </div>
                                        <#else>
                                            <div class="col-1 p-0 m-0">
                                                <div class="d-flex flex-column">
                                                    <button class="d-flex align-items-stretch btn btn-outline-success" disabled>
                                                        Победитель
                                                    </button>
                                                    <button class="d-flex align-items-stretch btn btn-outline-warning" disabled>
                                                        Отменить
                                                    </button>
                                                    <button class="d-flex align-items-stretch btn btn-outline-danger" disabled>
                                                        Удалить
                                                    </button>
                                                </div>
                                            </div>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        markAsWinner = (id) => {
            sendRequest('GET', 'http://localhost:8585/administration/competitions/participants/' + id + '/markAsWinner')
                .then(response => {
                    window.location.replace("http://localhost:8585/administration/competitions/${competition.id}/participants");
                });
        }
        markAsNotWinner = (id) => {
            sendRequest('GET', 'http://localhost:8585/administration/competitions/participants/' + id + '/markAsNotWinner')
                .then(response => {
                    window.location.replace("http://localhost:8585/administration/competitions/${competition.id}/participants");
                });
        }
        deleteParticipant = (id) => {
            console.log('CLICK');
            sendRequest('DELETE', 'http://localhost:8585/competitions/participants/' + id)
                .then(response => {
                    window.location.replace("http://localhost:8585/administration/competitions/${competition.id}/participants");
                });
        }
    </script>

</@c.page>