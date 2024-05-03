 var campoDiretor = "";
 
 // Function que escreve a mensagem de confirmação da div, ajusta o conteúdo da variável 
 // campoProduto e mostra a div modal
 function mostraConfDelete(codDiretor) {
    campoDiretor = codDiretor;
    jQuery("#confirmMsg").html("Tem <strong>CERTEZA</strong> que deseja excluir o diretor de código " + codDiretor + "? <br/><br/>Essa operação não poderá ser desfeita.");
    jQuery("#conf-modal").modal('show');
 }

 // Adiciona um método para quando clicar no botão 'sim' do modal
 jQuery("#conf-btn-sim").on("click", function () {
    jQuery("#conf-modal").modal('hide');
    location.href = 'Diretores?action=excluiDiretorDB&codDiretor=' + campoDiretor;
 });
