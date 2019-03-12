$("#cardapio").ready(()=>{

    var renderIngredientes = function(e){
        var a = [];
        e.forEach(element => a.push(" " + element.nome + " "))
        return a;
      }
  
      $.ajax({
          url: "http://localhost/cardapio/Lanches",
          type: "GET",
          crossDomain: true
      }).done(function(data){
          
          $("#titulo").prepend(data.titulo);
          
          data.promocoes.forEach((promocao) => {
              $("#promocoes").prepend(`
                  <li>
                  <div>
                      <h5 class="mb-1">${promocao.nome}</h5>
                      <small>${promocao.descricao}</small>
                  </div>
                  </li>
              `);
          });
  
          data.lanches.forEach((lanche) => {
              $("#cardapio").prepend(`
              <li id="${lanche.nome}" class="lanche list-group-item card-body">
                  <div class="flex justify-content-between ">
                      <h5 class="nome texto ">${lanche.nome}</h5>
                  </div>
                  <div class="ingredientes flex">${renderIngredientes(lanche.ingredientes)}</div>
                    <div class="flex">
                      <p class="moeda">R$</p><div class="preco font-weight-bold">${parseFloat(lanche.valor).toFixed(2)}</div>
                        <div class="button-group flex ">
                            <label class="font-weight-bold">qtd<label/>
                            <input class="qtdLanche form-control" type="number" value="0" maxlength="10" min="0" >
                        </div>  
                    </div>
              </li>`)
  
        });
  
  
        $(".qtdLanche").change(function() {
            valorLanchesCardapio = 0; 
              var lanche = $(this).parents('.lanche:eq(0)').attr('id')
              var valor = parseFloat($(this).parent().parent().parent().children('.preco').text());
              var qtd = $(this).val();
  
              var total = qtd*valor;
  
              var qtd = $(this).val();
              montarPedido(lanche,qtd, total)
  
              let resultado = totalPedido.reduce((total, valor)=> total + valor.valor, 0)
                
              valorLanchesCardapio = resultado;
  
            somarLanches();
      });
  
})
})