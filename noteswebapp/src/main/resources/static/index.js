
function showform(showdiv) {
    const allDivs = document.querySelectorAll('#login-form, #register-form, #user-notes');
    allDivs.forEach(div => {
        div.style.display = 'none';
    });

    const specificDiv = document.getElementById(showdiv);
    if (specificDiv) {
        specificDiv.style.display = 'flex';
    }
}

function register(event) {
    event.preventDefault();
    const username = document.getElementById('register-username').value;
    const password = document.getElementById('register-password').value;
    document.getElementById("register-error-message").innerHTML = "";

    const url = "http://localhost:8080/account/register";

    const registercredentials = {
        userName: username,
        password: password
    }

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(registercredentials)
    })
        .then(response => response.json())
        .then(data => {
            if (data.success === true) {
                clearForm("register-form");
                showform('login-form');
            }
            else {
                document.getElementById("register-error-message").innerHTML = data.message;
            }
        })
        .catch(error => document.getElementById("register-error-message").innerHTML = error);
}

function login(event) {
    event.preventDefault();

    const username = document.getElementById('login-username').value.trim();
    const password = document.getElementById('login-password').value.trim();
    document.getElementById("login-error-message").innerHTML = "";

    const url = "http://localhost:8080/account/login";

    const logincredentials = {
        userName: username,
        password: password
    };

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(logincredentials)
    })
        .then(response => response.json())
        .then(data => {
            if (data.success === true) {
                sessionStorage.setItem("username", username);
                document.getElementById("session-name").innerHTML = username;
                alert(data.message);
                clearForm("login-form");
                showform('user-notes');
            } else {
                document.getElementById("login-error-message").innerHTML = data.message;
            }
        })
        .catch(error => document.getElementById("login-error-message").innerHTML = error);
}


function logout() {
    document.getElementById("all-notes").innerHTML = "";
    sessionStorage.clear();
    showform('login-form');
}


function showfield(showdiv) {
    document.getElementById("all-notes").innerHTML = "";

    const allDivs = document.querySelectorAll('.input-field');
    allDivs.forEach(div => {
        div.style.display = 'none';
    });

    const specificDiv = document.getElementById(showdiv);
    if (specificDiv) {
        specificDiv.style.display = 'flex';
    }
}

function clearForm(clearfield) {
    const clearid = document.getElementById(clearfield);
    const inputs = clearid.querySelectorAll("input, textarea");
    inputs.forEach(input => {
        input.value = "";
    });
}

function add() {
    const addurl = "http://localhost:8080/note/add";
    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;

    const note = {
        userName: sessionStorage.getItem("username"),
        title: title,
        content: content
    };

    fetch(addurl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(note)
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("all-notes").innerHTML = data;
            clearForm("add-note-form");
        })
        .catch(error => document.getElementById("all-notes").innerHTML = error)
}

function display() {
    const username = sessionStorage.getItem("username");
    const url = `http://localhost:8080/note/display?username=${encodeURIComponent(username)}`;
    fetch(url)
        .then(response => {
            return response.json();
        })
        .then(data => {
            let html = `<table class="note-table">
                <thead>
                    <tr>
                        <th>Note ID</th>
                        <th>Title</th>
                        <th>Content</th>
                    </tr>
                </thead>
                <tbody>`;
            data.forEach(note => {
                html += `<tr>
                <td>${note.id}</td>
                <td>${note.title}</td>
                <td>${note.content}</td>
             </tr>`;
            });
            html += `</tbody></table>`;

            document.getElementById("all-notes").innerHTML = html;
        })
        .catch(error => document.getElementById("all-notes").innerHTML = error);
}

function deleteNote() {
    const username = sessionStorage.getItem("username");
    const deletenote = document.getElementById("delete-id").value;
    const deleteurl = `http://localhost:8080/note/delete?username=${encodeURIComponent(username)}&id=${encodeURIComponent(deletenote)}`;

    fetch(deleteurl, {
        method: "DELETE"
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("all-notes").innerHTML = data;
            clearForm("delete-note-form");
        })
        .catch(error => document.getElementById("all-notes").innerHTML = error)
}

function update() {
    const updatenote = document.getElementById("update-id").value;
    const newtitle = document.getElementById("new-title").value;
    const newcontent = document.getElementById("new-content").value;
    const updateurl = `http://localhost:8080/note/update/${encodeURIComponent(updatenote)}`;

    const note = {
        userName: sessionStorage.getItem("username"),
        title: newtitle,
        content: newcontent
    }

    fetch(updateurl, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(note)
    })
        .then(respose => respose.text())
        .then(data => {
            document.getElementById("all-notes").innerHTML = data;
            clearForm("update-note-form");
        })
        .catch(error => document.getElementById("all-notes").innerHTML = error)
}

function search() {
    const username = sessionStorage.getItem("username");
    const searchnote = document.getElementById("key").value;
    const searchurl = `http://localhost:8080/note/search?username=${encodeURIComponent(username)}&key=${encodeURIComponent(searchnote)}`;

    fetch(searchurl)
        .then(respose => respose.json())
        .then(data => {
            let html = "";
            if (data.length === 0) {
                html = "<p>No matching notes found.</p>";
            } else {
                html = `<table class="note-table">
                        <thead>
                            <tr>
                                <th>Note ID</th>
                                <th>Title</th>
                                <th>Content</th>
                            </tr>
                        </thead>
                        <tbody>`;
                data.forEach(note => {
                    html += `<tr>
                                <td>${note.id}</td>
                                <td>${note.title}</td>
                                <td>${note.content}</td>
                            </tr>`;
                });
                html += `</tbody></table>`;
            }
            document.getElementById("all-notes").innerHTML = html;
            clearForm("search-note-form");
        })
        .catch(error => document.getElementById("all-notes").innerHTML = error);
}