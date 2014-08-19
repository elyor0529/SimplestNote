/**
 * Created by Elyor on 8/17/2014.
 */
function formatDate(date) {
    return date.toLocaleDateString();
}

$(function () {

    $('#logoutModal').modal({
        backdrop: true,
        keyboard: true,
        show: false
    });

    $('input.datepicker').datepicker({
        format: 'yyyy-mm-dd'
    });

});