 var campoDiretor = "";
 
 // Function que escreve a mensagem de confirma��o da div, ajusta o conte�do da vari�vel 
 // campoProduto e mostra a div modal
 function mostraConfDelete(codDiretor) {
    campoDiretor = codDiretor;
    jQuery("#confirmMsg").html("Tem <strong>CERTEZA</strong> que deseja excluir o diretor de c�digo " + codDiretor + "? <br/><br/>Essa opera��o n�o poder� ser desfeita.");
    jQuery("#conf-modal").modal('show');
 }

 // Adiciona um m�todo para quando clicar no bot�o 'sim' do modal
 jQuery("#conf-btn-sim").on("click", function () {
    jQuery("#conf-modal").modal('hide');
    location.href = 'Diretores?action=excluiDiretorDB&codDiretor=' + campoDiretor;
 });
