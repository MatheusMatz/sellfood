
var valorLanchesCardapio = 0;
var valorLanchesPersonalizados = 0
var totalValorLanchesPersonalizados = [];

function listaLanches () {
    
    var nomeLanche;

    $("#lanches").empty();
        lanches.forEach(lanche => {
                       
            if(!lanche.nome){
                nomeLanche = "Lanche"
            }

            if (nomeLanche != lanche.nome && lanche.quantidade > 0 || nomeLanche == "personalizado") {
                $("#lanches").prepend(`
                <li class=" ${nomeLanche} list-group-item d-flex justify-content-between align-items-center">
                <p>${lanche.nome ? lanche.nome : nomeLanche}</p> 
                <p>${listaIngredientes(lanche.ingredientes)}</p>
                    <span class="badge badge-primary badge-pill">${lanche.quantidade}</span>
                </li>
            `);
        }

            qtdAnterior =lanche.quantidade;
            nomeLanche = lanche.nome;
        })

      }

      function listaIngredientes(ingredientes){
            var a = [];
            if(ingredientes){
                ingredientes.forEach(ingrediente => a.push(" " + ingrediente.nome + " "))
            }
            return a;
      }

      function somarLanches() {

        
        valorLanchesPersonalizados = totalValorLanchesPersonalizados.reduce((total, valor)=> total + valor, 0)

        console.log(valorLanchesCardapio);
        console.log(valorLanchesPersonalizados)
        
        let resultado = valorLanchesCardapio + valorLanchesPersonalizados;
        $("#totalPedido").val(parseFloat(resultado.toFixed(2)));
      }