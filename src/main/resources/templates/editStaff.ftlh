<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-12 p-0 m-0'>
                <div class="card">
                    <h5 class="card-header">Редактирование</h5>
                    <div class="card-body">
                        <form id="userEdit" class="needs-validation" method="post">
                            <input type="text" class="form-control" id="createdBy.id" name="createdBy.id"
                                   value="${staff.createdBy.id}"
                                   hidden>
                            <div class="form-group">
                                <label for="username">Имя пользователя</label>
                                <input type="text" class="form-control" id="username" name="username"
                                       value="${staff.username}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="password">Пароль</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       value="${staff.password}" required>
                            </div>
                            <div class="form-group">
                                <label for="surname">Фамилия</label>
                                <input type="text" class="form-control" id="surname" name="surname"
                                       value="${staff.surname}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="name">Имя</label>
                                <input type="text" class="form-control" id="name" name="name" value="${staff.name}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="patronymic">Отчество</label>
                                <input type="text" class="form-control" id="patronymic" name="patronymic"
                                       value="${staff.patronymic}" required>
                            </div>
                            <div class="form-group">
                                <label for="dateOfBirth">Дата рождения</label>
                                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth"
                                       value="${staff.dateOfBirth}" required>
                            </div>
                            <div class="form-group">
                                <label for="description">Описание</label>
                                <textarea class="form-control" id="description" name="description" rows="4"
                                          required>${staff.description}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="role">Роль</label>
                                <select class="form-control" id="role" name="role">
                                    <#if staff.role == "ORGANIZER">
                                        <option value="ORGANIZER" selected>Организатор</option>
                                        <option value="ADMIN">Админ</option>
                                    </#if>
                                    <#if staff.role == "ADMIN">
                                        <option value="ADMIN" selected>Админ</option>
                                        <option value="ORGANIZER">Организатор</option>
                                    </#if>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="userEdit" type="submit" class="btn btn-primary">Сохранить</button>
                        <a class="btn btn-danger" href="/administration/staff">Отменить</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        if (window.location.href.includes("error")) {
            alert("Данное имя пользователя уже занято");
        }
    </script>
</@c.page>