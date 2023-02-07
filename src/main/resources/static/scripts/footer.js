
var detailsBar = true;

function logout() {
    location.href="/TpDaaw/home/index.html"
}

function viewdetails() {
    if(detailsBar) {
        document.querySelector("footer").setAttribute("style","height:200px");
        document.querySelector("footer").querySelector("img").setAttribute("style","transform : rotateX(180deg) translateY(0px) ")

        var spans = document.querySelector("footer").querySelector(".more-details").querySelectorAll("span");
        var spansNbr = document.querySelector("footer").querySelector(".more-details").querySelectorAll("span").length;
        for(var i = 0 ; i < spansNbr ; i++ ) {
            spans[i].setAttribute("style","transform:scale(1);");
            document.querySelector("footer").querySelector(".more-details").querySelectorAll("i")[i].setAttribute("style","transform:scale(1);");
        }

        var icons = document.querySelector("footer").querySelector(".details").querySelectorAll(".icon");
        var iconsNbr = document.querySelector("footer").querySelector(".details").querySelectorAll(".icon").length;
        for(var i = 0 ; i < iconsNbr ; i++ ) {
            icons[i].setAttribute("style","transform:scale(1);")
        }
        detailsBar = false;
    }
    else {
        document.querySelector("footer").setAttribute("style","height:52px");
        document.querySelector("footer").querySelector("img").setAttribute("style","transform : rotateX(0deg) translateY(0px)")

        var spans = document.querySelector("footer").querySelector(".more-details").querySelectorAll("span");
        var spansNbr = document.querySelector("footer").querySelector(".more-details").querySelectorAll("span").length;
        for(var i = 0 ; i < spansNbr ; i++ ) {
            spans[i].setAttribute("style","transform:scale(0);");
            document.querySelector("footer").querySelector(".more-details").querySelectorAll("i")[i].setAttribute("style","transform:scale(0);");
        }

        var icons = document.querySelector("footer").querySelector(".details").querySelectorAll(".icon");
        var iconsNbr = document.querySelector("footer").querySelector(".details").querySelectorAll(".icon").length;
        for(var i = 0 ; i < iconsNbr ; i++ ) {
            icons[i].setAttribute("style","transform:scale(0);")
        }
        detailsBar = true;
    }
}