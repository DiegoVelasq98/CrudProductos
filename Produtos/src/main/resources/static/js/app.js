// URL de la API
const apiUrl = 'http://localhost:8082/api/v1/productos';


function fetchProducts() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('productsTable');
            tableBody.innerHTML = ''; // Limpiar tabla antes de actualizar


            data.forEach(product => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${product.idProducto}</td>
                    <td>${product.nombre}</td>
                    <td>${product.descripcion}</td>
                    <td>${product.precio}</td>
                    <td>${product.fechaRegistro}</td>
                    <td>
                        <button class="btn btn-warning" onclick="editProduct(${product.idProducto})">Editar</button>
                        <button class="btn btn-danger" onclick="deleteProduct(${product.idProducto})">Eliminar</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error al obtener productos:', error));
}


window.onload = fetchProducts;


document.getElementById('addProductForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const product = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value,
        precio: parseFloat(document.getElementById('precio').value),
        fechaRegistro: document.getElementById('fechaRegistro').value
    };

    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(response => response.json())
        .then(() => {
            fetchProducts(); // Recargar la lista de productos
            document.getElementById('addProductModal').modal('hide'); // Cerrar modal
        })
        .catch(error => console.error('Error al agregar producto:', error));
});

// Función para editar producto (por ejemplo, similar a agregar pero con un PUT)
function editProduct(id) {
    // Puedes implementar la lógica de edición aquí
    console.log('Editar producto:', id);
}

// Función para eliminar producto
function deleteProduct(id) {
    fetch(`${apiUrl}/${id}`, {
        method: 'DELETE'
    })
        .then(() => fetchProducts()) // Recargar productos después de eliminar
        .catch(error => console.error('Error al eliminar producto:', error));
}
