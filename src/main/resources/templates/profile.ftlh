<#import "containers/common.ftlh" as c>
<@c.page>
    <#include "component/navbar.ftlh">

    <div class="container-fluid">
        <div class="d-flex justify-content-center">
            <div class='col-11'>
                <div class="card mb-3">
                    <h5 class="card-header">Профиль</h5>
                    <div class="card-body">
                        <p class="card-text m-0"><strong>Имя пользователя:</strong> ${user.username}</p>
                        <p class="card-text m-0"><strong>Фамилия:</strong> ${user.surname}</p>
                        <p class="card-text m-0"><strong>Имя:</strong> ${user.name}</p>
                        <p class="card-text m-0"><strong>Отчество:</strong> ${user.patronymic}</p>
                        <p class="card-text m-0"><strong>Дата рождения:</strong> ${user.dateOfBirth}</p>
                    </div>
                </div>

                <#if user.participants??>
                    <#list user.participants as participant>
                        <div class="card mb-3">
                            <h5 class="card-header">Конкурс: ${participant.competition.name}
                                <#if participant.isWinner??>
                                    <#if participant.isWinner>
                                        <div class="badge badge-success">Победитель</div>
                                    <#else>
                                        <div class="badge badge-danger">Проигравший</div>
                                    </#if>
                                </#if>
                            </h5>
                            <div class="card-body">
                                <p class="card-text m-0"><strong>Описание:</strong> ${participant.competition.description}
                                </p>
                                <p class="card-text m-0"><strong>Приз:</strong> ${participant.competition.prize}</p>
                                <p class="card-text m-0"><strong>Дата начала
                                        регистрации:</strong> ${participant.competition.startDate}</p>
                                <p class="card-text m-0"><strong>Дата окончания
                                        регистрации:</strong> ${participant.competition.endDate}</p>
                                <p class="card-text m-0"><strong>Сообщение:</strong> ${participant.message}</p>
                            </div>
                            <#if participant.competition.isActive>
                                <div class="card-footer">
                                    <button class="btn btn-outline-warning"
                                            onclick="deleteParticipant(${participant.id})">
                                        Отказаться от участия
                                    </button>
                                </div>
                            </#if>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        deleteParticipant = (id) => {
            console.log('CLICK');
            sendRequest('DELETE', 'http://localhost:8585/competitions/participants/' + id)
                .then(response => {
                    window.location.replace("http://localhost:8585/users/profile");
                });
        }
    </script>

</@c.page>