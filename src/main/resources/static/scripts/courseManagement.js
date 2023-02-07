function deleteFile() {
    var userid = event.target.parentElement.parentElement.parentElement.querySelectorAll('td')[1].querySelector('p').innerHTML;
    let file_path = document.getElementById("file_path");
    file_path.value = userid;
    document.querySelector(".deletePopup").setAttribute("style", "transform: scale(1)");


}
function hundle_course() {
    // get teaching course from drop down 
    var select_level = document.getElementById('sort_courses');
    var level_value = select_level.options[select_level.selectedIndex].value;
    var teaching_level = document.querySelector(".course").value = level_value;
    console.log(teaching_level);
}
function download_file() {
    var url = event.target.parentElement.parentElement.parentElement.querySelectorAll('td')[1].querySelector('a').href;
    window.location.href = url;

}
function closeform() {
    document.querySelector(".deletePopup").setAttribute("style", "transform: scale(0)");
}
function setFile_name() {
    file = document.querySelector("#fileToUpload").value;
    console.log(file);

    document.querySelector("#file_name").innerHTML = file.slice(12);

}
let tableSize = document.querySelectorAll("td").length;

// if (tableSize <= 3) {
//     document.querySelector(".fl-table tbody>tr:last-of-type>td:nth-child(3) ").setAttribute("style", "border-bottom-right-radius: 20px;");
//     document.querySelector(".fl-table tbody>tr:last-of-type>td:nth-child(1) ").setAttribute("style", "border-bottom-left-radius: 20px;");
// }
