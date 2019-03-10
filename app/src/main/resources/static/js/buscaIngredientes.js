$("#ingredientes").ready(() => {
    
    
    $.ajax({
        url: "http://localhost/ingrediente/todos",
        type: "GET",
        crossDomain: true
    }).done(function(data){
        data.forEach((ingrediente) => {
        $("#ingredientes").prepend(`
        <li id="${ingrediente.nome}" class="ingrediente list-group-item ">
            <div class="nome  font-weight-bold">${ingrediente.nome}</div>
        
        <div class="flex">
        <p class="moeda ">R$<p/><div class="preco">${ingrediente.valor}</div>
        <div class="button-group flex ">
            <label>qtd<label/>
            <input class="qtdIngrediente form-control" type="number" value="0" maxlength="10" min="0" >
        </div>  
        </div>
        </li>`)
    })

    
    
    $(".qtdIngrediente").change(function() {
        var ingrediente = $(this).parents('.ingrediente:eq(0)').attr('id')
        var valor = parseFloat($(this).parent().parent().parent().children('.preco').text());
        var qtd = $(this).val();

        var total = qtd*valor;
        
        $(this).parents('input .valor').val(total)
          
        adicionarIngredientes(ingrediente,qtd,total)
        let resultado = totalValor.reduce((total, valor)=> total + valor.valor, 0)

        $("#total").val(resultado);

    });
    
  });
})
