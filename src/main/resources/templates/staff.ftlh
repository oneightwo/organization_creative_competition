<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid">
        <div class="d-flex justify-content-center">
            <div class='col-11'>
                <#if users??>
                    <#list users as user>
                        <div class="card mb-3">
                            <h5 class="card-header">
                                Пользователь: ${user.username}
                            </h5>
                            <div class="card-body">
                                <p class="card-text m-0"><strong>Фамилия:</strong> ${user.surname}</p>
                                <p class="card-text m-0"><strong>Имя:</strong> ${user.name}</p>
                                <p class="card-text m-0"><strong>Отчество:</strong> ${user.patronymic}</p>
                                <p class="card-text m-0"><strong>Дата рождения:</strong> ${user.dateOfBirth}</p>
                                <p class="card-text m-0"><strong>Описание:</strong> ${user.description}</p>
                                <p class="card-text m-0"><strong>Роль:</strong> ${user.role}</p>
                            </div>
                            <div class="card-footer">
                                <button class="btn btn-outline-danger" onclick="deleteUser(${user.id})">Удалить</button>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        deleteUser = (id) => {
            console.log('CLICK');
            sendRequest('DELETE', 'http://localhost:8585/administration/users/' + id)
                .then(response => {
                    window.location.replace("http://localhost:8585/administration/staff");
                });
        }
    </script>

</@c.page>