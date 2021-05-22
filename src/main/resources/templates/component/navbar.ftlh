<nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light light shadow fixed-top">
    <a class="navbar-brand" href="/competitions">АИС</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/competitions">Конкурсы</a>
            </li>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/users/profile">Профиль</a>
                </li>
            </#if>
            <#if user?? && user.role == "ORGANIZER">
                <li class="nav-item">
                    <a class="nav-link" href="/administration">Мои конкурсы</a>
                </li>
            <#elseif user?? && user.role == "ADMIN">
                <li class="nav-item">
                    <a class="nav-link" href="/administration/staff">Администрирование</a>
                </li>
            </#if>

        </ul>

        <#if !(user??)>
            <a class="btn btn-primary mr-1" href="/registration">Зарегистрироваться</a>
            <a class="btn btn-success" href="/login">Войти</a>
        <#else>
            <#if user.role == "ADMIN">
                <a class="btn btn-primary mr-1" href="/administration/registration">Зарегистрировать</a>
            <#elseif user?? && user.role == "ORGANIZER">
                <a class="btn btn-success mr-1" href="/administration/competitions/create">Создать конкурс</a>
            </#if>
            <a class="btn btn-outline-danger" href="/logout">Выйти</a>
        </#if>
    </div>
</nav>
<div style='margin-top: 71px'></div>