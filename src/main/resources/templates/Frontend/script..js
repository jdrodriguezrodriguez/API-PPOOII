// Llama a la API para obtener las coordenadas
fetch('http://localhost:8080/api/personas/coordenadas') // Cambia la URL según sea necesario
    .then(response => response.json())
    .then(data => {
        // Agrega cada marcador al mapa
        data.forEach(persona => {
            L.marker([persona.latitud, persona.longitud]).addTo(map)
                .bindPopup(persona.marca_cord);
        });
    })
    .catch(error => {
        console.error('Error al obtener datos:', error);
    });
