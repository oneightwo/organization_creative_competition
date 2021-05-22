<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-12 p-0 m-0'>
                <div class="card">
                    <h5 class="card-header">Создать</h5>
                    <div class="card-body">
                        <form id="competitionCreate" method="post" class="needs-validation" action="/administration/competitions">
                            <div class="form-group">
                                <label for="name">Название</label>
                                <input type="text" class="form-control" id="name" name="name" required>
                            </div>
                            <div class="form-group">
                                <label for="description">Описание</label>
                                <textarea class="form-control" id="description" name="description"
                                          rows="6" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="prize">Приз</label>
                                <input type="text" class="form-control" id="prize" name="prize" required>
                            </div>
                            <div class="form-group">
                                <label for="startDate">Дата начала регистрации</label>
                                <input type="datetime-local" class="form-control" id="startDate" name="startDate" required>
                            </div>
                            <div class="form-group">
                                <label for="endDate">Дата окончания регистрации</label>
                                <input type="datetime-local" class="form-control" id="endDate" name="endDate" required>
                            </div>
                            <div class="form-group">
                                <label for="isActive">Активна</label>
                                <select class="form-control" id="isActive" name="isActive" required>
                                    <option value="true" selected>Да</option>
                                    <option value="false">Нет</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="competitionCreate" type="submit" class="btn btn-primary">Сохранить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>