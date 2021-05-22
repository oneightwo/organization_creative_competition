<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-12 p-0 m-0'>
                <div class="card">
                    <h5 class="card-header">Зарегистрироваться</h5>
                    <div class="card-body">
                        <p class="card-text"><strong>Конкурс:</strong> ${competition.name}</p>
                        <p class="card-text"><strong>Описание:</strong> ${competition.description}</p>
                        <form id="participantCreate" method="post">
                            <div class="form-group">
                                <label for="message"><strong>Сообщение</strong></label>
                                <textarea class="form-control" id="message" name="message"
                                          rows="6"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="participantCreate" type="submit" class="btn btn-primary">Сохранить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>