/**
 * Created by dima on 06.01.18.
 */
$(document).ready(function() {
    // $('.minus').click(function () {
    //     var $input = $(this).parent().find('input');
    //     var count = parseInt($input.val()) - 1;
    //     count = count < 0 ? 0 : count;
    //     $input.val(count);
    //     $input.change();
    //     return false;
    // });
    // $('.plus').click(function () {
    //     var $input = $(this).parent().find('input');
    //     $input.val(parseInt($input.val()) + 1);
    //     $input.change();
    //     return false;
    // });


    // $(".button").on("click", function() {
    //
    //     var $button = $(this);
    //     var oldValue = $button.parent().find("input").val();
    //
    //     if ($button.text() == "+") {
    //         var newVal = parseFloat(oldValue) + 1;
    //     } else {
    //         // Don't allow decrementing below zero
    //         if (oldValue > 0) {
    //             var newVal = parseFloat(oldValue) - 1;
    //         } else {
    //             newVal = 0;
    //         }
    //     }
    //
    //     $button.parent().find("input").val(newVal);
    //
    // });

    $(function() {
        $('#spinner').spinner({
            min: 2,
            max: 20,
            step: 2
        });
    });

});