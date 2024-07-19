function orders() {
    console.log('click');
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var url = "/cart/orders";
    var cartOrderDtoList = [];

    $(".cartItemId").each(function(index, element) {
        var cartItemId = $(element).val();
        var count = $(element).siblings(".count").val();

        var cartOrderItem = {
            cartItemId: cartItemId,
            count: count
        };

        cartOrderDtoList.push(cartOrderItem);
    });

    var paramData = {
        cartOrderDtoList: cartOrderDtoList,
        phonenum: $("#phonenum").val(),
        postcode: $("#postcode").val(),
        address1: $("#address1").val(),
        address2: $("#address2").val(),
        paymentMethod: $("input[name='paymentMethod']:checked").val()
    };

    var param = JSON.stringify(paramData);

    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: param,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        dataType: "json",
        cache: false,
        success: function(result, status) {
            alert("주문이 완료되었습니다.");
            location.href = "/orders";
        }
    });
}