// update sort list of course by level 
function update_sort_level() {
    var select_level = document.getElementById('sort_level');
    var level_value = select_level.options[select_level.selectedIndex].value;
    var teaching_level = document.querySelector("#teaching_level").value = level_value;
    console.log(teaching_level);
    if (teaching_level === "L2") {
        console.log(teaching_level);

        for (var i = 0; i < document.querySelectorAll(".l2_courses").length; i++) {
            document.querySelectorAll(".l2_courses")[i].selected = true;
            document.querySelectorAll(".l2_courses")[i].setAttribute("style", "display:flex");
        }
        for (var i = 0; i < document.querySelectorAll(".l3_courses").length; i++) {

            document.querySelectorAll(".l3_courses")[i].setAttribute("style", "display:none");
        }

    }
    if (teaching_level === "L3") {
        console.log(teaching_level);
        for (var i = 0; i < document.querySelectorAll(".l2_courses").length; i++) {
            document.querySelectorAll(".l2_courses")[i].setAttribute("style", "display:none");
        }
        for (var i = 0; i < document.querySelectorAll(".l3_courses").length; i++) {
            document.querySelectorAll(".l3_courses")[i].selected = true;
            document.querySelectorAll(".l3_courses")[i].setAttribute("style", "display:flex");
        }

    }
}
let next = document.querySelector(".next");
next.addEventListener("mouseover", () => {
    var select = document.getElementById('sort_role');
    var value = select.options[select.selectedIndex].value;
    let user_role = document.querySelector("#user_role").value = value;
    console.log(document.querySelector("#user_role").value);
    var confimationPassword = document.querySelector("#Password_AddformCf").value;
    var Password = document.querySelector("#Password_Addform").value;
    if (confimationPassword != Password) {
        alert("password confirmation does not match");
    }
    // var select_degree = document.getElementById('sort_degree');
    // var value_degree = select_degree.options[select_degree.selectedIndex].value;
    // var student_degree_value = document.querySelector("#student_degree").value = value_degree;
    // console.log(student_degree_value);
});
next.addEventListener("click", () => {
    next.setAttribute("style", "display:none;");
    document.querySelector("#Add_UserBTN").setAttribute("style", "display:block;");
    document.querySelector("#return_btn").setAttribute("style", "display:block;");

    //display new form 
    if (user_role.value == "student") {
        document.querySelector(".studentAdd").setAttribute("style", "display:flex;");
    } else {
        // display teacher form 
        document.querySelector(".teacherInfoAdd").setAttribute("style", "display:flex;");
    }
    // hide previous form 
    document.querySelector(".side0").setAttribute("style", "display:none;");
    document.querySelector(".side1").setAttribute("style", "display:none;");
    document.querySelector(".d2").setAttribute("style", "display:none;");
});

