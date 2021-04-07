var inputs = document.getElementsByClassName('input');
var sums = document.getElementsByClassName('sum');
var save = document.getElementsByClassName('div-save');
for (let i = 0; i < inputs.length; i++) {
    inputs[i].value = (inputs[i].value == Math.round(inputs[i].value)) ? Math.round(inputs[i].value) : inputs[i].value;
    inputs[i].type = "text";
    typeDay = inputs[i].parentNode.children[3];
    colorType(inputs[i], typeDay);
}

for (let i = 0; i < sums.length; i++) {
    sums[i].value = (sums[i].value == Math.round(sums[i].value)) ? Math.round(sums[i].value) : Math.round(sums[i].value * Math.pow(10, 1)) / Math.pow(10, 1);
    sums[i].type = "number";
}
for (let i = 0; i < save.length; i++) {
    typeDay = save[i].parentNode.children[3];
    save[i].style = "background-color: " + colorType(save[i], typeDay) + "; " + "border-width: 2px";
}

function ChangeColor(Element) {
    let type = document.getElementById('typeDay');
    let check = document.getElementById('check');
    let typeDay = Element.parentNode.children[3];
    if (check.checked) {
        if(type.value == 1){
            Element.value = 8;
        }
        typeDay.value = type.value;
        colorType(Element, typeDay);
    }
}

function colorType(Element, typeDay) {
    console.log(Element.value);
    console.log(typeDay.value);
    if (typeDay.value == 1) {
        Element.style = "background-color:" + "#FFF" + "; color: #000;";
    } else if (typeDay.value == 2) {
        Element.style = "background-color:" + "#FF0" + "; color: #000;";
    } else if (typeDay.value == 3) {
        Element.style = "background-color:" + "#8DD452" + "; color: #000;";
        Element.value = "ОТ";
    } else if (typeDay.value == 4) {
        Element.style = "background-color:" + "#03AE48" + "; color: #000;";
        Element.value = "ОТ";
    } else if (typeDay.value == 5) {
        Element.style = "background-color:" + "#BED7EB" + "; color: #00000000;";
        Element.value = "0";
    } else if (typeDay.value == 6) {
        Element.style = "background-color:" + "#F9D1C5" + "; color: #000;";
        Element.value = "ДО";
    } else if (typeDay.value == 7) {
        Element.style = "background-color:" + "#DEC8EE" + "; color: #000;";
        Element.value = "Б";
    } else if (typeDay.value == 8) {
        Element.style = "background-color:" + "#FFF" + "; color: #00000000;";
        Element.value = "";
    } else if (typeDay.value == 9) {
        Element.style = "background-color:" + "#D5DADE" + "; color: #00000000;";
        Element.value = "";
    } else if (typeDay.value == 10) {
        Element.style = "background-color:" + "#F565F8" + "; color: #000;";
        Element.value = "OВ";
    } else if (typeDay.value == 11) {
        Element.style = "background-color:" + "#FFBF01" + "; color: #000;";
    } else if (typeDay.value == 12) {
        Element.style = "background-color:" + "#BD0000" + "; color: #000;";
        Element.value = "РВ";
    }
}

function updateTable() {
        var month = $("#monthInt").val();
        var year = $("#yearInt").val();
        var url = "../hours/" + month + "-" + year;
        $.get(url, function (fragment) { // get from controller
            $("#table-hours").replaceWith(fragment); // update snippet of page
        });
}

function updatingHours() {
$("#updateHoursImg").css('animation', 'updating  2s infinite linear');
$.ajax({
    type:"POST",
    url:"/hours/update",
    dataType:"json",
    success:function(response){
        $("#updateHoursImg").css('animation', 'none');
        document.location.reload();
    }
});
}

$( document ).ready(function() {
    $("#addUserBtn").click(
		function(){
			addUser();
			return false;
		}
	);
});

function addUser() {
    var form = $("#addUser");
    var url = form.attr('action');
    console.log("SAVE USER");
    $.ajax({
           type: "POST",
           url: url,
           data: form.serialize(), // serializes the form's elements.
           success: function(data)
           {
              document.location.reload();
           }
    });
}

