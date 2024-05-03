const campos_colunas = new Map();

campos_colunas.set('COD_DIRETOR', 'codDiretor');
campos_colunas.set('NOME', 'nome');


jQuery(document).ready(function () {
    jQuery("input[name=nome]").focus();
});

// Define que a function enviaForm deve ser chamada ao enviar o form
// jQuery("#formDiretorManut").submit(enviaForm);

function enviaForm(event) {
    // Desabilita o botão salvar enquanto envia os dados, para evitar que o
    // usuário clique várias vezes
    jQuery("#botaoSalvar").attr("disabled", true);
   
    // Barra o funcionamento normal do botão submit
    event.preventDefault();

    // Pega o valor do action do form
    var form = jQuery(this),
            servlet = form.attr("action"),
            url = servlet;

    // Envia os dados usando post de forma assíncrona
    var posting = jQuery.post(url, jQuery("#formDiretorManut").serialize());

    posting.done(function (response) {
        const dadosResponse = response.split("#");
        if (dadosResponse[0] == "true") {
            mostraToast("Dados gravados com sucesso!", "success");
            setTimeout(function () {
                var link = 'Diretores?action=consultaDiretores';
                location.href = link;
            }, 1000);
        } else {
            jQuery("#botaoSalvar").removeAttr('disabled');
            var dadosErro = response.split("#");
            if (dadosErro.length > 2) {
                if (dadosErro[0] == "20001") {
                    jQuery("#" + campos_colunas.get(dadosErro[2])).focus();
                    mostraToast("ERRO: " + dadosErro[1], "danger");
                }
            } else {
                mostraToast("ERRO: " + dadosErro[1], "danger");
            }
        }
    });
}