submitAddUser = document.querySelector("#Add_UserBTN");
submitAddUser.addEventListener("mouseover", () => {

    if (user_role.value == "student") {
        // get student degree
        var select_degree = document.getElementById('sort_degree');
        var value_degree = select_degree.options[select_degree.selectedIndex].value;
        var student_degree_value = document.querySelector("#student_degree").value = value_degree;
        console.log(student_degree_value);
        // get student class
        var select_class = document.getElementById('sort_class');
        var value_class = select_class.options[select_class.selectedIndex].value;
        var student_class_value = document.querySelector("#student_class").value = value_class;
        console.log(student_class_value);
    } else {
        // get teacher grade from drop down 
        var select_grade = document.getElementById('sort_grade');
        var value_grade = select_grade.options[select_grade.selectedIndex].value;
        var student_grade_value = document.querySelector("#teacher_grade").value = value_grade;
        console.log(student_grade_value);
        // get teaching level from drop down 
        var select_level = document.getElementById('sort_level');
        var level_value = select_level.options[select_level.selectedIndex].value;
        var teaching_level = document.querySelector("#teaching_level").value = level_value;
        console.log(teaching_level);


        // get course of grp 1 if user check grp1 
        var grp1 = document.querySelector("#grp1")
        if (grp1.checked == true) {
            var select_course = document.getElementById('sort_courses_grp1');
            var course_value = select_course.options[select_course.selectedIndex].value;
            var course_input = document.querySelector("#course_grp1").value = course_value;
            console.log(course_input);
        }
        // get course of grp 2 if user check grp2
        var grp2 = document.querySelector("#grp2")
        if (grp2.checked == true) {
            var select_course = document.getElementById('sort_courses_grp2');
            var course_value = select_course.options[select_course.selectedIndex].value;
            var course_input = document.querySelector("#course_grp2").value = course_value;
            console.log(course_input);

        }
        // get course of grp 2 if user check grp2
        var grp3 = document.querySelector("#grp3")
        if (grp3.checked == true) {
            var select_course = document.getElementById('sort_courses_grp3');
            var course_value = select_course.options[select_course.selectedIndex].value;
            var course_input = document.querySelector("#course_grp3").value = course_value;
            console.log(course_input);

        }

    }

});


function return_btn() {
    next.setAttribute("style", "display:block;");
    submitAddUser.setAttribute("style", "display:none;");
    // hide cuurent feild of teacher or student inputs 
    if (user_role.value == "student") {
        document.querySelector(".studentAdd").setAttribute("style", "display:none;");
    } else {
        // display teacher form 
        document.querySelector(".teacherInfoAdd").setAttribute("style", "display:none;");
    }
    // display previous form 
    document.querySelector(".side0").setAttribute("style", "display:flex;");
    document.querySelector(".side1").setAttribute("style", "display:flex;");
    document.querySelector(".d2").setAttribute("style", "display:block;");
    //make return button hidden
    document.querySelector("#return_btn").setAttribute("style", "display:none;");
}
function add_row() {
    var form = document.querySelector(".addUserForm")
    var select = form.getElementsByTagName("select")[0]
    var dob = document.querySelector("#date_of_birth").value;
    var type = select.options[select.selectedIndex].value
    var firstName = form.querySelectorAll("input")[0].value
    var lastName = form.querySelectorAll("input")[1].value
    var adress = form.querySelectorAll("input")[2].value
    var mail = form.querySelectorAll("input")[3].value
    var password = form.querySelectorAll("input")[4].value
    var phone = form.querySelectorAll("input")[6].value

    var table = document.getElementById("data_table");
    var table_len = (table.rows.length);

    document.getElementById("data_table").querySelectorAll("tr")[table_len - 1].querySelector("td").classList.remove("lastChildLeft")
    document.getElementById("data_table").querySelectorAll("tr")[table_len - 1].querySelectorAll("td")[3].classList.remove("lastChildRight")

    var row = table.insertRow(table_len).outerHTML = "<tr id='User" + table_len + "' ><td id='FirstName' class='lastChildLeft'>" + firstName + "</td><td id='LastName'>" + lastName + "</td><td id='mail'>" + mail + "</td><td id='type' class='lastChildRight'>" + type + "</td><td class='hidden_info' id='Password'>" + password + "</td><td class='hidden_info' id='Adress'>" + adress + "</td><td class='hidden_info' id='Phone'>" + phone + "</td><td><button id='deleteUser' class='delete' onclick='delete_popup()' onmouseover='hundle_delete()'><img src='../icons/user-delete.svg' width='30px' alt='' srcset=''></button><button id='editUser'  class='edit' onclick='edit_form()' onmouseover='hundle_edit()'><img src='../icons/user-update.svg' width='30px' alt='' srcset=''></button></td></tr>";
    closeform()
    document.querySelector(".table-wrapper").scrollTo(0, 99999999999999999999)
}


function hundle_edit() {
    let ypos = event.clientY;
    ypos -= 138
    document.querySelector(".editForm").setAttribute("style", "transform-origin : 915px " + ypos + "px")
    window.value = ypos
}


