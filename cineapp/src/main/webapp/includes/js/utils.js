function mostraToast(mensagem, tipo) {
    const toastLiveExample = document.getElementById('liveToast')
    toastLiveExample.classList.remove("text-bg-danger");
    toastLiveExample.classList.remove("text-bg-primary");
    toastLiveExample.classList.remove("text-bg-warning");
    toastLiveExample.classList.add("text-bg-"+tipo);
    const corpoToast = document.getElementById('msgToast')
    corpoToast.innerHTML = mensagem;

    const toast = new bootstrap.Toast(toastLiveExample)
   
    toast.show();    
}

