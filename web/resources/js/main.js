/**
 * Created by Elyor on 8/17/2014.
 */
function formatDate(date) {
    return date.toLocaleDateString();
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

$(function () {

    $('.modal').modal({
        backdrop: true,
        keyboard: true,
        show: false
    });

    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd'
    });

});