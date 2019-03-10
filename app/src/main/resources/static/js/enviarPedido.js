var t = []

function enviarPedido () {
 
    var pedidos = {}
        pedidos.lanches = lanches;
    

        $.ajax({
            type: "POST",
            url: "http://localhost/pedido/efetuar",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            data: JSON.stringify(pedidos)
            }).done((data) => {

                $('#resumo').modal('show');
                    let nomeLanche;

                    data.lanches.forEach(lanche => {
                        let lancheIgual = data.lanches.filter((n) => n["nome"] == lanche.nome);
                        let totalValorLanche = lancheIgual.reduce(( prevVal, elem ) => { return prevVal + elem.valor }, 0 );
                        let qtdLanche = lancheIgual.reduce(( prevVal, elem , index) => { return index});
                        
                        if (nomeLanche != lanche.nome) {
                            $('#lanchesDoPedido').append(`
                                <li class="list-group-item row align-items-start">
                                    <div class="col">
                                        ${qtdLanche +" x "+ lanche.nome}
                                        <div class="valorLanche">R$ ${totalValorLanche}</div>
                                    </div>
                                </li>
                            `)
                        }

                        nomeLanche = lanche.nome;


                        
                    })
                    $('#totalCompra').text(data.valor)
                    
                        return t = data.lanches
                    
            });
        }