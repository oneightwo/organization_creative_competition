<#import "containers/common.ftlh" as c>
<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-6 p-0 m-0'>
                <div class="container-fluid">
                    <div class="card mb-3">
                        <h5 class="card-header">Авторизация</h5>
                        <div class="card-body">
                            <form id="login" class="needs-validation" action="/login" method="post">
                                <div class="form-row">
                                    <label for="username">Имя пользователя</label>
                                    <input type="text" id="username" name="username" class="form-control" required>
                                </div>
                                <div class="form-row mt-2">
                                    <label for="password">Пароль</label>
                                    <input type="password" id="password" name="password" class="form-control" required>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer">
                            <button class="btn btn-success" form="login" type="submit">Войти</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>