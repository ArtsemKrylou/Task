window.onload = function () {
    drawTable();
};

function drawTable() {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var table = document.getElementById("mainTable");
            var arr = JSON.parse(xhr.responseText);
            for (var i = 0; i < arr.length; i++) {
                var row = document.createElement("tr");
                for (var key in arr[i]) {
                    if (key !== "id") {
                        var data = document.createElement("td");
                        var id = key + "_" + arr[i]["id"];
                        var value = arr[i][key];
                        data.innerHTML = "<td><input type=\"text\" id=\"" + id + "\" value=\"" + value + "\"></td>";
                        row.appendChild(data);
                    }
                }
                var buttons = document.createElement("td");
                buttons.innerHTML = "<td><input type=\"button\" value=\"change\" onclick=\"update(" + arr[i]["id"] + ")\"/></td>" +
                    "<td><input type=\"button\" value=\"remove\" onclick=\"remove(" + arr[i]["id"] + ")\"/></td>";
                row.appendChild(buttons);
                table.appendChild(row);
            }
        }
    };

    xhr.open('get', 'updateInfo', true);
    xhr.send();
}

function clearTable() {
    document.getElementById("mainTable").innerHTML = '';
}

function redrawTable() {
    clearTable();
    drawTable();
}

function create() {
    // $.ajax({
    //     url: 'subjects',
    //     type: "post",
    //     data: {
    //         name: $('#new_name').val(),
    //         mark: $('#new_mark').val()
    //
    //     },
    //     success: function (responseText) {
    //         location.reload();
    //     }
    // });
    var xhr = new XMLHttpRequest();

    xhr.open('post', 'subjects', true);
    var name = document.getElementById("new_name").value;
    var mark = document.getElementById("new_mark").value;
    var body = {
        name: name,
        mark: mark
    };

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            redrawTable();
        }
    };


    if (validate(name, mark)) {
        xhr.send(JSON.stringify(body));

        // if (xhr.status != 200) {
        //     console.log(xhr.status + ': ' + xhr.statusText);
        // } else {
        //     console.log(xhr.responseText);
        //     location.reload();
        // }
    }
}

function update(id) {
    var name = document.getElementById('name_' + id).value;
    var mark = document.getElementById('mark_' + id).value;
    var xhr = new XMLHttpRequest();

    xhr.open('put', 'subjects', true);
    var body = {
        name: name,
        mark: mark,
        id: id
    };

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            redrawTable();
        }
    };


    if (validate(name, mark)) {
        xhr.send(JSON.stringify(body));

    }
    // $.ajax({
    //     url: 'subjects',
    //     type: "put",
    //     data: {
    //         name: name,
    //         mark: mark,
    //         id: id
    //     },
    //     success: function (responseText) {
    //         location.reload();
    //     }
    // });

}

function remove(id) {
    var xhr = new XMLHttpRequest();
    xhr.open('delete', 'subjects', true);

    var body = {
        id: id
    };

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            redrawTable();
        }
    };

    xhr.send(JSON.stringify(body));

}

function validate(name, mark) {
    if (name.toString() === "") {
        alert("name not null");
        return false
    }
    if (mark < 1 || mark > 100) {
        alert("mark should be from 1 to 100");
        return false
    }

    return true
}