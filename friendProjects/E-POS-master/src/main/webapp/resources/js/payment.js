jQuery(document).ready(function () {
//                        $.noConflict();
    $('label[id*="card_label"]').hide();
    $('label[id*="card_no_label"]').hide();

    $('label[id*="card_holder_label"]').hide();
    $('label[id*="card_expiray_label"]').hide();
    $('label[id*="card_cvc_label"]').hide();



    $('input[id*="cardNumber"]').hide();

    $('input[id*="cardholder"]').hide();
    $('input[id*="cardexpiry"]').hide();
    $('input[id*="cvc"]').hide();

    $('input[id*="payMentcash"]').hide();
    $('table[id*="cardType"]').hide();
    $('input:radio[id*=cardType]').prop("disabled", true);
    $('input:radio[id*=cardType]').prop("required", false);
    $('input[id*="cardNumber"]').prop("required", false);
    $('input[id*="cardNumber"]').prop("disabled", true);

    $('input[id*="cardholder"]').prop("disabled", true);
    $('input[id*="cardexpiry"]').prop("disabled", true);
    $('input[id*="cvc"]').prop("disabled", true);

    $('input[id*="cardholder"]').prop("required", false);
    $('input[id*="cardexpiry"]').prop("required", false);
    $('input[id*="cvc"]').prop("required", false);


    $('table[id*="pamentType"]').click(function () {
        var value = $('input:radio[id*=pamentType]:checked').val();
        if (value == "card") {
//                                alert("in card type");
            $('table[id*="cardType"]').show();
            $('input[id*="cardNumber"]').attr("disabled", false);
            $('input:radio[id*=cardType]').prop("disabled", false);
            $('input:radio[id*=cardType]').prop("required", true);
            $('input[id*="cardNumber"]').prop("required", true);

            $('input[id*="cardholder"]').prop("disabled", false);
            $('input[id*="cardexpiry"]').prop("disabled", false);
            $('input[id*="cvc"]').prop("disabled", false);
            $('label[id*="card_label"]').show();
            $('input[id*="cardholder"]').prop("required", true);
            $('input[id*="cardexpiry"]').prop("required", true);
            $('input[id*="cvc"]').prop("required", true);

        }
        if (value === "cash") {
//                                alert("in card type");
            $('input[id*="payMentcash"]').show();
            $('input:radio[id*=cardType]:checked').attr('checked', false);
            $('table[id*="cardType"]').hide();
            $('input[id*="cardNumber"]').hide();

            $('input[id*="cardNumber"]').attr("disabled", true);
            $('input:radio[id*=cardType]').prop("disabled", true);


            $('label[id*="card_no_label"]').hide();
            $('label[id*="card_label"]').hide();
            $('label[id*="card_holder_label"]').hide();
            $('label[id*="card_expiray_label"]').hide();
            $('label[id*="card_cvc_label"]').hide();



            $('input[id*="cardNumber"]').hide();

            $('input[id*="cardholder"]').hide();
            $('input[id*="cardexpiry"]').hide();
            $('input[id*="cvc"]').hide();

        }
//                            alert(value);
    });
    $('table[id*="cardType"]').click(function () {
//                            alert("here");
        $('input[id*="cardNumber"]').show();


        $('label[id*="card_no_label"]').show();

        $('label[id*="card_holder_label"]').show();
        $('label[id*="card_expiray_label"]').show();
        $('label[id*="card_cvc_label"]').show();



        $('input[id*="cardNumber"]').show();

        $('input[id*="cardholder"]').show();
        $('input[id*="cardexpiry"]').show();
        $('input[id*="cvc"]').show();
    });

});


