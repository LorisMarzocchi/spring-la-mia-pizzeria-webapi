const baseUrl = "http://localhost:8080/api/v1/pizzas";
const root = document.getElementById("root");

// Funzione per ottenere l'array delle pizze
const getPizza = async (searchName = "", searchPrice = "") => {
  try {
    const url = `${baseUrl}?search=${searchName}&searchPrezzo=${searchPrice}`;
    const response = await axios.get(url);
    renderPizzaList(response.data.content);
  } catch (error) {
    console.log(error);
  }
};

// Funzione per la ricerca
const searchPizza = () => {
  const searchName = document.getElementById("searchNameField").value;
  const searchPrice = document.getElementById("searchPriceField").value;
  getPizza(searchName, searchPrice);
};

// Funzione per eliminare una pizza
const deletePizza = async (pizzaId) => {
  try {
    if (confirm("Sei sicuro di voler eliminare questa pizza?")) {
      await axios.delete(`${baseUrl}/${pizzaId}`);
      getPizza(); // Aggiorna l'elenco delle pizze
    }
  } catch (error) {
    console.log(error);
    alert("Si è verificato un errore durante l'eliminazione della pizza.");
  }
};
// Funzione per renderizzare ogni pizza nell'elenco
const renderPizza = (element) => {
  return `
    <div class="card" style="width: 15rem;">
      <img src="${element.urlImage}" class="card-img-top" alt="${element.nome}">
      <div class="card-body">
        <h5 class="card-title">${element.nome}</h5>
        <p class="card-text">${element.descrizione}</p>
        <p class="card-text">Prezzo: €${element.prezzo}</p>
        <a href="show.html?id=${element.id}" class="card-link">Dettagli</a>
        <button onclick="deletePizza(${element.id})">Elimina</button>
      </div>
    </div>`;
};

// Funzione per renderizzare l'elenco delle pizze
const renderPizzaList = (data) => {
  let content = '<div class="row">';
  data.forEach((element) => {
    content += `<div class="col-4 mt-3">${renderPizza(element)}</div>`;
  });
  content += "</div>";
  root.innerHTML = content;
};

// Esegui la funzione per ottenere l'elenco delle pizze all'avvio
getPizza();
