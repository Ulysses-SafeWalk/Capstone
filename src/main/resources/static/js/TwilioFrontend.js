
$('#emergency').click(function() {
    let geocoder = new google.maps.Geocoder;
    let crd = window.SafeWalkGeo.position;
    let latitude = parseFloat(crd.latitude);
    let longitude = parseFloat(crd.longitude);
    let buttonType = "0";

    console.log(latitude);
    console.log(longitude);
    console.log(buttonType);

    $.post("/",{latitude: latitude,
                    longitude: longitude,
                    buttonType: buttonType
        }, function(){
            console.log("data sent");
        },)});

$('#safe').click(function() {
    let latitude = 37.3320;
    let longitude = 77.2736;
    let buttonType = "1";
    $.post("/",{latitude: latitude,
            longitude: longitude,
            buttonType: buttonType
        }, function(){
            console.log("data sent");
        },)});