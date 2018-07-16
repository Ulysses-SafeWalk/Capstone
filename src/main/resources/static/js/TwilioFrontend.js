
function sendIt(latitude, longitude, buttonType){
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/', true);
    //Send the proper header information along with the request
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("latitude=" + latitude + "&longitude=" + longitude + "&buttonType=" + buttonType);
    console.log('xhr request process');
}

$('#emergency').click(function() {
    let geocoder = new google.maps.Geocoder;
    let crd = window.SafeWalkGeo.position;
    let latitude = parseFloat(crd.latitude);
    let longitude = parseFloat(crd.longitude);
    let buttonType = "0";

    sendIt(latitude, longitude, buttonType);


});

$('#safe').click(function() {
    let latitude = 37.3320;
    let longitude = 77.2736;
    let buttonType = "1";

    sendIt(latitude, longitude, buttonType);
});

