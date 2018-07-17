"use strict";

(function () {

    $('#submit').click(function (event) {
        $('#firstName').val($('#contactFirst').val());
        $('#lastName').val($('#contactLast').val());
        $('#phoneNumber').val($('#contactNumber').val());
    });
})();