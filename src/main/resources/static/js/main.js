
let viewUsersUrl = 'http://localhost:8080/userList/info';
let authUserUrl = 'http://localhost:8080/name/info';
let newUserUrl = 'http://localhost:8080/newUser';
let deleteUserUrl = 'http://localhost:8080/deleteUser/';
let getUserByIdUrl = 'http://localhost:8080/user/';
let updateUserUrl = 'http://localhost:8080/updateUser';

let elementUserTable = document.getElementById('nav-list-tab');
let elementNewUser = document.getElementById('nav-newUser-tab');
let elementCreateUser = document.getElementById('createUser');
let elementCreateUserRoles = document.getElementById('roleSelect');
let elementCloseDeleteModal1 = document.getElementById('closeDeleteModal');
let elementCloseDeleteModal2 = document.getElementById('closeDeleteModal2');
let elementCloseUpdateModal1 = document.getElementById('closeUpdateModal');
let elementCloseUpdateModal2 = document.getElementById('closeUpdateModal2');

let userInfo = $('#userInfoJs tbody');
let navbarInfo = $('#navBarInfo li');
let adminUsersTable = $('#userTableJs tbody');
let deleteButtonInModalForm = $('#deleteButtonInModal div');
let saveButtonInModalForm = $('#updateButtonInModal div');

$(document).ready(function () {

    showAllUsers();
    const jsonUser = fetch(authUserUrl).then(r => r.json());
    navbar(jsonUser);
    showUserInfo(jsonUser);
});

elementCreateUser.onclick = () => {void newUser();};
elementNewUser.onclick = () => { hideTheUserTable();};
elementUserTable.onclick = () => {showTheUserTable();};


elementCloseDeleteModal1.onclick =  () => {document.getElementById('delButtInModal').remove();};
elementCloseUpdateModal1.onclick = () =>  {document.getElementById('updButtInModal').remove();};

// elementCloseUpdateModal2.onclick = () => {document.getElementById('updButtInModal').remove();};
// elementCloseDeleteModal2.onclick = () => {document.getElementById('delButtInModal').remove();};
/************************************************************************/

function showAllUsers() {

    let userIdForDelete = 0;
    let userIdForUpdate = 0;

    fetch(viewUsersUrl)
        .then((response) => {
            if (!response.ok) {throw Error("Error: " + response.status);}
            return response.json();
        })
        .then((data) => {
            data.map(user => {

                let buttonDelete = document.createElement('button');
                let buttonEdit = document.createElement('button');

                let tr = document.createElement('tr');
                tr.setAttribute('id', "userDataTable");

                let counter = 0, td;

                for (let o in user) {
                    let td = document.createElement('td');

                    if (counter === 0) {
                        userIdForDelete = "fillingModalFormDelete" + "(" + user.id + ")";
                        userIdForUpdate = "fillingModalFormUpdate" + "(" + user.id + ")";
                    }

                    if (counter < 9) {
                        td.appendChild(document.createTextNode(user[o]));
                        tr.appendChild(td);
                    }
                    counter++;
                }

                buttonEdit.setAttribute('id', "updateButton");
                buttonEdit.setAttribute('class', "btn btn-info");
                buttonEdit.setAttribute('role', "button");
                buttonEdit.setAttribute('data-toggle', "modal");
                buttonEdit.setAttribute('data-target', "#updateModal");
                buttonEdit.setAttribute('onclick', `${userIdForUpdate}`);
                buttonEdit.appendChild(document.createTextNode("Update"));

                buttonDelete.setAttribute('id', "deleteButton");
                buttonDelete.setAttribute('class', "btn btn-danger");
                buttonDelete.setAttribute('role', "button");
                buttonDelete.setAttribute('data-toggle', "modal");
                buttonDelete.setAttribute('data-target', "#deleteModal");
                buttonDelete.setAttribute('onclick', `${userIdForDelete}`);
                buttonDelete.appendChild(document.createTextNode("Delete"));
                td = document.createElement('td');
                td.appendChild(buttonEdit);
                tr.appendChild(td);
                td = document.createElement('td');
                td.appendChild(buttonDelete);
                tr.appendChild(td);
                adminUsersTable.append(tr);
            });
        })
        .catch(error => {
            console.log(error);
        })
}

function navbar(jsonUser) {
    jsonUser
        .then((data2) => {
            let p = document.createElement('p');
            p.setAttribute('class', "navbar-text text-white");
            p.appendChild(document.createTextNode(data2.name+ " with roles : " + data2.roles));
            navbarInfo.append(p);
        })
        .catch(error => { console.log(error);});
}

async function newUser() {

    document.getElementById('hideTheUsersTable').hidden = true;
    document.getElementById('hideTheCreateUserForm').hidden = false;

    let roleSelectedValues = Array.from(elementCreateUserRoles.selectedOptions).map(el => el.value);
    let roleArray = convertToRoleSet(roleSelectedValues); //создаем массив из значений полученных с селектора

    let data = {
        firstName: $('#firstName').val(),
        lastName: $('#lastName').val(),
        age: $('#age').val(),
        name: $('#name').val(),
        password: $('#password').val(),
        status: $('#status').val(),
        roles: roleArray.toString()
    };

    const response = await fetch(newUserUrl, {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {'Content-Type': 'application/json'},
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(data)
    })
        .catch(error => {console.log(error);});
    return response.json();
}

