
function llenarFormulario(fila){
    var id = $(fila).find(".id").text();
    var titulo = $(fila).find(".titulo").text();
    var sinopsis = $(fila).find(".sinopsis").text();
    var paginaOficial = $(fila).find(".paginaoficial").text();
    var anio = $(fila).find(".anio").text();
    var genero = $(fila).find(".genero").text();
    var duracion = $(fila).find(".duracion").text();
    var distribuidora = $(fila).find(".distribuidora").text();
    var director = $(fila).find(".director").text();
    var clasificacionEdad = $(fila).find(".clasificacionedad").text();
    var otrosDatos = $(fila).find(".otrosdatos").text();
    
    $("#txtid").val(id);
    $("#txtTitulo").val(titulo);
    $("#txtSinopsis").val(sinopsis);
    $("#txtPaginaoficial").val(paginaOficial);
    $("#txtAnio").val(anio);
    $("#txtGenero").val(genero);
    $("#txtDuracion").val(duracion);
    $("#txtDistribuidora").val(distribuidora);
    $("#txtDirector").val(director);
    $("#txtClasificacion").val(clasificacionEdad);
    $("#txtOtrosdatos").val(otrosDatos);
}

function modalEliminar(fila){
    var id = $(fila).find(".id").text();
    var titulo = $(fila).find(".titulo").text();
    
    $("#txtEliminarId").val(id);
    $("#txtEliminarTitulo").val(titulo);
}

$(document).ready(function (){
   $(document).on('click','.btnEditar', function(){
        llenarFormulario($(this).closest('tr'));
   }) ;
});

$(document).ready(function (){
   $(document).on('click','.btnEliminar', function(){
        modalEliminar($(this).closest('tr'));
   }) ;
});