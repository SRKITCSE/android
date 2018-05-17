

$(document).ready(function(){
    
    $("#signin_button").click(function(){
        $("#signin_form").show();
        $("#signup_form").hide();
        $("#booking_form").hide();
        
    });
    $("#signup_button").click(function(){
        $("#signin_form").hide();
        $("#signup_form").show();
        $("#booking_form").hide();
    });
    $("#booking_button").click(function(){
        $("#signin_form").hide();
        $("#signup_form").hide();
        $("#booking_form").show();
    });
});

$(document).ready(function() { 
  $("#carid" ).change(function() {
      var value = $(this).find('option:selected').attr("data-cost");
      //alert(value);
      document.getElementById("carcost").innerHTML = value;
  });
});

//
//function getcardetails(){
//    //alert('hi');
//    alert(this.options[this.selectedIndex].getAttribute('customatrribute'));
//}