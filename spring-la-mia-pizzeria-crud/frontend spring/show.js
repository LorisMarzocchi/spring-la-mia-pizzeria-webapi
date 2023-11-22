const baseUrl = "http://localhost:8080/api/v1/pizzas";
const pizzaDetailsDiv = document.getElementById("pizzaDetails");

// Assumiamo che l'ID della pizza sia passato come parametro URL
const pizzaId = new URLSearchParams(window.location.search).get("id");

// Funzione per ottenere i dettagli della pizza
const getPizzaDetails = async () => {
  try {
    const response = await axios.get(`${baseUrl}/${pizzaId}`);
    displayPizzaDetails(response.data);
  } catch (error) {
    console.log(error);
    pizzaDetailsDiv.innerHTML = `<div class="alert alert-danger">Errore nel caricamento dei dettagli della pizza.</div>`;
  }
};
// Funzione per ottenere i dettagli di una pizza specifica
// const showPizzaDetails = async (pizzaId) => {
//   try {
//     const response = await axios.get(`${baseUrl}/${pizzaId}`);
//     displayPizzaDetails(response.data);
//   } catch (error) {
//     console.log(error);
//   }
// };

// Funzione per visualizzare i dettagli della pizza
const displayPizzaDetails = (pizza) => {
  const detailsHtml = `
        <h3 class="text-danger">${pizza.nome}</h3>
        <img src="${pizza.urlImage}" alt="${
    pizza.nome
  }" style="width:100%;max-width:300px;">
        <p>${pizza.descrizione}</p>
        <p>Prezzo: €${pizza.prezzo}</p>
         <div class="card-footer">${renderIngredient(pizza.ingredients)}</div>
    `;
  pizzaDetailsDiv.innerHTML = detailsHtml;
};
// render ingredient
const renderIngredient = (ingredients) => {
  console.log(ingredients);
  let content;
  if (ingredients.length === 0) {
    content = "No categories";
  } else {
    content =
      '<h3 class="text-danger">Ingredienti</h3><ul class="list-unstyled">';
    ingredients.forEach((ingredient) => {
      content += `<li>${ingredient.nome}</li>`;
    });
    content += "</ul>";
  }
  return content;
};
// Carica i dettagli della pizza all'avvio
getPizzaDetails();
