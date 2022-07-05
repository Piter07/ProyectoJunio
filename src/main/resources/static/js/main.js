 function abrirAlbum(id){
 document.getElementById(id).addEventListener("click", function () {
        window.location.href = "/login";
    });
    document.getElementById(id).style.cursor = "pointer";
 }

