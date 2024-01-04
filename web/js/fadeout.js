$(document).ready(function () {
    // Desvanecer el div después de 5 segundos
    function fadeOutDiv(divId) {
        setTimeout(function () {
            $("#" + divId).addClass("fadeOut").delay(500).queue(function (next) {
                $(this).hide();
                next();
            });
        }, 5000);
    }

    // Mostrar el mensaje de error si está presente
    var errorMensaje = "<%= (String) request.getAttribute('error') %>";
    if (errorMensaje) {
        fadeOutDiv("errorDiv");
    }

    // Mostrar el mensaje de registro correcto si está presente
    var sucessMensaje = "<%= (String) request.getAttribute('sucess') %>";
    if (sucessMensaje) {
        fadeOutDiv("sucessDiv");
    }
});