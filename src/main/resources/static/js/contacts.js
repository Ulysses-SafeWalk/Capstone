"use strict";

(function () {

    $('#submit').click(function (event) {
        $('#firstName').val($('#contactFirst').valueOf());
        $('#lastName').val($('#contactLast').valueOf());
        $('#phoneNumber').val($('#contactNumber').valueOf());
    });
})();