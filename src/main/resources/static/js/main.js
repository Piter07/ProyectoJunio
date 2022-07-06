 function abrirAlbum(id){
 document.getElementById(id).addEventListener("click", function () {
        window.location.href = "/album-fotos";
    });
    document.getElementById(id).style.cursor = "pointer";
 }

