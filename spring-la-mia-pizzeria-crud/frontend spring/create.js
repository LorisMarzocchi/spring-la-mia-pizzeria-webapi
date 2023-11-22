const apiUrl = "http://localhost:8080/api/v1/pizzas";
const ingredients = [
  {
    id: 1,
    name: "Pomodoro",
  },
  {
    id: 2,
    name: "Mozzarella",
  },
  {
    id: 3,
    name: "Basilico",
  },
  {
    id: 4,
    name: "Aglio",
  },
  {
    id: 5,
    name: "Origano",
  },
  {
    id: 6,
    name: "Funghi",
  },
  {
    id: 7,
    name: "Prosciutto",
  },
  {
    id: 8,
    name: "Salame Piccante",
  },
  {
    id: 9,
    name: "Gorgonzola",
  },
  {
    id: 10,
    name: "Parmigiano",
  },
  {
    id: 11,
    name: "Pecorino",
  },
  {
    id: 12,
    name: "Verdure Grigliate",
  },
  {
    id: 13,
    name: "Funghi Champignon",
  },
  {
    id: 14,
    name: "Acciughe",
  },
  {
    id: 15,
    name: "Mozzarella di Bufala",
  },
  {
    id: 16,
    name: "Prosciutto Cotto",
  },
];

document
  .getElementById("createPizzaForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const descrizione = document.getElementById("descrizione").value;
    const prezzo = document.getElementById("prezzo").value;
    const urlImage = document.getElementById("urlImage").value;

    const selectedIngredients = Array.from(
      document.querySelectorAll("input[type=checkbox]:checked")
    ).map((checkbox) => ({ id: checkbox.value }));

    try {
      let pizza = {
        nome,
        descrizione,
        prezzo: parseFloat(prezzo),
        urlImage,
        ingredients: selectedIngredients,
      };
      console.log(pizza);
      await axios.post(apiUrl, pizza);
      window.location.href = "index.html";
    } catch (error) {
      console.error(error);
    }
  });

const populateIngredientsCheckbox = (ingredients) => {
  const ingredientsContainer = document.getElementById("ingredientsContainer");
  const checkboxesContent = renderIngredientCheckboxes(ingredients);
  ingredientsContainer.innerHTML = checkboxesContent;
};

const renderIngredientCheckboxes = (ingredients) => {
  let content = "";
  if (ingredients.length === 0) {
    content = "Nessun ingrediente presente.";
  } else {
    ingredients.forEach((ingredient) => {
      content += `
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="ingredient${ingredient.id}" value="${ingredient.id}">
                    <label class="form-check-label" for="ingredient${ingredient.id}">${ingredient.name}</label>
                </div>
            `;
    });
  }
  return content;
};
// // render ingredient
// const renderIngredient = (ingredients) => {
//   console.log(ingredients);
//   let content;
//   if (ingredients.length === 0) {
//     content = "No categories";
//   } else {
//     content = '<ul class="list-unstyled">';
//     ingredients.forEach((ingredient) => {
//       content += `<li>${ingredient.nome}</li>`;
//     });
//     content += "</ul>";
//   }
//   return content;
// };

populateIngredientsCheckbox(ingredients);
