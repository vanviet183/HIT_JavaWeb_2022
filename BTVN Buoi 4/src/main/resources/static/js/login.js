var $ = document.querySelector.bind(document);
var $$ = document.querySelectorAll.bind(document);

var usernameInput = $('#username');
var passwordInput = $('#password');
var btnSubmit = $('button');
var form = $('form');
var users = [
    {
        username: "vanviet1",
        password: "vanviet1",
    },
    {
        username: "vanviet2",
        password: "vanviet2",
    },
    {
        username: "vanviet3",
        password: "vanviet3",
    },
    {
        username: "vanviet4",
        password: "vanviet4",
    },
    {
        username: "vanviet5",
        password: "vanviet5",
    },
]

var infoUser = []
users.forEach((e) => {
    infoUser.push(Object.values(e))
})

const handleLogin = function () {
    btnSubmit.onclick = () => {
        var isCorrect = false
        infoUser.forEach((info, index) => {
            if (usernameInput == info[0] && passwordInput == info[1]) {
                isCorrect = true;
            }
        })
        if(isCorrect) {
            form.setAttribute('th:action', '@{/success}');
        } else {
            form.setAttribute('th:action', '@{/login}');
            form.setAttribute('method', 'get');
        }
    }
}

handleLogin();