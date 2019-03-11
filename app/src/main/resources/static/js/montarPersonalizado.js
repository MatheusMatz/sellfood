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

    $("#confirmaInclusao").modal("show");

    item.ingredientes.forEach(ingrediente => {

        let nomeIngediente;

        let ingredienteIgual = item.ingredientes.filter((n) => n["nome"] == ingrediente.nome);
        let totalValorIngrediente = ingredienteIgual.reduce(( prevVal, elem ) => { return prevVal + elem.valor }, 0 );
        let qtdIngrediente = ingredienteIgual.reduce(( prevVal, elem , index) => { return index});
        

        if (nomeIngediente != ingrediente.nome) {
            $('#resumoIngredientes').append(`
                <li class="list-group-item row align-items-start">
                    <div class="col">
                        ${qtdIngrediente +" x "+ ingrediente.nome}
                        <div class="valorLanche">R$ ${parseFloat(totalValorIngrediente).toFixed(2)}</div>
                    </div>
                </li>
            `)
        }

        nomeIngediente = ingrediente.nome;
    });

    lanches.push(item);
    listaLanches();

}





