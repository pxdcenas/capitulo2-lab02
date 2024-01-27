function eliminar(usuario){
    if(confirm('¿Está seguro que desea eliminar ' + usuario + '?')){
        $.ajax({
            type: "POST",
            url: "/eliminar",
            data: {
                usuario: usuario
            },
            success: function(response){
                window.location.href = "/listarUsuarios";
            }
        });
    }
}