function hundle_delete() {
    let ypos = event.clientY;
    ypos -= 360
    document.querySelector(".deletePopup").setAttribute("style", "transform-origin : 855px " + ypos + "px")
    window.value = ypos
}


function edit_form() {
    let userid = event.target.parentElement.parentElement.parentElement.id
    let role = document.getElementById(userid).querySelector("#role").innerHTML;

    let FirstName = document.getElementById(userid).querySelector("#FirstName").innerHTML;
    let LastName = document.getElementById(userid).querySelector("#LastName").innerHTML;
    let username = document.getElementById(userid).querySelector("#username").innerHTML;
    let date_of_birth = document.getElementById(userid).querySelector("#date_of_birth").innerHTML;
    let password = document.getElementById(userid).querySelector("#password").innerHTML;
    let place_of_birth = document.getElementById(userid).querySelector("#place_of_birth").innerHTML;


    //attribute from original table
    if (role == "student") {
        let id = document.querySelectorAll("#Id_form")[0]
        id.setAttribute("value", userid)

        let FName = document.querySelectorAll("#FirstName_form")[0];
        FName.value = FirstName;

        let LName = document.querySelectorAll("#LastName_form")[0];
        LName.value = LastName;

        let usernameform = document.querySelectorAll("#username_form")[0];
        usernameform.value = username

        let place_of_birth_form = document.querySelectorAll("#place_of_birth_form")[0];
        place_of_birth_form.value = place_of_birth

        let date_of_birth_form = document.querySelectorAll("#date_of_birth_form")[0];
        date_of_birth_form.value = date_of_birth;

        let PasswordF = document.querySelectorAll("#Password_form")[0];
        PasswordF.value = password;

        document.querySelectorAll(".editForm")[0].setAttribute("style", "transform: scale(1)");
        document.querySelector("body").setAttribute("style", "pointer-events: none");
        document.querySelector("body").classList.add("bodydisable");
        // change selected element of student grade in sort_degree_edited
        let student_grade = document.getElementById(userid).querySelector("#grade").innerHTML;
        // change grade iput status to send it to spring boot 
        let student_grade_edited = document.querySelector("#grade_form");
        student_grade_edited.value = student_grade;
        //change ndirha kima ta3 add 
        switch (student_grade) {
            case "L1":
                document.querySelectorAll("#sort_degree_edited")[2].selected = true;
                break;
            case "L2":
                document.querySelectorAll("#sort_degree_edited")[1].selected = true;
                break;
            case "L3":

                document.querySelectorAll("#sort_degree_edited")[0].selected = true;
                break;

            default:
                break;
        }
        // change selected class for student in edit form 
        let student_class = document.getElementById(userid).querySelector("#label").innerHTML;
        // change class input ... 
        let student_class_edited = document.querySelector("#class_name_editF");
        student_class_edited.value = student_class;
        switch (student_class) {
            case "GRP3":
                document.querySelectorAll("#sort_class_edited")[2].selected = true;
                break;
            case "GRP2":
                document.querySelectorAll("#sort_class_edited")[1].selected = true;
                break;
            case "GRP1":

                document.querySelectorAll("#sort_class_edited")[0].selected = true;
                break;

            default:
                break;
        }


    } else {
        let id = document.querySelectorAll("#Id_form")[1]
        id.setAttribute("value", userid)
        window.location.href = "./teacherProfile?id=" + id.value


        // let FName = document.querySelectorAll("#FirstName_form")[1];
        // FName.value = FirstName;

        // let LName = document.querySelectorAll("#LastName_form")[1];
        // LName.value = LastName;

        // let usernameform = document.querySelectorAll("#username_form")[1];
        // usernameform.value = username

        // let place_of_birth_form = document.querySelectorAll("#place_of_birth_form")[1];
        // place_of_birth_form.value = place_of_birth

        // let date_of_birth_form = document.querySelectorAll("#date_of_birth_form")[1];
        // date_of_birth_form.value = date_of_birth;

        // let PasswordF = document.querySelectorAll("#Password_form")[1];
        // PasswordF.value = password;

        // document.querySelectorAll(".editForm")[1].setAttribute("style", "transform: scale(1)");
        // document.querySelector("body").setAttribute("style", "pointer-events: none");
        // document.querySelector("body").classList.add("bodydisable");
    }


}
function hundleUpdate() {

    let student_grade_edited = document.querySelector("#grade_form");
    let student_class_edited = document.querySelector("#class_name_editF");
    // for grade update (level)
    let select_grade = document.getElementById('sort_degree_edited_Full');
    let grade_value = select_grade.options[select_grade.selectedIndex].value;
    student_grade_edited.value = grade_value;
    // alert("grade (level)" + student_grade_edited.value)

    // for class update 
    let select_class = document.getElementById('student_class_edited_Full');
    let class_value = select_class.options[select_class.selectedIndex].value;
    student_class_edited.value = class_value;
    // alert("class (grp)" + student_class_edited.value)

}
function hundle_next() {
    document.querySelector(".edit_feild").querySelector(".cote1").setAttribute("style", "display:none");
    document.querySelector(".edit_feild").querySelector(".cote2").setAttribute("style", "display:none");
    document.querySelector(".groupe_editing").setAttribute("style", "display:flex");
}
function delete_popup() {
    var userid = event.target.parentElement.parentElement.parentElement.id

    let id = document.querySelector("#id_person")
    id.setAttribute("value", userid);
    document.querySelector(".deletePopup").setAttribute("style", "transform:scale(1) ;");
    document.querySelector("body").setAttribute("style", "pointer-events: none");
    document.querySelector("body").classList.add("bodydisable");
}

