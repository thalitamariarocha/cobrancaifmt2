//Chamadas das funções inicias
generateSelectOptions();

//Funções
function generateSelectOptions() {
    const element = document.querySelector("select");
    var statusTitulo = ["Pendente", "Recebido"];

    for(let i = 0; i < statusTitulo.length; i++) {
        element.insertAdjacentHTML(
            "beforeend",
            '<option value="'+  statusTitulo[i].toUpperCase() +  '">' + statusTitulo[i] + '</option>',
        );
    }
}

