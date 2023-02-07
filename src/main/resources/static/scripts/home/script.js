function sleep(ms) {  //stop executing code for some time
    return new Promise(resolve => setTimeout(resolve, ms));
}

var form = true; //flipcard 

var users = [{ email: "a", password: "a", type: 0 }, { email: "mo7.tabli@gmail.com", password: "mo72001", type: 0 }, { email: "prof", password: "prof", type: 1 }, { email: "admin", password: "admin", type: 2 }]


document.querySelector(".back").setAttribute("style", "pointer-events: none;");
function flipcard() {
    if (form) {
        document.querySelector(".back").setAttribute("style", "");
        document.querySelector(".content").setAttribute("style", "transform:rotateY( 180deg );");
        document.querySelector(".front").setAttribute("style", "pointer-events: none;");
        form = false;
    }
    else {
        document.querySelector(".content").setAttribute("style", "transform:rotateY( 0deg );");
        document.querySelector(".front").setAttribute("style", "");
        document.querySelector(".back").setAttribute("style", "");
        form = true;
    }
}


// darkmode switch
var mode = "light";

function switchmode() {
    var checkbox = document.getElementById("darkmode-checkbox").checked;
    var label = document.getElementById("darkmode-label");

    if (checkbox) {
        document.querySelector("html").classList.remove("darkmode");
        label.innerHTML = "switch to dark mode"
        changesvg(checkbox);
    }
    else {
        document.querySelector("html").classList.add("darkmode");
        label.innerHTML = "switch to light mode"
        changesvg(checkbox);
    }

}

function changesvg(checkbox) {

    if (checkbox) {
        var path = "/assets/Svg/";
        var svgpath = path;

        for (var i = 0; i < 3; i++) {
            switch (i) {
                case 0: svgpath += "online-learning.svg";
                    break;
                case 1: svgpath += "share-link.svg";
                    break;
                case 2: svgpath += "online-browsing.svg";
                    break;
            }
            document.querySelector(".images-box").querySelectorAll("figure")[i].querySelector("img").setAttribute("src", svgpath)
            svgpath = path;
        }

        document.querySelector("footer").querySelector("img").setAttribute("src", "assets/icons/up-arrow.svg")

    }
    else {

        var path = "assets/Svg/Darkmode/";
        var svgpath = path;

        for (var i = 0; i < 3; i++) {
            switch (i) {
                case 0: svgpath += "online-learning.svg";
                    break;
                case 1: svgpath += "share-link.svg";
                    break;
                case 2: svgpath += "online-browsing.svg";
                    break;
            }
            document.querySelector(".images-box").querySelectorAll("figure")[i].querySelector("img").setAttribute("src", svgpath)
            svgpath = path;
        }

        document.querySelector("footer").querySelector("img").setAttribute("src", "/assets/icons/Darkmode/up-arrow.svg")
    }
}

// function login() {
//     var email = document.querySelector(".front").querySelectorAll("input")[0].value;
//     var password = document.querySelector(".front").querySelectorAll("input")[1].value;

//     checklogin(email, password)
// }

// function checklogin(email, password) {
//     var exist = false;
//     users.forEach(user => {
//         if (user.email == email && user.password == password) {
//             exist = true;
//             document.querySelector(".front").querySelectorAll("input")[0].value = ""
//             document.querySelector(".front").querySelectorAll("input")[1].value = ""
//             switch (user.type) {
//                 case 0: location.href = "/TpDaaw/panels/studentPanel.html";
//                     break;
//                 case 1: location.href = "/TpDaaw/panels/profPanel.html";
//                     break;
//                 case 2: location.href = "/TpDaaw/panels/adminPanel.html";
//                     break;
//             }
//         }
//     });
//     if (exist == false) {
//         document.querySelector(".front").querySelectorAll("input")[0].value = ""
//         document.querySelector(".front").querySelectorAll("input")[1].value = ""
//         alert("Username/Password Invalid")
//     }
// }