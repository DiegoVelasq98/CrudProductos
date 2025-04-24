const apiUrl = "http://localhost:8082/api/v1/productos";

document.addEventListener("DOMContentLoaded", () => {
    cargarProductos();

    const form = document.getElementById("addProductForm");
    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const producto = {
            nombre: document.getElementById("nombre").value,
            descripcion: document.getElementById("descripcion").value,
            precio: parseFloat(document.getElementById("precio").value),
            fechaRegistro: document.getElementById("fechaRegistro").value
        };

        await fetch(apiUrl, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(producto)
        });

        form.reset();
        cargarProductos();
        const modal = bootstrap.Modal.getInstance(document.getElementById("addProductModal"));
        modal.hide();
    });
});

async function cargarProductos() {
    const res = await fetch(apiUrl);
    const data = await res.json();

    const tabla = document.getElementById("productsTable");
    tabla.innerHTML = "";

    data.forEach(p => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td>${p.idProducto}</td>
            <td>${p.nombre}</td>
            <td>${p.descripcion}</td>
            <td>${p.precio}</td>
            <td>${p.fechaRegistro}</td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="eliminarProducto(${p.idProducto})">Eliminar</button>
            </td>
        `;
        tabla.appendChild(fila);
    });
}

async function eliminarProducto(id) {
    await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
    cargarProductos();
}
