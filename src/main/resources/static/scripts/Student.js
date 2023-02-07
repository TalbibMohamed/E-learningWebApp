
var listModule = document.querySelectorAll(".module").length;

for (let i = 0; i < listModule; i++) {
    let index = i;
    document.querySelectorAll(".module")[i].addEventListener("mouseover", () => {
        for (let i = 0; i < listModule; i++) {
            if (index == i) {
                continue;
            } else {
                document.querySelectorAll(".module")[i].setAttribute("style", "z-index:-120;");
            }
        }
    });
    document.querySelectorAll(".module")[i].addEventListener("mouseout", () => {
        for (let i = 0; i < listModule; i++) {
            if (index == i) {
                continue;
            } else {
                document.querySelectorAll(".module")[i].setAttribute("style", "z-index:0;");
            }
        }
    });
}


var moduleBox = document.querySelector("main").querySelector("section").querySelectorAll(".module");

moduleBox.addEventListener("mouseout", (event) => {
    event.target.classList.add("module-less-visible")
});

moduleBox.addEventListener("mouseover", (event) => {
    event.target.classList.remove("module-less-visible")
});

