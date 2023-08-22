console.log("ENTERED RegisterValidator.js");

console.log("FIRST");

const minPassLength = 8;

var checkPassword = function () {
    var passwordElement = document.getElementById('password');
    var passwordRepeatElement = document.getElementById('passwordRepeat');
    var msgElement = document.getElementById('msg');

    if (passwordElement.value === passwordRepeatElement.value) {
        msgElement.innerHTML = '';
    } else {
        msgElement.style.color = 'red';
        msgElement.innerHTML = 'Passwords don\'t match';
    }
}

console.log("SECOND");


var check = function () {
    var passwordElement = document.getElementById('password');
    var passwordRepeatElement = document.getElementById('passwordRepeat');
    var usernameElement = document.getElementById('username');

    if (passwordElement.value !== passwordRepeatElement.value) {
        alert('Passwords don\'t match');
        return false;
    }

    if (passwordElement.value.length < minPassLength) {
        alert("Password must contain at least " + minPassLength + " characters");
        return false;
    }

    if (usernameElement.value.charAt(0) >= '0' && usernameElement.value.charAt(0) <= '9') {
        alert("Username can't start with a digit");
        return false;
    }

    return true;
}
