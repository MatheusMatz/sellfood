function listaLanches () {

        let nome;
        let nomeLanche;

        lanches.forEach(lanche => {
            if(lanche.nome != ''){
                nome = lanche.nome;
            } else {
                nome = 'Lanche personalizado'
            }
            
            console.log($(`li ${nome}`).find())

            

            if (nomeLanche != lanche.nome) {
                $("#lanches").prepend(`
                <li class=" ${nome} list-group-item d-flex justify-content-between align-items-center">
                    ${nome}
                    <span class="badge badge-primary badge-pill">${lanche.quantidade}</span>
                </li>
                `);
            }
            
            nomeLanche = nome;
        })
      }