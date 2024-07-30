async function sendDataEditUser(user, userId) {
    await fetch(` /${userId}`,
        {method:"PATCH", headers: {'Content-type': 'application/json'}, body: JSON.stringify(user)} )
}

const modalEdit = document.getElementById("editModal");

async function EditModalHandler() {
    await fillModal(modalEdit);
}

modalEdit.addEventListener("submit", async function(event){
    event.preventDefault();

    const rolesSelected = document.getElementById("rolesEdit");

    let roles = [];
    for (let option of rolesSelected.selectedOptions) {
        if(option.value === ROLE_USER.roleName) {
            roles.push(ROLE_USER);
        } else if (option.value === ROLE_ADMIN.roleName) {
            roles.push(ROLE_ADMIN);
        }
    }

    let user = {
        id: document.getElementById("idEdit").value,
        firstName: document.getElementById("firstNameEdit").value,
        lastName: document.getElementById("lastNameEdit").value,
        age: document.getElementById("ageEdit").value,
        username: document.getElementById("emailEdit").value,
        password: document.getElementById("passwordEdit").value,
        roles: roles
    }

    const userId = event.target.querySelector("#idEdit").value;
    await sendDataEditUser(user, userId);
    await fillTableOfAllUsers();

    const modalBootstrap = bootstrap.Modal.getInstance(modalEdit);
    modalBootstrap.hide();
})
