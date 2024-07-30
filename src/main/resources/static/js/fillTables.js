
async function dataAboutAllUsers() {
    const response = await fetch("/users");
    return await response.json();
}

async function dataAboutCurrentUser() {
    const response = await fetch("/showUser")
    return await response.json();
}


//заполнение таблицы всех пользователей в Admin Panel
async function fillTableOfAllUsers() {
    const usersTable = document.getElementById("usersTable");
    const users = await dataAboutAllUsers();


    let usersTableHTML = "";
    for (let user of users) {
        usersTableHTML +=
            `<tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.username}</td>
                <td>${user.roles.map(role => role.roleName).join(' ')}</td>
                <td>
                    <button class="btn btn-info btn-sm text-white"
                            data-bs-toggle="modal"
                            data-bs-target="#editModal"
                            data-user-id="${user.id}">
                        Edit</button>
                </td>
                <td>
                    <button class="btn btn-danger btn-sm btn-delete"
                            data-bs-toggle="modal"
                            data-bs-target="#deleteModal"
                            data-user-id="${user.id}">                     
                        Delete</button>
                </td>
            </tr>`;
    }
    usersTable.innerHTML = usersTableHTML;
}


//заполнение таблицы текущего пользователя
async function fillTableAboutCurrentUser(){
    const currentUserTable = document.getElementById("currentUserTable");
    const currentUser = await dataAboutCurrentUser();

    let currentUserTableHTML = "";
    currentUserTableHTML +=
        `<tr>
            <td>${currentUser.id}</td>
            <td>${currentUser.name}</td>
            <td>${currentUser.lastName}</td>
            <td>${currentUser.age}</td>
            <td>${currentUser.password}</td>
            <td>${currentUser.roles.map(role => role.roleName).join(' ')}</td>
        </tr>`
    currentUserTable.innerHTML = currentUserTableHTML;
}