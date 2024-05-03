 var campoFilme = "";
 
 // Function que escreve a mensagem de confirmação da div, ajusta o conteúdo da variável 
 // campoPessoa e mostra a div modal
 function mostraConfDelete(codFilme) {
    campoPessoa = codFilme;
    jQuery("#confirmMsg").html("Tem <strong>CERTEZA</strong> que deseja excluir o filme de código " + codFilme + "? <br/><br/>Essa operação não poderá ser desfeita.");
    jQuery("#conf-modal").modal('show');
 }

 // Adiciona um método para quando clicar no botão 'sim' do modal
 jQuery("#conf-btn-sim").on("click", function () {
    jQuery("#conf-modal").modal('hide');
    location.href = 'Filmes?action=excluiFilmeDB&codFilme=' + campoFilme;
 });
