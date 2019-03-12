var ingredientes = [];
var totalValor = [];

function adicionarIngredientes(ingrediente, qtd, valor) {
    item = {}
    item ["nome"] = ingrediente;
    item ["quantidade"] = qtd;
    
    item2 = {}
    item2 ["nome"] = ingrediente;
    item2 ["valor"] = valor;

    if(ingredientes.length > 0) {
        atualizarIngredientes("nome", ingrediente)
        atualizarValor("nome", ingrediente)
    }
    
    ingredientes.push(item);
    totalValor.push(item2);
    

    return ingredientes;
}


function atualizarIngredientes(chave, valor){
    ingredientes = ingredientes.filter(function(novoJson) {
        return novoJson[chave] != valor;
    });
    return ingredientes;
}

function atualizarValor(chave, valor){
    totalValor = totalValor.filter(function(novoJson) {
        return novoJson[chave] != valor;
    });
    return totalValor;
}

function montarPersonalizado () {

    var qtd = $("#qtdLanchePersonalizado").val();
    
    item = {}
    item ["quantidade"] = qtd;
    item ["ingredientes"] = ingredientes;
    ingredientes = [];

    $("#qtdLanchePersonalizado").val(1);
    $(".qtdIngrediente").val(0);
    $("#total").val(0);
    $('#confirmaInclusao').modal('show');

    setTimeout(function() {
      $('#confirmaInclusao').modal('hide');
    }, 2000); 

    lanches.push(item);
    totalValorLanchesPersonalizados.push(qtd * totalValor.reduce((total, valor)=> total + valor.valor, 0))
    listaLanches();
    
    somarLanches();

}





