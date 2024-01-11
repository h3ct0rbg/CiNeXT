document.addEventListener('DOMContentLoaded', function () {
    // Seleccionar el elemento de entrada de texto para asientos
    var inputAsientos = document.getElementById('asientos');

    // Seleccionar todos los elementos de tipo checkbox
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');

    // Agregar un evento de cambio a cada checkbox
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            actualizarAsientosSeleccionados(this);
        });
    });

    function actualizarAsientosSeleccionados(checkbox) {
        // Obtener el valor actual del input
        var asientosSeleccionados = inputAsientos.value;

        if (checkbox.checked) {
            // Agregar el ID del asiento seleccionado al input con una barra vertical
            inputAsientos.value = asientosSeleccionados + '[ ' + checkbox.value + ' ]';
        } else {
            // Eliminar el ID del asiento deseleccionado del input
            inputAsientos.value = asientosSeleccionados.replace('[ ' + checkbox.value + ' ]', '').trim();
        }
    }
});