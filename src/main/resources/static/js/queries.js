deleteCompetition = (id) => {
    console.log('CLICK');
    sendRequest('DELETE', `http://localhost:8585/administration/competitions/${id}`)
        .then(response => {
            window.location.replace("http://localhost:8585/administration");
        });
}

deleteParticipant = (id) => {
    console.log('CLICK');
    sendRequest('DELETE', `http://localhost:8585/competitions/participants/${id}`)
        .then(response => {
            window.location.replace("http://localhost:8585/users/profile");
        });
}


sendRequest = (method, url, body) => {
    const headers = {
        'Content-Type': 'application/json'
    }
    console.log(body);
    if (body !== null) {
        return fetch(url, {
            method: method,
            body: JSON.stringify(body),
            headers: headers
        });
    } else {
        return fetch(url, {
            method: method,
            headers: headers
        });
    }
}