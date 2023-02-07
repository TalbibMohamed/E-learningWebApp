table_len = document.querySelectorAll(".teacherTR").length;
for (var i = 0; i < table_len; i++) {
    document.querySelectorAll(".teacherTR")[i].setAttribute("id", i);
}
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
function update_sort_levelV2() {
    var select_level = document.getElementById('sort_levelV2');
    var level_value = select_level.options[select_level.selectedIndex].value;
    var teaching_level = document.querySelector("#teaching_levelV2").value = level_value;
    console.log(teaching_level);
    if (teaching_level === "L2") {
        console.log(teaching_level);

        for (var i = 0; i < document.querySelectorAll(".l2_coursesV2").length; i++) {
            document.querySelectorAll(".l2_coursesV2")[i].selected = true;
            document.querySelectorAll(".l2_coursesV2")[i].setAttribute("style", "display:flex");
        }
        for (var i = 0; i < document.querySelectorAll(".l3_coursesV2").length; i++) {

            document.querySelectorAll(".l3_coursesV2")[i].setAttribute("style", "display:none");
        }

    }
    if (teaching_level === "L3") {
        console.log(teaching_level);
        for (var i = 0; i < document.querySelectorAll(".l2_courses").length; i++) {
            document.querySelectorAll(".l2_coursesV2")[i].setAttribute("style", "display:none");
        }
        for (var i = 0; i < document.querySelectorAll(".l3_courses").length; i++) {
            document.querySelectorAll(".l3_coursesV2")[i].selected = true;
            document.querySelectorAll(".l3_coursesV2")[i].setAttribute("style", "display:flex");
        }

    }
}
submitAddUser = document.querySelector("#Add_UserBTN");
submitAddUser.addEventListener("mouseover", () => {


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
        console.log(grp1.value);
        console.log(course_input);
    }
    // get course of grp 2 if user check grp2
    var grp2 = document.querySelector("#grp2")
    if (grp2.checked == true) {
        var select_course = document.getElementById('sort_courses_grp2');
        var course_value = select_course.options[select_course.selectedIndex].value;
        var course_input = document.querySelector("#course_grp2").value = course_value;
        console.log(grp2.value);
        console.log(course_input);
    }
    // get course of grp 2 if user check grp2
    var grp3 = document.querySelector("#grp3")
    if (grp3.checked == true) {
        var select_course = document.getElementById('sort_courses_grp3');
        var course_value = select_course.options[select_course.selectedIndex].value;
        var course_input = document.querySelector("#course_grp3").value = course_value;
        console.log(grp3.value);
        console.log(course_input);
    }



});

function just_hundle() {
    // miss function 
    /*
     *not hide the current update  
     */
    document.querySelector(".teacherAssignment").setAttribute("style", "transform:scale(0)");
}


function hundle_delete() {
    let userid = event.target.parentElement.parentElement.parentElement.id;
    var teachingLevel = document.getElementById(userid).querySelector("#teachingLevel").innerHTML;
    var teachingClass = document.getElementById(userid).querySelector("#teachingClass").innerHTML;
    var teachingCourse = document.getElementById(userid).querySelector("#teachingCourse").innerHTML;
    // document.querySelectorAll(".forForm").length;

    document.querySelector(".deleteAssignment").setAttribute("style", "transform:scale(1)");
    var old_level = document.querySelectorAll(".old_level")[1].value = teachingLevel;
    var old_class = document.querySelectorAll(".old_class")[1].value = teachingClass;
    var old_course = document.querySelectorAll(".old_course")[1].value = teachingCourse;
}
function AddingTeachInfo() {
    // get teaching level from drop down 
    var select_level = document.getElementById('sort_levelV2');
    var level_value = select_level.options[select_level.selectedIndex].value;
    var teaching_level = document.querySelector("#teaching_levelV2").value = level_value;
    console.log(teaching_level);


    // get course of grp 1 if user check grp1 
    var grp1 = document.querySelector(".grp1")
    if (grp1.checked == true) {
        var select_course = document.querySelector('.sort_courses_grp1');

        var course_value = select_course.options[select_course.selectedIndex].value;
        var course_input = document.querySelector(".course_grp1").value = course_value;
        console.log(grp1.value);
        console.log(course_input);
    }
    // get course of grp 2 if user check grp2
    var grp2 = document.querySelector(".grp2")
    if (grp2.checked == true) {
        var select_course = document.querySelector('.sort_courses_grp2');
        var course_value = select_course.options[select_course.selectedIndex].value;
        var course_input = document.querySelector(".course_grp2").value = course_value;
        console.log(grp2.value);
        console.log(course_input);
    }
    // get course of grp 2 if user check grp2
    var grp3 = document.querySelector(".grp3")
    if (grp3.checked == true) {
        var select_course = document.querySelector('.sort_courses_grp3');
        var course_value = select_course.options[select_course.selectedIndex].value;
        var course_input = document.querySelector(".course_grp3").value = course_value;
        console.log(grp3.value);
        console.log(course_input);
    }


}
function ShowAddForm() {
    document.querySelector(".AddAssignment").setAttribute("style", "transform:scale(1)");
}
function closeform() {
    document.querySelector(".AddAssignment").setAttribute("style", "transform:scale(0)");
    document.querySelector(".teacherAssignment").setAttribute("style", "transform:scale(0)");
    document.querySelector(".deleteAssignment").setAttribute("style", "transform:scale(0)");
}


// function showcourses() {
//     window.location.href = "./courseManagement"
// }
// document.querySelector("#showCourses").addEventListener("click", () => {
//     alert("As");
// });




function hundle_update() {

    let userid = event.target.parentElement.parentElement.parentElement.id;
    var teachingLevel = document.getElementById(userid).querySelector("#teachingLevel").innerHTML;
    var teachingClass = document.getElementById(userid).querySelector("#teachingClass").innerHTML;
    var teachingCourse = document.getElementById(userid).querySelector("#teachingCourse").innerHTML;



    var old_level = document.querySelectorAll(".old_level")[0].value = teachingLevel;
    var old_class = document.querySelectorAll(".old_class")[0].value = teachingClass;
    var old_course = document.querySelectorAll(".old_course")[0].value = teachingCourse;


    // document.querySelector(".teacherAssignment").setAttribute("style", "transform:scale(0.8)");
    document.querySelector(".teacherAssignment").setAttribute("style", "transform:scale(1)");

}
function showUsers() {
    window.location.href = "./AdminDashboard";
}