<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-12 p-0 m-0'>
                <div class="card">
                    <h5 class="card-header">Регистрация</h5>
                    <div class="card-body">
                        <form id="userCreate" class="needs-validation" method="post">
                            <div class="form-group">
                                <label for="username">Имя пользователя</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Пароль</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="form-group">
                                <label for="surname">Фамилия</label>
                                <input type="text" class="form-control" id="surname" name="surname" required>
                            </div>
                            <div class="form-group">
                                <label for="name">Имя</label>
                                <input type="text" class="form-control" id="name" name="name" required>
                            </div>
                            <div class="form-group">
                                <label for="patronymic">Отчество</label>
                                <input type="text" class="form-control" id="patronymic" name="patronymic" required>
                            </div>
                            <div class="form-group">
                                <label for="dateOfBirth">Дата рождения</label>
                                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
                            </div>
                            <div class="form-group">
                                <label for="description">Описание</label>
                                <textarea class="form-control" id="description" name="description" rows="4"
                                          required></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="userCreate" type="submit" class="btn btn-primary">Сохранить</button>
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