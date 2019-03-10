var lanches = [];
var totalPedido = [];

function montarPedido(lanche, qtd, valor) {
    item = {}
    item ["nome"] = lanche;
    item ["quantidade"] = qtd;

    item2 = {}
    item2 ["nome"] = lanche;
    item2 ["valor"] = valor;

    if(lanches.length > 0) {
        atualizarJson("nome", lanche)
        atualizarValorTotal("nome", lanche)
    }
    
    lanches.push(item);
    totalPedido.push(item2);
    listaLanches();


    return lanches;
}

function atualizarValorTotal(chave, valor){
    totalPedido = totalPedido.filter(function(novoJson) {
        return novoJson[chave] != valor;
    });
    return totalPedido;
}

function atualizarJson(chave, valor){
    lanches = lanches.filter(function(novoJson) {
        return novoJson[chave] != valor;
    });
    return lanches;
}

