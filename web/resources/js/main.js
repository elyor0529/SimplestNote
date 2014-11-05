/**
 * Created by Elyor on 8/17/2014.
 */
function formatDate(date) {
    return date.toLocaleDateString();
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function split( val ) {
    return val.split( /,\s*/ );
}
function extractLast( term ) {
    return split( term ).pop();
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