async function createNewUser(user) {
        await fetch("/users",
        {method: 'POST',headers: {'Content-Type': 'application/json'}, body: JSON.stringify(user)})
}

async function addNewUserForm() {
    const newUserForm = document.getElementById("newUser");

    newUserForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        const firstName = newUserForm.querySelector("#firstName").value.trim();
        const lastName = newUserForm.querySelector("#lastName").value.trim();
        const age = newUserForm.querySelector("#age").value.trim();
        const username = newUserForm.querySelector("#email").value.trim();
        const password = newUserForm.querySelector("#password").value.trim();

        const rolesSelected = document.getElementById("roles");

        let roles = [];
        for (let option of rolesSelected.selectedOptions) {
            if(option.value === ROLE_USER.roleName) {
                roles.push(ROLE_USER);
            } else if (option.value === ROLE_ADMIN.roleName) {
                roles.push(ROLE_ADMIN);
            }
        }

        const newUserData = {
            firstName: firstName,
            lastName: lastName,
            age: age,
            username:username,
            password: password,
            roles: roles
        };

        await createNewUser(newUserData);
        newUserForm.reset();

        document.querySelector('a#show-users-table').click();
        await fillTableOfAllUsers();
    });
}