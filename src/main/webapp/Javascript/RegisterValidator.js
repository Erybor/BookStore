const minPassLength = 8;
var checkPassword = function () {
    if (document.getElementById('password').value ==
        document.getElementById('passwordRepeat').value) {
        document.getElementById('msg').innerHTML = '';
    } else {
        document.getElementById('msg').style.color = 'red';
        document.getElementById('msg').innerHTML = 'Passwords don\'t match';
    }
}
var check = function () {
    var password = document.getElementById('password').value;
    var rep_password = document.getElementById('passwordRepeat').value;
    if (password != rep_password) {
        return false;
    }
    if (document.getElementById('password').value.length < minPassLength) {
        alert("Password must contain at least " + minPassLength + " characters");
        return false;
    }
    let username = document.getElementById('username').value;
    if (username.charAt(0) >= '0' && username.charAt(0) <= '9') {
        alert("Username can't start with a digit");
        return false;
    }
}