document.addEventListener('DOMContentLoaded', function () {
    const diasContainer = document.querySelector('.dias-container');
    const anteriorBtn = document.getElementById('anterior');
    const siguienteBtn = document.getElementById('siguiente');

    let fechaActual = new Date();
    let diaSeleccionado = null; // Almacena la referencia al día seleccionado

    function actualizarDias() {
        // Limpia los días existentes
        diasContainer.innerHTML = '';

        // Genera los próximos 7 días
        for (let i = 0; i < 7; i++) {
            const dia = new Date();
            dia.setDate(fechaActual.getDate() + i);

            const diaElemento = document.createElement('div');
            diaElemento.classList.add('dia');
            diaElemento.textContent = obtenerFormatoFecha(dia);

            // Agrega evento de clic para seleccionar el día y redirigir a otra página
            diaElemento.addEventListener('click', function () {
                seleccionarDia(this);
            });

            diasContainer.appendChild(diaElemento);
        }
    }

    function obtenerFormatoFecha(fecha) {
        const opciones = {weekday: 'short', day: 'numeric', month: 'short'};
        return fecha.toLocaleDateString('es-ES', opciones);
    }

    function retrocederDias() {
        if (fechaActual.getDate() > new Date().getDate()) {
            fechaActual.setDate(fechaActual.getDate() - 1);
            actualizarDias();
        }
    }

    function avanzarDias() {
        if (fechaActual.getDate() < new Date().getDate() + 30) {
            fechaActual.setDate(fechaActual.getDate() + 1);
            actualizarDias();
        }
    }

    function seleccionarDia(diaElemento) {
        // Elimina la selección anterior
        if (diaSeleccionado) {
            diaSeleccionado.classList.remove('seleccionado');
        }

        // Establece el nuevo día seleccionado
        diaSeleccionado = diaElemento;

        // Agrega la clase 'seleccionado' al día seleccionado
        diaSeleccionado.classList.add('seleccionado');

        // Obtiene la fecha del día seleccionado
        const fechaDiaSeleccionado = new Date(fechaActual);
        fechaDiaSeleccionado.setDate(fechaActual.getDate() + Array.from(diasContainer.children).indexOf(diaElemento));

        // Crea un enlace (<a>) dinámicamente y lo simula haciendo clic para redirigir
        const enlace = document.createElement('a');
        enlace.href = `pelicula?fecha=${fechaDiaSeleccionado.toISOString()}`;
        enlace.click();
    }


    // Inicializa el carrusel
    actualizarDias();

    // Agrega eventos a los botones
    anteriorBtn.addEventListener('click', retrocederDias);
    siguienteBtn.addEventListener('click', avanzarDias);

    // Agrega evento de teclado para retroceder (tecla retroceso)
    document.addEventListener('keydown', function (event) {
        if (event.key === 'Backspace') {
            retrocederDias();
        }
    });
});