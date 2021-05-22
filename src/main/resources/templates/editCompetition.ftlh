<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-12 p-0 m-0'>
                <div class="card">
                    <h5 class="card-header">Редактировать</h5>
                    <div class="card-body">
                        <form id="competitionEdit" class="needs-validation" method="post">
                            <div class="form-group">
                                <label for="name">Название</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       value="${competition.name}" required>
                            </div>
                            <div class="form-group">
                                <label for="description">Описание</label>
                                <textarea class="form-control" id="description" name="description"
                                          rows="6" required>${competition.description}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="prize">Приз</label>
                                <input type="text" class="form-control" id="prize" name="prize"
                                       value="${competition.prize}" required>
                            </div>
                            <div class="form-group">
                                <label for="startDate">Дата начала регистрации</label>
                                <input type="datetime-local" class="form-control" id="startDate" name="startDate"
                                       value="${competition.startDate}" required>
                            </div>
                            <div class="form-group">
                                <label for="endDate">Дата окончания регистрации</label>
                                <input type="datetime-local" class="form-control" id="endDate" name="endDate"
                                       value="${competition.endDate}" required>
                            </div>
                            <div class="form-group">
                                <label for="isActive">Активна</label>
                                <select class="form-control" id="isActive" name="isActive">
                                    <#if competition.isActive>
                                        <option value="true" selected>Да</option>
                                        <option value="false">Нет</option>
                                    <#else>
                                        <option value="true">Да</option>
                                        <option value="false" selected>Нет</option>
                                    </#if>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="competitionEdit" type="submit" class="btn btn-primary">Сохранить</button>
                        <button class="btn btn-danger" type="button"
                                onclick="deleteCompetition(${competition.id})">
                            Удалить
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        deleteCompetition = (id) => {
            console.log('CLICK');
            sendRequest('DELETE', 'http://localhost:8585/administration/competitions/' + id)
                .then(response => {
                    window.location.replace("http://localhost:8585/administration");
                });
        }
    </script>

</@c.page>