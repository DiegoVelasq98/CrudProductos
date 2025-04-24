const apiUrl = "http://localhost:8082/api/v1/productos";
const idProductoInput = document.getElementById("idProducto");
const groupIdProducto = document.getElementById("group-id-producto");
const form = document.getElementById("addProductForm");

function cargarProductos() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector("#tabla-productos tbody");
            tbody.innerHTML = "";

            data.forEach(producto => {
                const fila = document.createElement("tr");
                fila.innerHTML = `
                    <td>${producto.idProducto}</td> 
                    <td>${producto.nombre}</td>
                    <td>${producto.descripcion}</td>
                    <td>${producto.precio}</td>
                    <td>${producto.fechaRegistro}</td>
                    <td>
                        <button class="btn btn-warning btn-sm btn-editar" data-id="${producto.idProducto}">Editar</button>
                        <button class="btn btn-danger btn-sm btn-eliminar" data-id="${producto.idProducto}">Eliminar</button>
                    </td>
                `;
                tbody.appendChild(fila);
            });

            agregarEventosBotones();
        })
        .catch(error => console.error("Error al cargar productos:", error));
}

function agregarEventosBotones() {
    document.querySelectorAll(".btn-editar").forEach(boton => {
        boton.addEventListener("click", function () {
            const id = this.getAttribute("data-id");

            fetch(`${apiUrl}/${id}`)
                .then(response => response.json())
                .then(producto => {
                    idProductoInput.value = producto.idProducto;
                    document.getElementById("nombre").value = producto.nombre;
                    document.getElementById("descripcion").value = producto.descripcion;
                    document.getElementById("precio").value = producto.precio;
                    document.getElementById("fechaRegistro").value = producto.fechaRegistro;
                    groupIdProducto.classList.remove("d-none");

                    new bootstrap.Modal(document.getElementById("addProductModal")).show();
                });
        });
    });

    document.querySelectorAll(".btn-eliminar").forEach(boton => {
        boton.addEventListener("click", function () {
            const id = this.getAttribute("data-id");

            if (confirm("¿Estás seguro de que deseas eliminar este producto?")) {
                fetch(`${apiUrl}/${id}`, {
                    method: "DELETE"
                })
                    .then(() => cargarProductos())
                    .catch(error => console.error("Error al eliminar:", error));
            }
        });
    });
}

form.addEventListener("submit", function (e) {
    e.preventDefault();

    const producto = {
        nombre: document.getElementById("nombre").value,
        descripcion: document.getElementById("descripcion").value,
        precio: document.getElementById("precio").value,
        fechaRegistro: document.getElementById("fechaRegistro").value
    };

    const id = idProductoInput.value;

    const method = id ? "PUT" : "POST";
    const url = apiUrl;


    fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(producto)
    })
        .then(response => response.json())
        .then(data => {
            console.log("Respuesta del servidor:", data);
            if (data.idProducto) {
                producto.idProducto = data.idProducto;
            }

            cargarProductos();
            form.reset();
            bootstrap.Modal.getInstance(document.getElementById("addProductModal")).hide();
            groupIdProducto.classList.add("d-none");
            idProductoInput.value = "";
        })
        .catch(error => console.error("Error al guardar:", error));
});

function mostrarFormularioNuevo() {
    form.reset();
    groupIdProducto.classList.add("d-none");
    idProductoInput.value = "";
}

document.addEventListener("DOMContentLoaded", cargarProductos);