function delete_row() {

    var id = document.querySelector("#Id_form").value
    var table = document.getElementById("data_table").querySelector("tbody");
    var table_len = (table.rows.length);

    var idNum = id.replace(/\D/g, '');

    if (idNum == table_len) {
        document.getElementById("data_table").querySelectorAll("tr")[table_len - 1].querySelector("td").classList.add("lastChildLeft")
        document.getElementById("data_table").querySelectorAll("tr")[table_len - 1].querySelectorAll("td")[3].classList.add("lastChildRight")
    }

    document.getElementById(id).outerHTML = "";
    closeform()
}

function update_row() {

    var userid = document.getElementById("Id_form").value

    var user = document.getElementById(userid)
    user.querySelectorAll("td")[0].innerHTML = document.getElementById("FirstName_form").value
    user.querySelectorAll("td")[1].innerHTML = document.getElementById("LastName_form").value
    user.querySelectorAll("td")[2].innerHTML = document.getElementById("Mail_form").value

    closeform()
}


function closeform() {
    document.querySelectorAll(".editForm")[0].setAttribute("style", "transform:scale(0); transform-origin:915px+" + window.value + "px;");
    document.querySelectorAll(".editForm")[1].setAttribute("style", "transform:scale(0); transform-origin:915px+" + window.value + "px;");
    document.querySelector(".deletePopup").setAttribute("style", "transfrom:scale(0); transform-origin:855px+" + window.value + "px;");
    document.querySelector(".addUserForm").setAttribute("style", "transform:translateX(270px) translateY(-270px) scale(0) ;");
    document.querySelector("body").setAttribute("style", "pointer-events: all");
    document.querySelector("body").classList.remove("bodydisable");

}

document.querySelector(".AddUserIcon").addEventListener("click", () => {

    document.querySelector(".addUserForm").setAttribute("style", "transform:translateX(0px) translateY(0px) scale(1) ;");
    document.querySelector("body").setAttribute("style", "pointer-events: none");
    document.querySelector("body").classList.add("bodydisable");

});
