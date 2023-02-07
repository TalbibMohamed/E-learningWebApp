
var mode = "light";

function switchmode() {
    var checkbox = document.getElementById("darkmode-checkbox").checked;
    var label = document.getElementById("darkmode-label");

    if (checkbox) {
        document.querySelector("html").classList.remove("darkmode");
        document.querySelector(".search").querySelector("img").setAttribute("src", "/assets/icons/search.svg")
        document.querySelector("footer").querySelector("img").setAttribute("src", "/assets/icons/up-arrow.svg")
        label.innerHTML = "switch to dark mode"
        document.querySelector(".addUserForm").querySelector("img").setAttribute("src", "/assets/icons/close.svg")
        document.querySelector(".editForm").querySelector("img").setAttribute("src", "/assets/icons/close.svg")
    }
    else {
        document.querySelector("html").classList.add("darkmode");
        document.querySelector(".search").querySelector("img").setAttribute("src", "/assets/icons/Darkmode/search.svg")
        document.querySelector("footer").querySelector("img").setAttribute("src", "/assets/icons/Darkmode/up-arrow.svg")
        label.innerHTML = "switch to light mode"
        document.querySelector(".addUserForm").querySelector("img").setAttribute("src", "/assets/icons/Darkmode/close.svg")
        document.querySelector(".editForm").querySelector("img").setAttribute("src", "/assets/icons/Darkmode/close.svg")
    }

}

