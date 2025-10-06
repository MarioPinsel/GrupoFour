#!/bin/bash

ip=$(hostname -I | awk '{print $1}')
dis=$(df -h --output=avail / | tail -n 1)
ram=$(free -h | awk '/Mem:/ {print $7}')
car="/sys/class/power_supply/AC/online"
estadoPrev=""

generar_html() {
cat <<EOF > /var/www/html/index.html
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Documento</title>
</head>
<body>
    <p id="nombre" onclick="titulo()">Fabian Baron</p>
    <p id="fecha" onclick="fecha()">Mostrar fecha</p>
    <p onclick="mostrarRespuesta(this, 'respuesta1')">1. ¿Qué parámetros y facilidades debe tener la empresa para efectuar el proyecto?</p>
    <p onclick="mostrarRespuesta(this, 'respuesta2')">2. ¿Cómo van a medir el impacto del software?</p>
    <p onclick="mostrarRespuesta(this, 'respuesta3')">3. ¿Hacia qué empresas estaría dirigido especialmente?</p>
    <p onclick="mostrarRespuesta(this, 'respuesta4')">4. ¿Cómo van a hacer que la DIAN les dé esa información?</p>
    <p onclick="mostrarRespuesta(this, 'respuesta5')">5. ¿Si tengo una microempresa de zapatillas cómo puedo implementar su software?</p>

    <h1>FORMULARIO DE PRUEBA</h1>
    <form action="sc.py" method="POST">
        <input type="submit" value="EJECUTAR EL SCRIPT">
    </form>

    <h1>IP:</h1>
    <p>$ip</p>
    <h1>DISCO:</h1>
    <p>$dis</p>
    <h1>RAM:</h1>
    <p>$ram</p>

    <script>
    function titulo(){
        document.title = "Quiz";
    }
    function fecha(){
        if(document.getElementById("fechaActual")) return;
        let nuevo = document.createElement("p");
        nuevo.id = "fechaActual";
        nuevo.textContent = Date();
        let actual = document.getElementById("fecha");
        actual.insertAdjacentElement("afterend", nuevo);
    }

    const respuestas = {
        respuesta1: "Las empresas deben contar con conectividad a internet estable, acceso a dispositivos básicos (PC o laptop), y disposición para adoptar procesos digitales.",
        respuesta2: "A través de la aplicación de cuestionarios de calidad, orientados a evaluar la satisfacción de los usuarios y la eficiencia del sistema. Al igual en casos de quejas y reclamos.",
        respuesta3: "Nuestro público objetivo está conformado principalmente por las empresas dedicadas a actividades de compra y venta.",
        respuesta4: "Para que la DIAN nos proporcione acceso a toda la información requerida, es necesario cumplir con unas serie de procesos formales, entre los cuales se encuentran el registro como empresas y la habilitación para la facturación electrónica. Todo ello se realiza conforme a lo establecido en los formularios y manuales oficiales que esta entidad pone a disposición.",
        respuesta5: "Principalmente, además de ofrecer un manejo integral del inventario para la empresa de calzado, también proporcionaremos el servicio de facturación electrónica y un sistema de gestión de usuarios, lo cual resulta especialmente útil para aquellas empresas que no cuentan con experiencia en el manejo de bases de datos."
    };

    function mostrarRespuesta(preguntaActual, respuesta) {
        if (document.getElementById(respuesta)) return;
        const nueva = document.createElement("p");
        nueva.id = respuesta;
        nueva.textContent = respuestas[respuesta];
        preguntaActual.insertAdjacentElement("afterend", nueva);
    }
    </script>
</body>
</html>
EOF

    echo " HTML generado."
}
escuchar_mouse() {
    echo "Esperando movimiento del mouse..."
    while true; do
        if read -n 3 -t 0.1 _ < /dev/input/mice; then
            echo " Movimiento del mouse detectado"
            generar_html
            break
        fi
    done
}

escuchar_tecla() {
    echo "Esperando pulsación de flecha ABAJO..."
    while true; do
        if read -sn3 -t 0.1 tecla; then
            if [[ "$tecla" == $'\e[B' ]]; then
                echo " Flecha abajo detectada"
                generar_html
                break
            fi
        fi
    done
}

escuchar_cargador() {
    echo "Esperando conexión del cargador..."
    estadoPrev=""
    while true; do
        state=$(cat "$car")
        if [[ "$state" != "$estadoPrev" ]]; then
            if [[ "$state" -eq 1 ]]; then
                echo " Cargador conectado"
                generar_html
                break
            fi
            estadoPrev=$state
        fi
        sleep 0.5
    done
}

escuchar_usb() {
    echo "Esperando conexión de un dispositivo USB..."
while read -r line;do
        if [[ "$line" == *"add"* ]]; then
            echo " USB detectado"
            generar_html
            break
        fi
    done < <(udevadm monitor --udev)
}

# audífonos (diff simple del codec)
escuchar_audifonos() {
    echo "Esperando cambio en la conexión de audífonos..."
    codec="/proc/asound/card0/codec#0"
    tmp="/tmp/codec_state.$$"

    # Verifica que el archivo exista
    [[ -f "$codec" ]] || { echo "No se encontró $codec"; return; }

    # Crea una copia inicial del estado del codec
    cp "$codec" "$tmp"

    while true; do
        if ! diff -q "$codec" "$tmp" >/dev/null 2>&1; then
            echo " Cambio detectado en audífonos"
            generar_html
            cp "$codec" "$tmp"
            break
        fi
        sleep 2
    done
}
echo "Seleccione el tipo de evento a escuchar:"
echo "1. Movimiento del mouse"
echo "2. Tecla (Flecha abajo)"
echo "3. Conexión del cargador"
echo "4. Conexión de USB"
echo "5. Conexión/desconexión de audífonos (jack)"
read -p "Ingrese el número de opción (1-5): " opcion
case "$opcion" in
    1)
        escuchar_mouse
        ;;
    2)
        escuchar_tecla
        ;;
    3)
        escuchar_cargador
        ;;
    4)
        escuchar_usb
        ;;
    5)
        escuchar_audifonos
        ;;
    *)
        echo " Opción no válida. Saliendo."
        exit 1
        ;;
esac
su -c 'firefox "10.0.2.15"' fabian
