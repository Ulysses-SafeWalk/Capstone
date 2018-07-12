

$('#emergency').click(function(event) {
    let geocoder = new google.maps.Geocoder;
    let crd = window.SafeWalkGeo.position;
    let latitude = parseFloat(crd.latitude);
    let longitude = parseFloat(crd.longitude);
    let buttonType = "0";
        $.post("/",
            latitude, longitude, buttonType

)

});