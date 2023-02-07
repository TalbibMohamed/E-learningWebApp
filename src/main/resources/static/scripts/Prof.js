function edit_row(no) {

  document.getElementById("editUser" + no).style.display = "none";
  document.getElementById("save_button" + no).style.display = "block";

  var name = document.getElementById("Name" + no);
  var lname = document.getElementById("lname" + no);
  var country = document.getElementById("mail" + no);
  var age = document.getElementById("type" + no);

  var name_data = name.innerHTML;
  var lname_data = lname.innerHTML;
  var country_data = country.innerHTML;
  var age_data = age.innerHTML;

  name.innerHTML = "<input type='text' id='name_text" + no + "' value='" + name_data + "'>";
  lname.innerHTML = "<input type='text' id='lname_text" + no + "' value='" + lname_data + "'>";
  country.innerHTML = "<input type='text' id='country_text" + no + "' value='" + country_data + "'>";
  age.innerHTML = "<input type='text' id='age_text" + no + "' value='" + age_data + "'>";
}


function save_row(no) {
  var name_val = document.getElementById("name_text" + no).value;
  var lname_val = document.getElementById("lname_text" + no).value;
  var country_val = document.getElementById("country_text" + no).value;
  var age_val = document.getElementById("age_text" + no).value;

  document.getElementById("Name" + no).innerHTML = name_val;
  document.getElementById("lname" + no).innerHTML = lname_val;
  document.getElementById("mail" + no).innerHTML = country_val;
  document.getElementById("type" + no).innerHTML = age_val;

  document.getElementById("editUser" + no).style.display = "block";
  document.getElementById("save_button" + no).style.display = "none";
}


function delete_row(no) {

  var table = document.getElementById("data_table").querySelector("tbody");
  var table_len = (table.rows.length) - 1;

  if (no == table_len) {
    document.getElementById("data_table").querySelectorAll("tr")[table_len - 1].querySelector("td").classList.add("lastChildLeft")
    document.getElementById("data_table").querySelectorAll("tr")[table_len - 1].querySelectorAll("td")[3].classList.add("lastChildRight")
  }

  document.getElementById("User" + no + "").outerHTML = "";
}

function showstudents() {
  window.location.href = "./studentsList"
}
function showDashboard() {
  window.location.href = "./teacherDashboard";
}
function showcourses() {
  window.location.href = "./courseManagement"
}

function showschedule() {
  window.location.href = "./schedule"
}




function add_row() {
  var new_name = document.getElementById("new_name").value;
  var new_lname = document.getElementById("new_lname").value;
  var new_country = document.getElementById("new_country").value;
  var new_age = document.getElementById("new_age").value;

  var table = document.getElementById("data_table");
  var table_len = (table.rows.length) - 1;

  document.getElementById("data_table").querySelectorAll("tr")[table_len - 1].querySelector("td").classList.remove("lastChildLeft")
  document.getElementById("data_table").querySelectorAll("tr")[table_len - 1].querySelectorAll("td")[3].classList.remove("lastChildRight")

  var row = table.insertRow(table_len).outerHTML = "<tr id='User" + table_len + "' ><td id='Name" + table_len + "' class='lastChildLeft'>" + new_name + "</td><td id='lname" + table_len + "'>" + new_lname + "</td><td id='mail" + table_len + "'>" + new_country + "</td><td id='type" + table_len + "' class='lastChildRight'>" + new_age + "</td><td><button id='deleteUser' class='delete' onclick='delete_row(" + table_len + ")'><img src='/assets/icons/user-delete.svg' width='30px' alt='' srcset=''></button><button id='save_button" + table_len + "' style='display: none;' class='save'  onclick='save_row(" + table_len + ")'><img src='/assets/icons/user-save.svg' width='30px' alt='' srcset=''></button><button id='editUser" + table_len + "'  class='edit' onclick='edit_row(" + table_len + ")'><img src='/assets/icons/user-update.svg' width='30px' alt='' srcset=''></button></td></tr>";
  edit_row(table_len);
  document.getElementById("new_name").value = "";
  document.getElementById("new_lname").value = "";
  document.getElementById("new_country").value = "";
  document.getElementById("new_age").value = "";

  document.querySelector(".table-wrapper").scrollTo(0, 99999999999999999999);
}

(function (document) {
  'use strict';

  var LightTableFilter = (function (Arr) {

    var _input;

    function _onInputEvent(e) {
      _input = e.target;
      var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
      Arr.forEach.call(tables, function (table) {
        Arr.forEach.call(table.tBodies, function (tbody) {
          Arr.forEach.call(tbody.rows, _filter);
        });
      });
    }

    function _filter(row) {
      var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
      row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
    }

    return {
      init: function () {
        var inputs = document.getElementsByClassName('light-table-filter');
        Arr.forEach.call(inputs, function (input) {
          input.oninput = _onInputEvent;
        });
      }
    };
  })(Array.prototype);

  document.addEventListener('readystatechange', function () {
    if (document.readyState === 'complete') {
      LightTableFilter.init();
    }
  });

})(document);