function showUserInfo(jsonUser) {
    jsonUser
        .then((data) => {// const userRoles = data.roleSet.map(role => {return role.name;}).join(", ");
            let tr = document.createElement('tr');
            let counter = 0;
            for (let o in data) {
                let td = document.createElement('td');
                if (counter < 8) { td.appendChild(document.createTextNode(data[o]));
                    tr.appendChild(td);}
                counter++;
            }
            userInfo.append(tr);
        })
        .catch(error => {console.log(error);});
}

async function updateUser(value) {

    let id = '#updUserID';
    let firstName = '#updUserFirstName';
    let lastName = '#updUserLastName';
    let age = '#updUserAge';
    let name = '#updUserName';
    let password = '#updUserPassword';
    let status = '#updUserStatus';

    let elementUpdateUserRoles = document.getElementById('userUpdRoles');
    let roleSelectedValues = Array.from(elementUpdateUserRoles.selectedOptions).map(el => el.value);
    let roleArray = convertToRoleSet(roleSelectedValues);

    let data = {
        id:$(id).val(),
        firstName:$(firstName).val(),
        lastName:$(lastName).val(),
        age:$(age).val(),
        name:$(name).val(),
        password:$(password).val(),
        status:$(status).val(),
        roles: roleArray.toString()
    };

    const response = await fetch(updateUserUrl, {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {'Content-Type': 'application/json'},
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(data)
    });

    document.getElementById('updButtInModal').remove();
    clearTable();
    showAllUsers();
    return response.json();
}

async function deleteData(value) {

    await fetch(deleteUserUrl + value, {
        method: 'DELETE',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {'Content-Type': 'application/json'},
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
    });

    document.getElementById('delButtInModal').remove();
    clearTable();
    showAllUsers();
}

function convertToRoleSet(Array) {
    let roleArray = [];

    if (Array.indexOf("USER") !== -1) {
        roleArray.unshift("USER");
    }
    if (Array.indexOf("ADMIN") !== -1) {
        roleArray.unshift("ADMIN");
    }

    return roleArray;
}

function hideTheUserTable() {
    document.getElementById('hideTheUsersTable').hidden = true;
    document.getElementById('hideTheCreateUserForm').hidden = false;
    clearTable();
}

function showTheUserTable() {
    if (document.getElementById("userDataTable") == null) {showAllUsers();}
    document.getElementById('hideTheUsersTable').hidden = false;
    document.getElementById('hideTheCreateUserForm').hidden = true;
}

function clearTable() {
    while (document.getElementById("userDataTable") != null) {
        document.getElementById("userDataTable").remove();
    }
}

function fillingModalFormDelete(id) {

    let deleteButtonInModal = document.createElement('button');
    let userIdForDeleteButton = "deleteData" + "(" + id + ")";

    deleteButtonInModal.setAttribute('type', "button");
    deleteButtonInModal.setAttribute('id', "delButtInModal");
    deleteButtonInModal.setAttribute('class', "btn btn-danger");
    deleteButtonInModal.setAttribute('data-dismiss', "modal");
    deleteButtonInModal.setAttribute('onclick', `${userIdForDeleteButton}`);
    deleteButtonInModal.appendChild(document.createTextNode("Delete"));

    deleteButtonInModalForm.append(deleteButtonInModal);

    fetch(getUserByIdUrl + id).then(function (response) {
        response.json().then(function (data) {

            $('#delUserID').val(data.id);
            $('#delUserFirstName').val(data.firstName);
            $('#delUserLastName').val(data.lastName);
            $('#delUserAge').val(data.age);
            $('#delUserName').val(data.name);
            $('#delUserRoles').val(data.roles);
            $('#delUserPassword').val(data.password);
            $('#delUserStatus').val(data.status);

        });
    });
}

function fillingModalFormUpdate(id) {

    let updateButtonInModal = document.createElement('button');
    let userIdForUpdateButton = "updateUser" + "(" + id + ")";

    updateButtonInModal.setAttribute('type', "button");
    updateButtonInModal.setAttribute('id', "updButtInModal");
    updateButtonInModal.setAttribute('class', "btn btn-success");
    updateButtonInModal.setAttribute('data-dismiss', "modal");
    updateButtonInModal.setAttribute('onclick', `${userIdForUpdateButton}`);
    updateButtonInModal.appendChild(document.createTextNode("Update"));

    saveButtonInModalForm.append(updateButtonInModal);

    fetch(getUserByIdUrl + id).then(function (response) {
        response.json().then(function (data) {

            $('#updUserID').val(data.id);
            $('#updUserFirstName').val(data.firstName);
            $('#updUserLastName').val(data.lastName);
            $('#updUserAge').val(data.age);
            $('#updUserName').val(data.name);
            $('#updUserPassword').val(data.password);
            $('#updUserStatus').val(data.status);

        });
    });
}