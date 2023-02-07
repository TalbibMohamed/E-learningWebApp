var FlowintWindow = document.querySelector(".Profile-sidebar");

FlowintWindow.addEventListener("click", () => {
    let id = document.querySelector("#teacher_id");
    // id.setAttribute("value", userid)
    window.location.href = "./teacherProfile?id=" + id.innerHTML
});
FlowintWindow.addEventListener("mouseover", (event) => {
    document.querySelector(".Polygon-message").classList.add("Polygon-message2");
});
FlowintWindow.addEventListener("mouseout", (event) => {
    document.querySelector(".Polygon-message").classList.remove("Polygon-message2");
});

